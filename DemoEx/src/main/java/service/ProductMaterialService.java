package service;

import data.ProductMaterialDao;
import entities.ProductMaterial;

import java.sql.SQLException;

public class ProductMaterialService {
    private final ProductMaterialDao productMaterialDao;
    public ProductMaterialService(){
        this.productMaterialDao = new ProductMaterialDao();
    }
    public ProductMaterial getProductMaterialByProductAndMaterialId(int productId, int materialId) throws SQLException {
        return productMaterialDao.getProductMaterialByProductAndMaterialId(productId, materialId);
    }
}
