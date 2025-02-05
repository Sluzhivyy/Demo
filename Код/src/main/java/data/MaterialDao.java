package data;

import entities.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialDao {

    public Material getMaterialById(int id) throws SQLException {
        String sql = "SELECT * FROM materials WHERE material_id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return mapResultSetToMaterial(rs);
                }
            }
        }
        return null;
    }
    private Material mapResultSetToMaterial(ResultSet rs) throws SQLException {
        Material material = new Material();
        material.setMaterialId(rs.getInt("material_id"));
        material.setMaterialType(rs.getString("material_type"));
        material.setName(rs.getString("name"));
        material.setSupplierId(rs.getInt("supplier_id"));
        material.setPackageQuantity(rs.getInt("package_quantity"));
        material.setUnit(rs.getString("unit"));
        material.setDescription(rs.getString("description"));
        material.setImage(rs.getString("image"));
        material.setCost(rs.getDouble("cost"));
        material.setStockQuantity(rs.getInt("stock_quantity"));
        material.setMinQuantity(rs.getInt("min_quantity"));
        return material;
    }
}
