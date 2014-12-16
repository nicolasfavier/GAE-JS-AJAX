package dao;

import java.util.ArrayList;
import java.util.List;

import models.Exercice;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.labs.repackaged.com.google.common.primitives.Ints;

public class PendingExerciceDao{
	private DatastoreService datastore;
	private Entity pendingExerciceEntity;
	private ExerciceDao exerciceDao;
	
	public PendingExerciceDao(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		pendingExerciceEntity = new Entity("pendingExercice");
		exerciceDao = new ExerciceDao();
	}
	
}
