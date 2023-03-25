package db;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

public class Connection {
	private static Connection instate;
	private MongoClient mongoClient;
	
	public Connection() {
		String uri = "mongodb://localhost:27017";
		mongoClient = MongoClients.create(uri);
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getinState() {
		if(instate == null)
			instate = new Connection();
		return instate;
	}
	
	public MongoClient getMongoClient() {
		return mongoClient;
	}
}
