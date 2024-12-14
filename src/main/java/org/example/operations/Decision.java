/*
    @author Joanna Ćwierz
    @author Maciej Wydrych
*/

package org.example.operations;

public enum Decision {
    REPAIR("naprawa"),
    REPLACE("wymiana"),
    REFUND("zwrot"),
    REJECT("odmowa");

    private final String message;

    Decision(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return this.ordinal() + " - " + message;
    }

    public static void print() {
        for (Decision decision : Decision.values()) {
            System.out.println(decision);
        }
    }
}
