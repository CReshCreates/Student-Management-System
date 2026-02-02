package Model.Joins;

public class TeacherAndDepartment {
    int t_id;
    String fullName;
    int deptId;

    public TeacherAndDepartment(int t_id, String fullName, int deptId){
        this.t_id = t_id;
        this.fullName = fullName;
        this.deptId = deptId;
    }
}
