package servlet.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Trainning;

import com.google.gson.Gson;

import dao.TrainningDao;

public class TrainningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TrainningServlet() {
        super();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TrainningDao trainningDao = new TrainningDao();
		Trainning trainning = new Trainning();
		
		
		String id = request.getParameter("id");
		String kind = request.getParameter("kind");
		
		PrintWriter out= response.getWriter();
		Gson gson = new Gson();
		
		if ( id!= null){
			Long longID = Long.decode(id);
			
			trainning = trainningDao.getTrainningById(longID);
			
			String trainningJSON = gson.toJson(trainning);
			
			out.write(trainningJSON);
		}
		
		if(kind != null){
			List<Trainning> trainnings = new ArrayList<Trainning>();
			trainnings = trainningDao.getTrainningByKind(kind);
			
			String trainningJSON = gson.toJson(trainnings);
			
			out.write(trainningJSON);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		TrainningDao trainningDao = new TrainningDao();
		String bodyRequest = request.getParameter("json");
    	Gson gson = new Gson();
    	Trainning trainningSubmited = gson.fromJson(bodyRequest, Trainning.class);
    	trainningDao.createTrainning(trainningSubmited);
	    
		//Send the Json object to the web browser
		PrintWriter out= response.getWriter();
		out.write("TrainningServlet");
	}
	

}
