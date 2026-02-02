package Model.Normal;

public class Course {
    String courseName;
    int courseId;

    public Course(String courseName, int courseId){
        this.courseName = courseName;
        this.courseId = courseId;
    }

    //getters
    public String getCourseName(){
        return courseName;
    }

    public int getCourseId(){
        return courseId;
    }

    public String toString(){
        return "Course Id: " + courseId + "\tCourse Name: " + courseName;
    }
}
