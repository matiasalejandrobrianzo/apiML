package org.glassfish.jersey.archetypes;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class SrvDSF {

	private static final DatastoreService INSTANCE = DatastoreServiceFactory
			.getDatastoreService();

	public static DatastoreService getDatastoreService() {
		return INSTANCE;
	}

	private SrvDSF() {
	}
}