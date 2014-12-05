package dao;

import models.Trainning;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

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

		datastore.put(trainningEntity);
	}
	
}
