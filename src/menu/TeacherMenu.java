package menu;

import Controller.PasswordController;
import Controller.TeacherController;
import session.Session;

import java.util.Scanner;

public class TeacherMenu {
    private final TeacherController teacherController = new TeacherController();

    public void show(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------Welcome to Teacher Menu--------");
        System.out.println("1. View course assignment.");
        System.out.println("2. Take student's attendance.");
        System.out.println("3. View my attendance record.");
        System.out.println("4. Assign assignments to students.");
        System.out.println("5. View student's submitted assignments.");
        System.out.println("6. Assign lab assignments.");
        System.out.println("7. Attach notes.");
        System.out.println("8. Upload marks.");
        System.out.println("9. Change Password.");
        System.out.println("10. Logout.");
        System.out.println("Enter your choice:");

        int choice = scanner.nextInt();

        switch(choice){
            case 1 -> teacherController.getCourseAssignment();
            case 2 -> teacherController.takeStudentAttendance();
            case 3 -> teacherController.getMyAttendance();
            case 4 -> teacherController.assignAssignments();
            case 5 -> teacherController.getSubmittedAssignments();
            case 6 -> teacherController.assignLabAssignments();
            case 7 -> teacherController.attachNotes();
            case 8 -> teacherController.uploadMarks();
            case 9 -> new PasswordMenu().show();
            case 10 -> {
                Session.logout();
                System.out.println("Logged Out Successfully!!!");
                return;
            }
        }
    }
}
