package com.peaksoft;

public class Truck {
    private int id;
    private String truck;
    private Driver driver;
    private Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTruck() {
        return truck;
    }

    public void setTruck(String truck) {
        this.truck = truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public static Truck creatTruck(int id, String name, Driver driver, Status status) {
        Truck truck = new Truck();
        truck.id = id;
        truck.truck = name;
        truck.driver = driver;
        truck.status = status;
        return truck;
    }

    @Override
    public String toString() {
        return ""+id+
                "|   "+ truck +
                "   | "+driver+
                "        | " +status;
    }
}
