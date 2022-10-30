import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class DataVerification {
    static private final String nameRegex = "([a-zA-Z][-']?)+[a-zA-Z]$";

    static private final String emailRegex = "((\".*\")" +
            "|(\\w+([\\.\\+\\!\\%/-]\\w+)*-?))" +
            "@\\w+([\\.\\+/-]\\w+)*\\.\\w+";

    static private final int minCredentialParts = 3;

    static private boolean verifyName(String name) {
        boolean result = Pattern.compile(nameRegex).matcher(name).matches();

        if (!result) {
            System.out.println("Incorrect first name.");
        }

        return result;
    }

    static private boolean verifyLastName(String lastName) {
        String[] lastNameParts = lastName.split("\\s+");

        int properParts = (int) Arrays.stream(lastNameParts)
                .filter(word -> Pattern.compile(nameRegex).matcher(word).matches()).count();

        boolean result = (properParts == lastNameParts.length);

        if (!result) {
            System.out.println("Incorrect last name.");
        }

        return result;
    }

    static private boolean verifyEmail(String email) {
        if (Database.containsEmail(email)) {
            System.out.println("This email is already taken.");
            return false;
        }
        if (!Pattern.compile(emailRegex).matcher(email).matches()) {
            System.out.println("Incorrect email");
            return false;
        }
        return true;
    }

    static boolean checkCredentials(String credentials) {
        if (credentials.trim().split("\s+").length < minCredentialParts) {
            System.out.println("Incorrect credentials.");
            return false;
        }

        credentials = credentials.replaceAll("\s{2,}", " ");

        StudentCredentials studentCredentials = new StudentCredentials(credentials);

        return verifyName(studentCredentials.getFirstName()) &&
                verifyLastName(studentCredentials.getLastName()) &&
                verifyEmail(studentCredentials.getEmail());
    }

    static boolean checkID(String id) {
        try {
            int parsedID = Integer.parseInt(id);
            if (!(parsedID >= StudentID.getStartingID() && parsedID <= StudentID.getActualID())) {
                System.out.println("No student is found for id=" + id + ".");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("No student is found for id=" + id + ".");
            return false;
        }
    }

    static boolean checkPoints(String points) {
        try {
            int parsedPoints = Integer.parseInt(points);

            return parsedPoints >= 0;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean checkStudentResults(String results) {
        results = results.replace("\\s{2,}", "\\s");
        String[] parts = results.split("\\s");
        if (parts.length != 5) {
            System.out.println("Incorrect points format.");
            return false;
        }

        String id = parts[0];

        if (!checkID(id)) {
            return false;
        }

        for (int i = 1; i < parts.length; i++) {
            if (!checkPoints(parts[i])) {
                System.out.println("Incorrect points format.");
                return false;
            }
        }

        return true;
    }

    static boolean checkCourseName(String course) {
        course = course.toLowerCase();
        Set<String> courses = Set.of("java", "dsa", "databases", "spring");
        return (courses.contains(course));
    }
}
