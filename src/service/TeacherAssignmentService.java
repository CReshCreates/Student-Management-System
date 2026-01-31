package service;

import Model.TeacherAssignment;
import Repository.TeacherAssignmentRepository;
import util.DBUtil;

import java.util.List;

public class TeacherAssignmentService {

    private final TeacherAssignmentRepository teacherAssignmentRepository = new TeacherAssignmentRepository();
    private final DBUtil dbUtil = new DBUtil();

    /* public List<TeacherAssignment> getTeacherAssignment(){
        return teacherAssignmentRepository.getAllTeacherAssignments(dbUtil.connection());
    }
    */
}
