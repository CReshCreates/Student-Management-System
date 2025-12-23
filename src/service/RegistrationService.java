package service;

import Model.User;
import Repository.*;
import util.DBUtil;
import util.PasswordUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class RegistrationService {
    private final UserRepository userRepo = new UserRepository();
    private final BatchRepository batchRepo = new BatchRepository();
    private final StudentRepository studentRepo = new StudentRepository();
    private final TeacherRepository teacherRepo = new TeacherRepository();
    private final DepartmentRepository departmentRepo = new DepartmentRepository();
    private final PasswordUtil hashed_password = new PasswordUtil();
    private final AdminRepository adminRepo = new AdminRepository();
    private final DBUtil dbUtil = new DBUtil();

    public boolean isUsernameAvailable(String username){
        try {
            User user = userRepo.findUserByName(username);
            return user == null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerStudent(String user_name, String password, String role, String full_name, String phone_number, String address, int year, String program, String section){
        Connection conn = null;
        try{
            conn = dbUtil.connection();
            conn.setAutoCommit(false);

            String reg_hashed_password = hashed_password.encryptPassword(password);

            int uId = userRepo.regUser(conn, user_name,reg_hashed_password,role);
            int batchId = batchRepo.regNewStudentBatch(conn, year, program, section);
            studentRepo.reg_students(conn, full_name, phone_number, address, uId, batchId);

            conn.commit();

        } catch (Exception e) {
            try{
                if(conn != null){
                    conn.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try{
                if(conn!= null){
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerTeacher(String user_name, String password, String role, String full_name, String phone_number, String address, String department){
        String reg_hashed_password = hashed_password.encryptPassword(password);
        Connection conn = null;

        try{
            conn = dbUtil.connection();
            conn.setAutoCommit(false);

            int uId = userRepo.regUser(conn, user_name,reg_hashed_password,role);
            int deptId = departmentRepo.getDeptId(conn, department);
            teacherRepo.regTeachers(conn, full_name, phone_number,address,uId,deptId);

            conn.commit();
        } catch (Exception e) {
            try{
                if(conn != null){
                    conn.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try{
                if(conn != null){
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerDepartment(String deptName){
        Connection conn = null;

        try{
            conn = dbUtil.connection();
            conn.setAutoCommit(false);

            departmentRepo.departmentRegistration(conn, deptName);

            conn.commit();

        } catch (Exception e) {
            if(conn != null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        finally{
            if(conn != null){
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void registerAdmin(String userName, String password, String role, String fullName){
        String hashedPassword = hashed_password.encryptPassword(password);
        Connection conn = null;
        try{
            conn = dbUtil.connection();
            conn.setAutoCommit(false);

            int u_id = userRepo.regUser(conn, userName, hashedPassword, role);
            adminRepo.registerAdmin(conn, fullName, u_id);

            conn.commit();

        } catch (Exception e) {
            try{
                if(conn != null){
                    conn.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        finally{
            try{
                if(conn != null){
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
