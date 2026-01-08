package service;

import Model.Course;
import Repository.CourseRepository;

import java.util.List;

public class CourseSerivce {
    private final CourseRepository courseRepo = new CourseRepository();

    public void viewCourse(){
        transactionManager.execute(conn -> {
            List<Course> courses = courseRepo.viewCourse(conn);
            courses.forEach(System.out::println);
        });
    }
}
