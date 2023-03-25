package entity;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import com.google.gson.annotations.SerializedName;
@BsonDiscriminator
public class Customer {
	@BsonId
	private String customerID;
	@BsonProperty("first_name")
	private String firstName;
	@BsonProperty("last_name")
	private String lastName;
	@BsonProperty("email")
	private String email;
	private Address address;
	@BsonProperty("registration_date")
	private Date registrationDate;
	private List<Phone> phone;
	//private String fullname;
	public Customer(String customerID, String firstName, String lastName, String email, Date registrationDate) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.registrationDate = registrationDate;
	}
	
	public Customer(Document doc) {
		this.customerID = doc.getString("customer_id");
		this.firstName = doc.getString("first_name");
		this.lastName = doc.getString("last_name");
		this.email = doc.getString("email");
	}
	
	
	
	


	public Customer(String customerID, String firstName, String email) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.email = email;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	public List<Phone> getPhone() {
		return phone;
	}
	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}
	@SerializedName("full_name")
	public String getfullName() {
		return this.firstName+" "+this.lastName;
	}


	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", fulname=" + getfullName() + ", email=" + email + "]";
	}
	
	

	
	
	

	
	
	
	
}
