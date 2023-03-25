package entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Product {
	//@BsonId
	private long id;
	@BsonProperty("brand_name")
	private String brandName;
	@BsonProperty("category_name")
	private String categoryName;
	private List<String> colors;
	@BsonProperty("model_year")
	private int modelYear;
	@BsonProperty("product_name")
	private String productName;
	@BsonProperty("price")
	private double price;
	
	public Product(Document doc)
	{
		this.id = doc.getInteger("_id");
		this.brandName = doc.getString("brand_name");
		this.categoryName = doc.getString("category_name");
		this.colors = new ArrayList<String>();
		this.modelYear = doc.getInteger("model_year");
		this.productName = doc.getString("product_name");
		this.price = doc.getDouble("price");
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<String> getColors() {
		return colors;
	}
	public void setColors(List<String> colors) {
		this.colors = colors;
	}
	public int getModelYear() {
		return modelYear;
	}
	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Product(long id, String brandName, String categoryName, List<String> colors, int modelYear,
			String productName, double price) {
		super();
		this.id = id;
		this.brandName = brandName;
		this.categoryName = categoryName;
		this.colors = colors;
		this.modelYear = modelYear;
		this.productName = productName;
		this.price = price;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", brandName=" + brandName + ", categoryName=" + categoryName + ", colors="
				+ colors + ", modelYear=" + modelYear + ", productName=" + productName + ", price=" + price + "]";
	}

	
	
	
}
