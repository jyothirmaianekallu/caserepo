package com.innominds.db;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.org.lightcouch.CouchDbException;

@Configuration
public class CloudantDB {
	

	private static CloudantClient cloudant = null;
	private static Database db = null;

	//private static String databasename = "person";

	private static String user = "mounica100100";
	private static String password = "mounica1234$";

	private static void initClient() {
		if (cloudant == null) {
			synchronized (CloudantDB.class) {
				if (cloudant != null) {
					return;
				}
				cloudant = createClient();

			}
		}

	}
	
	@Bean
	private static CloudantClient createClient() {
		
		try {
			System.out.println("Connecting to Cloudant : " + user);
			CloudantClient client = ClientBuilder.account(user)
					.username(user)
					.password(password)
					.build();
			return client;
		} catch (CouchDbException e) {
			throw new RuntimeException("Unable to connect to repository", e);
		}
		
	}
	
	public  Database getDB() {
		if (cloudant == null) {
			initClient();
		}

		if (db == null) {
			try {
				db = cloudant. database("mydetails", true);
			} catch (Exception e) {
				throw new RuntimeException("DB Not found", e);
			}
		}
		return db;
	}

	public CloudantDB() {
	}
}