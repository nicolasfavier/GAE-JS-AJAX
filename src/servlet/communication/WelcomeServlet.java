package servlet.communication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;


public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WelcomeServlet() {
        super();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		
		Cache cache = null;
		Map props = new HashMap();
		props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
		props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
		
		try{
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(props);
		}
		catch(CacheException e ){
			
		}
		String key="contentWelcome";
		String value = (String) cache.get(key);

		if (value == null){
			Entity msgEntity = new Entity("Welcome");
			value = "Sed fruatur sane hoc solacio atque hanc insignem ignominiam, "
					+ "quoniam uni praeter se inusta sit, putet esse leviorem, dum modo, "
					+ "cuius exemplo se consolatur, eius exitum expectet, praesertim cum "
					+ "in Albucio nec Pisonis libidines nec audacia Gabini fuerit ac tamen "
					+ "hac una plaga conciderit, ignominia senatus.";
			
			msgEntity.setProperty("content", value);
			datastore.put(msgEntity);
			
			cache.put(key, value);
			System.out.println("Not in cache");
		}
		else{
			System.out.println("In cache");
		}
		response.getWriter().println(value);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {}

}
