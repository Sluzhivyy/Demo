package data;

import entities.ProductMaterial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMaterialDao {

    public ProductMaterial getProductMaterialByProductAndMaterialId(int productId, int materialId) throws SQLException {
        String sql = "SELECT * FROM product_materials WHERE product_id = ? AND material_id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,productId);
            pstmt.setInt(2, materialId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToProductMaterial(rs);
                }
            }
        }
        return null;
    }

    private ProductMaterial mapResultSetToProductMaterial(ResultSet rs) throws SQLException{
        ProductMaterial productMaterial = new ProductMaterial();
        productMaterial.setProductMaterialId(rs.getInt("product_material_id"));
        productMaterial.setProductId(rs.getInt("product_id"));
        productMaterial.setMaterialId(rs.getInt("material_id"));
        productMaterial.setQuantity(rs.getDouble("quantity"));
        productMaterial.setCoefficient(rs.getDouble("coefficient"));
        return productMaterial;
    }
}