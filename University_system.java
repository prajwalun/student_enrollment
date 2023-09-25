import java.util.*;

class Student {
    private int studentId;
    private String name;
    private Set<String> enrolledCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new HashSet<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public Set<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void enrollInCourse(String courseCode) {
        enrolledCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        enrolledCourses.remove(courseCode);
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private Set<Integer> enrolledStudents;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.enrolledStudents = new HashSet<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public Set<Integer> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(int studentId) {
        enrolledStudents.add(studentId);
    }

    public void dropStudent(int studentId) {
        enrolledStudents.remove(studentId);
    }
}

class AVLNode {
    String key;
    Course value;
    int height;
    AVLNode left, right;

    public AVLNode(String key, Course value) {
        this.key = key;
        this.value = value;
        this.height = 1;
    }
}

class AVLTree {
    private AVLNode root;

    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    private int balanceFactor(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    private AVLNode rotateLeft(AVLNode y) {
        AVLNode x = y.right;
        AVLNode t2 = x.left;

        x.left = y;
        y.right = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLNode rotateRight(AVLNode x) {
        AVLNode y = x.left;
        AVLNode t2 = y.right;

        y.right = x;
        x.left = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(String key, Course value) {
        root = insertRec(root, key, value);
    }

    private AVLNode insertRec(AVLNode node, String key, Course value) {
        if (node == null) return new AVLNode(key, value);

        if (key.compareTo(node.key) < 0)
            node.left = insertRec(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = insertRec(node.right, key, value);
        else return node; // Duplicate keys are not allowed

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = balanceFactor(node);

        // Left Heavy
        if (balance > 1) {
            if (key.compareTo(node.left.key) < 0)
                return rotateRight(node);
            else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        // Right Heavy
        if (balance < -1) {
            if (key.compareTo(node.right.key) > 0)
                return rotateLeft(node);
            else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    public Course search(String key) {
        return searchRec(root, key);
    }

    private Course searchRec(AVLNode node, String key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);

        if (cmp < 0) return searchRec(node.left, key);
        else if (cmp > 0) return searchRec(node.right, key);
        else return node.value;
    }
}

class University {
    private Map<Integer, Student> students;
    private AVLTree courses;

    public University() {
        students = new HashMap<>();
        courses = new AVLTree();
    }

    public void addStudent(int studentId, String name) {
        if (!students.containsKey(studentId)) {
            students.put(studentId, new Student(studentId, name));
        }
    }

    public void addCourse(String courseCode, String courseName) {
        if (courses.search(courseCode) == null) {
            courses.insert(courseCode, new Course(courseCode, courseName));
        }
    }

    public void enrollStudentInCourse(int studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.search(courseCode);

        if (student != null && course != null) {
            student.enrollInCourse(courseCode);
            course.enrollStudent(studentId);
        }
    }

    public void dropStudentFromCourse(int studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.search(courseCode);

        if (student != null && course != null) {
            student.dropCourse(courseCode);
            course.dropStudent(studentId);
        }
    }

    public List<String> getStudentSchedule(int studentId) {
        Student student = students.get(studentId);
        if (student != null) {
            return new ArrayList<>(student.getEnrolledCourses());
        }
        return Collections.emptyList();
    }

    public List<Integer> getCourseRoster(String courseCode) {
        Course course = courses.search(courseCode);
        if (course != null) {
            return new ArrayList<>(course.getEnrolledStudents());
        }
        return Collections.emptyList();
    }
}

public class University_system {
    public static void main(String[] args) {
        University university = new University();

        // Adding students and courses
        university.addStudent(25, "Michael Scott");
        university.addStudent(26, "Dwight Schrute");
        university.addStudent(27, "Creed");
        university.addCourse("CSC501", "Design and Analysis of Algorithms");
        university.addCourse("CSC311", "Data Structures");
        university.addCourse("CSC582", "OOAD");

        // Enrolling students in the courses
        university.enrollStudentInCourse(25, "CSC501");
        university.enrollStudentInCourse(26, "CSC311");
        university.enrollStudentInCourse(26, "CSC501");
        university.enrollStudentInCourse(27, "CSC311");
        university.enrollStudentInCourse(27, "CSC582");

        // Retrieving Student schedule and Course roster
        System.out.println("Dwight's Schedule: " + university.getStudentSchedule(26));
        System.out.println("Michael's Schedule: " + university.getStudentSchedule(25));
        System.out.println("Creed's Schedule: " + university.getStudentSchedule(27));
        System.out.println("CSC501 Roster: " + university.getCourseRoster("CSC501"));
        System.out.println("CSC311 Roster: " + university.getCourseRoster("CSC311"));
        System.out.println("CSC582 Roster: " + university.getCourseRoster("CSC582"));

        // Dropping a course
        university.dropStudentFromCourse(26, "CSC311");
        System.out.println("Dwight's Schedule: " + university.getStudentSchedule(26));


    }
}
