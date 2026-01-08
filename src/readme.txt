------------------------------------------------Student Management System-----------------------------------------------
How to run:
1. Create a database in MySQL using following codes.
    a. Database creation.
        CREATE DATABASE SIS;

    b. Table creation.
    !!!!!!!! Use SIS as default schema !!!!!!!!

    Just copy-paste the code below in your MySQL Workbench

    CREATE TABLE Users (
        u_id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL UNIQUE,
        password_hash VARCHAR(255) NOT NULL,
        role ENUM('ADMIN', 'STUDENT', 'TEACHER') NOT NULL
    );

    CREATE TABLE Admin (
        a_id INT AUTO_INCREMENT PRIMARY KEY,
        full_name VARCHAR(100) NOT NULL,
        u_id INT NOT NULL,
        FOREIGN KEY (u_id) REFERENCES Users(u_id)
            ON DELETE CASCADE
    );

    CREATE TABLE Department (
        dept_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL UNIQUE
    );

    CREATE TABLE Batch (
        batch_id INT AUTO_INCREMENT PRIMARY KEY,
        year INT NOT NULL,
        program VARCHAR(100) NOT NULL,
        section VARCHAR(10) NOT NULL
    );

    CREATE TABLE Students (
        s_id INT AUTO_INCREMENT PRIMARY KEY,
        full_name VARCHAR(100) NOT NULL,
        phone_number VARCHAR(20),
        address VARCHAR(255),
        u_id INT NOT NULL UNIQUE,
        batch_id INT NOT NULL,
        FOREIGN KEY (u_id) REFERENCES Users(u_id)
            ON DELETE CASCADE,
        FOREIGN KEY (batch_id) REFERENCES Batch(batch_id)
            ON DELETE RESTRICT
    );

    CREATE TABLE Teachers (
        t_id INT AUTO_INCREMENT PRIMARY KEY,
        full_name VARCHAR(100) NOT NULL,
        phone_number VARCHAR(20),
        address VARCHAR(255),
        u_id INT NOT NULL UNIQUE,
        dept_id INT NOT NULL,
        FOREIGN KEY (u_id) REFERENCES Users(u_id)
            ON DELETE CASCADE,
        FOREIGN KEY (dept_id) REFERENCES Department(dept_id)
            ON DELETE RESTRICT
    );

    CREATE TABLE Course (
        course_id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        dept_id INT NOT NULL,
        FOREIGN KEY (dept_id) REFERENCES Department(dept_id)
            ON DELETE RESTRICT
    );

    CREATE TABLE Subjects (
        sub_id INT AUTO_INCREMENT PRIMARY KEY,
        code VARCHAR(20) NOT NULL UNIQUE,
        name VARCHAR(100) NOT NULL,
        semester INT NOT NULL,
        course_id INT NOT NULL,
        FOREIGN KEY (course_id) REFERENCES Course(course_id)
            ON DELETE CASCADE
    );

    CREATE TABLE Teacher_Assignment (
        ta_id INT AUTO_INCREMENT PRIMARY KEY,
        t_id INT NOT NULL,
        sub_id INT NOT NULL,
        batch_id INT NOT NULL,
        FOREIGN KEY (t_id) REFERENCES Teachers(t_id)
            ON DELETE CASCADE,
        FOREIGN KEY (sub_id) REFERENCES Subjects(sub_id)
            ON DELETE CASCADE,
        FOREIGN KEY (batch_id) REFERENCES Batch(batch_id)
            ON DELETE CASCADE,
        UNIQUE (t_id, sub_id, batch_id)
    );

    CREATE TABLE Attendance (
        att_id INT AUTO_INCREMENT PRIMARY KEY,
        s_id INT NOT NULL,
        ta_id INT NOT NULL,
        date DATE NOT NULL,
        status ENUM('PRESENT', 'ABSENT') NOT NULL,
        FOREIGN KEY (s_id) REFERENCES Students(s_id)
            ON DELETE CASCADE,
        FOREIGN KEY (ta_id) REFERENCES Teacher_Assignment(ta_id)
            ON DELETE CASCADE,
        UNIQUE (s_id, ta_id, date)
    );

    CREATE TABLE Exams (
        exam_id INT AUTO_INCREMENT PRIMARY KEY,
        term VARCHAR(50) NOT NULL,
        ta_id INT NOT NULL,
        FOREIGN KEY (ta_id) REFERENCES Teacher_Assignment(ta_id)
            ON DELETE CASCADE
    );

    CREATE TABLE Result (
        result_id INT AUTO_INCREMENT PRIMARY KEY,
        s_id INT NOT NULL,
        exam_id INT NOT NULL,
        marks DECIMAL(5,2) NOT NULL,
        FOREIGN KEY (s_id) REFERENCES Students(s_id)
            ON DELETE CASCADE,
        FOREIGN KEY (exam_id) REFERENCES Exams(exam_id)
            ON DELETE CASCADE,
        UNIQUE (s_id, exam_id)
    );

    CREATE TABLE Notes (
        notes_id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(150) NOT NULL,
        path VARCHAR(255) NOT NULL,
        upload_date DATE NOT NULL,
        ta_id INT NOT NULL,
        uploaded_by INT NOT NULL,
        FOREIGN KEY (ta_id) REFERENCES Teacher_Assignment(ta_id)
            ON DELETE CASCADE,
        FOREIGN KEY (uploaded_by) REFERENCES Users(u_id)
            ON DELETE CASCADE
    );

2. Admin Registration
    SHA-256 encryption website and encrypt your password. (https://emn178.github.io/online-tools/sha256.html)
    Copy-paste the below code replacing username and password.

    INSERT INTO users (username, pashword_hash, role) VALUES (your_username, your_encrypted_password, admin);

3. Environment Setup
    a. Open cmd and type:
    setx DB_USER "root"
    setx DB_Pass "your_database_password"

4. Clone the repository and run. Use your userId and password to login.