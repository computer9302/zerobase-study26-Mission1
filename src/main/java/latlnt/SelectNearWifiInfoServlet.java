package latlnt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mission1.ApiExplorer;
import mission1.ApiKmDto;

import java.util.ArrayList;

/**
 * Servlet implementation class SelectNearWifiInfoServlet
 */
@WebServlet("/selectNearWifiInfo")
public class SelectNearWifiInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectNearWifiInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApiExplorer apiExplorer = new ApiExplorer();
		ArrayList<ApiKmDto> wifiInfoList = apiExplorer.selectNearWifiInfo();
	
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		String jsonResponse = gson.toJson(wifiInfoList);
		response.getWriter().write(jsonResponse);
	
	}

}
