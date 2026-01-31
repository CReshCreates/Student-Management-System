package menu;

import Controller.CourseController;
import Model.Course;
import Model.Subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CourseMenu {
    private Scanner scanner = new Scanner(System.in);
    private CourseController courseController = new CourseController();

    public void show(){
        while(true){
            System.out.println("--------Course Management--------");
            System.out.println("1. Add Course.");
            System.out.println("2. View Course.");
            System.out.println("3. Update Course.");
            System.out.println("4. Delete Course.");
            System.out.println("5. Back");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1 -> addCourse();
                case 2 -> viewCourse();
                case 3 -> updateCourseMenu();
                case 4 -> deleteCourse();
                case 5 -> new AdminMenu().show();
                default -> System.out.println("Invalid Choice!");
            }
        }
    }

    private void addCourse(){
        System.out.println("Course Name:");
        String courseName = scanner.nextLine();


        System.out.println("Assign to department:");
        String deptName = scanner.nextLine();
        System.out.println("Enter number of subjects:");
        int subjectCount = scanner.nextInt();
        scanner.nextLine();

        List<Subjects> subjects = new ArrayList<>();

        for(int i = 0; i < subjectCount; i++){
            System.out.println("Enter subject name:");
            String subName = scanner.nextLine();
            System.out.println("Subject Code:");
            String subCode = scanner.nextLine();
            System.out.println("Enter the semester to which the subject is going to be taught:");
            int semester = scanner.nextInt();
            scanner.nextLine();

            subjects.add(new Subjects(subCode, subName, semester));
        }
        /*boolean success = courseController.addCourse(courseName, deptName, subjects);
        if(success)
            System.out.println("Course added Successfully.");

         */
    }

    private void viewCourse(){
        List<Course> courses = courseController.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("Courses:");
        courses.forEach(System.out::println);

        System.out.print("Enter Course Name to see subjects (or EXIT to skip): ");
        String courseName = scanner.nextLine().toUpperCase();
        if (!courseName.equals("EXIT")) {
            List<Subjects> subjects = courseController.getSubjectsByCourseName(courseName);
            if (subjects.isEmpty()) System.out.println("No subjects found.");
            else subjects.forEach(s -> System.out.println(
                    "Code: " + s.getCode() + ", Name: " + s.getSubName() + ", Sem: " + s.getSem()));
        }
    }

    private void updateCourseMenu() {
        System.out.println("-------- Welcome to Course Update Menu --------");

        int courseId;
        while (true) {
            System.out.print("Enter the course id to update: ");
            if (scanner.hasNextInt()) {
                courseId = scanner.nextInt();
                scanner.nextLine();

                if (courseController.isCourseAvailable(courseId)) {
                    break;
                } else {
                    System.out.println("Course not available. Please try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a numeric course ID.");
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("\n-------- Update Course Menu --------");
            System.out.println("1. Update course name");
            System.out.println("2. Update course duration");
            System.out.println("3. Update subject name");
            System.out.println("4. Update subject code");
            System.out.println("5. Update semester");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume leftover newline
            } else {
                System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                scanner.nextLine(); // discard invalid input
                continue;
            }

            switch (choice) {
                case 1 -> updateCourseName(courseId);
                case 2 -> updateCourseDuration(courseId);
                case 3 -> updateSubjectName();
                case 4 -> updateSubjectCode();
                case 5 -> updateSemester();
                case 6 -> {
                    System.out.println("Returning to previous menu...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        }
    }


    private void deleteCourse(){
        boolean success = false;
        while(!success){
            System.out.println("Enter the course id to delete: ");
            int courseId = scanner.nextInt();
            scanner.nextLine();

            if(!courseController.isCourseAvailable(courseId)){
                System.out.println("Course not available. Please try again.");
                continue;
            }

            success = courseController.deleteCourse(courseId);
            System.out.println(success ? "Course deleted successfully." : "Course deletion failed.");
        }
    }

    public void updateCourseName(int courseId){
        System.out.println("Enter new course name: ");
        String courseName = scanner.nextLine();

        boolean success = courseController.updateCourseName(courseId, courseName);
        System.out.println(success ? "Updated Successfully." : "Update failed.");
    }

    public void updateCourseDuration(int courseId){
        System.out.println("Enter new course duration: ");
        int duration = scanner.nextInt();

        boolean success = courseController.updateCourseDuration(courseId, duration);
        System.out.println(success ? "Updated Successfully." : "Update failed.");
    }

    public void updateSubjectName(){
        System.out.println("Enter subject code: ");
        String code = scanner.nextLine();
        System.out.println("Enter new subject name: ");
        String newName = scanner.nextLine();

        boolean success = courseController.updateSubjectName(code, newName);
        System.out.println(success ? "Updated Successfully." : "Update failed.");
    }

    public void updateSubjectCode(){
        System.out.println("Enter old subject code: ");
        String oldCode = scanner.nextLine();
        System.out.println("Enter new subject code: ");
        String newCode = scanner.nextLine();

        boolean success = courseController.updateSubjectCode(oldCode, newCode);
        System.out.println(success ? "Updated Successfully." : "Update failed.");
    }

    public void updateSemester(){
        System.out.println("Enter subject code: ");
        String code = scanner.nextLine();
        System.out.println("Enter updated semester");
        int newSem = scanner.nextInt();
        scanner.nextLine();

        boolean success = courseController.updateSemester(code, newSem);
        System.out.println(success ? "Updated Successfully" : "Update failed.");
    }
}
