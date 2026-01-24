package Model;

public class DeptInfo {
    int deptId;
    String deptName;

    public DeptInfo(int deptId, String deptName){
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public int getDeptId(){
        return deptId;
    }

    public String getDeptName(){
        return deptName;
    }
}
