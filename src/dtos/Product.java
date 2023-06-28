package dtos;

/**
 *
 * @author NgocLinh
 */
public class Product {

    private String productID;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String status;
    
    
    public Product() {
    }

    public Product(String productID, String productName, double unitPrice, int quantity, String status) {
        this.productID = productID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        if (!productID.isEmpty()) {
            this.productID = productID;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        if (!productName.isEmpty()) {
            this.productName = productName;
        }
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!status.isEmpty()) {
            this.status = status;
        }
    }

    @Override
    public String toString() {
        return productID + ", " + productName + ", " + unitPrice + ", " + quantity + ", " + status;
    }

    public void print() {
        System.out.printf(" %-9s| %-19s| %-19.2f| %-21d| %-14s \n", productID, productName, unitPrice, quantity, status);
    }

}
