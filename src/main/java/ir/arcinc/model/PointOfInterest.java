package ir.arcinc.model;

import java.util.UUID;

/**
 * Created by tahae on 6/19/2016.
 */
public class PointOfInterest extends Position {
    private UUID poiid;
    private String name;
    private String desc;
    private String phone;

    public PointOfInterest(UUID poiid, String name, String desc, String phone, double longitude, double latitude) {
        super(longitude, latitude);
        this.poiid = poiid;
        this.name = name;
        this.desc = desc;
        this.phone = phone;
    }

    public PointOfInterest(String name, String desc, String phone, double longitude, double latitude){
        this(null,name,desc,phone,longitude,latitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UUID getPoiid() {
        return poiid;
    }

    public void setPoiid(UUID poiid) {
        this.poiid = poiid;
    }

    @Override
    public String toString() {
        return "PointOfInterest{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", phone='" + phone + '\'' +
                ", " + super.toString() +
                '}';
    }
}
