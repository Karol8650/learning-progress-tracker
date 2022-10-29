public class StudentID {
    static private final int startingID = 10000;
    static private int actualID = startingID;

    static int getID () {
        return actualID++;
    }
    static int getActualID() {
        return actualID;
    }
    static int getStartingID() {
        return startingID;
    }
}
