package servlet.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Exercice;
import models.PendingTrainning;

import org.json.simple.JSONObject;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import dao.ExerciceDao;
import dao.PendingExerciceDao;
import dao.PendingTrainningDao;
import dao.UserDao;

public class OpenIdServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Map<String, String> openIdProviders;

	static {
		openIdProviders = new HashMap<String, String>();
		openIdProviders.put("Google", "https://www.google.com/accounts/o8/id");
		openIdProviders.put("Yahoo", "yahoo.com");
		openIdProviders.put("MySpace", "myspace.com");
		openIdProviders.put("AOL", "aol.com");
		openIdProviders.put("MyOpenId.com", "myopenid.com");
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser(); // or req.getUserPrincipal()
		Set<String> attributes = new HashSet();

		// Format the answer
		resp.setContentType("application/json");
		JSONObject jsonToSend;
		jsonToSend = new JSONObject();

		if (user != null) {
			String nickNname = user.getNickname();
			UserDao userDao = new UserDao();
			models.User u = userDao.createUser(nickNname);
			
			/******** TEST ********/
			
			//PendingTrainningDao pendingTrainningDao = new PendingTrainningDao();
			//Long id = Long.decode("5647091720257536");
			//PendingTrainning pendingTraining = pendingTrainningDao.createOrGetPendingTrainning(id, u.getKey());
			
			//PendingExerciceDao pendingExerciceDao = new PendingExerciceDao();
			//Long idEx = Long.decode("5084141766836224");
			//ExerciceDao exerciceDao = new ExerciceDao();
			//PendingExercice pendingExercice = pendingExerciceDao.createOrGetPendingExercice(idEx, id);	
			/******** TEST ********/
			
			
			jsonToSend.put("userNickname", nickNname);
			jsonToSend.put("logoutUrl",userService.createLogoutURL("/ha-search-screen.html"));
		}
		// Send the Json object to the web browser
		PrintWriter out = resp.getWriter();
		out.write(jsonToSend.toString());

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser(); // or req.getUserPrincipal()
		Set<String> attributes = new HashSet();
		String providerUrl = "";
		String type = req.getParameter("type");

		// Format the answer
		resp.setContentType("application/json");
		JSONObject jsonToSend;
		jsonToSend = new JSONObject();

		if (type.equals("Google")) {
			providerUrl = openIdProviders.get("Google");
		}
		else if (type.equals("Yahoo")) {
			providerUrl = openIdProviders.get("Yahoo");
		}
		else {
			providerUrl = openIdProviders.get("MyOpenId.com");
		}

		String loginUrl = userService.createLoginURL("/ha-search-screen.html", null,
				providerUrl, attributes);

		jsonToSend.put("Url", loginUrl);

		PrintWriter out = resp.getWriter();
		out.write(jsonToSend.toString());

	}
}