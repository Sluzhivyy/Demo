package service;

import data.PartnerDao;
import entities.Partner;

import java.sql.SQLException;
import java.util.List;

public class PartnerService {

    private final PartnerDao partnerDao;

    public PartnerService() {
        this.partnerDao = new PartnerDao();
    }

    public List<Partner> getAllPartners() throws SQLException {
        return partnerDao.getAllPartners();
    }
    public Partner getPartnerById(int id) throws SQLException {
        return partnerDao.getPartnerById(id);
    }
    public boolean addPartner(Partner partner) throws SQLException {
        return partnerDao.addPartner(partner);
    }

    public boolean updatePartner(Partner partner) throws SQLException{
        return partnerDao.updatePartner(partner);
    }
    public boolean deletePartner(int id) throws SQLException{
        return partnerDao.deletePartner(id);
    }
}
