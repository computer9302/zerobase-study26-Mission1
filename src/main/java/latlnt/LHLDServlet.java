package latlnt;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import mission1.ApiExplorer;
import mission1.LocationHistoryListDto;

/**
 * Servlet implementation class LHLDServlet
 */
@WebServlet("/LHLDServlet")
public class LHLDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LHLDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApiExplorer apiExplorer = new ApiExplorer();
		ArrayList<LocationHistoryListDto> dataList = apiExplorer.selectLocationHistoryList();
		
		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(dataList));
	}


}
