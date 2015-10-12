package com.saltside.setup.mongodb;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import com.saltside.resource.Bird;

public class MongoDBSetup {
	public static void main(String[] args) {
		try {
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("restDB");
			DBCollection collection = db.getCollection("restCol");

			// convert JSON to DBObject directly
			String[] continents = new String[]{"asia","africa"};
			Bird bird = new Bird("Eagle", "Raptors", continents);
			String birdJson = new Gson().toJson(bird);
			DBObject dbObject = (DBObject) JSON
					.parse(birdJson);

			collection.insert(dbObject);

			DBCursor cursorDoc = collection.find();
			while (cursorDoc.hasNext()) {
				System.out.println(cursorDoc.next());
			}

			System.out.println("Sample data inserted");

		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
}
