package org.example.operations;

import org.example.helpers.DbHelper;

import java.sql.SQLException;

/*
    @author Sebastian Kubalski
*/
public final class ExitOperation implements IServiceOperation {
    @Override
    public void execute() {
        try {
            DbHelper.getInstance().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exiting application");
        System.exit(0);
    }
}
