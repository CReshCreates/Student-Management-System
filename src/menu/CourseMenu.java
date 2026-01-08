package menu;

import Model.Subjects;
import service.CourseSerivce;
import service.RegistrationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CourseMenu {
    private Scanner scanner = new Scanner(System.in);
    private CourseSerivce courseSerivce = new CourseSerivce();
    private RegistrationService regService = new RegistrationService();

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

        regService.registerNewCourse(courseName, deptName, subjects);
    }

    private void viewCourse(){
        courseSerivce.viewCourse();
    }

    private void updateCourse(){

    }

    private void deleteCourse(){

    }
}
