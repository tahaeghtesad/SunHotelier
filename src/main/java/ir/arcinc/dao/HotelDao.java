package ir.arcinc.dao;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import ir.arcinc.Connection;
import ir.arcinc.model.Hotel;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tahae on 6/19/2016.
 */
public class HotelDao extends GenericDao {

    public HotelDao(Connection connection) {
        super(connection);

        connection.execute(dropCityTableQuery);
        connection.execute(dropHotelTableQuery);

        connection.execute(createCityTableQuery);
        connection.execute(createHotelTableQuery);
    }

    private final static String dropHotelTableQuery = "DROP TABLE IF EXISTS hotel";

    private final static String dropCityTableQuery = "DROP TABLE IF EXISTS city";

    private final static String createHotelTableQuery =
            "CREATE TABLE IF NOT EXISTS hotel(" +
                "hotelid uuid, " +
                "name text, " +
                "address text, " +
                "phone text, " +
                "longitude double," +
                "latitude double," +
                "PRIMARY KEY(hotelid)" +
            ")";

    private final static String createCityTableQuery =
            "CREATE TABLE IF NOT EXISTS city(" +
                "city text," +
                "hotelid uuid," +
                "PRIMARY KEY (city,hotelid)" +
            ")";

    final private static String createHotelQuery =
            "INSERT INTO hotel (hotelid,name,address,phone,longitude,latitude) VALUES (?,?,?,?,?,?)";

    final private static String createCityHotelQuery =
            "INSERT INTO city (city,hotelid) VALUES (?,?)";

    final private static String findHotelByIdQuery = //TODO some bullshit
            "SELECT hotelid,name,address,phone,longitude,latitude FROM hotel WHERE hotelid IN";

    final private static String findHotelIdByCityQuery =
            "SELECT hotelid FROM city WHERE city=?";

    public UUID create(Hotel h){
        UUID id = UUIDs.timeBased();
        h.setHotelid(id);
        connection.execute(createHotelQuery,id,h.getName(),h.getAddress(),h.getPhone(),h.getLongitude(),h.getLatitude());
        connection.execute(createCityHotelQuery,h.getCity(),id);
        return id;
    }

    public List<Hotel> findByCity(String city){
        ResultSet rs = connection.execute(findHotelIdByCityQuery,city);
        String ids = "(";
        int index = 0;
        for (Row r : rs){
            if (index!=0)
                ids += ",";
            ids += r.getUUID("hotelid");
            index++;
        }
        ids += ")";
        rs = connection.execute(findHotelByIdQuery+ids);
        List<Hotel> hotels = new LinkedList<>();
        for (Row r : rs){
            hotels.add(new Hotel(
                    r.getUUID("hotelid"),
                    r.getString("name"),
                    r.getString("address"),
                    r.getString("phone"),
                    city,
                    r.getDouble("longitude"),
                    r.getDouble("latitude")
            ));
        }
        return hotels;
    }
}
