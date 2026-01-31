package Controller;

import Model.*;
import service.*;
import util.PasswordUtil;
import util.PasswordValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController {

    private final BatchService batchService = new BatchService();
    private final RegistrationService registrationService = new RegistrationService();
    private final UserService userService = new UserService();
    private final DepartmentService deptService = new DepartmentService();
    private final PasswordValidator passwordValidate = new PasswordValidator();
    private final PasswordUtil passwordUtil = new PasswordUtil();
    private final TeacherService teacherService = new TeacherService();
    private final CourseService courseService = new CourseService();
    private final TeacherAssignmentService teacherAssignmentService = new TeacherAssignmentService();


    //Validations

    public String roleCheck(Scanner scanner, String role){
        while (!role.equals("ADMIN") && !role.equals("TEACHER") && !role.equals("STUDENT")) {
            System.out.println("Invalid role. Try again:");
            role = scanner.nextLine().toUpperCase();
        }
        return role;
    }

    public String usernameValidation(Scanner scanner, String username){
        while(!registrationService.isUsernameAvailable(username)){
            System.out.println("Username already taken. Enter another.");
            username = scanner.nextLine();
        }
        return username;
    }

    public BatchInfo validateBatchAndProgram(Scanner scanner, int year, String program){
        BatchInfo batchInfo = batchService.getBatch(year, program);
        while(batchInfo == null){
            System.out.println("Either program or batch not found.");
            System.out.println("Enter a valid batch: ");
            year = readInt(scanner);

            System.out.println("Enter a valid program for that batch: ");
            program = scanner.nextLine();

            batchInfo = batchService.getBatch(year, program);
        }
        return batchInfo;
    }


    public DeptInfo departmentValidation(Scanner scanner, String department){
        DeptInfo deptInfo = deptService.getDepartment(department);
        while(deptInfo == null){
            System.out.println("Department not available. Enter a valid department: ");
            department =scanner.nextLine();

            deptInfo = deptService.getDepartment(department);
        }
        return deptInfo;
    }

    public void createNewBatchWithValidation(Scanner scanner, int year, String program) {
        while (batchService.getBatch(year, program) != null) {
            System.out.println("Batch with this program already exists.");

            System.out.print("Enter new batch year: ");
            year = readInt(scanner);

            System.out.print("Enter new program: ");
            program = scanner.nextLine();
        }
        batchService.addBatch(year, program);
    }


    //Registrations

    public void registerStudent(UserRegInfo regInfo, BatchInfo batchInfo){
        registrationService.registerStudent(
                regInfo.getUserName(),
                regInfo.getFullName(),
                regInfo.getPhoneNumber(),
                regInfo.getAddress(),
                regInfo.getSection(),
                batchInfo.getBatchId(),
                encryptPassword(regInfo.getPassword())
        );
    }

    public void registerTeacher(UserRegInfo regInfo, DeptInfo deptInfo){
        registrationService.registerTeacher(
                regInfo.getUserName(),
                regInfo.getFullName(),
                regInfo.getPhoneNumber(),
                regInfo.getAddress(),
                deptInfo.getDeptId(),
                encryptPassword(regInfo.getPassword())
        );
    }

    public void registerAdmin(UserRegInfo regInfo){
        registrationService.registerAdmin(
                regInfo.getUserName(),
                regInfo.getFullName(),
                encryptPassword(regInfo.getPassword())
        );
    }

    public void registerDepartment(String deptName){
        registrationService.registerDepartment(deptName);
    }


    //Other Operations

    public List<UserView>viewAllUsers(){
        return userService.viewAllUsers();
    }

    public void viewAllDepartment(){
        deptService.viewAllDepartment();
    }

    public void deleteUser(String userToBeDeleted){
        userService.deleteUser(userToBeDeleted);
    }

    /* public List<TeacherAssignmentList> teacherAssignmentList(String courseName) {
        List<Subjects> subjectsList = courseService.viewSubjects(courseName);
        List<BatchInfo> batchInfoList = batchService.getBatchInfo();
        List<TeacherAssignment> teacherAssignmentList = teacherAssignmentService.getTeacherAssignment();

        TeacherAssignmentList teacherAssignmentList1 = new TeacherAssignmentList(batchInfoList, subjectsList, teacherAssignmentList);

        List<TeacherAssignmentList> result = new ArrayList<>();
        result.add(teacherAssignmentList1);

        return result;
    }


     */
    public List<TeacherAndDepartment> getTeacherAndDepartment(int deptId){
        return teacherService.getTeachersAndDepartments(deptId);
    }

    //Util

    public String validate(String password){
        password = passwordValidate.passwordConfirmation(password);
        return passwordValidate.validate(password);
    }

    public String encryptPassword(String password){
        return passwordUtil.encryptPassword(password);
    }

    private int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a valid number:");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
