package ir.arcinc.dao;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import ir.arcinc.Connection;
import ir.arcinc.model.Reservation;

import java.util.*;

/**
 * Created by tahae on 6/19/2016.
 */
public class ReservationDao extends GenericDao {
    public ReservationDao(Connection connection) {
        super(connection);

        connection.execute(dropReservationTableQuery);
        connection.execute(createReservationTableQuery);
    }

    private final static String createReservationTableQuery =
            "CREATE TABLE IF NOT EXISTS reservation(" +
                "resid uuid," +
                "hotelid uuid," +
                "roomno int," +
                "username text," +
                "day date," +
                "PRIMARY KEY ((hotelid,roomno),day)" +
            ")";

    private final static String dropReservationTableQuery =
            "DROP TABLE IF EXISTS reservation";

    private final static String insertReservationQuery =
            "INSERT INTO reservation(resid,hotelid,roomno,username,day) VALUES (?,?,?,?,?)";

    private final static String findReservationByDayQuery =
            "SELECT resid,hotelid,roomno,username,day FROM reservation WHERE day=? AND hotelid=? AND roomno=?";

    private final static String findReservationBetweenDates =
            "SELECT resid,hotelid,roomno,username,day FROM reservation WHERE day>=? AND day<=? AND hotelid=? AND roomno=?";

    public UUID reserve(Reservation r){
        UUID id = UUIDs.timeBased();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(r.getStart());
        end.setTime(r.getEnd());

        if (findReservationBetween(r.getHotelId(),r.getRoomNo(),r.getStart(),r.getEnd()).size() != 0)
            return null;

        start.setTime(r.getStart());
        end.setTime(r.getEnd());
        for (; !start.after(end); start.add(Calendar.DATE,1)){
            connection.execute(insertReservationQuery,id,r.getHotelId(),r.getRoomNo(),r.getUsername(),LocalDate.fromMillisSinceEpoch(start.getTime().toInstant().toEpochMilli()));
        }
        r.setResId(id);
        return id;
    }

    public boolean isReserved(UUID hotelId, int roomNo, Date date){
        ResultSet rs = connection.execute(findReservationByDayQuery, LocalDate.fromMillisSinceEpoch(date.toInstant().toEpochMilli()), hotelId, roomNo);
        return rs.all().size()!=0;
    }

    public List<Reservation> findReservationBetween(UUID hotelId, int roomNo, Date start, Date end){
        List<Reservation> reservations = new LinkedList<>();
        for (Row r : connection.execute(findReservationBetweenDates,LocalDate.fromMillisSinceEpoch(start.toInstant().toEpochMilli()),LocalDate.fromMillisSinceEpoch(end.toInstant().toEpochMilli()),hotelId,roomNo)){
            reservations.add(new Reservation(
                    //resid,hotelid,roomno,username,day
                    r.getUUID("resid"),
                    r.getUUID("hotelid"),
                    r.getInt("roomno"),
                    r.getString("username"),
                    new Date(r.getDate("day").getMillisSinceEpoch()),
                    new Date(r.getDate("day").getMillisSinceEpoch())
            ));
        }
        return reservations;
    }
}
