# Student Management System

This application is designed to manage the school application process from student registration. It covers four main entities: student, grade, fee, and sport.

## Features

1. **Student Registration:** Manages the registration process of students.
2. **Entities Covered:**
   - **Student:** Includes student details, the fees they are associated with, the grade they are in, and the sport selected by the student.
   - **Grade:** Contains grade information and the students associated with each grade.
   - **Fee:** Manages the fee details for each student.
   - **Sport:** Consists of different sports selected by students during registration.

## Prerequisites

- Install Maven and set the classpath for the system variable.

## Checking Maven Installation

To verify Maven is installed on your system, open the command prompt and run the following commands:

1. `mvn -v` - This checks the Maven version and verifies the installation.
2. `mvn clean install` - This command cleans and builds the project.
3. `mvn exec:java -Dexec.mainClass="Main"` - This command executes the Java program with the specified main class.
