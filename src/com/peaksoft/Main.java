package com.peaksoft;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {
    private static GsonBuilder GSON_BUILDER = new GsonBuilder();
    private static Gson GSON = GSON_BUILDER.setPrettyPrinting().create();
    private static Path TRUCK_URl = Paths.get("./truck.json");
    private static Path DRIVERURL = Paths.get("./driver.json");

    public static void main(String[] args) {
        Truck[] trucks = {Truck.creatTruck(1, "Renault", new Driver(), Status.BASE),
                Truck.creatTruck(2, "Volvo  ", new Driver(), Status.BASE),
                Truck.creatTruck(3, "DAF XT ", new Driver(), Status.BASE)
        };
        String json = GSON.toJson(trucks);
        writeTruck(json);
        Truck[] trucks1 = GSON.fromJson(readTruck(), Truck[].class);
        System.out.println("#|" + "    Truck    |" + " Driver  " + "|State");
        System.out.println("----------------------------------------------");
        for (Truck truck : trucks1) {
            System.out.println(truck);
        }
        Driver[] drivers = {
                Driver.creatDriver(1, "Alex  ", " "),
                Driver.creatDriver(2, "Fisher", " "),
                Driver.creatDriver(3, "Bob   ", " ")
        };
        String json2 = GSON.toJson(drivers);
        writeDriver(json2);
        Driver[] drivers1 = GSON.fromJson(readDriver(), Driver[].class);
        System.out.println("----------------------------------------------");
        System.out.println("#|" + "  Driver" + "      |Truck");
        System.out.println("----------------------------------------------");
        for (Driver driver : drivers1
        ) {
            System.out.println(driver.infoDriver());
        }
        while (true) {
            try {
                TruckService truckService = new TruckService();
                Scanner sc = new Scanner(System.in);
                System.out.println("Выберите грузовика или нажмите 0 чтобы остановить программу:");
                int truck = sc.nextInt();
                truckService.changeTruck(trucks,truck,drivers);
                if (truck == 0) {
                    break;
                }
                for (Truck truck1 : trucks) {
                    if (truck1.getId() == truck) {
                        System.out.println("#" + truck1.getId());
                        System.out.println("Truck:" + truck1.getTruck());
                        System.out.println("Driver:" + truck1.getDriver().getName());
                        System.out.println("Truck status:" + truck1.getStatus());
                    }
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
            public static void writeTruck (String json){
                try {
                    Files.writeString(TRUCK_URl, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    public static String readTruck() {
        String json = " ";
        int a;
        try {
            FileReader reader = new FileReader(String.valueOf(TRUCK_URl));
            while ((a = reader.read()) != -1) {
                json += (char) a;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void writeDriver(String json) {
        try {
            Files.writeString(DRIVERURL, json, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readDriver() {
        String json = " ";
        int a;
        try {
            FileReader reader = new FileReader(String.valueOf(DRIVERURL));
            while ((a = reader.read()) != -1) {
                json += (char) a;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}