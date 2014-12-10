package dao;

import models.Exercice;
import models.Trainning;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class TrainningDao {
	private DatastoreService datastore;
	private Entity trainningEntity;
	
	public TrainningDao(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		trainningEntity = new Entity("Trainning");
	}
	
	public void createTrainning(Trainning trainningSubmited){
		trainningEntity.setProperty("title", trainningSubmited.getTitle());
		trainningEntity.setProperty("description",trainningSubmited.getDescription() );
		trainningEntity.setProperty("kind",trainningSubmited.getKind().getValue() );
		Key trainningKey = datastore.put(trainningEntity);

		for (Exercice ex : trainningSubmited.getExercices()){
			Entity exercice = new Entity("Exercice", trainningKey);

			exercice.setProperty("title",ex.getTitle());
			exercice.setProperty("description", ex.getDescription());
			exercice.setProperty("duration", ex.getDuration());
			
			datastore.put(exercice);
		}
		
	}
	
}
