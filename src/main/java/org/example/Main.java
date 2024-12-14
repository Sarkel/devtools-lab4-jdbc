package org.example;

import org.example.helpers.ConfigHelper;
import org.example.helpers.DbHelper;

/*
    @author Sebastian Kubalski
*/
public class Main {
    // todo: add proper handling of the exception
    public static void main(String[] args) {
        ConfigHelper.getInstance().load();
        DbHelper.getInstance().connect();

        final ServiceOperations serviceOperations = new ServiceOperations();
        serviceOperations.operate();
    }

//    public static void main(String[] args) throws SQLException, IOException {
//        ConfigHelper.getInstance().load();
//
//        final DbHelper dbHelper = DbHelper.getInstance();
//        dbHelper.connect();
//        dbHelper.setup();
//
//        Scanner scanner;
//
//        while (true){
//            System.out.println("Podaj tryb działania programu:");
//            System.out.println("0 - zakończenie programu.");
//            System.out.println("1 - wprowadzanie naprawy");
//            System.out.println("2 - wprowadzenie decyzji");
//            System.out.println("3 - wypisywanie danych.");
//            scanner = new Scanner(System.in);
//            int choice = scanner.nextInt();
//            switch (choice) {
//                case 1: {
//                    System.out.println("Wprowadz ownera:");
//                    scanner = new Scanner(System.in);
//                    String owner = scanner.next();
//
//                    try (Statement statement = dbHelper.getConnection().createStatement()) {
//                        statement.execute("INSERT INTO DeviceService(Owner, Status) VALUES ('" + owner + "', "+ Status.SENT +")");
//                    }catch (SQLException sql) {
//                        System.out.println(sql.getMessage());
//                    }
//                }
//                case 2: {
//                    System.out.println("Lista decyzji:\n Naprawa \n Wymiana \n Zwrot pieniędzy \n Odmowa");
//                    System.out.println("Wprowadź ID sprzętu, którego decyzja dotyczny:");
//                    scanner = new Scanner(System.in);
//                    String IDdevice = scanner.nextLine();
//                    System.out.println("Jaka decyzja ma zostać wprowadzona?");
//                    scanner = new Scanner(System.in);
//                    String decision = scanner.nextLine();
//                    try (Statement statement = dbHelper.getConnection().createStatement()) {
//                        statement.execute("INSERT INTO DeviceService(Decision) VALUES ('" + decision+ "') WHERE (ID = '" + IDdevice + "')");
//                    }catch (SQLException sql) {
//                        System.out.println(sql.getMessage());
//                    }
//
//                }
//                case 3: {
//                    try (Statement statement = dbHelper.getConnection().createStatement()) {
//                        ResultSet resultSet = statement.executeQuery("SELECT * FROM DeviceService");
//                        System.out.println("Zawartość tabeli:");
//
//                        while (resultSet.next()) {
//                            int ID = resultSet.getInt("ID");
//                            String owner = resultSet.getString("Owner");
//                            String status = resultSet.getString("Status");
//                            String decision = resultSet.getString("Decision");
//                            System.out.println("Identyfikator: " + ID + ", Właściciel: " + owner + ", Stan: " + status + ", Decyzja: " + decision);
//                        }
//
//                    }catch (SQLException sql) {
//                        System.out.println(sql.getMessage());
//                    }
//                }
//                case 0: {
//                    dbHelper.close();
//                    System.exit(0);
//                }
//            }
//        }
}
