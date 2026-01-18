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
                case 3 -> updateCourse();
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
        boolean success = courseController.addCourse(courseName, deptName, subjects);
        if(success)
            System.out.println("Course added Successfully.");
    }

    private void viewCourse(){
        List<Course> courses = courseController.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        System.out.println("Courses:");
        courses.forEach(System.out::println);

        System.out.print("Enter Course ID to see subjects (or 0 to skip): ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        if (courseId != 0) {
            List<Subjects> subjects = courseController.getSubjectsByCourseId(courseId);
            if (subjects.isEmpty()) System.out.println("No subjects found.");
            else subjects.forEach(s -> System.out.println(
                    "Code: " + s.getCode() + ", Name: " + s.getSubName() + ", Sem: " + s.getSem()));
        }
    }

    private void updateCourse(){

    }

    private void deleteCourse(){

    }
}
