package org.example.operations;

import org.example.db.DeviceServiceEntity;
import org.example.db.DeviceServiceRepository;
import org.example.db.Status;

import java.util.Scanner;

/*
    @author Sebastian Kubalski
*/
public final class InsertOperation implements IServiceOperation {
    private final DeviceServiceRepository deviceServiceRepository = new DeviceServiceRepository();

    @Override
    public void execute() {
        System.out.println("Wprowadz ownera: ");
        Scanner scanner = new Scanner(System.in);
        String ownerName = scanner.nextLine();

        DeviceServiceEntity deviceServiceEntity = DeviceServiceEntity.builder()
                .status(Status.RECEIVED)
                .ownerName(ownerName)
                .build();

        deviceServiceRepository.create(deviceServiceEntity);

        System.out.println("Wprowadzowno ownera: " + deviceServiceEntity.getOwnerName());
    }
}
