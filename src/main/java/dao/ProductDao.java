package dao;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.print.Doc;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import db.Connection;
import entity.Order;
import entity.Product;

public class ProductDao {
	private MongoCollection<Product> collection;
	private MongoCollection<Document> proDucument;
	private MongoClient mongoClient;
	private MongoDatabase db;
	
	public ProductDao() {
		mongoClient = Connection.getinState().getMongoClient();
		db = mongoClient.getDatabase("BikeStore");
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
				);
		collection = db.getCollection("products",Product.class).withCodecRegistry(codecRegistry);
		proDucument = db.getCollection("products");
		// TODO Auto-generated constructor stub
	}
	
//	public List<Product> getproductfirst() {
//		BikeSubscriber<Product> sub;
//		collection.find().first()
//		.subscribe(sub = new BikeSubscriber<Product>());
//		return sub.getResults();
//	}
	
	public boolean insertOnes(Product pd) throws InterruptedException
	{
		
		CountDownLatch latch = new CountDownLatch(1);
		AtomicBoolean rs = new AtomicBoolean(false);
		
		Publisher<InsertOneResult> pub = collection.insertOne(pd);
		Subscriber<InsertOneResult> sub = new Subscriber<InsertOneResult>() {
			
			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				
			}
			
			@Override
			public void onNext(InsertOneResult t) {
				if(t.getInsertedId() != null)
					rs.set(true);
				
			}
			
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
				
			}
			
			@Override
			public void onComplete() {
				latch.countDown();
				
			}
		};
		
		pub.subscribe(sub);
		
		latch.await();
		return rs.get();
	}
	
	
	public boolean insertMany(List<Product> pd) throws InterruptedException {
		CountDownLatch lautch = new CountDownLatch(1);
		AtomicBoolean rs = new AtomicBoolean(false);
		
		Publisher<InsertManyResult> pub = collection.insertMany(pd);
		Subscriber<InsertManyResult> sub = new Subscriber<InsertManyResult>() {

			@Override
			public void onSubscribe(Subscription s) {
				// TODO Auto-generated method stub
				s.request(1);
			}

			@Override
			public void onNext(InsertManyResult t) {
				if(t.getInsertedIds() != null)
					rs.set(true);
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
		};
		
		pub.subscribe(sub);
		
		lautch.await();
		return rs.get();
	}
	
	//db.products.updateOne({"_id":23926},{$set:{"price":300}})
	
	public boolean updateOne(Product p) throws InterruptedException {
		
		CountDownLatch latch = new CountDownLatch(1);
		AtomicBoolean rs = new AtomicBoolean(false);
		
		Document doc = Document.parse("{\"_id\":"+p.getId()+"}");
		Document doc1 = Document.parse("{$set:{\"price\":"+p.getPrice()+"}}");
		
		Publisher<UpdateResult> pub = collection.updateOne(doc,doc1);
		Subscriber<UpdateResult> sub = new Subscriber<UpdateResult>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(UpdateResult t) {
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
		};
		pub.subscribe(sub);
		
		latch.await();
		return rs.get();
	}
	//db.products.deleteOne({"_id":23925})
	public void deleteOne(Product p) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Document doc = Document.parse("{\"_id\":"+p.getId()+"}");
		Publisher<DeleteResult> pub = collection.deleteOne(doc);
		Subscriber<DeleteResult> sub = new Subscriber<DeleteResult>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(DeleteResult t) {
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
		};
		pub.subscribe(sub);
		
		latch.await();
	}
	
	//db.products.aggregate([{$match:{"_id":23923}},{$unset:"phone"}])
	
	public void deletefield(Product p) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Document doc = Document.parse("{$match:{\"_id\":"+p.getId()+"}}");
		Document doc1 = Document.parse("{$unset:\"phone\"}");
		proDucument.aggregate(Arrays.asList(doc,doc1)).subscribe(new Subscriber<Document>() {
			
			private Subscription s;
			
			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				this.s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(Document t) {
				System.out.println(t);
				this.s.request(1);
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
	
	
	//db.products.aggregate([{$match:{"_id":23923}},{$addFields:{"phone":"0367201132"}}])
	public void addfield(Product p) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Document doc = Document.parse("{$match:{\"_id\":"+p.getId()+"}}");
		Document doc1 = Document.parse("{$addFields:{\"phone\":\"0367201132\"}}");
		proDucument.aggregate(Arrays.asList(doc,doc1)).subscribe(new Subscriber<Document>() {
			
			private Subscription s;
			
			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				this.s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(Document t) {
				System.out.println(t);
				this.s.request(1);
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
	//db.products.find({"_id":23924})
	public void getproductid(long id) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(1);
		Document doc = Document.parse("{\"_id\":23924}");
		collection.find(doc).subscribe(new Subscriber<Product>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNext(Product t) {
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
