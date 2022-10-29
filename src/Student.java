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
        Student student = StudentDatabase.getStudent((id));

        student.getStudentPoints().addJavaPoints(Integer.parseInt(parts[index++]));
        student.getStudentPoints().addDSAPoints(Integer.parseInt(parts[index++]));
        student.getStudentPoints().addDatabasesPoints(Integer.parseInt(parts[index++]));
        student.getStudentPoints().addSpringPoints(Integer.parseInt(parts[index]));
    }

    public static void displayPoints(String id) {
        int parsedID = Integer.parseInt(id);
        StudentPoints studentPoints = StudentDatabase.getStudent((parsedID)).getStudentPoints();

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
