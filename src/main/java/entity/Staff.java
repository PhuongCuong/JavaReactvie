package entity;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

public class Staff {
	@BsonId
	private long id;
	@BsonProperty("first_name")
	private String firstName;
	@BsonProperty("last_name")
	private String lastName;
	private Phone phone;
	@BsonProperty("email")
	private String email;
	//@BsonProperty("manager_id")
	private Staff manager;
	
	public Staff(long id, String firstName, String lastName, Phone phone, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}
	
	

//	public Staff(long id, String fullname, Phone phone) {
//		super();
//		this.id = id;
//		fullname = this.firstName + this.lastName;
//		this.phone = phone;
//	}
	
	public Staff(Document doc) {
		this.id = doc.getLong("_id");
		this.firstName = doc.getString("first_name");
		this.lastName = doc.getString("last_name");
		this.phone = doc.get("phone",Phone.class);
		this.email = doc.getString("email");
		//this.manager = (Staff) doc.get("manager_id");
	}



	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Staff getManager() {
		return manager;
	}

	public void setManager(Staff manager) {
		this.manager = manager;
	}



	@Override
	public String toString() {
		return "Staff [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone
				+ ", email=" + email + ", manager=" + manager + "]";
	}
	
	
	
}
