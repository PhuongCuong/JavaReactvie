package dao;

import java.nio.file.DirectoryStream.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Address;
import entity.Customer;
import entity.Order;
import entity.OrderDetail;
import entity.Phone;
import entity.Product;
import entity.Staff;

public class OrderDao {
	private MongoCollection<Order> collecion;
	private MongoCollection<Document> ordcu;
	private MongoCollection<Document> cusdoc;
	private MongoCollection<Customer> cuscollection;
	private MongoClient mongoClient;
	private MongoDatabase db;
	public OrderDao() {
		mongoClient = Connection.getinState().getMongoClient();
		db = mongoClient.getDatabase("BikeStore");
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
				);
		collecion = db.getCollection("orders",Order.class).withCodecRegistry(codecRegistry);	
		cuscollection = db.getCollection("customers",Customer.class).withCodecRegistry(codecRegistry);
		ordcu = db.getCollection("orders");
		//cusdoc = db.getCollection("customers");
		// TODO Auto-generated constructor stub
	}
	
	
	//db.orders.findOne({"_id":ObjectId("615279c4dc90aa2be71fd8f9")})
	public void getorderbyid(ObjectId id) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		//Document doc = Document.parse("{\"_id\":"+id+"}");
		collecion.find(Filters.eq("_id", id)).subscribe(new Subscriber<Order>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(Order t) {
				System.out.println(t);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				latch.countDown();
				// TODO Auto-generated method stub
				
			}
		});
		latch.await();
	}
	
	public void mapCustomerToOrder(Document customerDoc, Order order) {
	    String customerId = customerDoc.getString("_id");
	    String customerName = customerDoc.getString("first_name") + " " + customerDoc.getString("last_name");
	    String customerEmail = customerDoc.getString("email");

	    order.getCustomer().setCustomerID(customerId);
	    order.getCustomer().setFirstName(customerName);
	    order.getCustomer().setEmail(customerEmail);

	    // Ánh xạ thêm các thông tin khác vào đơn hàng tại đây
	}

	public void mapCustomerToOrder(Order order) {
		
		//ObjectId customerId = new ObjectId(order.getCustomer().getCustomerID());
	    CountDownLatch latch = new CountDownLatch(1);
	    
	    ordcu.find().first()
	            .subscribe(new Subscriber<Document>() {
	                public void onSubscribe(Subscription s) {
	                    s.request(1);
	                }

	                @Override
	                public void onNext(Document doc) {
	                    Customer customer = new Customer();
	                    customer.setCustomerID(doc.getString("_id"));
	                    customer.setFirstName(doc.getString("first_name") + " " + doc.getString("last_name"));
	                    customer.setEmail(doc.getString("email"));

	                    List<Document> phones = (List<Document>) doc.get("phones");
	                    if (!phones.isEmpty()) {
	                        //customer.setPhone(phones.get(0).getString("number"));
	                    }

	                    order.setCustomer(customer);
	                    latch.countDown();
	                }

	                @Override
	                public void onError(Throwable t) {
	                    t.printStackTrace();
	                    latch.countDown();
	                }

	                @Override
	                public void onComplete() {
	                    latch.countDown();
	                }

		       });

	    try {
	        latch.await();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void getFindOrder(ObjectId id) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		collecion.find(Filters.eq("_id",id)).subscribe(new Subscriber<Order>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(Order t) {
				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			}
		});
		latch.await();
	}
	
	
	public Customer fromcus(Document doc) {
		Customer cus = new Customer(doc.getString("_id"), doc.getString("first_name")+doc.getString("last_name"),doc.getString("email"));
		return cus;
	}

	
}
