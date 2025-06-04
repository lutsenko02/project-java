package controller;
import jakarta.servlet.RequestDispatcher;
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

/**
* Servlet implementation class RoleServlet_
*/
@WebServlet("/district")
public class DistrictServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public DistrictServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        List<District> districts;
        DistrictDbDAO dao = new DistrictDbDAO();
        
        try {
            districts = dao.findAll();
            request.setAttribute("districts", districts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/district.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DistrictDbDAO dao = new DistrictDbDAO();
        
        try {
            String name = request.getParameter("name");
            String area = request.getParameter("area");
            String year = request.getParameter("year");
            String numberOfPeopleStr = request.getParameter("numberOfPeople");
            String idStr = request.getParameter("id");
            
            int numberOfPeople = 0;
            if (numberOfPeopleStr != null && !numberOfPeopleStr.isEmpty()) {
                numberOfPeople = Integer.parseInt(numberOfPeopleStr);
            }
            
            Long id = null;
            if (idStr != null && !idStr.isEmpty()) {
                id = Long.parseLong(idStr);
            }
            
            District district = new District(id, name, area, year, numberOfPeople);
            
            if (id == null) {
                dao.insert(district);
            } else {
                dao.update(district);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/district");
    }
}