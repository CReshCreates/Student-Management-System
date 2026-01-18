package Controller;

import Model.Course;
import Model.Subjects;
import service.CourseService;
import service.RegistrationService;

import java.util.List;

public class CourseController {

    private final RegistrationService registrationService = new RegistrationService();
    private final CourseService courseService = new CourseService();

    public boolean addCourse(String courseName, String deptName, List<Subjects> subjects){
        try{
            registrationService.registerNewCourse(courseName, deptName, subjects);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public List<Course> getAllCourses(){
        return courseService.viewCourse();
    }

    public List<Subjects> getSubjectsByCourseId(int courseId){
        return courseService.viewSubjects(courseId);
    }
}
