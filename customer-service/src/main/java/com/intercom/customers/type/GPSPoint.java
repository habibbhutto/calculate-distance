package com.intercom.customers.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GPSPoint {
    public double latitude;
    public double longitude;

    public GPSPoint() {
        this.latitude = 0.0;
        this.longitude = 0.0;
    }

    public GPSPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Calculates the distance between two GPS points using spherical law of cosines
     * @param gpsPoint
     * @return double distance in KM
     */
    public double calculateDistance(GPSPoint gpsPoint) {
        double R = 6371.0088; // Planet Earth's mean radius
        double theta = Math.abs(this.longitude - gpsPoint.longitude);
        double centerAngle = Math.acos( Math.sin(degToRad(this.latitude)) * Math.sin(degToRad(gpsPoint.latitude))
                                  + Math.cos(degToRad(this.latitude)) * Math.cos(degToRad(gpsPoint.latitude))
                                  * Math.cos(degToRad(theta)));
        double distance = R * centerAngle;
        return distance;
    }

    public double calculateDistance(double latitude, double longitude) {
        return this.calculateDistance(new GPSPoint(latitude, longitude));
    }

    /**
     * Converts value from degree to radians
     * @param degree
     * @return double radians
     */
    private double degToRad(double degree) {
        return (degree * Math.PI / 180.0);
    }

    @Override
    public String toString() {
        return new StringBuffer()
                .append(this.latitude)
                .append(", ")
                .append(this.longitude).toString();
    }
}
