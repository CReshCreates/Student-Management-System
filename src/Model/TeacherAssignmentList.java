package Model;

import Model.Normal.BatchInfo;
import Model.Normal.Subjects;
import Model.Normal.TeacherAssignment;

import java.util.List;

public class TeacherAssignmentList {
    private List<BatchInfo> batchInfoList;
    private List<Subjects> subjectsList;
    private List<TeacherAssignment> teacherAssignmentList;

    public TeacherAssignmentList(List<BatchInfo> batchInfoList, List<Subjects> subjectsList, List<TeacherAssignment> teacherAssignmentList){
        this.batchInfoList = batchInfoList;
        this.subjectsList = subjectsList;
        this.teacherAssignmentList = teacherAssignmentList;
    }

    public List<BatchInfo> getBatchList(){
        return batchInfoList;
    }

    public List<Subjects> getSubjectsList(){
        return subjectsList;
    }

    public List<TeacherAssignment> getTeacherAssignmentList(){
        return teacherAssignmentList;
    }
}
