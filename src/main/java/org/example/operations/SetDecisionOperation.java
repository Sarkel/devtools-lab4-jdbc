package org.example.operations;

import org.example.db.DeviceServiceEntity;
import org.example.db.DeviceServiceRepository;
import org.example.helpers.DbHelper;
import org.example.db.Decision;
import org.example.db.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
    @author Joanna Ćwierz
    @author Maciej Wydrych
*/

public final class SetDecisionOperation implements IServiceOperation {
    private final DeviceServiceRepository deviceServiceRepository = new DeviceServiceRepository();

    @Override
    public void execute() {
        System.out.println("Wprowadź identyfikator sprawy, dla której chcesz podjąć decyzję:");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        System.out.println("Zawartość rekordu tabeli dla wprowadzonego identyfikatora:");
        System.out.println(deviceServiceRepository.getById(id));

        System.out.println();
        System.out.println("Podaj decyzję dla wprowadzonego identyfikatora sprawy:");
        Decision.print();

        scanner = new Scanner(System.in);
        int rawDecision = scanner.nextInt();

        Decision decision = Decision.values()[rawDecision];

        deviceServiceRepository.update(
                DeviceServiceEntity.builder()
                        .id(id)
                        .decision(decision)
                        .build()
        );

        System.out.println("Wprowadzono decyzję: " + decision.getMessage());

        System.out.println("Zawartość rekordu tabeli po wprowadzeniu decyzji:");
        System.out.println(deviceServiceRepository.getById(id));
    }
}
