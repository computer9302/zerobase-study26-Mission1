package latlnt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LatLntBeanServlet
 */
@WebServlet("/positionForm")
public class LatLntBeanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Double lat;
	public static Double lnt;

    /**
     * Default constructor. 
     */
    public LatLntBeanServlet() {
        // TODO Auto-generated constructor stub
    	super();
    }

	 // static Double myLat;
	// static Double myLnt;
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	        // myLat = Double.parseDouble(request.getParameter("lat"));
	        // myLnt = Double.parseDouble(request.getParameter("lnt"));
		 	
		 	lat = Double.parseDouble(request.getParameter("lat"));
		 	lnt = Double.parseDouble(request.getParameter("lnt"));
	       
		 	// System.out.println(myLat+ " " + myLnt);
		 	System.out.println("Latitude: " + lat);
		 	System.out.println("Longitude: " + lnt);
		 	
	    
	        
	        // request.setAttribute("lat", myLat);
	        // request.setAttribute("lnt", myLnt);
		 	response.setContentType("text/plain");
		 	response.getWriter().write("Location received successfully");
	        
	        //RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	        //dispatcher.forward(request,  response);
	 	}

}
