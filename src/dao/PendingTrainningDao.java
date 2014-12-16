package dao;

import java.util.ArrayList;
import java.util.List;

import models.Exercice;
import models.PendingTrainning;
import models.Trainning;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class PendingTrainningDao {
	private DatastoreService datastore;
	private Entity pendingTrainningEntity;
	private TrainningDao trainningDao;
	
	public PendingTrainningDao(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		trainningDao = new TrainningDao();
		pendingTrainningEntity = new Entity("pendingTrainning");
	}
	
	public PendingTrainning createOrGetPendingTrainning(Long trainningId, Key userKey){
		PendingTrainning pendingTrainning = getPendingTrainning(trainningId, userKey);
		if( pendingTrainning == null){
			Entity pendingTrainningEntity = new Entity("pendingTrainning", userKey);
			pendingTrainningEntity.setProperty("trainningId", trainningId);
			datastore.put(pendingTrainningEntity);
			
			pendingTrainning = getPendingTrainning(trainningId, userKey);
		}
		return pendingTrainning;
	}
	
	public PendingTrainning getPendingTrainning(Long trainningId, Key userKey){
		List<PendingTrainning> listPendingTrainning = new ArrayList<PendingTrainning>();
		PendingTrainning pendingTrainning = null;
		
		listPendingTrainning = getListPendingTrainning(userKey);
		for(PendingTrainning pendingTrain : listPendingTrainning){
			if (pendingTrain.getTrainning().getId().equals(trainningId)){
				pendingTrainning = pendingTrain;
			}
		}
		
		return pendingTrainning;
	}
	
	public List<PendingTrainning> getListPendingTrainning(Key userKey){
		
		List<PendingTrainning> listPendingTrainning = new ArrayList<PendingTrainning>();
		
		Query q = new Query("pendingTrainning").setAncestor(userKey);
		PreparedQuery pq = datastore.prepare(q);
		
		
		for(Entity pendingTrainningEntity : pq.asIterable()){
			
			Long trainningIdEntity = (Long) pendingTrainningEntity.getProperty("trainningId");
			Long idPendingTrainningEntity = (Long) pendingTrainningEntity.getKey().getId();

			PendingTrainning pendingTrainning = new PendingTrainning();
			
			pendingTrainning.setId(idPendingTrainningEntity);
			
			Trainning trainning = new Trainning();
			trainning = trainningDao.getTrainningById(trainningIdEntity);
			
			pendingTrainning.setTrainning(trainning);
			
			listPendingTrainning.add(pendingTrainning);								
		}
		return listPendingTrainning;
	}
}
