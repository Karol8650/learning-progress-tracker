import java.util.ArrayList;
import java.util.List;

abstract class Course {
    static protected CoursesStatistics courseStatistics = new CoursesStatistics();
    protected final int maxPoints;
    protected final List<Student> enrolledStudents;
    protected final String name;

    Course(String name, int maxPoints) {
        this.name = name;
        this.maxPoints = maxPoints;
        enrolledStudents = new ArrayList<>();
    }

    void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    static public void displayCoursesStatistics() {
        courseStatistics.displayCoursesStatistics();
    }
    public String getName() {
        return name;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public CoursesStatistics getCourseStatistics() {
        return courseStatistics;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
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

