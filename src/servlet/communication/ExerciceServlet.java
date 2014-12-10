package servlet.communication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Exercice;

import com.google.gson.Gson;

import dao.ExerciceDao;

public class ExerciceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExerciceServlet() {
        super();
    }
    
   @Override
   public void init() throws ServletException {
	   super.init();
   }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ExerciceDao exerciceDao = new ExerciceDao();
		List<Exercice> exercices = new ArrayList<Exercice>();
		
		String search = request.getParameter("search");		
		exercices = exerciceDao.getListExerciceByName(search);
		
		Gson gson = new Gson();
		String exercicesJSON = gson.toJson(exercices);
		
		//Send the Json object to the web browser
		PrintWriter out= response.getWriter();
		out.write(exercicesJSON);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Send the Json object to the web browser
			PrintWriter out= response.getWriter();
			out.write("ExerciceServletextends");
	}

}