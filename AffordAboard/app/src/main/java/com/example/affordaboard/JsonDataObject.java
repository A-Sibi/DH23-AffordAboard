package com.example.affordaboard;

/**
 * Class which will have instances of individual flight values which we can fetch to display
 */
public class JsonDataObject implements Comparable<JsonDataObject> {

    /**
     * Number of total constructed flights
     */
    public static int objectCount;
    private String totalPrice;
    private String departureAt;
    private String arrivalAt;

    public JsonDataObject(String totalPrice, String departureAt, String arrivalAt) {
        this.totalPrice = totalPrice;
        this.departureAt = departureAt;
        this.arrivalAt = arrivalAt;

        objectCount++;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(String departureAt) {
        this.departureAt = departureAt;
    }

    public String getArrivalAt() {
        return arrivalAt;
    }

    public void setArrivalAt(String arrivalAt) {
        this.arrivalAt = arrivalAt;
    }

    @Override
    public int compareTo(JsonDataObject other) {
        return Integer.compare(Integer.valueOf(this.totalPrice), Integer.valueOf(other.totalPrice));
    }

    @Override
    public String toString(){
        return "Opcija id: " + objectCount + "\nPrice " + totalPrice + "\nDeparture time: " + departureAt + "\nArrival time: " + arrivalAt;
    }
}