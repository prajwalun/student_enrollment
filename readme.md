# Data Structure Design

The "students" class is a Hashmap which has the studentID as the key and the student object as the value.

The "courses" class is an AVL tree with key-value pair that consists of the course code as the key and the course object with information like the name and studentIDs as the value.

# Enrollment Process

We first start by adding the students and courses to the system using the "addStudent" and "addCourse" methods. The student is enrolled into the course based on whether the studentID already exists in the course.
If the ID doesn't exist, the student is enrolled into the course and the course roster is updated by adding the student ID in the course object.
The roster is updated with the enrolled student ID's and the student object is updated with the course code.
