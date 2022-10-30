import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CoursesStatistics {

    private int totalEnrolledStudents;
    private int completedTasks;
    private int averageGradePerAssignment;

    private final List<Course> courses = Database.getCourses();

    private String getBestResult(IntSupplier supplier) {

        int max = courses.stream().mapToInt(a -> supplier.getAsInt()).min().orElse(0);

        return courses.stream().mapToInt(a -> supplier.getAsInt()).filter(a -> a == max)
                .mapToObj(String::valueOf).collect(Collectors.joining(", "));
    }

    private String getWorstResult(IntSupplier supplier) {

        int min = courses.stream().mapToInt(a -> supplier.getAsInt()).min().orElse(0);

        return courses.stream().mapToInt(a -> supplier.getAsInt()).filter(a -> a == min)
                .mapToObj(String::valueOf).collect(Collectors.joining(", "));
    }

    private boolean checkAllCoursesTheSameResult(String result) {
        return result.split(", ").length == courses.size();
    }

    private String getMostPopularCourse() {
        String result = getBestResult(this::getTotalEnrolledStudents);
        return totalEnrolledStudents > 0 && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    private String getLeastPopularCourse() {
        String result = getWorstResult(this::getTotalEnrolledStudents);
        return totalEnrolledStudents > 0 && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    private String getHighestActivityCourse() {
        String result = getBestResult(this::getCompletedTasks);
        return completedTasks > 0 && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    private String getLowestActivityCourse() {
        String result = getWorstResult(this::getCompletedTasks);
        return completedTasks > 0 && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    private String getEasiestCourse() {
        String result = getBestResult(this::getAverageGradePerAssignment);
        return averageGradePerAssignment > 0 && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    private String getHardestCourse() {
        String result = getWorstResult(this::getAverageGradePerAssignment);
        return averageGradePerAssignment > 0 && !checkAllCoursesTheSameResult(result) ? result : "n/a";
    }

    void displayCoursesStatistics() {
        System.out.println("Most popular: " + getMostPopularCourse());
        System.out.println("Least popular: " + getLeastPopularCourse());
        System.out.println("Highest activity: " + getHighestActivityCourse());
        System.out.println("Lowest activity: " + getLowestActivityCourse());
        System.out.println("Easiest course: " + getEasiestCourse());
        System.out.println("Hardest course: " + getHardestCourse());
    }

    static void displayCourseStatistics(String name) {
        System.out.printf("%-6s %-9s %-9s\n", "id", "points", "completed");

        Course course = Database.getCourse(name);
        List<Student> enrolledStudents = course.getEnrolledStudents();
        enrolledStudents = sortStudents(enrolledStudents);

        DecimalFormat decFormat = new DecimalFormat("#%");
        for (Student student: enrolledStudents) {
            int points = student.getStudentPoints().getPointsByCourse(name);
            System.out.printf("%-6s %-9d %-9.1s\n",
                    student.getID(), points, decFormat.format(points / course.getMaxPoints()));
        }

    }

    static List<Student> sortStudents(List<Student> students) {
        return students.stream().
                sorted(Comparator.comparing((Student a) -> a.getStudentPoints().getTotalPoints()).reversed().
                        thenComparing(Student::getID)).
                collect(Collectors.toList());
    }


    public int getTotalEnrolledStudents() {
        return totalEnrolledStudents;
    }

    public int getCompletedTasks() {
        return completedTasks;
    }

    public int getAverageGradePerAssignment() {
        return averageGradePerAssignment;
    }
}
