package Controller;

import Model.Course;
import Model.Subjects;
import service.CourseService;
import service.RegistrationService;

import java.util.List;

public class CourseController {

    private final RegistrationService registrationService = new RegistrationService();
    private final CourseService courseService = new CourseService();

    /*public boolean addCourse(String courseName, String deptName, List<Subjects> subjects){
        try{
            registrationService.registerNewCourse(courseName, deptName, subjects);
            return true;
        }catch(Exception e){
            return false;
        }
    }

     */

    public List<Course> getAllCourses(){
        return courseService.viewCourse();
    }

    public List<Subjects> getSubjectsByCourseId(int courseId){
        return courseService.viewSubjects(courseId);
    }

    public boolean isCourseAvailable(int courseId){
        return courseService.isCourseAvailable(courseId);
    }

    public boolean updateCourseName(int courseId, String newCourseName){
        try{
            return courseService.updateCourseName(courseId, newCourseName);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateCourseDuration(int courseId, int courseDuration){
        try{
            return courseService.updateCourseDuration(courseId, courseDuration);
        }catch(Exception e){
            return false;
        }
    }

    public boolean updateSubjectName(String code, String newName){
        try{
            return courseService.updateSubjectName(code, newName);
        }catch(Exception e){
            return false;
        }
    }

    public boolean updateSubjectCode(String oldCode, String newCode){
        try{
            return courseService.updateSubjectCode(oldCode, newCode);
        }catch(Exception e){
            return false;
        }
    }

    public boolean updateSemester(String code, int semester){
        try{
            return courseService.updateSemester(code, semester);
        }catch(Exception e){
            return false;
        }
    }

    public boolean deleteCourse(int courseId){
        try{
            return courseService.deleteCourse(courseId);
        }catch(Exception e){
            return false;
        }
    }
}
