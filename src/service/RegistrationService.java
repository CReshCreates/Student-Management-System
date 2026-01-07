package service;

import Model.Subjects;
import Model.User;
import Repository.*;
import util.PasswordUtil;
import util.PasswordValidator;
import java.sql.SQLException;
import java.util.List;

public class RegistrationService {
    private final UserRepository userRepo = new UserRepository();
    private final BatchRepository batchRepo = new BatchRepository();
    private final StudentRepository studentRepo = new StudentRepository();
    private final TeacherRepository teacherRepo = new TeacherRepository();
    private final DepartmentRepository departmentRepo = new DepartmentRepository();
    private final PasswordUtil hashed_password = new PasswordUtil();
    private final AdminRepository adminRepo = new AdminRepository();
    private final PasswordValidator passwordValidate = new PasswordValidator();
    private final CourseRepository courseRepository = new CourseRepository();
    private final SubjectRepository subRepo = new SubjectRepository();

    public boolean isUsernameAvailable(String username){
        try {
            User user = userRepo.findUserByName(username);
            return user == null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerStudent(String user_name, String password, String full_name, String phone_number, String address, int year, String program, String section){
        transactionManager.execute(conn -> {
            String hashedPassword = validateAndHash(password);

            int userId = userRepo.regUser(conn, user_name, hashedPassword, "STUDENT");
            int batchId = batchRepo.regNewStudentBatch(conn, year, program, section);
            studentRepo.reg_students(conn, full_name,phone_number, address, userId, batchId);
        });
    }

    public void registerTeacher(String user_name, String password, String full_name, String phone_number, String address, String department){
        transactionManager.execute(conn -> {
            String hashedPassword = validateAndHash(password);

            int userId = userRepo.regUser(conn, user_name, hashedPassword, "TEACHER");
            int deptId = departmentRepo.getDeptId(conn, department);
            teacherRepo.regTeachers(conn, full_name, phone_number, address, userId, deptId);
        });
    }

    public void registerDepartment(String deptName){
        transactionManager.execute(conn -> {
            departmentRepo.departmentRegistration(conn, deptName);
        });
    }

    public void registerAdmin(String userName, String password,  String fullName){
        transactionManager.execute(conn -> {

            String hashedPassword = validateAndHash(password);
            int userId = userRepo.regUser(conn, userName, hashedPassword,"ADMIN");
            adminRepo.registerAdmin(conn, fullName, userId);

        });
    }

    public void registerNewCourse(String courseName, String deptName, List<Subjects> subjects){
        transactionManager.execute(conn ->{
            int deptId = departmentRepo.getDeptId(conn, deptName);
            int courseId = courseRepository.addCourse(conn, courseName, deptId);

            for(Subjects subject : subjects){
                subRepo.addSubject(conn, subject.getCode(), subject.getSubName(), subject.getSem(), courseId);
            }
            System.out.println("Course registration successful.");
        });
    }

    private String validateAndHash(String password){
        passwordValidate.passwordConfirmation(password);
        passwordValidate.validate(password);
        return hashed_password.encryptPassword(password);
    }
}
