import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class CoursesStatistics {

    static private boolean isAnyEnrolledStudent = false;
    static private boolean isAnyCompletedTask = false;


    static private final List<Course> courses = Database.getCourses();

    static private String getBestResult(Function<Course, Double> function) {

        double max = courses.stream().mapToDouble(function::apply).max().orElse(0);

        return courses.stream().filter(a -> function.apply(a) == max)
                .map(Course::getName).collect(Collectors.joining(", "));
    }

    static private String getWorstResult(Function<Course, Double> function) {

        double min = courses.stream().mapToDouble(function::apply).min().orElse(0);

        return courses.stream().filter(a -> function.apply(a) == min)
                .map(Course::getName).collect(Collectors.joining(", "));
    }

    static private boolean checkAllCoursesTheSameResult(String result) {
        return result.split(", ").length == courses.size();
    }

    static private String getMostPopularCourse() {
        String result = getBestResult(Course::getTotalEnrolledStudents);
        return isAnyEnrolledStudent ? result : "n/a";
    }

    static private String getLeastPopularCourse() {
        String result = getWorstResult(Course::getTotalEnrolledStudents);
        return isAnyEnrolledStudent && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    static private String getHighestActivityCourse() {
        String result = getBestResult(Course::getCompletedTasks);
        return isAnyCompletedTask ? result : "n/a";
    }

    static private String getLowestActivityCourse() {
        String result = getWorstResult(Course::getCompletedTasks);
        return isAnyCompletedTask && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    static private String getEasiestCourse() {
        String result = getBestResult(Course::getAverageGradePerAssignment);
        return isAnyCompletedTask ? result : "n/a";
    }

    static private String getHardestCourse() {
        String result = getWorstResult(Course::getAverageGradePerAssignment);
        return isAnyCompletedTask && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    static void displayCoursesStatistics() {
        System.out.println("Most popular: " + getMostPopularCourse());
        System.out.println("Least popular: " + getLeastPopularCourse());
        System.out.println("Highest activity: " + getHighestActivityCourse());
        System.out.println("Lowest activity: " + getLowestActivityCourse());
        System.out.println("Easiest course: " + getEasiestCourse());
        System.out.println("Hardest course: " + getHardestCourse());
    }

    static void displayCourseStatistics(String name) {
        Course course = Database.getCourse(name);
        List<Student> enrolledStudents = course.getEnrolledStudents();
        enrolledStudents = sortStudents(enrolledStudents, name);

        System.out.println(course.getName());
        System.out.printf("%-6s %-9s %-9s\n", "id", "points", "completed");
        DecimalFormat decFormat = new DecimalFormat("0.0%");
        for (Student student : enrolledStudents) {
            int points = student.getStudentPoints().getPointsByCourse(name);
            if (points != 0) {
                double epsilon = 0.000001d;
                double completed = ((double) points / (double) course.getMaxPoints());
                if (Math.abs(completed * 1000 - 0.5 - Math.floor(completed * 1000)) < epsilon) {
                    System.out.printf("%-6s %-9d %-9s\n",
                            student.getID(), points, decFormat.format(Math.ceil(completed * 1000) / 1000));
                } else {
                    System.out.printf("%-6s %-9d %-9s\n",
                            student.getID(), points, decFormat.format(completed));
                }
            } else {
                System.out.printf("%-6s %-9d %-9.1s\n", student.getID(), points, decFormat.format(points));
            }
        }

    }

    static List<Student> sortStudents(List<Student> students, String courseName) {
        return students.stream()
                .sorted(Comparator.comparing((Student a) -> {
                            StudentPoints points = a.getStudentPoints();
                            return points.getPointsByCourse(courseName);
                        })
                        .reversed().thenComparing(Student::getID)).
                collect(Collectors.toList());

    }

    static void setIsAnyEnrolledStudentsTrue() {
        isAnyEnrolledStudent = true;
    }

    static void setIsAnyCompletedTaskTrue() {
        isAnyCompletedTask = true;
    }
}
