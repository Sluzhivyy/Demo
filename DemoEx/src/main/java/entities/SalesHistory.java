package entities;

import java.time.LocalDate;
import java.util.Objects;

public class SalesHistory {
    private LocalDate saleDate;
    private int quantity;
    private String productName;
    public SalesHistory(){}
    public SalesHistory(LocalDate saleDate, int quantity, String productName) {
        this.saleDate = saleDate;
        this.quantity = quantity;
        this.productName = productName;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesHistory that = (SalesHistory) o;
        return quantity == that.quantity && Objects.equals(saleDate, that.saleDate) && Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(saleDate, quantity, productName);
    }
}
