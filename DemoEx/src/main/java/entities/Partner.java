package entities;
import java.util.Objects;

public class Partner {
    private int partnerId;
    private String partnerType;
    private String companyName;
    private String legalAddress;
    private String inn;
    private String directorName;
    private String phone;
    private String email;
    private String logo;
    private double rating;
    private String salesLocations;
    public Partner(){};

    public Partner(String partnerType, String companyName, String legalAddress, String inn, String directorName, String phone, String email, String logo, double rating, String salesLocations) {
        this.partnerType = partnerType;
        this.companyName = companyName;
        this.legalAddress = legalAddress;
        this.inn = inn;
        this.directorName = directorName;
        this.phone = phone;
        this.email = email;
        this.logo = logo;
        this.rating = rating;
        this.salesLocations = salesLocations;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSalesLocations() {
        return salesLocations;
    }

    public void setSalesLocations(String salesLocations) {
        this.salesLocations = salesLocations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partner partner = (Partner) o;
        return partnerId == partner.partnerId && Double.compare(rating, partner.rating) == 0 && Objects.equals(partnerType, partner.partnerType) && Objects.equals(companyName, partner.companyName) && Objects.equals(legalAddress, partner.legalAddress) && Objects.equals(inn, partner.inn) && Objects.equals(directorName, partner.directorName) && Objects.equals(phone, partner.phone) && Objects.equals(email, partner.email) && Objects.equals(logo, partner.logo) && Objects.equals(salesLocations, partner.salesLocations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partnerId, partnerType, companyName, legalAddress, inn, directorName, phone, email, logo, rating, salesLocations);
    }
}