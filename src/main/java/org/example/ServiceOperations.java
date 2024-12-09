package org.example;

import org.example.operations.Context;
import org.example.operations.ExitOperation;
import org.example.operations.IServiceOperation;
import org.example.operations.InsertOperation;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ServiceOperations {
    private static final Context[] CONTEXTS = Context.values();

    private static final Map<Context, IServiceOperation> OPERATION_BY_CONTEXT = Map.ofEntries(
            Map.entry(Context.EXIT, new ExitOperation()),
            Map.entry(Context.INSERT, new InsertOperation())
    );

    public void operate() {
        while (true) {
            printServiceOperations();

            Scanner scanner = new Scanner(System.in);

            try {
                Context ctx = Optional.of(scanner.nextInt())
                        .filter(v -> CONTEXTS.length - 1 >= v)
                        .map(v -> CONTEXTS[v])
                        .orElseThrow(() -> new RuntimeException("Operation is not valid"));

                IServiceOperation serviceOperation = Optional.of(OPERATION_BY_CONTEXT.get(ctx))
                        .orElseThrow(() -> new RuntimeException("Operation is not implemented yet"));

                serviceOperation.execute();
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void printServiceOperations() {
        System.out.println();
        System.out.println("Podaj tryb działania programu:");
        Context.print();
    }
}
