import java.util.*;

// Student Enrollment
class Student {
    private int studentId;
    private String name;
    private Set<String> enrolledCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new HashSet<>();
    }

    public void enrollInCourse(String courseCode) {
        enrolledCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        enrolledCourses.remove(courseCode);
    }
}

// Course Enrollment
class Course {
    private String courseCode;
    private String courseName;
    private Set<Integer> enrolledStudents;

    public Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.enrolledStudents = new HashSet<>();
    }

    public void enrollStudent(int studentId) {
        enrolledStudents.add(studentId);
    }

    public void dropStudent(int studentId) {
        enrolledStudents.remove(studentId);
    }
}

// AVL Implementation
class AVLNode {
    int key;
    String value;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int key, String value) {
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

    public void insert(int key, String value) {
        root = _insert(root, key, value);
    }

    private AVLNode _insert(AVLNode node, int key, String value) {
        if (node == null) {
            return new AVLNode(key, value);
        }

        if (key < node.key) {
            node.left = _insert(node.left, key, value);
        } else if (key > node.key) {
            node.right = _insert(node.right, key, value);
        } else {
            return node; 
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = balanceFactor(node);

        if (balance > 1) {
            if (key < node.left.key) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }

        if (balance < -1) {
            if (key > node.right.key) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    public String search(int key) {
        return _search(root, key);
    }

    private String _search(AVLNode node, int key) {
        if (node == null) {
            return null;
        }

        if (key == node.key) {
            return node.value;
        } else if (key < node.key) {
            return _search(node.left, key);
        } else {
            return _search(node.right, key);
        }
    }
}

// University Info
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
            courses.insert(courseCode.hashCode(), courseName);
        }
    }

    public void enrollStudentInCourse(int studentId, String courseCode) {
        Student student = students.get(studentId);
        String courseInfo = courses.search(courseCode.hashCode());

        if (student != null && courseInfo != null) {
            student.enrollInCourse(courseCode);
            courses.insert(courseCode.hashCode(), courseInfo);
        }
    }

    public void dropStudentFromCourse(int studentId, String courseCode) {
        Student student = students.get(studentId);
        String courseInfo = courses.search(courseCode.hashCode());

        if (student != null && courseInfo != null) {
            student.dropCourse(courseCode);
            courses.insert(courseCode.hashCode(), courseInfo);
        }
    }

    public List<String> getStudentSchedule(int studentId) {
        Student student = students.get(studentId);
        return (student != null) ? new ArrayList<>(student.getEnrolledCourses()) : new ArrayList<>();
    }

    public List<Integer> getCourseRoster(String courseCode) {
        String courseInfo = courses.search(courseCode.hashCode());
        return (courseInfo != null) ? new ArrayList<>(courses.getEnrolledStudents()) : new ArrayList<>();
    }
}

public class Main {
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