package org.example.operations;

/*
    @author Sebastian Kubalski
*/
public enum Context {
    EXIT("zakończenie programu"),
    INSERT("wprowadzanie naprawy"),
    SET_DECISION("wprowadzenie decyzji"),
    READ("wypisywanie danyc");

    private final String message;

    Context(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.ordinal() + " - " + message;
    }

    public static void print() {
        for (Context context : Context.values()) {
            System.out.println(context);
        }
    }
}
