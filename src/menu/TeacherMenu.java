package menu;

import Controller.TeacherController;
import Model.Normal.TeacherAssignment;
import Model.Normal.User;
import Session.Session;
import util.ReadInteger;
import util.ReturnToMenuUsingAnyKey;

import java.util.List;
import java.util.Scanner;

public class TeacherMenu {
    private final TeacherController teacherController = new TeacherController();
    private final ReadInteger readInteger = new ReadInteger();
    private final ReturnToMenuUsingAnyKey returnToMenuUsingAnyKey = new ReturnToMenuUsingAnyKey();

    public void show(Scanner scanner){

        while(true){
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

            int choice = readInteger.readInt(scanner);

            switch(choice){
                case 1 -> courseAssignmentView();
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

    public void courseAssignmentView(){
        User user = Session.getCurrentUser();
        System.out.println("Welcome " + user.getName());
        System.out.println("Your subject assignments are: ");

        List<TeacherAssignment> teacherAssignments = teacherController.getCourseAssignment(user.getUserId());

        teacherAssignments.forEach(System.out::println);
        returnToMenuUsingAnyKey.pressAnyKeyToContinue();
    }
}
