package service;

import Model.Normal.TeacherAssignment;
import Repository.TeacherAssignmentRepository;
import util.DBUtil;

import java.util.List;

public class TeacherAssignmentService {

    private final TeacherAssignmentRepository teacherAssignmentRepository = new TeacherAssignmentRepository();
    private final DBUtil dbUtil = new DBUtil();

    public void assignSubjectToTeachers(int teacherId, int subjectId, int batchId, String[] section, int noOfSection){
        TransactionManager.execute(conn -> {
            for(int i = 0; i< noOfSection; i++){
                teacherAssignmentRepository.assignSubject(conn, teacherId, subjectId, batchId, section[i]);
            }
        });
    }

    public List<TeacherAssignment> getSubjectAssignment(int teacherId){
        return teacherAssignmentRepository.getSubjectAssignment(dbUtil.connection(), teacherId);
    }
}
