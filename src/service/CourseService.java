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
    private DBUtil dbUtil = new DBUtil();

    public List<Course> viewCourse(){
        Connection conn = dbUtil.connection();
        return courseRepo.viewCourse(conn);
    }

    public List<Subjects> viewSubjects(int courseId){
        Connection conn = dbUtil.connection();
        return subjectRepo.viewSubjects(conn, courseId);
    }
}
