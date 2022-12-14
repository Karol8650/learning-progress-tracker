import java.util.*;

class Database {
    static private final List<Course> courses = List.of(
            new JavaCourse(), new DSACourse(), new DatabasesCourse(), new SpringCourse()); // immutable list
    static private final Map<Integer, Student> students = new HashMap<>();
    static private final Set<String> emails = new HashSet<>();

    static void addStudent(Student student) {
        students.put(student.getID(), student);
        addEmail(student.getEmail());
    }

    static void addEmail(String email) {
        emails.add((email));
    }

    static int numOfStudents() {
        return students.size();
    }

    static boolean containsEmail(String email) {
        return emails.contains((email));
    }

    static Student getStudent(int id) {
        return students.get(id);
    }

    static void listStudents() {
        if (students.size() == 0) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("Students:");
        for (Integer id : students.keySet()) {
            System.out.println(id);
        }
    }

    static List<Course> getCourses() {
        return courses;
    }

    static Course getCourse(String name) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(name))
                return course;
        }
        return null;
    }

    static Collection<Student> getStudents() {
        return students.values();
    }
}
