package servlet.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Exercice;
import models.Trainning;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

import dao.ExerciceDao;
import dao.TrainningDao;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String search = request.getParameter("search");
		TrainningDao TDAO = new TrainningDao();
		ExerciceDao EDAO = new ExerciceDao();
		List<Trainning> listTrainning = TDAO.getListTrainningByName(search);
		List<Exercice> listExercice = EDAO.getListExerciceByName(search);

		Gson gson = new Gson();
		String TrainningJSON = gson.toJson(listTrainning);
		String ExerciceJSON = gson.toJson(listExercice);
		
		String jsonToSend = "{ \"listTrainning\" : " +TrainningJSON + ",\"listExercice\" : " +ExerciceJSON + ", \"fluxrss\" : "+ getFluxRss() +" }";

		PrintWriter out= response.getWriter();
		out.write(jsonToSend);
	}

	private String getFluxRss() {
		String str = "";
		try {
			URL url = new URL(
					"https://ajax.googleapis.com/ajax/services/feed/load?v=1.0&q=http://www.runnersworld.com/taxonomy/term/740/1/feed");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				str += line;
			}
			reader.close();

		} catch (MalformedURLException e) {
			System.out.print(e);
		} catch (IOException e) {
			System.out.print(e);
		}
		return str;
	}
}