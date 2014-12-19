package dao;

import java.util.ArrayList;
import java.util.List;

import models.Exercice;
import models.PendingExercice;
import models.PendingTrainning;
import models.Trainning;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.com.google.common.primitives.Ints;

public class PendingTrainningDao {
	private DatastoreService datastore;
	private Entity pendingTrainningEntity;
	private TrainningDao trainningDao;
	private PendingExerciceDao pendingExerciceDao;
	
	public PendingTrainningDao(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		trainningDao = new TrainningDao();
		pendingExerciceDao = new PendingExerciceDao();
		pendingTrainningEntity = new Entity("pendingTrainning");
	}
	
	public PendingTrainning createOrGetPendingTrainning(Long trainningId, Key userKey){
		PendingTrainning pendingTrainning = getPendingTrainning(trainningId, userKey);
		
		if( pendingTrainning == null){
			Entity pendingTrainningEntity = new Entity("pendingTrainning", userKey);
			pendingTrainningEntity.setProperty("trainningId", trainningId);
			datastore.put(pendingTrainningEntity);
			
			pendingTrainning = addExercicesPendingTrainning(trainningId, userKey);
			
			
		}
		return pendingTrainning;
	}
	
	public void updatePendingTrainning(PendingTrainning pendingTrainningSubmited, Key userKey){
		
		Key keyPendingTrainning = KeyFactory.createKey(userKey,"pendingTrainning", pendingTrainningSubmited.getId());
		
		try {
			pendingTrainningEntity = datastore.get(keyPendingTrainning);
			
			for(PendingExercice pendingEx : pendingTrainningSubmited.getPendingExercice()){
				pendingExerciceDao.updatePendingExercice(pendingEx, keyPendingTrainning);
			}
			
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public PendingTrainning getPendingTrainning(Long trainningId, Key userKey){
		List<PendingTrainning> listPendingTrainning = new ArrayList<PendingTrainning>();
		PendingTrainning pendingTrainning = null;
		
		listPendingTrainning = getListPendingTrainning(userKey);
		for(PendingTrainning pendingTrain : listPendingTrainning){
			if (pendingTrain.getTrainningId().equals(trainningId)){
				pendingTrainning = pendingTrain;
			}
		}
		
		return pendingTrainning;
	}
	
	public PendingTrainning addExercicesPendingTrainning(Long trainningId, Key userKey){
		List<PendingTrainning> listPendingTrainning = new ArrayList<PendingTrainning>();
		PendingTrainning pendingTrainning = null;
		
		listPendingTrainning = createListPendingTrainning(userKey);
		for(PendingTrainning pendingTrain : listPendingTrainning){
			if (pendingTrain.getTrainningId().equals(trainningId)){
				pendingTrainning = pendingTrain;
			}
		}
		
		return pendingTrainning;
	}
	
	public List<PendingTrainning> getListPendingTrainning(Key userKey){
		
		List<PendingTrainning> listPendingTrainning = new ArrayList<PendingTrainning>();
		List<PendingExercice> listPendingEx = new ArrayList<PendingExercice>();
		
		Query q = new Query("pendingTrainning").setAncestor(userKey);
		PreparedQuery pq = datastore.prepare(q);
		
		
		for(Entity pendingTrainningEntity : pq.asIterable()){
			
			Long trainningIdEntity = (Long) pendingTrainningEntity.getProperty("trainningId");
			Long idPendingTrainningEntity = (Long) pendingTrainningEntity.getKey().getId();

			PendingTrainning pendingTrainning = new PendingTrainning();
			TrainningDao trainningDao = new TrainningDao();
			Trainning trainning = trainningDao.getTrainningById(trainningIdEntity);
			
			pendingTrainning.setId(idPendingTrainningEntity);
			
			Query qEx = new Query("pendingExercice").setAncestor(pendingTrainningEntity.getKey());
			PreparedQuery pqEx = datastore.prepare(qEx);
			
			ExerciceDao exDao = new ExerciceDao();

			for(Entity pendingExerciceEntity : pqEx.asIterable()){
				int timeEx = Ints.checkedCast((Long) pendingExerciceEntity.getProperty("time"));
				Boolean isFinishEx = (Boolean) pendingExerciceEntity.getProperty("finish");
				Long exId = (Long) pendingExerciceEntity.getProperty("exerciceId");
				
				Exercice ex = exDao.getExerciceById(exId, trainningIdEntity);
				
				PendingExercice pendingExercice = new PendingExercice();
				pendingExercice.setTitle(ex.getTitle());
				pendingExercice.setDescription(ex.getDescription());
				pendingExercice.setDuration(ex.getDuration());
				pendingExercice.setId(pendingExerciceEntity.getKey().getId());
				pendingExercice.setRepetition(ex.getRepetition());
				pendingExercice.setFinish(isFinishEx);
				pendingExercice.setTime(timeEx);
				
				listPendingEx.add(pendingExercice);
			}
			/*Trainning trainning = new Trainning();
			trainning = trainningDao.getTrainningById(trainningIdEntity);
			List<PendingExercice> listPendingEx = new ArrayList<PendingExercice>();
			
			for(Exercice ex : trainning.getExercices()){
				
				Entity pendingExerciceEntity = new Entity("pendingExercice", pendingTrainningEntity.getKey());
				pendingExerciceEntity.setProperty("exerciceId", ex.getId());
				pendingExerciceEntity.setProperty("time", 0);
				pendingExerciceEntity.setProperty("finish", false);
				
				Key keyPendingEx = datastore.put(pendingExerciceEntity);
				
				PendingExercice pendingExercice = new PendingExercice();
				pendingExercice.setTitle(ex.getTitle());
				pendingExercice.setDescription(ex.getDescription());
				pendingExercice.setDuration(ex.getDuration());
				pendingExercice.setId(keyPendingEx.getId());
				pendingExercice.setRepetition(ex.getRepetition());
				listPendingEx.add(pendingExercice);
			}
			*/
			pendingTrainning.setDate(trainning.getDate());
			pendingTrainning.setDescription(trainning.getDescription());
			pendingTrainning.setTitle(trainning.getTitle());
			pendingTrainning.setExpectedTime(trainning.getExpectedTime());
			pendingTrainning.setTrainningId(trainning.getId());
			pendingTrainning.setPendingExercice(listPendingEx);
			
			listPendingTrainning.add(pendingTrainning);								
		}
		return listPendingTrainning;
	}
	
	public List<PendingTrainning> createListPendingTrainning(Key userKey){
		
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
			List<PendingExercice> listPendingEx = new ArrayList<PendingExercice>();
			
			for(Exercice ex : trainning.getExercices()){
				
				Entity pendingExerciceEntity = new Entity("pendingExercice", pendingTrainningEntity.getKey());
				pendingExerciceEntity.setProperty("exerciceId", ex.getId());
				pendingExerciceEntity.setProperty("time", 0);
				pendingExerciceEntity.setProperty("finish", false);
				
				Key keyPendingEx = datastore.put(pendingExerciceEntity);
				
				PendingExercice pendingExercice = new PendingExercice();
				pendingExercice.setTitle(ex.getTitle());
				pendingExercice.setDescription(ex.getDescription());
				pendingExercice.setDuration(ex.getDuration());
				pendingExercice.setId(keyPendingEx.getId());
				pendingExercice.setRepetition(ex.getRepetition());
				listPendingEx.add(pendingExercice);
			}
			
			pendingTrainning.setDate(trainning.getDate());
			pendingTrainning.setDescription(trainning.getDescription());
			pendingTrainning.setTitle(trainning.getTitle());
			pendingTrainning.setExpectedTime(trainning.getExpectedTime());
			pendingTrainning.setTrainningId(trainning.getId());
			pendingTrainning.setPendingExercice(listPendingEx);
			
			listPendingTrainning.add(pendingTrainning);								
		}
		return listPendingTrainning;
	}
}

