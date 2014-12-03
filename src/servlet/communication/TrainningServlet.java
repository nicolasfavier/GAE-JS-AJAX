package servlet.communication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Send the Json object to the web browser
			PrintWriter out= response.getWriter();
			out.write("TrainningServlet");
	}

}
