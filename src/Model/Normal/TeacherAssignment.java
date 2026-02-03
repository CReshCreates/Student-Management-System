package Model.Normal;

public class TeacherAssignment {
    int teacherAssignmentId;
    String subName;
    String courseName;
    int batch;
    String program;
    String section;


    public TeacherAssignment(int teacherAssignmentId, String subName, String courseName, int batch, String program, String section){
        this.teacherAssignmentId = teacherAssignmentId;
        this.subName = subName;
        this.courseName = courseName;
        this.batch = batch;
        this.program = program;
        this.section = section;
    }

    public String toString(){
        return "Teacher Assignment Id : " + teacherAssignmentId + " ,Subject Name: " + subName + " ,Course Name : " + courseName + " ,Batch : " + batch + " ,Program : " + program + " ,Section : " + section + "\n";
    }
}
