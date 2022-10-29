public class StudentPoints {
    private int Java;
    private int DSA;
    private int Databases;
    private int Spring;

    void addJavaPoints(int points) {
        Java += points;
    }

    void addDSAPoints(int points) {
        DSA += points;
    }

    void addDatabasesPoints(int points) {
        Databases += points;
    }

    void addSpringPoints(int points) {
        Spring += points;
    }

    int getJavaPoints() {
        return Java;
    }

    int getDSAPoints() {
        return DSA;
    }

    int getDatabasesPoints() {
        return Databases;
    }

    int getSpringPoints() {
        return Spring;
    }
}
