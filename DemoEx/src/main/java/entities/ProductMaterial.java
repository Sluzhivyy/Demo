package entities;

import java.util.Objects;

public class ProductMaterial {
    private int productMaterialId;
    private int productId;
    private int materialId;
    private double quantity;
    private double coefficient;

    public ProductMaterial(){}
    public ProductMaterial(int productId, int materialId, double quantity, double coefficient) {
        this.productId = productId;
        this.materialId = materialId;
        this.quantity = quantity;
        this.coefficient = coefficient;
    }

    public int getProductMaterialId() {
        return productMaterialId;
    }

    public void setProductMaterialId(int productMaterialId) {
        this.productMaterialId = productMaterialId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductMaterial that = (ProductMaterial) o;
        return productMaterialId == that.productMaterialId && productId == that.productId && materialId == that.materialId && Double.compare(quantity, that.quantity) == 0 && Double.compare(coefficient, that.coefficient) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productMaterialId, productId, materialId, quantity, coefficient);
    }
}
