
# Student Enrollment 
class Student:
    def __init__(self, student_id, name):
        self.student_id = student_id
        self.name = name
        self.enrolled_courses = set()

    def enroll_in_course(self, course_code):
        self.enrolled_courses.add(course_code)

    def drop_course(self, course_code):
        self.enrolled_courses.discard(course_code)

#Course Enrollment
class Course:
    def __init__(self, course_code, course_name):
        self.course_code = course_code
        self.course_name = course_name
        self.enrolled_students = set()

    def enroll_student(self, student_id):
        self.enrolled_students.add(student_id)

    def drop_student(self, student_id):
        self.enrolled_students.discard(student_id)

#AVL Implementation 
class AVLNode:
    def __init__(self, key, value):
        self.key = key
        self.value = value
        self.left = None
        self.right = None
        self.height = 1

class AVLTree:
    def __init__(self):
        self.root = None

    def height(self, node):
        return 0 if node is None else node.height

    def balance_factor(self, node):
        return self.height(node.left) - self.height(node.right)

    def rotate_left(self, y):
        x = y.right
        t2 = x.left

        x.left = y
        y.right = t2

        y.height = max(self.height(y.left), self.height(y.right)) + 1
        x.height = max(self.height(x.left), self.height(x.right)) + 1

        return x

    def rotate_right(self, x):
        y = x.left
        t2 = y.right

        y.right = x
        x.left = t2

        x.height = max(self.height(x.left), self.height(x.right)) + 1
        y.height = max(self.height(y.left), self.height(y.right)) + 1

        return y

    def insert(self, key, value):
        self.root = self._insert(self.root, key, value)

    def _insert(self, node, key, value):
        if not node:
            return AVLNode(key, value)

        if key < node.key:
            node.left = self._insert(node.left, key, value)
        elif key > node.key:
            node.right = self._insert(node.right, key, value)
        else:
            return node  # Duplicate keys are not allowed

        node.height = max(self.height(node.left), self.height(node.right)) + 1

        balance = self.balance_factor(node)

        if balance > 1:
            if key < node.left.key:
                return self.rotate_right(node)
            else:
                node.left = self.rotate_left(node.left)
                return self.rotate_right(node)

        if balance < -1:
            if key > node.right.key:
                return self.rotate_left(node)
            else:
                node.right = self.rotate_right(node.right)
                return self.rotate_left(node)

        return node

    def search(self, key):
        return self._search(self.root, key)

    def _search(self, node, key):
        if not node:
            return None

        if key == node.key:
            return node.value
        elif key < node.key:
            return self._search(node.left, key)
        else:
            return self._search(node.right, key)

#University Info
class University:
    def __init__(self):
        self.students = {}
        self.courses = AVLTree()

    #Adding a student
    def add_student(self, student_id, name):
        if student_id not in self.students:
            self.students[student_id] = Student(student_id, name)

    #Adding a course
    def add_course(self, course_code, course_name):
        if self.courses.search(course_code) is None:
            self.courses.insert(course_code, Course(course_code, course_name))

    #Enroll a student in the course
    def enroll_student_in_course(self, student_id, course_code):
        student = self.students.get(student_id)
        course_info = self.courses.search(course_code)

        if student and course_info:
            student.enroll_in_course(course_code)
            course_info.enroll_student(student_id)
    
    #Drop a student from the course
    def drop_student_from_course(self, student_id, course_code):
        student = self.students.get(student_id)
        course_info = self.courses.search(course_code)

        if student and course_info and course_code in student.enrolled_courses:
            student.drop_course(course_code)
            course_info.drop_student(student_id)

    #Retrieve student schedule
    def get_student_schedule(self, student_id):
        student = self.students.get(student_id)
        return list(student.enrolled_courses) if student else []

    #Retrieve course information
    def get_course_roster(self, course_code):
        course_info = self.courses.search(course_code)
        return list(course_info.enrolled_students) if course_info else []

if __name__ == "__main__":
    university = University()

    #Adding students and courses
    university.add_student(25, "Michael Scott")
    university.add_student(26, "Dwight Schrute")
    university.add_course("CSC501", "Design and Analysis of Algorithms")
    university.add_course("CSC311", "Data Structures")

    #Enrolling students in the courses
    university.enroll_student_in_course(25, "CSC501")
    university.enroll_student_in_course(26, "CSC311")
    university.enroll_student_in_course(26, "CSC501")

    print("Dwight's Schedule:", university.get_student_schedule(26))
    print("Micahel's Schedule:", university.get_student_schedule(25))
    print("CSC501 Roster:", university.get_course_roster("CSC501"))
    print("CSC311 Roster:", university.get_course_roster("CSC311"))