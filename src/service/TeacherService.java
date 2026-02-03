package service;

import Model.Normal.Teacher;
import Repository.TeacherRepository;
import util.DBUtil;

import java.util.List;

public class TeacherService {

    private final TeacherRepository teacherRepo = new TeacherRepository();
    private final DBUtil dbUtil = new DBUtil();

    public List<Teacher> getTeachersRelativeToDepartment(int deptId){
        return teacherRepo.getTeachersRelativeToDepartment(dbUtil.connection(), deptId);
    }

    public boolean isTeacherAvailable(int teacherId){
        return teacherRepo.isAvailable(dbUtil.connection(), teacherId);
    }

    public Teacher getTeacherInfoByUserId(int userId){
        return teacherRepo.getTeacherInfo(dbUtil.connection(), userId);
    }

    public void studentAttendance(){

    }

    public void getAttendance(){

    }

    public void assignAssignments(){

    }

    public void getSubmittedAssignments(){

    }

    public void assignLabAssignments(){

    }

    public void storeNotes(){

    }

    public void setMarks(){

    }
}
