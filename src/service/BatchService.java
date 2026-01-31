package service;

import Model.BatchInfo;
import Repository.BatchRepository;
import util.DBUtil;

import java.util.List;

public class BatchService {
    private final DBUtil dbUtil = new DBUtil();
    private final BatchRepository batchRepo = new BatchRepository();

    public BatchInfo getBatch(int batch, String program){
        return batchRepo.batchInfo(dbUtil.connection(), batch, program);
    }

    public void addBatch(int year, String program){
        batchRepo.regBatch(dbUtil.connection(), year, program);
    }

    public List<BatchInfo> getBatchInfo(){
        return batchRepo.getProgramAndBatch(dbUtil.connection());
    }
}
