package service;

import data.SalesHistoryDao;
import entities.SalesHistory;

import java.sql.SQLException;
import java.util.List;

public class SalesHistoryService {
    private final SalesHistoryDao salesHistoryDao;

    public SalesHistoryService() {
        this.salesHistoryDao = new SalesHistoryDao();
    }

    public List<SalesHistory> getSalesHistoryByPartnerId(int partnerId) throws SQLException{
        return salesHistoryDao.getSalesHistoryByPartnerId(partnerId);
    }
}