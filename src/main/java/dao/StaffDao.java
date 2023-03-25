package dao;

import java.util.concurrent.CountDownLatch;

import org.bson.Document;
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
import entity.Staff;

public class StaffDao {
	private MongoCollection<Staff> collection;
	private MongoClient mongoClient;
	private MongoDatabase db;
	
	public StaffDao() {
		mongoClient = Connection.getinState().getMongoClient();
		db = mongoClient.getDatabase("BikeStore");
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
				);
		collection = db.getCollection("staffs", Staff.class).withCodecRegistry(codecRegistry);
		// TODO Auto-generated constructor stub
	}
	//db.staffs.find({"_id":10})
	public void getstaffbyID(long id) throws InterruptedException {
		CountDownLatch lautch = new CountDownLatch(1);
		Document doc = Document.parse("{\"_id\":10}");
		
		collection.find(doc).subscribe(new Subscriber<Staff>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(Staff staff) {
				System.out.println(staff);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				lautch.countDown();
				// TODO Auto-generated method stub
				
			}
		});
		lautch.await();
	}
}
