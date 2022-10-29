import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class StudentDatabase {
    private static final Map<Integer, Student> students = new HashMap<>();
    private static final Set<String> emails = new HashSet<>();

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
}
