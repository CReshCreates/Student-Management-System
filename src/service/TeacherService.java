package service;

import Model.TeacherAndDepartment;
import Repository.TeacherRepository;
import util.DBUtil;

import java.util.List;

public class TeacherService {

    private final TeacherRepository teacherRepo = new TeacherRepository();
    private final DBUtil dbUtil = new DBUtil();

    public List<TeacherAndDepartment> getTeachersAndDepartments(int deptId){
        return teacherRepo.getTeacherAndBatch(dbUtil.connection(), deptId);
    }

    public void viewCourseAssignment(){

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
