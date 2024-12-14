package org.example.db;

/*
    @author Joanna Ćwierz
    @author Maciej Wydrych
*/
public enum Decision {
    REPAIR("naprawa"),
    REPLACE("wymiana"),
    REFUND("zwrot"),
    REJECT("odmowa");

    private final String message;

    Decision(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.ordinal() + " - " + message;
    }

    public static void print() {
        for (Decision context : Decision.values()) {
            System.out.println(context);
        }
    }
}
