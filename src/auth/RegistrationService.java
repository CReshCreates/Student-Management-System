package auth;

import Model.User;
import Repository.*;

import java.sql.SQLException;

public class RegistrationService {
    UserRepository userRepo = new UserRepository();
    public boolean isUsernameAvailable(String username){
        try {
            User user = userRepo.findUserByName(username);
            return user == null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerStudent(String user_name, String password, String role, String full_name, String phone_number, String address, int year, String program, String section){
        PasswordUtil hashed_password = new PasswordUtil();
        String reg_hashed_password = hashed_password.encryptPassword(password);

        UserRepository userRepo = new UserRepository();
        int uId = userRepo.regUser(user_name,reg_hashed_password,role);

        BatchRepository batchRepo = new BatchRepository();
        int batchId = batchRepo.regNewStudentBatch(year, program, section);

        StudentRepository studentRepo = new StudentRepository();
        studentRepo.reg_students(full_name, phone_number, address, uId, batchId);
    }

    public void registerTeacher(String user_name, String password, String role, String full_name, String phone_number, String address, String department){
        PasswordUtil hashed_password = new PasswordUtil();
        String reg_hashed_password = hashed_password.encryptPassword(password);

        UserRepository userRepo = new UserRepository();
        int uId = userRepo.regUser(user_name,reg_hashed_password,role);

        DepartmentRepository deptRepo = new DepartmentRepository();
        int deptId = deptRepo.getDeptId(department);

        TeacherRepository teacherRepo = new TeacherRepository();
        teacherRepo.regTeachers(full_name, phone_number,address,uId,deptId);
    }
}
