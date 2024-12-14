package org.example.operations;

import org.example.DbHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
    @author Joanna Ćwierz
    @author Maciej Wydrych
*/

public final class ReadOperation implements IServiceOperation {
    @Override
    public void execute() {
        try (Statement statement = DbHelper.getInstance().getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM device_service");
            System.out.println("Zawartość tabeli w bazie danych:");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String owner_name = resultSet.getString("owner_name");
                int status = resultSet.getInt("status");
                int decision = resultSet.getInt("decision");
                System.out.println("ID: " + id + ", Właściciel: " + owner_name + ", Stan: " + Status.values()[status] + ", Decyzja: " + Decision.values()[decision]);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
