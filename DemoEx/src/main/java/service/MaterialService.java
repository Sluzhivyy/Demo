package service;

import data.MaterialDao;
import entities.Material;

import java.sql.SQLException;

public class MaterialService {
    private final MaterialDao materialDao;
    public MaterialService(){
        this.materialDao = new MaterialDao();
    }
    public Material getMaterialById(int id) throws SQLException{
        return materialDao.getMaterialById(id);
    }
}
