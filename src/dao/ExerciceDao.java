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
}
