package ir.arcinc;

import ir.arcinc.dao.*;
import ir.arcinc.model.*;

import java.util.Date;

/**
 * Created by tahae on 6/18/2016.
 */
public class Main {
    public static void main(String[] args) {
        Connection connection = new Connection();
        HotelDao hotelDao = new HotelDao(connection);
        Hotel h1 = new Hotel(
                "Esteghlal",
                "Parkway",
                "0935",
                "Tehran",
                0.00,
                0.00
        );
        hotelDao.create(h1);
        Hotel h2 = new Hotel(
                "Parsian",
                "Yadgar-Chamran",
                "09311",
                "Tehran",
                1.00,
                1.00
        );
        hotelDao.create(h2);
        System.out.println(hotelDao.findByCity("Tehran"));


        RoomDao roomDao = new RoomDao(connection);
        Room r1 = new Room(
                h1.getHotelid(),1,5,"Normal"
                );
        r1.addAmenity("tv");
        r1.addAmenity("bathtub");
        roomDao.createRoom(r1);
        Room r2 = new Room(
                h1.getHotelid(),2,10,"Duplex"
        );
        r2.addAmenity("jacuzzi");
        r2.addAmenity("snooker");
        r2.addAmenity("king-size bed");
        roomDao.createRoom(r2);
        System.out.println(roomDao.findRoomByHotelId(h1.getHotelid()));


        PoiDao poiDao = new PoiDao(connection);
        PointOfInterest p1 = new PointOfInterest("Takht Jamshid","Jamshid bude tush, hokumat mikarde!", "010", 0.00, 0.00);
        PointOfInterest p2 = new PointOfInterest("Pasargad","Kurushe bozorg inja morde", "1110", 0.00, 0.00);
        poiDao.insertPOI(p1);
        poiDao.insertPOI(p2);
        System.out.println(poiDao.findPOINearHotel(h1,50));


        UserDao userDao = new UserDao(connection);
        User u1 = new User("taha","0935336","taha","pass","taha@gmail.com");
        userDao.createUser(u1);
        System.out.println(userDao.findByUserName("taha"));

        ReservationDao reservationDao = new ReservationDao(connection);
        Date date = new Date();
        Reservation res1 = new Reservation(h1.getHotelid(),1,"taha",date,date);
        reservationDao.reserve(res1);
        System.out.println(reservationDao.findReservationBetween(h1.getHotelid(),1,date,date));
        System.out.println(reservationDao.isReserved(h1.getHotelid(),1,date));
    }
}
