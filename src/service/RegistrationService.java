package service;

import Model.Normal.DeptInfo;
import Model.Normal.Subjects;
import Model.Normal.User;
import Repository.*;

import java.sql.SQLException;
import java.util.List;

public class RegistrationService {
    private final UserRepository userRepo = new UserRepository();
    private final StudentRepository studentRepo = new StudentRepository();
    private final TeacherRepository teacherRepo = new TeacherRepository();
    private final DepartmentRepository departmentRepo = new DepartmentRepository();
    private final AdminRepository adminRepo = new AdminRepository();
    private final SubjectRepository subRepo = new SubjectRepository();
    private final CourseRepository courseRepo = new CourseRepository();

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

    public void registerNewCourse(String courseName, String deptName, List<Subjects> subjects){
        TransactionManager.execute(conn ->{
            DeptInfo deptInfo = departmentRepo.getDept(conn, deptName);
            int deptId = deptInfo.getDeptId();
            int courseId = courseRepo.addCourse(conn, courseName, deptId);

            for(Subjects subject : subjects){
                subRepo.addSubject(conn, subject.getCode(), subject.getSubName(), subject.getSem(), courseId);
            }
            System.out.println("Course registration successful.");
        });
    }


}
