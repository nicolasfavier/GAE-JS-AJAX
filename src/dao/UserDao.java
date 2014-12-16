package dao;

import java.util.ArrayList;
import java.util.List;

import models.PendingTrainning;
import models.Trainning;
import models.User;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class UserDao {

	private DatastoreService datastore;
	private Entity userEntity;
	
	public UserDao(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		userEntity = new Entity("User");
	}
	
	public void createUser(String nickname){
		if(getUser(nickname) != null){
			userEntity.setProperty("nickname", nickname);
			datastore.put(userEntity);
		}
	}
	
	public User getUser(String nickname){
		Filter nicknameFilter = new FilterPredicate("nickname", FilterOperator.EQUAL, nickname);
		Query q =  new Query("User").setFilter(nicknameFilter);
		PreparedQuery pq = datastore.prepare(q);
		User user = null;

		for(Entity userEntity : pq.asIterable()){
			user = new User();
			String nicknameEntity = (String) userEntity.getProperty("nickname"); 
			user.setNickname(nicknameEntity);			
		}		
		return user;
	}
	
	List<PendingTrainning> getListPendingTrainning(Key userKey){
		
		List<PendingTrainning> listPendingTrainning = new ArrayList<PendingTrainning>();
		
		Query q = new Query("PendingTrainning").setAncestor(userKey);
		PreparedQuery pq = datastore.prepare(q);
		
		
		for(Entity pendingTrainningEntity : pq.asIterable()){
			
			Trainning trainningEntity = (Trainning) pendingTrainningEntity.getProperty("trainning");
			Long idPendingTrainningEntity = (Long) pendingTrainningEntity.getKey().getId();

			PendingTrainning pendingTrainning = new PendingTrainning();
			
			pendingTrainning.setId(idPendingTrainningEntity);
			pendingTrainning.setTrainning(trainningEntity);
			
			listPendingTrainning.add(pendingTrainning);								
		}
		return listPendingTrainning;
	}

}
