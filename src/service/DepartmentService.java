package service;

import Model.Normal.DeptInfo;
import Repository.DepartmentRepository;
import util.DBUtil;

import java.sql.Connection;
import java.util.List;

public class DepartmentService {
    private final DepartmentRepository deptRepo = new DepartmentRepository();
    private final DBUtil dbUtil = new DBUtil();

    public DeptInfo getDepartment(String department){
        Connection conn = dbUtil.connection();
        return deptRepo.getDept(conn, department);
    }

    public List<DeptInfo> getDeptInfo(){
        Connection conn = dbUtil.connection();
        return deptRepo.getAllDepartInfo(conn);
    }
}
