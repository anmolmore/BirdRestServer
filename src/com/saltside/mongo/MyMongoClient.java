package com.saltside.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class MyMongoClient {
	private static Mongo mongo = new Mongo("localhost", 27017);
	private static DB db = mongo.getDB("restDB");
	private static DBCollection collection = db.getCollection("restCol");
	
	public static Mongo getInstance() {
		return mongo;
	}
	
	public static DBCollection getDBCollection() {
		return collection;
	}
}
