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
	
	public PendingExercice createOrGetPendingExercice(Long exerciceId, Long trainningPendingId){
		PendingExercice pendingExercice = getPendingExercice(exerciceId, trainningPendingId);
		if( pendingExercice == null){
			Key keyPendingTrainning = KeyFactory.createKey("pendingTrainning", trainningPendingId);
			Entity pendingTrainningEntity = new Entity("pendingExercice", keyPendingTrainning);
			pendingTrainningEntity.setProperty("exerciceId", exerciceId);
			datastore.put(pendingTrainningEntity);
			
			pendingExercice = getPendingExercice(exerciceId, trainningPendingId);
		}
		return pendingExercice;
	}
	
	public PendingExercice getPendingExercice(Long exerciceId, Long trainningPendingId){
		List<PendingExercice> listPendingExercice = new ArrayList<PendingExercice>();
		PendingExercice pendingExercice = null;
		
		Key keyPendingTrainning = KeyFactory.createKey("pendingTrainning", trainningPendingId);
		
		listPendingExercice = getListPendingExercice(keyPendingTrainning);
		
		for(PendingExercice pendingEx : listPendingExercice){
			if (pendingEx.getExercice().getId().equals(exerciceId)){
				pendingExercice = pendingEx;
			}
		}
		
		return pendingExercice;
	}
	
	public List<PendingExercice> getListPendingExercice(Key pendingTrainningKey){
		
		List<PendingExercice> listPendingExercice = new ArrayList<PendingExercice>();
		
		Query q = new Query("pendingExercice").setAncestor(pendingTrainningKey);
		PreparedQuery pq = datastore.prepare(q);
		
		
		for(Entity pendingExerciceEntity : pq.asIterable()){
			
			Long exerciceIdEntity = (Long) pendingExerciceEntity.getProperty("exerciceId");
			Long idPendingExerciceEntity = (Long) pendingExerciceEntity.getKey().getId();

			PendingExercice pendingExercice = new PendingExercice();
			
			pendingExercice.setId(idPendingExerciceEntity);
			
			Exercice exercice = new Exercice();
			exercice = exerciceDao.getExerciceById(exerciceIdEntity);
			
			pendingExercice.setExercice(exercice);			
			listPendingExercice.add(pendingExercice);								
		}
		return listPendingExercice;
	}
	
}
