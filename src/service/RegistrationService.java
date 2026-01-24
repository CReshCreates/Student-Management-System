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

    public void registerStudent(String user_name, String full_name, String phone_number, String address, String section, int batchId, String hashedPassword){
        TransactionManager.execute(conn -> {
            int userId = userRepo.regUser(conn, user_name, hashedPassword, "STUDENT");
            studentRepo.reg_students(conn, full_name,phone_number, address, userId, batchId, section);
        });
    }

    public void registerTeacher(String user_name, String full_name, String phone_number, String address, int deptId, String hashedPassword){
        TransactionManager.execute(conn -> {

            int userId = userRepo.regUser(conn, user_name, hashedPassword, "TEACHER");
            teacherRepo.regTeachers(conn, full_name, phone_number, address, userId, deptId);
        });
    }

    public void registerDepartment(String deptName){
        TransactionManager.execute(conn -> {
            departmentRepo.departmentRegistration(conn, deptName);
        });
    }

    public void registerAdmin(String userName,  String fullName, String hashedPassword){
        TransactionManager.execute(conn -> {
            int userId = userRepo.regUser(conn, userName, hashedPassword,"ADMIN");
            adminRepo.registerAdmin(conn, fullName, userId);

        });
    }

    /*public void registerNewCourse(String courseName, String deptName, List<Subjects> subjects){
        TransactionManager.execute(conn ->{
            int deptId = departmentRepo.getDeptId(conn, deptName);
            int courseId = courseRepository.addCourse(conn, courseName, deptId);

            for(Subjects subject : subjects){
                subRepo.addSubject(conn, subject.getCode(), subject.getSubName(), subject.getSem(), courseId);
            }
            System.out.println("Course registration successful.");
        });
    }

     */
}
