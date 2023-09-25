# Data Structure Design

The "students" class is a Hashmap which has the studentID as the key and the student object as the value.

The "courses" class is an AVL tree with key-value pair that consists of the course code as the key and the course object with information like the name and studentIDs as the value.

# Enrollment Process

We first start by adding the students and courses to the system using the "addStudent" and "addCourse" methods. The student is enrolled into the course based on whether the studentID already exists in the course.
If the ID doesn't exist, the student is enrolled into the course and the course roster is updated by adding the student ID in the course object.
The roster is updated with the enrolled student ID's and the student object is updated with the course code.

# Registration Process

The student adds a course using the course code, the first step would be to check if the course exists, if it does, the student is enroleld into the course. This is done by adding the student into the course object that consists of the list of student ID's and the course code is added to the student object which consists of the list of course codes.

The student drops a course using the course code, the way this works is by checking if the studentID is present in the course object, if it does, the student ID is removed from the course object and the course code is removed from the list of courses in the student object.

The use of data structures to store these info and validating the data along with error checking is an efficient way to handle these operations.
