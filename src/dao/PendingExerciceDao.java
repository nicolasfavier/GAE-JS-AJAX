package dao;

import java.util.ArrayList;
import java.util.List;

import models.Exercice;
import models.PendingExercice;
import models.PendingTrainning;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

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
