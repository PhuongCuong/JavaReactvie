package dao;

import java.util.concurrent.CountDownLatch;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.mongodb.MongoClientSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Customer;

public class CustomerDao {
	private MongoCollection<Customer> collection;
	private MongoClient mongoClient;
	private MongoDatabase db;
	public CustomerDao() {
		mongoClient = Connection.getinState().getMongoClient();
		db = mongoClient.getDatabase("BikeStore");
		CodecRegistry coRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		collection = db.getCollection("customers",Customer.class).withCodecRegistry(coRegistry);
				
		// TODO Auto-generated constructor stub
	}
	
	public void getfindOne() throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		collection.find().first().subscribe(new Subscriber<Customer>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(Customer t) {
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
}
