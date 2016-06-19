package ir.arcinc.dao;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import ir.arcinc.Connection;
import ir.arcinc.model.Hotel;
import ir.arcinc.model.PointOfInterest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tahae on 6/19/2016.
 */
public class PoiDao extends GenericDao {
    public PoiDao(Connection connection) {
        super(connection);

        connection.execute(dropPOITableQuery);
        connection.execute(createPOITableQuery);
    }

    private final static String createPOITableQuery =
            "CREATE TABLE IF NOT EXISTS poi(" +
                "poiid uuid," +
                "name text," +
                "description text," +
                "phone text," +
                "longitude double," +
                "latitude double," +
                "PRIMARY KEY(poiid)" +
            ")";

    private final static String dropPOITableQuery =
            "DROP TABLE IF EXISTS poi";

    private final static String getAllPOIsQuery =
            "SELECT poiid,name,description,phone,longitude,latitude FROM poi";

    private final static String insertPOIQuery =
            "INSERT INTO poi(poiid,name,description,phone,longitude,latitude) VALUES (?,?,?,?,?,?)";

    public UUID insertPOI(PointOfInterest poi){
        UUID id = UUIDs.timeBased();
        poi.setPoiid(id);
        connection.execute(insertPOIQuery,id,poi.getName(),poi.getDesc(),poi.getPhone(),poi.getLongitude(),poi.getLatitude());
        return id;
    }

    public List<PointOfInterest> findPOINearHotel(Hotel h, double distance){
        List<PointOfInterest> pois = new ArrayList<>();
        for (Row r : connection.execute(getAllPOIsQuery)){
            PointOfInterest p = new PointOfInterest(
                    r.getUUID("poiid"),
                    r.getString("name"),
                    r.getString("description"),
                    r.getString("phone"),
                    r.getDouble("longitude"),
                    r.getDouble("latitude")
            );
            if (p.distance(h) < distance)
                pois.add(p);
        }
        return pois;
    }
}
