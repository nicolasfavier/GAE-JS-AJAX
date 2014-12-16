package dao;

import java.util.ArrayList;
import java.util.List;

import models.PendingTrainning;
import models.Trainning;
import models.User;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
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
	private PendingTrainningDao pendingTrainningDao;
	
	public UserDao(){
		datastore = DatastoreServiceFactory.getDatastoreService();
		pendingTrainningDao = new PendingTrainningDao();
		userEntity = new Entity("User");
	}
	
	public User createUser(String nickname){
		User user = getUser(nickname);
		if( user == null){
			userEntity.setProperty("nickname", nickname);
			datastore.put(userEntity);
		}
		return user;
	}
	
	public User getUser(String nickname){
		Filter nicknameFilter = new FilterPredicate("nickname", FilterOperator.EQUAL, nickname);
		Query q =  new Query("User").setFilter(nicknameFilter);
		PreparedQuery pq = datastore.prepare(q);
		User user = null;

		for(Entity userEntity : pq.asIterable()){
			user = new User();
			String nicknameEntity = (String) userEntity.getProperty("nickname"); 
			Key keyEntity = userEntity.getKey();
			
			List<PendingTrainning> pendingTrainningList = new ArrayList<PendingTrainning>();
			pendingTrainningList = pendingTrainningDao.getListPendingTrainning(keyEntity);
			
			user.setNickname(nicknameEntity);
			user.setKey(keyEntity);
			user.setPendingTrainnings(pendingTrainningList);
		}		
		return user;
	}
}
