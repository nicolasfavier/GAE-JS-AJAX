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
    	String bodyRequest = getBody(request);
    	Gson gson = new Gson();
    	Trainning trainningSubmited = gson.fromJson(bodyRequest, Trainning.class);
    	trainningDao.createTrainning(trainningSubmited);
	    
		//Send the Json object to the web browser
		PrintWriter out= response.getWriter();
		out.write("TrainningServlet");
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
