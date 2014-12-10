package dao;

import java.util.ArrayList;
import java.util.List;

import models.Exercice;
import models.Trainning;
import models.Trainning.Kind;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.labs.repackaged.com.google.common.primitives.Ints;

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
	
	@SuppressWarnings("deprecation")
	public Trainning getTrainningById(Long id){
		
		/*Filter keyFilter = new FilterPredicate("title",
				                      FilterOperator.EQUAL,
				                      key);
		Query q =  new Query("Trainning").setFilter(keyFilter);*/

		Trainning trainning = new Trainning();
		
		Key key = KeyFactory.createKey("Trainning", id);
		Entity trainningEntity = null;
		try {
			trainningEntity = datastore.get(key);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		if(trainningEntity != null){
			String title = (String) trainningEntity.getProperty("title"); 
			String description = (String) trainningEntity.getProperty("description");
			String kindString = (String) trainningEntity.getProperty("kind");
			Long idTrainning = (Long) trainningEntity.getKey().getId();
			
			trainning.setTitle(title);
			trainning.setDescription(description);
			Kind  kind = Kind.valueOf(kindString.toUpperCase());
			trainning.setKind(kind);
			trainning.setId(idTrainning);
			
			trainning.setExercices(this.getListExercices(trainningEntity.getKey()));
		
		}
		
		return trainning;
		
	}
	
	List<Exercice> getListExercices(Key trainningKey){
		
		List<Exercice> listExercice = new ArrayList<Exercice>();
		
		Query q = new Query("Exercice").setAncestor(trainningKey);
		PreparedQuery pq = datastore.prepare(q);
		
		
		for(Entity exEntity : pq.asIterable()){
			String titleEx = (String) exEntity.getProperty("title"); 
			String descriptionEx = (String) exEntity.getProperty("description"); 
			int duration = Ints.checkedCast((long) exEntity.getProperty("duration"));

			Exercice exercice = new Exercice();
			
			exercice.setTitle(titleEx);
			exercice.setDescription(descriptionEx);
			exercice.setDuration(duration);
			
			listExercice.add(exercice);								
		}
		return listExercice;
	}
}
