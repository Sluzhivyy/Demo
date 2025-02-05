package data;

import entities.Partner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PartnerDao {

    public List<Partner> getAllPartners() throws SQLException {
        List<Partner> partners = new ArrayList<>();
        String sql = "SELECT * FROM partners";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                partners.add(mapResultSetToPartner(rs));
            }
        }
        return partners;
    }

    public Partner getPartnerById(int id) throws SQLException{
        String sql = "SELECT * FROM partners WHERE partner_id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            try(ResultSet rs = pstmt.executeQuery()){
                if(rs.next()){
                    return mapResultSetToPartner(rs);
                }
            }
        }
        return null;
    }
    public boolean addPartner(Partner partner) throws SQLException {
        String sql = "INSERT INTO partners (partner_type, company_name, legal_address, inn, director_name, phone, email, logo, rating, sales_locations) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, partner.getPartnerType());
            pstmt.setString(2, partner.getCompanyName());
            pstmt.setString(3, partner.getLegalAddress());
            pstmt.setString(4, partner.getInn());
            pstmt.setString(5, partner.getDirectorName());
            pstmt.setString(6, partner.getPhone());
            pstmt.setString(7, partner.getEmail());
            pstmt.setString(8, partner.getLogo());
            pstmt.setDouble(9, partner.getRating());
            pstmt.setString(10, partner.getSalesLocations());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    public boolean updatePartner(Partner partner) throws SQLException {String sql = "UPDATE partners SET partner_type = ?, company_name = ?, legal_address = ?, inn = ?, director_name = ?, phone = ?, email = ?, logo = ?, rating = ?, sales_locations = ? WHERE partner_id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, partner.getPartnerType());
            pstmt.setString(2, partner.getCompanyName());
            pstmt.setString(3, partner.getLegalAddress());
            pstmt.setString(4, partner.getInn());
            pstmt.setString(5, partner.getDirectorName());
            pstmt.setString(6, partner.getPhone());
            pstmt.setString(7, partner.getEmail());
            pstmt.setString(8, partner.getLogo());
            pstmt.setDouble(9, partner.getRating());
            pstmt.setString(10, partner.getSalesLocations());
            pstmt.setInt(11,partner.getPartnerId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deletePartner(int id) throws SQLException{
        String sql = "DELETE FROM partners WHERE partner_id = ?";
        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1,id);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }


    private Partner mapResultSetToPartner(ResultSet rs) throws SQLException {
        Partner partner = new Partner();
        partner.setPartnerId(rs.getInt("partner_id"));
        partner.setPartnerType(rs.getString("partner_type"));
        partner.setCompanyName(rs.getString("company_name"));
        partner.setLegalAddress(rs.getString("legal_address"));
        partner.setInn(rs.getString("inn"));
        partner.setDirectorName(rs.getString("director_name"));
        partner.setPhone(rs.getString("phone"));
        partner.setEmail(rs.getString("email"));
        partner.setLogo(rs.getString("logo"));
        partner.setRating(rs.getDouble("rating"));
        partner.setSalesLocations(rs.getString("sales_locations"));
        return partner;
    }
}