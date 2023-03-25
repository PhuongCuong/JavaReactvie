package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Order {
	@BsonId
	private ObjectId id;
	@BsonProperty("order_date")
	private Date orderDate;
	private Customer customer;
	private OrderStatus orderStatus;
	@BsonProperty("shipped_date")
	private Date shippedDate;
	private Staff staff;
	@BsonProperty("order_total")
	private double orderTotal;
	@BsonProperty("order_details")
	private List<OrderDetail> orderDetails;
	private Address shippingAddress;
	
	public Order() {
        this(new Date(), OrderStatus.NEW);
    }
	
//	public Order(Document doc) {
//		this.id = doc.getObjectId("_id");
//	    this.orderDate = doc.getDate("order_date");
//	    this.customer = new Customer(doc.get("customers",Document.class));
//	    this.orderStatus = OrderStatus.valueOf(doc.getString("order_status"));
//	    this.shippedDate = doc.getDate("shipped_date");
//	    this.staff = new Staff(doc.get("staff", Document.class));
//	    this.orderTotal = doc.getDouble("order_total");
//	    this.orderDetails = new ArrayList<>();
//	    List<Document> orderDetailsDocs = doc.getList("order_details", Document.class);
//	    for (Document orderDetailDoc : orderDetailsDocs) {
//	        this.orderDetails.add(new OrderDetail(orderDetailDoc));
//	    }
//	    this.shippingAddress = doc.get("shipping_address",Address.class);
//	}

	public Order(Date shipDate, OrderStatus orderStatus) {
		 this.orderDate = new Date();
	        this.orderStatus = OrderStatus.NEW;
	        this.shippedDate = new Date();
	        this.orderTotal = 0;
	        this.orderDetails = new ArrayList<OrderDetail>();
		// TODO Auto-generated constructor stub
	}
	
	public void addOrderDetail(OrderDetail orderDetail) {
        this.orderDetails.add(orderDetail);
        this.orderTotal += orderDetail.getLineTotal();
    }

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	
	public Order(ObjectId id, Date orderDate, Customer customer, OrderStatus orderStatus, Date shippedDate, Staff staff,
			double orderTotal, List<OrderDetail> orderDetails, Address shippingAddress) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.customer = customer;
		this.orderStatus = orderStatus;
		this.shippedDate = shippedDate;
		this.staff = staff;
		this.orderTotal = orderTotal;
		this.orderDetails = orderDetails;
		this.shippingAddress = shippingAddress;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", customer=" + customer + ", orderStatus="
				+ orderStatus + ", shippedDate=" + shippedDate + ", staff=" + staff + ", orderTotal=" + orderTotal
				+ ", orderDetails=" + orderDetails + ", shippingAddress=" + shippingAddress + "]";
	}

	
	
	
	
	
	
}
