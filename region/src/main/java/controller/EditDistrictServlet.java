package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import domain.District;
import dao.DistrictDbDAO;
import dao.ConnectionProperty;
@WebServlet("/editdistrict")
public class EditDistrictServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
ConnectionProperty prop;

 public EditDistrictServlet()
throws FileNotFoundException, IOException {
 super();
 prop = new ConnectionProperty();
 }
 
protected void doGet(HttpServletRequest request,
HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");
	String userPath;
	List<District> districts;
	District editdistrict = null;
	DistrictDbDAO dao = new DistrictDbDAO();
	
	try {
		districts = dao.findAll();
		request.setAttribute("districts", districts);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	String strId = request.getParameter("id");
	
	Long id = null;
	if(strId != null) {
		id = Long.parseLong(strId);
	}
	
	try {
		editdistrict  = dao.findById(id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	request.setAttribute("districtEdit", editdistrict);
	userPath = request.getServletPath();
	
	if ("/editdistrict".equals(userPath)) {
		request.getRequestDispatcher("/views/editdistrict.jsp")
		.forward(request, response);
	}
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	DistrictDbDAO dao = new DistrictDbDAO();
	String strId = request.getParameter("id");
	String name = request.getParameter("nameDistrict");
	String area = request.getParameter("areaDistrict");
	String year = request.getParameter("yearDistrict");
	String number = request.getParameter("numberDistrict");
	
	Long id = null;
	if(strId != null) {
		id = Long.parseLong(strId);
	}
	
	int numberOfPeople = 0;
	if(number != null) {
		numberOfPeople = Integer.parseInt(number);
	}
	
	District editdistrict = new District(id, name, area, year, numberOfPeople);
	
	try {
		dao.update(editdistrict);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	response.sendRedirect("/region/district");
}
}