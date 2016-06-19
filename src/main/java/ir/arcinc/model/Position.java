package ir.arcinc.model;

/**
 * Created by tahae on 6/19/2016.
 */
public abstract class Position {
    protected double longitude;
    protected double latitude;

    public Position(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @param p   another position on map p
     * @return distance between this position and p in KM.
     */
    public double distance(Position p){
        double theta = p.longitude - this.longitude;
        double dist = Math.sin(deg2rad(p.latitude)) * Math.sin(deg2rad(this.latitude))
                + Math.cos(deg2rad(p.latitude)) * Math.cos(deg2rad(this.latitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return dist;
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    @Override
    public String toString() {
        return "Position{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
