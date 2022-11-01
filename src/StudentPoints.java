import java.util.Objects;

public class StudentPoints {
    private int JavaPoints;
    private int DSAPoints;
    private int DatabasesPoints;
    private int SpringPoints;

    void addJavaPoints(int points) {
        int maxPoints = Objects.requireNonNull(Database.getCourse("java")).getMaxPoints();
        if (JavaPoints + points > maxPoints) {
            JavaPoints = maxPoints;
        } else {
            JavaPoints += points;
        }
    }

    void addDSAPoints(int points) {
        int maxPoints = Objects.requireNonNull(Database.getCourse("dsa")).getMaxPoints();
        if (DSAPoints + points > maxPoints) {
            DSAPoints = maxPoints;
        } else {
            DSAPoints += points;

        }
    }

    void addDatabasesPoints(int points) {
        int maxPoints = Objects.requireNonNull(Database.getCourse("databases")).getMaxPoints();
        if (DatabasesPoints + points > maxPoints) {
            DatabasesPoints = maxPoints;
        } else {
            DatabasesPoints += points;

        }
    }

    void addSpringPoints(int points) {
        int maxPoints = Objects.requireNonNull(Database.getCourse("spring")).getMaxPoints();
        if (SpringPoints + points > maxPoints) {
            SpringPoints = maxPoints;
        } else {
            SpringPoints += points;

        }
    }

    int getPointsByCourse(String name) {
        name = name.toLowerCase();
        switch (name) {
            case "java" -> {
                return JavaPoints;
            }
            case "dsa" -> {
                return DSAPoints;
            }
            case "databases" -> {
                return DatabasesPoints;
            }
            case "spring" -> {
                return SpringPoints;
            }
            default -> {
                return 0;
            }
        }
    }
}
