package Controller;

import Model.Normal.Teacher;
import Model.Normal.TeacherAssignment;
import service.TeacherAssignmentService;
import service.TeacherService;

import java.util.List;

public class TeacherController {

    private final TeacherService teacherService = new TeacherService();
    private final TeacherAssignmentService teacherAssignmentService = new TeacherAssignmentService();

    public List<TeacherAssignment> getCourseAssignment(int userId){
        Teacher teacherInfo = teacherService.getTeacherInfoByUserId(userId);
        return teacherAssignmentService.getSubjectAssignment(teacherInfo.getTeacherId());
    }

    public void takeStudentAttendance(){

    }

    public void getMyAttendance(){

    }

    public void assignAssignments(){

    }

    public void getSubmittedAssignments(){

    }

    public void assignLabAssignments(){

    }

    public void attachNotes(){

    }

    public void uploadMarks(){

    }
}
