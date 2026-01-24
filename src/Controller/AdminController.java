package Controller;

import Model.BatchInfo;
import Model.DeptInfo;
import Model.UserRegInfo;
import service.BatchService;
import service.DepartmentService;
import service.RegistrationService;
import service.UserService;
import util.PasswordUtil;
import util.PasswordValidator;

import java.util.Scanner;

public class AdminController {

    private final BatchService batchService = new BatchService();
    private final RegistrationService registrationService = new RegistrationService();
    private final UserService userService = new UserService();
    private final DepartmentService deptService = new DepartmentService();
    private final PasswordValidator passwordValidate = new PasswordValidator();
    private final PasswordUtil passwordUtil = new PasswordUtil();


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
            year = scanner.nextInt();
            scanner.nextLine();

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

    public void viewAllUsers(){
        userService.viewAllUsers();
    }

    public void deleteUser(String userToBeDeleted){

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
