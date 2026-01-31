package Model;

public class TeacherAssignment {
    int teacherAssignmentId;
    int teacherId;
    int subId;
    int batchId;
    String section;

    public TeacherAssignment(int teacherAssignmentId, int teacherId, int subId, int batchId, String section){
        this.teacherAssignmentId = teacherAssignmentId;
        this.teacherId = teacherId;
        this.subId = subId;
        this.batchId = batchId;
        this.section = section;
    }
}
