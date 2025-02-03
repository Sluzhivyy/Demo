package data;

import entities.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1,id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return mapResultSetToProduct(rs);
                }
            }
        }
        return null;
    }
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException{
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setArticle(rs.getString("article"));
        product.setProductType(rs.getString("product_type"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setImage(rs.getString("image"));
        product.setMinPrice(rs.getDouble("min_price"));
        product.setPackageLength(rs.getDouble("package_length"));
        product.setPackageWidth(rs.getDouble("package_width"));
        product.setPackageHeight(rs.getDouble("package_height"));
        product.setWeightWithoutPackage(rs.getDouble("weight_without_package"));
        product.setWeightWithPackage(rs.getDouble("weight_with_package"));
        product.setCertificate(rs.getString("certificate"));
        product.setStandardNumber(rs.getString("standard_number"));
        product.setProductionTime(rs.getInt("production_time"));
        product.setCost(rs.getDouble("cost"));
        product.setWorkshopNumber(rs.getInt("workshop_number"));
        product.setPeopleInProduction(rs.getInt("people_in_production"));
        return product;
    }
}