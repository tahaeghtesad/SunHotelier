package ir.arcinc.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tahae on 6/19/2016.
 */
public class Room {
    private UUID hotelid;
    private int roomNo;
    private double price;
    private String type;
    private List<String> amenities;

    public Room(UUID hotelid, int roomNo, double price, String type) {
        this.hotelid = hotelid;
        this.roomNo = roomNo;
        this.price = price;
        this.type = type;
        amenities = new LinkedList<>();
    }

    public UUID getHotelid() {
        return hotelid;
    }

    public void setHotelid(UUID hotelid) {
        this.hotelid = hotelid;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public void addAmenity(String amenity){
        this.amenities.add(amenity);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "hotelid=" + hotelid +
                ", roomNo=" + roomNo +
                ", price=" + price +
                ", type=" + type +
                ", amenities=" + amenities +
                '}';
    }
}
