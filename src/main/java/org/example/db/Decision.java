package org.example.db;

import lombok.Getter;

/*
    @author Joanna Ćwierz
    @author Maciej Wydrych
*/
@Getter
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
        return this.ordinal() + " - " + this.getMessage();
    }

    public static void print() {
        for (Decision context : Decision.values()) {
            System.out.println(context);
        }
    }
}
