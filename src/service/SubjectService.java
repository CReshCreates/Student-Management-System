package service;

import Repository.SubjectRepository;
import util.DBUtil;

import java.sql.Connection;

public class SubjectService {
    private final DBUtil dbUtil = new DBUtil();
    private final SubjectRepository subRepo = new SubjectRepository();

    public boolean isSubjectAvailable(String subjectCode){
        Connection connection = dbUtil.connection();
        return subRepo.isAvailable(connection, subjectCode);
    }

    public int getSubjectId(String code){
        Connection connection = dbUtil.connection();
        return subRepo.getSubId(connection, code);
    }
}
