package service;

import Repository.TeacherAssignmentRepository;

public class TeacherAssignmentService {

    private final TeacherAssignmentRepository teacherAssignmentRepository = new TeacherAssignmentRepository();

    public void assignSubjectToTeachers(int teacherId, int subjectId, int batchId, String[] section, int noOfSection){
        TransactionManager.execute(conn -> {
            for(int i = 0; i< noOfSection; i++){
                teacherAssignmentRepository.assignSubject(conn, teacherId, subjectId, batchId, section[i]);
            }
        });
    }
}
