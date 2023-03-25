package entity;

import org.bson.BsonDocument;
import org.bson.Document;

public class OrderDetail {
	private Product product;
	private int quantity;
	private String color;
	private double price;
	private double discount;
	private double lineTotal;
	//private long productID;
	public OrderDetail(Product product, int quantity, String color, double price, double discount, double lineTotal) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.color = color;
		this.price = price;
		this.discount = discount;
		this.lineTotal = price*quantity*(1-discount);
	}
	
	public OrderDetail(Document doc) {
		this.product = doc.get("products",Product.class);
		this.quantity = doc.getInteger("quantity");
		this.color = doc.getString("color");
		this.lineTotal = doc.getDouble("line_total");
		this.price = doc.getDouble("price");
		this.discount = doc.getDouble("discount");
	}
	public OrderDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getLineTotal() {
		return lineTotal;
	}
	public void setLineTotal(double lineTotal) {
		this.lineTotal = lineTotal;
	}

	@Override
	public String toString() {
		return "OrderDetail [product=" + product + ", quantity=" + quantity + ", color=" + color + ", price=" + price
				+ ", discount=" + discount + ", lineTotal=" + lineTotal + "]";
	}
	
	
}	
