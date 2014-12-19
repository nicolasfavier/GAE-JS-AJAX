package servlet.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import models.PendingTrainning;
import models.Trainning;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

import dao.PendingTrainningDao;
import dao.TrainningDao;
import dao.UserDao;

public class PendingTrainningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PendingTrainningServlet() {
        super();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PendingTrainningDao pendingTrainningDao = new PendingTrainningDao();
		PendingTrainning pendingTrainning = new PendingTrainning();
		
		
		String id = request.getParameter("id");
		
		PrintWriter out= response.getWriter();
		Gson gson = new Gson();
		if ( id != null){
			
			UserService userService = UserServiceFactory.getUserService();
			User user = userService.getCurrentUser(); // or req.getUserPrincipal()

			if (user != null) {
				String nickNname = user.getNickname();
				UserDao userDao = new UserDao();
				models.User u = userDao.createUser(nickNname);
				
				Long longID = Long.decode(id);
				
				pendingTrainning = pendingTrainningDao.createOrGetPendingTrainning(longID, u.getKey());
				
				String pendingTrainningJSON = gson.toJson(pendingTrainning);
				out.write(pendingTrainningJSON);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser(); 

		if (user != null) {
			String nickNname = user.getNickname();
			UserDao userDao = new UserDao();
			models.User u = userDao.createUser(nickNname);
				
			PendingTrainningDao pendingTrainningDao = new PendingTrainningDao();
	    	String bodyRequest = getBody(request);
	    	Gson gson = new Gson();
	    	PendingTrainning pendingTrainningSubmited = gson.fromJson(bodyRequest, PendingTrainning.class);
	    	pendingTrainningDao.updatePendingTrainning(pendingTrainningSubmited, u.getKey());
		    
			//Send the Json object to the web browser
			PrintWriter out= response.getWriter();
			out.write("");
		}
	}
	
	public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }

}
