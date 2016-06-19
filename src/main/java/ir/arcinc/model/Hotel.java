package ir.arcinc.model;

import java.util.UUID;

/**
 * Created by tahae on 6/18/2016.
 */
public class Hotel extends Position{
    private UUID hotelid;
    private String name;
    private String address;
    private String phone;
    private String city;

    public Hotel(UUID hotelid, String name, String address, String phone, String city, double longitude, double latitude) {
        super(longitude,latitude);
        this.hotelid = hotelid;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.city = city;
    }

    public Hotel(String name, String address, String phone, String city, double longitude, double latitude) {
        this(null,name,address,phone,city,longitude,latitude);
    }

    public UUID getHotelid() {
        return hotelid;
    }

    public void setHotelid(UUID hotelid) {
        this.hotelid = hotelid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelid=" + hotelid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", " + super.toString() +
                '}';
    }
}
