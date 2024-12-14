package org.example.operations;

import org.example.db.Status;
import org.example.helpers.DbHelper;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
    @author Sebastian Kubalski
*/
public final class InsertOperation implements IServiceOperation {
    @Override
    public void execute() {
        System.out.println("Wprowadz ownera: ");
        Scanner scanner = new Scanner(System.in);
        String owner_name = scanner.nextLine();

        try (Statement statement = DbHelper.getInstance().getConnection().createStatement()) {
            statement.execute(
                    "INSERT INTO device_service(owner_name, status) VALUES ('" + owner_name + "', '" + Status.SENT.ordinal() + "')"
            );
            System.out.println("Wprowadzowno ownera: " + owner_name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
