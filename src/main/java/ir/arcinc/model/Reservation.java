package ir.arcinc.model;

import java.time.Period;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tahae on 6/19/2016.
 */
public class Reservation {
    private UUID resId;
    private UUID hotelId;
    private int roomNo;
    private String username;
    private Date start;
    private Date end;

    public Reservation(UUID resId, UUID hotelId, int roomNo, String username, Date start, Date end) {
        this.resId = resId;
        this.hotelId = hotelId;
        this.roomNo = roomNo;
        this.username = username;
        this.start = start;
        this.end = end;
    }

    public Reservation(UUID hotelId, int roomNo, String username, Date start, Date end) {
        this(null,hotelId,roomNo,username,start,end);
    }

    public UUID getResId() {
        return resId;
    }

    public void setResId(UUID resId) {
        this.resId = resId;
    }

    public UUID getHotelId() {
        return hotelId;
    }

    public void setHotelId(UUID hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "resId=" + resId +
                ", hotelId=" + hotelId +
                ", roomNo=" + roomNo +
                ", username='" + username + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
