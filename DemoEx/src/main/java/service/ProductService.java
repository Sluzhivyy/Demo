package service;

import data.ProductDao;
import entities.Product;
import java.sql.SQLException;

public class ProductService {
    private final ProductDao productDao;
    public ProductService(){
        this.productDao = new ProductDao();
    }
    public Product getProductById(int id) throws SQLException{
        return productDao.getProductById(id);
    }
}
