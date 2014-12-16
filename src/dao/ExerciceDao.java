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
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.labs.repackaged.com.google.common.primitives.Ints;

public class ExerciceDao {
	private DatastoreService datastore;
	private Entity exerciceEntity;
	
	public ExerciceDao(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		exerciceEntity = new Entity("Exercice");
	}
	
	public List<Exercice> getListExerciceByName(String search){
		
		List<Exercice> exercicesMatchingName = new ArrayList<Exercice>();
		
		Filter searchFilter = new FilterPredicate("title", FilterOperator.EQUAL, search);
		Query q =  new Query("Exercice").setFilter(searchFilter);
		PreparedQuery pq = datastore.prepare(q);
		
		for(Entity exEntity : pq.asIterable()){
			Long trainningId = exEntity.getParent().getId();
			String titleEx = (String) exEntity.getProperty("title"); 
			String descriptionEx = (String) exEntity.getProperty("description"); 
			Long idExercice = (Long) exEntity.getKey().getId();
			int duration = Ints.checkedCast((Long) exEntity.getProperty("duration"));
			Exercice exercice = new Exercice();
			
			exercice.setTrainningId(trainningId);
			exercice.setTitle(titleEx);
			exercice.setDescription(descriptionEx);
			exercice.setDuration(duration);
			exercice.setId(idExercice);
			
			exercicesMatchingName.add(exercice);								
		}		
		return exercicesMatchingName;
	}
	
	public Exercice getExerciceById(Long id){

		Exercice exercice = new Exercice();
		
		Key key = KeyFactory.createKey("Exercice", id);
		Entity exerciceEntity = null;
		try {
			exerciceEntity = datastore.get(key);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		
		if(exerciceEntity != null){
			String title = (String) exerciceEntity.getProperty("title"); 
			String description = (String) exerciceEntity.getProperty("description");
			int duration = (Integer) exerciceEntity.getProperty("duration");
			Long idTrainning = (Long) exerciceEntity.getKey().getId();
			
			exercice.setTitle(title);
			exercice.setDescription(description);
			exercice.setDuration(duration);
			exercice.setId(idTrainning);
		
		}
		
		return exercice;
		
	}
}
