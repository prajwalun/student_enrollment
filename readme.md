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

# Generating Rosters

To generate a roster of enrolled student, the group implemtend a HashMap to efficiently store student recorsd with their student IDs as keys and student objects as values. Using a HashMap also optimizes qucik insertions and lookups. 

# Student Schedules

Using a HashMap allows students to view their schedule with efficiently. We were able to store students schdule as a ArrayList and represented it as a collection of course codes and the students IDs. Using this method allows the program to become efficient with inserting, removing, and querying course information. 

# Conflict Resolution

To prevent students from enrolling in course with overlapping schedules, it is important to have a Data Structure implemented to the code. For instance, using a AVL Tree where the keys are the course IDs and the values are the coruse name. Another thing to prevent this from happening is by validating the coruse schedule against thier existing schedule. 

# Efficiency Considerations 

The group was tasked to use AVL Tree and HashMaps in creating a Student Enrollment and Course project. By spliting the tasks of enrolling students, generating a roster, etc, between HashMaps and AVL Tree, this allows the project to become far more efficient and manageable when changes are want to be made. Xxploit the qualities of both data structure. Hash maps succeed at quick queries and additions, making them ideal for understudy and course the executives, while AVL trees are superb for keeping arranged control and can be utilized for creating lists and settling clashes effectively.
