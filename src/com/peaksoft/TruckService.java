package com.peaksoft;
import java.util.Random;
import java.util.Scanner;
public class TruckService {
    static String truckName;
    static String driverName = null;
    Scanner sc = new Scanner(System.in);
    static Driver driver = new Driver();
    static String dr;
    Random rd = new Random();

    public void changeTruck(Truck[] trucks, int id, Driver[] drivers) {
        for (int i = 0; i < trucks.length; i++) {
            if (trucks[i].getId() == id) {
                if (trucks[i].getDriver().getName() == null) {
                    dr = " ";
                } else {
                    dr = trucks[i].getDriver().getName();
                }
                truckName = trucks[i].getTruck();
                System.out.println("#            " + trucks[i].getId());
                System.out.println("Truck:         " + trucks[i].getTruck());
                System.out.println("Driver:      " + dr);
                System.out.println("State:       " + trucks[i].getStatus());
                if (trucks[i].getStatus().equals(Status.BASE)) {
                    System.out.println("Если хотите отправить в путь нажмите 1");
                    System.out.println("Если хотите отправить на ремонт нажмите 2");
                    System.out.println("Если хотите выбрать водителя нажмите 3");
                    int service = sc.nextInt();
                    if (service == 3) {
                        changeDriver(drivers);
                        trucks[i].setDriver(driver);
                        System.out.println("Хотите его отправить в путь? Тогда нажмите 1 если нет 0");
                        service = sc.nextInt();
                        if (service == 1) {
                            startDriving(truckName, driverName);
                            trucks[i].setStatus(Status.ROUTE);
                        } else if (service == 0) {
                            System.out.println("Грузовик остался на базе");
                        }
                    } else if (service == 1) {
                        randomDriver(drivers);
                        trucks[i].setDriver(driver);
                        startDriving(truckName, driverName);
                        trucks[i].setStatus(Status.ROUTE);
                    } else if (service == 2) {
                        startRepair(truckName);
                        trucks[i].setStatus(Status.RAPAIR);

                    }
                }
            }
        }
    }

    public void changeDriver(Driver[] drivers) {
        for (Driver value : drivers) {
            System.out.println(value.infoDriver());
        }
        System.out.println("Выберите водителя:");
        int vod = sc.nextInt();
        for (int i = 1; i < drivers.length; i++) {
            if (drivers[i].getId() == vod){
                driverName = drivers[i].getName();
                driver = drivers[i];
            }
        }System.out.println("Теперь у грузовика:" + truckName + ", водитель:" + driverName);
    }
    public static  void startDriving(String truck, String driver) {
        System.out.println("Теперь грузовик " + truck + " в пути и ведет его " + driver);
    }

    public static void startRepair(String truck) {
        System.out.println("Теперь грузовик " + truck + "в ремонте");
    }
    public void randomDriver(Driver[] drivers) {
        int vod = 0;
            vod = rd.nextInt(3)+1;
        for (int i = 0; i < drivers.length; i++) {
            if (drivers[i].getId() == vod) {
                driverName = drivers[i].getName();
                driver = drivers[i];
            }
        }
    }
}


