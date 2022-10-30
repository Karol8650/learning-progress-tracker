public class StudentPoints {
    private int totalPoints;
    private int JavaPoints;
    private int DSAPoints;
    private int DatabasesPoints;
    private int SpringPoints;

    public int getJavaPoints() {
        return JavaPoints;
    }

    public int getDSAPoints() {
        return DSAPoints;
    }

    public int getDatabasesPoints() {
        return DatabasesPoints;
    }

    public int getSpringPoints() {
        return SpringPoints;
    }

    void addJavaPoints(int points) {
        JavaPoints += points;
        totalPoints += points;
    }

    void addDSAPoints(int points) {
        DSAPoints += points;
        totalPoints += points;
    }

    void addDatabasesPoints(int points) {
        DatabasesPoints += points;
        totalPoints += points;
    }

    void addSpringPoints(int points) {
        SpringPoints += points;
        totalPoints += points;
    }

    int getPointsByCourse(String name) {
        name = name.toLowerCase();
        switch (name) {
            case "java" -> { return JavaPoints; }
            case "dsa" -> { return DSAPoints; }
            case "databases" -> { return DatabasesPoints; }
            case "spring" -> { return SpringPoints; }
        }
        return 0;
    }

    int getTotalPoints() {
        return totalPoints;
    }
}
