package data;

import entities.SalesHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesHistoryDao {

    public List<SalesHistory> getSalesHistoryByPartnerId(int partnerId) throws SQLException {
        List<SalesHistory> salesHistoryList = new ArrayList<>();
        String sql = "SELECT sh.sale_date, sh.quantity, p.name AS product_name " +
                "FROM sales_history sh " +
                "JOIN products p ON sh.product_id = p.product_id " +
                "WHERE sh.partner_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, partnerId);
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    SalesHistory history = new SalesHistory();
                    history.setSaleDate(rs.getDate("sale_date").toLocalDate());
                    history.setQuantity(rs.getInt("quantity"));
                    history.setProductName(rs.getString("product_name"));
                    salesHistoryList.add(history);
                }
            }

        }
        return salesHistoryList;
    }
}