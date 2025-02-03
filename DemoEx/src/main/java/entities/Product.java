
package entities;

import java.util.Objects;

public class Product {
    private int productId;
    private String article;
    private String productType;
    private String name;
    private String description;
    private String image;
    private double minPrice;
    private double packageLength;
    private double packageWidth;
    private double packageHeight;
    private double weightWithoutPackage;
    private double weightWithPackage;
    private String certificate;
    private String standardNumber;
    private int productionTime;
    private double cost;
    private int workshopNumber;
    private int peopleInProduction;

    public Product() {
    }

    public Product(String article, String productType, String name, String description, String image, double minPrice, double packageLength, double packageWidth, double packageHeight, double weightWithoutPackage, double weightWithPackage, String certificate, String standardNumber, int productionTime, double cost, int workshopNumber, int peopleInProduction) {
        this.article = article;
        this.productType = productType;
        this.name = name;
        this.description = description;
        this.image = image;
        this.minPrice = minPrice;
        this.packageLength = packageLength;
        this.packageWidth = packageWidth;
        this.packageHeight = packageHeight;
        this.weightWithoutPackage = weightWithoutPackage;
        this.weightWithPackage = weightWithPackage;
        this.certificate = certificate;
        this.standardNumber = standardNumber;
        this.productionTime = productionTime;
        this.cost = cost;
        this.workshopNumber = workshopNumber;
        this.peopleInProduction = peopleInProduction;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getPackageLength() {
        return packageLength;
    }

    public void setPackageLength(double packageLength) {
        this.packageLength = packageLength;
    }

    public double getPackageWidth() {
        return packageWidth;
    }

    public void setPackageWidth(double packageWidth) {
        this.packageWidth = packageWidth;
    }

    public double getPackageHeight() {
        return packageHeight;
    }

    public void setPackageHeight(double packageHeight) {
        this.packageHeight = packageHeight;
    }

    public double getWeightWithoutPackage() {
        return weightWithoutPackage;
    }

    public void setWeightWithoutPackage(double weightWithoutPackage) {
        this.weightWithoutPackage = weightWithoutPackage;
    }

    public double getWeightWithPackage() {
        return weightWithPackage;
    }

    public void setWeightWithPackage(double weightWithPackage) {
        this.weightWithPackage = weightWithPackage;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getStandardNumber() {
        return standardNumber;
    }

    public void setStandardNumber(String standardNumber) {
        this.standardNumber = standardNumber;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getWorkshopNumber() {
        return workshopNumber;
    }

    public void setWorkshopNumber(int workshopNumber) {
        this.workshopNumber = workshopNumber;
    }

    public int getPeopleInProduction() {
        return peopleInProduction;
    }

    public void setPeopleInProduction(int peopleInProduction) {
        this.peopleInProduction = peopleInProduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && Double.compare(minPrice, product.minPrice) == 0 && Double.compare(packageLength, product.packageLength) == 0 && Double.compare(packageWidth, product.packageWidth) == 0 && Double.compare(packageHeight, product.packageHeight) == 0 && Double.compare(weightWithoutPackage, product.weightWithoutPackage) == 0 && Double.compare(weightWithPackage, product.weightWithPackage) == 0 && productionTime == product.productionTime && Double.compare(cost, product.cost) == 0 && workshopNumber == product.workshopNumber && peopleInProduction == product.peopleInProduction && Objects.equals(article, product.article) && Objects.equals(productType, product.productType) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(image, product.image) && Objects.equals(certificate, product.certificate) && Objects.equals(standardNumber, product.standardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, article, productType, name, description, image, minPrice, packageLength, packageWidth, packageHeight, weightWithoutPackage, weightWithPackage, certificate, standardNumber, productionTime, cost, workshopNumber, peopleInProduction);
    }
}

