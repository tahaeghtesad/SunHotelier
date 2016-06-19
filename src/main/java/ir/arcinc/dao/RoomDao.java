package ir.arcinc.dao;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import ir.arcinc.Connection;
import ir.arcinc.model.Room;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tahae on 6/19/2016.
 */
public class RoomDao extends GenericDao{

    public RoomDao(Connection connection) {
        super(connection);

        connection.execute(dropAmenityTableQuery);
        connection.execute(dropRoomTableQuery);

        connection.execute(createRoomTableQuery);
        connection.execute(createAmenityTableQuery);
    }

    private final static String createRoomTableQuery =
            "CREATE TABLE IF NOT EXISTS room(" +
                "hotelid uuid," +
                "roomno int," +
                "price double," +
                "type text," +
                "PRIMARY KEY (hotelid,roomno)" +
            ")";

    private final static String dropRoomTableQuery =
            "DROP TABLE IF EXISTS room";

    private final static String createAmenityTableQuery =
            "CREATE TABLE IF NOT EXISTS amenity(" +
                "hotelid uuid," +
                "roomno int," +
                "amenity text," +
                "PRIMARY KEY ((hotelid, roomno),amenity)" +
            ")";

    private final static String dropAmenityTableQuery =
            "DROP TABLE IF EXISTS amenity";

    private final static String insertRoomQuery =
            "INSERT INTO room(hotelid,roomno,price,type) VALUES (?,?,?,?)";

    private final static String insertAmenityQuery =
            "INSERT INTO amenity(hotelid,roomno,amenity) VALUES (?,?,?)";

    private final static String findRoomByHotelId =
            "SELECT hotelid,roomno,price,type FROM room WHERE hotelid=?";

    private final static String findAmenityByHotelIdAndRoomNo=
            "SELECT amenity FROM amenity WHERE hotelid=? AND roomno=?";

    public void createRoom(Room r){
        connection.execute(insertRoomQuery,r.getHotelid(),r.getRoomNo(),r.getPrice(),r.getType());
        for (String a : r.getAmenities()){
            connection.execute(insertAmenityQuery, r.getHotelid(),r.getRoomNo(), a);
        }
    }

    public List<Room> findRoomByHotelId(UUID hotelid){
        List<Room> ret = new LinkedList<>();
        ResultSet rooms = connection.execute(findRoomByHotelId,hotelid);
        for (Row row : rooms){
            Room room = new Room(
                    row.getUUID("hotelid"),
                    row.getInt("roomno"),
                    row.getDouble("price"),
                    row.getString("type")
            );
            ResultSet amenities = connection.execute(findAmenityByHotelIdAndRoomNo,room.getHotelid(),room.getRoomNo());
            for (Row amenity : amenities){
                room.addAmenity(amenity.getString("amenity"));
            }
            ret.add(room);
        }
        return ret;
    }

}
