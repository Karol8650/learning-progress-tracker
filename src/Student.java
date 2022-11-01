import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Student {
    private final StudentPoints studentPoints = new StudentPoints();
    private final StudentCredentials studentCredentials;
    private final int id;

    private final Map<String, Boolean> notifications;

    Student(String credentials) {
        studentCredentials = new StudentCredentials(credentials);
        notifications = new HashMap<>();
        initializeNotifications();
        id = StudentID.getID();
    }

    private void initializeNotifications() {
        notifications.put("java", false);
        notifications.put("dsa", false);
        notifications.put("databases", false);
        notifications.put("spring", false);
    }

    static void updatePoints(String points) {
        points = points.replace("\\s{2,}", "\\s");
        String[] parts = points.split("\\s");

        int index = 0;
        int id = Integer.parseInt(parts[index++]);
        Student student = Database.getStudent((id));

        StudentPoints studentPoints = student.getStudentPoints();

        int javaPoints = Integer.parseInt(parts[index++]);
        Course course = Database.getCourse("Java");
        if (studentPoints.getPointsByCourse("java") == 0 && javaPoints > 0) {
            Objects.requireNonNull(course).enrollStudent(student);
            CoursesStatistics.setIsAnyEnrolledStudentsTrue();
        }
        if (javaPoints > 0) {
            studentPoints.addJavaPoints(javaPoints);
            Objects.requireNonNull(course).addCompletedTask();
            CoursesStatistics.setIsAnyCompletedTaskTrue();
        }


        int dsaPoints = Integer.parseInt(parts[index++]);
        course = Database.getCourse("DSA");
        if (studentPoints.getPointsByCourse("dsa") == 0 && dsaPoints > 0) {
            Objects.requireNonNull(course).enrollStudent(student);
            CoursesStatistics.setIsAnyEnrolledStudentsTrue();
        }
        if (dsaPoints > 0) {
            studentPoints.addDSAPoints(dsaPoints);
            Objects.requireNonNull(course).addCompletedTask();
            CoursesStatistics.setIsAnyCompletedTaskTrue();
        }


        int databasesPoints = Integer.parseInt(parts[index++]);
        course = Database.getCourse("Databases");
        if (studentPoints.getPointsByCourse("databases") == 0 && databasesPoints > 0) {
            Objects.requireNonNull(course).enrollStudent(student);
            CoursesStatistics.setIsAnyEnrolledStudentsTrue();
        }
        if (databasesPoints > 0) {
            studentPoints.addDatabasesPoints(databasesPoints);
            Objects.requireNonNull(course).addCompletedTask();
            CoursesStatistics.setIsAnyCompletedTaskTrue();
        }


        int springPoints = Integer.parseInt(parts[index]);
        course = Database.getCourse("Spring");
        if (studentPoints.getPointsByCourse("spring") == 0 && springPoints > 0) {
            Objects.requireNonNull(course).enrollStudent(student);
            CoursesStatistics.setIsAnyEnrolledStudentsTrue();
        }
        if (springPoints > 0) {
            studentPoints.addSpringPoints(springPoints);
            Objects.requireNonNull(course).addCompletedTask();
            CoursesStatistics.setIsAnyCompletedTaskTrue();
        }

    }

    static void notifyStudents() {
        int notifiedStudents = 0;
        for (Student student : Database.getStudents()) {
            boolean notified = false;
            if (student.getStudentPoints().getPointsByCourse("java") == Database.getCourse("java").getMaxPoints() && !student.isNotified("java")) {
                notifyStudent(student, "java");
                notified = true;
            }
            if (student.getStudentPoints().getPointsByCourse("dsa") == Database.getCourse("dsa").getMaxPoints() && !student.isNotified("dsa")) {
                notifyStudent(student, "dsa");
                notified = true;
            }
            if (student.getStudentPoints().getPointsByCourse("databases") == Database.getCourse("databases").getMaxPoints() && !student.isNotified("databases")) {
                notifyStudent(student, "databases");
                notified = true;
            }
            if (student.getStudentPoints().getPointsByCourse("spring") == Database.getCourse("spring").getMaxPoints() && !student.isNotified("spring")) {
                notifyStudent(student, "spring");
                notified = true;
            }
            if (notified)
                notifiedStudents++;
        }
        System.out.printf("Total %d students have been notified.\n", notifiedStudents);
    }

    static void notifyStudent(Student student, String courseName) {
        student.setNotified(courseName);
        System.out.printf("To: %s\n", student.getEmail());
        System.out.printf("Re: Your Learning Progress\n");
        System.out.printf("Hello, %s %s! You have accomplished our %s course!\n", student.getFirstName(), student.getLastName(), courseName);
    }

    public static void displayPoints(String id) {
        int parsedID = Integer.parseInt(id);
        StudentPoints studentPoints = Database.getStudent((parsedID)).getStudentPoints();

        int Java = studentPoints.getPointsByCourse("java");
        int DSA = studentPoints.getPointsByCourse("dsa");
        int Databases = studentPoints.getPointsByCourse("databases");
        int Spring = studentPoints.getPointsByCourse("spring");

        System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", parsedID, Java, DSA, Databases, Spring);
    }

    String getEmail() {
        return studentCredentials.getEmail();
    }

    String getFirstName() {
        return studentCredentials.getFirstName();
    }

    String getLastName() {
        return studentCredentials.getLastName();
    }

    ;

    int getID() {
        return id;
    }

    StudentPoints getStudentPoints() {
        return studentPoints;
    }

    private void setNotified(String courseName) {
        notifications.put(courseName, true);
    }

    private boolean isNotified(String courseName) {
        return notifications.get(courseName);
    }

}
