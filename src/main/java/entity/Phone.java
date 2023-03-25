package entity;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Phone {
	@BsonProperty("type")
	private String type;
	@BsonProperty("number")
	private String number;
	public Phone(String type, String number) {
		super();
		this.type = type;
		this.number = number;
	}
	
	public Phone(Document doc) {
		this.type = doc.getString("type");
		this.number = doc.getString("number");
	}
	public Phone() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Phone(String number) {
		super();
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Phone [type=" + type + ", number=" + number + "]";
	}
	
	
}
