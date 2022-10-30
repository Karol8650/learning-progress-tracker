import java.util.Objects;

class Student {
    private final StudentPoints studentPoints = new StudentPoints();
    private final StudentCredentials studentCredentials;
    private final int id;
    Student(String credentials) {
        studentCredentials = new StudentCredentials(credentials);
        id = StudentID.getID();
    }

    static void updatePoints(String points) {
        points = points.replace("\\s{2,}", "\\s");
        String[] parts = points.split("\\s");

        int index = 0;
        int id = Integer.parseInt(parts[index++]);
        Student student = Database.getStudent((id));

        StudentPoints studentPoints = student.getStudentPoints();

        int javaPoints = Integer.parseInt(parts[index++]);
        if (studentPoints.getJavaPoints() == 0 && javaPoints > 0) {
            Course course = Database.getCourse("Java");
            Objects.requireNonNull(course).enrollStudent(student);
        }
        studentPoints.addJavaPoints(javaPoints);

        int dsaPoints = Integer.parseInt(parts[index++]);
        if (studentPoints.getDSAPoints() == 0 && dsaPoints > 0) {
            Course course = Database.getCourse("DSA");
            Objects.requireNonNull(course).enrollStudent(student);
        }
        studentPoints.addDSAPoints(dsaPoints);

        int databasesPoints = Integer.parseInt(parts[index++]);
        if (studentPoints.getDatabasesPoints() == 0 && databasesPoints > 0) {
            Course course = Database.getCourse("Databases");
            Objects.requireNonNull(course).enrollStudent(student);
        }
        studentPoints.addDatabasesPoints(databasesPoints);

        int springPoints = Integer.parseInt(parts[index]);
        if (studentPoints.getSpringPoints() == 0 && springPoints > 0) {
            Course course = Database.getCourse("Spring");
            Objects.requireNonNull(course).enrollStudent(student);
        }
        studentPoints.addSpringPoints(springPoints);
    }

    public static void displayPoints(String id) {
        int parsedID = Integer.parseInt(id);
        StudentPoints studentPoints = Database.getStudent((parsedID)).getStudentPoints();

        int Java = studentPoints.getJavaPoints();
        int DSA = studentPoints.getDSAPoints();
        int Databases = studentPoints.getDatabasesPoints();
        int Spring = studentPoints.getSpringPoints();

        System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", parsedID, Java, DSA, Databases, Spring);
    }

    String getEmail() {
        return studentCredentials.getEmail();
    }

    int getID() {
        return id;
    }

    StudentPoints getStudentPoints() {
        return studentPoints;
    }


}
