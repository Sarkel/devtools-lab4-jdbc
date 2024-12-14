package org.example.operations;

import org.example.DbHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
    @author Joanna Ćwierz
    @author Maciej Wydrych
*/

public final class SetDecisionOperation implements IServiceOperation {
    @Override
    public void execute() {
        System.out.println("Wprowadź identyfikator sprawy, dla której chcesz podjąć decyzję:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        try (Statement statement = DbHelper.getInstance().getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM device_service WHERE id = '" + id + "'");
            System.out.println("Zawartość rekordu tabeli dla wprowadzonego identyfikatora:");
            System.out.println("ID: " + resultSet.getInt("id") + ", Właściciel: " + resultSet.getString("owner_name") + ", Stan: " + Status.values()[resultSet.getInt("status")] + ", Decyzja: " + Decision.values()[resultSet.getInt("decision")]);

            System.out.println();
            System.out.println("Podaj decyzję dla wprowadzonego identyfikatora sprawy:");
            Decision.print();

            int decision = scanner.nextInt();
            statement.execute(
                    "INSERT INTO device_service(decision) VALUES ('" + decision + "')"
            );
            System.out.println("Wprowadzono decyzję: " + decision);

            resultSet = statement.executeQuery("SELECT * FROM device_service WHERE id = '" + id + "'");
            System.out.println("Zawartość rekordu tabeli po wprowadzeniu decyzji:");
            System.out.println("ID: " + resultSet.getInt("id") + ", Właściciel: " + resultSet.getString("owner_name") + ", Stan: " + Status.values()[resultSet.getInt("status")] + ", Decyzja: " + Decision.values()[resultSet.getInt("decision")]);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
