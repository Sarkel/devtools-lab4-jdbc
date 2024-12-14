package org.example.operations;

import org.example.db.DeviceServiceEntity;
import org.example.db.DeviceServiceRepository;

import java.util.List;

/*
    @author Joanna Ćwierz
    @author Maciej Wydrych
*/

public final class ReadOperation implements IServiceOperation {
    private final DeviceServiceRepository deviceServiceRepository = new DeviceServiceRepository();

    @Override
    public void execute() {
        System.out.println("Zawartość tabeli w bazie danych:");

        List<DeviceServiceEntity> deviceServices = deviceServiceRepository.getAll();

        deviceServices.forEach(System.out::println);
    }
}
