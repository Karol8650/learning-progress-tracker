import java.util.ArrayList;
import java.util.List;

abstract class Course {

    protected final int maxPoints;
    protected final List<Student> enrolledStudents;
    protected int completedTasks;
    protected final String name;

    Course(String name, int maxPoints) {
        this.name = name;
        this.maxPoints = maxPoints;
        enrolledStudents = new ArrayList<>();
    }

    void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    void addCompletedTask() {
        completedTasks++;
    }

    String getName() {
        return name;
    }

    int getMaxPoints() {
        return maxPoints;
    }

    List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public double getTotalEnrolledStudents() {
        return enrolledStudents.size();
    }

    public double getCompletedTasks() {
        return completedTasks;
    }

    public double getAverageGradePerAssignment() {
        int totalCoursePoints = getTotalCoursePoints();
        if (completedTasks > 0) {
            return (double) totalCoursePoints / (double) completedTasks;
        }
        return 0;
    }

    public int getTotalCoursePoints() {
        int totalCoursePoints = 0;
        for (Student student : enrolledStudents) {
            totalCoursePoints += student.getStudentPoints().getPointsByCourse(name);
        }
        return totalCoursePoints;
    }

}

class JavaCourse extends Course {
    JavaCourse() {
        super("Java", 600);
    }
}

class DSACourse extends Course {
    DSACourse() {
        super("DSA", 400);
    }
}

class DatabasesCourse extends Course {
    DatabasesCourse() {
        super("Databases", 480);
    }
}

class SpringCourse extends Course {
    SpringCourse() {
        super("Spring", 550);
    }
}

