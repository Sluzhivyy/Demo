package entities;

import java.util.Objects;

public class Material {
    private int materialId;
    private String materialType;
    private String name;
    private int supplierId;
    private int packageQuantity;
    private String unit;
    private String description;
    private String image;
    private double cost;
    private int stockQuantity;
    private int minQuantity;

    public Material(){}

    public Material(String materialType, String name, int supplierId, int packageQuantity, String unit, String description, String image, double cost, int stockQuantity, int minQuantity) {
        this.materialType = materialType;
        this.name = name;
        this.supplierId = supplierId;
        this.packageQuantity = packageQuantity;
        this.unit = unit;
        this.description = description;
        this.image = image;
        this.cost = cost;
        this.stockQuantity = stockQuantity;
        this.minQuantity = minQuantity;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(int packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return materialId == material.materialId && supplierId == material.supplierId && packageQuantity == material.packageQuantity && stockQuantity == material.stockQuantity && minQuantity == material.minQuantity && Double.compare(cost, material.cost) == 0 && Objects.equals(materialType, material.materialType) && Objects.equals(name, material.name) && Objects.equals(unit, material.unit) && Objects.equals(description, material.description) && Objects.equals(image, material.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId, materialType, name, supplierId, packageQuantity, unit, description, image, cost, stockQuantity, minQuantity);
    }
}
