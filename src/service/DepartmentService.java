package service;

import Model.DeptInfo;
import Repository.DepartmentRepository;
import util.DBUtil;

import java.sql.Connection;

public class DepartmentService {
    private final DepartmentRepository deptRepo = new DepartmentRepository();
    private final DBUtil dbUtil = new DBUtil();

    public DeptInfo getDepartment(String department){
        Connection conn = dbUtil.connection();
        return deptRepo.getDeptId(conn, department);
    }
}
