package service;

import Model.Course;
import Model.Subjects;
import Repository.CourseRepository;
import Repository.SubjectRepository;
import util.DBUtil;

import java.sql.Connection;
import java.util.List;

public class CourseService {
    private final CourseRepository courseRepo = new CourseRepository();
    private final SubjectRepository subjectRepo = new SubjectRepository();
    private final TransactionManager transactionManager = new TransactionManager();
    private DBUtil dbUtil = new DBUtil();


    public List<Course> viewCourse(){
        Connection conn = dbUtil.connection();
        return courseRepo.viewCourse(conn);
    }

    public List<Subjects> viewSubjects(int courseId){
        Connection conn = dbUtil.connection();
        return subjectRepo.viewSubjects(conn, courseId);
    }

    public boolean isCourseAvailable(int courseId){
            Connection conn = dbUtil.connection();
            return courseRepo.isCourseAvailable(conn, courseId);
    }

    public boolean updateCourseName(int courseId, String newName){
        Connection conn = dbUtil.connection();
        return courseRepo.updateCourseName(conn, courseId, newName);
    }

    public boolean updateCourseDuration(int courseId, int duration){
        Connection conn = dbUtil.connection();
        return courseRepo.updateCourseDuration(conn, courseId, duration);
    }

    public boolean updateSubjectName(String code, String newName){
        Connection conn = dbUtil.connection();
        return subjectRepo.updateSubjectName(conn, code, newName);
    }

    public boolean updateSubjectCode(String oldCode, String newCode){
        Connection conn = dbUtil.connection();
        return subjectRepo.updateSubjectCode(conn, oldCode, newCode);
    }

    public boolean updateSemester(String code, int semester){
        Connection conn = dbUtil.connection();
        return subjectRepo.updateSemester(conn, code, semester);
    }

    public boolean deleteCourse(int courseId){
        Connection conn = dbUtil.connection();
        return courseRepo.deleteCourse(conn, courseId);
    }
}
