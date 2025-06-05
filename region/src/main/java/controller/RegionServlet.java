package controller;

import domain.District;
import domain.Region;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.RegionDbDAO;
import dao.ConnectionProperty;
import dao.DistrictDbDAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class RegionServlet
 */
@WebServlet("/region")
public class RegionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ConnectionProperty prop;

    public RegionServlet() throws FileNotFoundException, IOException {
        super();
        prop = new ConnectionProperty();
    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	        response.setContentType("text/html");
	        String userPath;
	        List<Region> regions;
	        List<District> districts;

	        RegionDbDAO daoRegion = new RegionDbDAO();
	        DistrictDbDAO daoDistrict = new DistrictDbDAO();
	
	        try {
	        	regions = daoRegion.findAll();
	            request.setAttribute("regions", regions);
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        try {
	        	districts = daoDistrict.findAll();
	            request.setAttribute("districts", districts);
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	
	        userPath = request.getServletPath();
	        
	        if ("/region".equals(userPath)) {
	            request.getRequestDispatcher("/views/region.jsp").forward(request, response);
	        }
	    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RegionDbDAO daoRegion = new RegionDbDAO();

        try {
            String name = request.getParameter("nameRegion");
            String area = request.getParameter("areaRegion");
            String city = request.getParameter("cityRegion");
            String head = request.getParameter("headRegion");
            String district = request.getParameter("district");
            String id = request.getParameter("id");
            
            int index1 = district.indexOf('=');
            int index2 = district.indexOf(",");
            String d1 = district.substring(index1+1, index2);
            Long idDi = Long.parseLong(d1.trim());
            
            Long idID = null;
	        if (id != null && !id.isEmpty()) {
	            String digitsOnlyId = id.replaceAll("\\D+", "");
	            if (!digitsOnlyId.isEmpty()) {
	            	idID = Long.parseLong(digitsOnlyId);
	            }
	        }
	        
            Region newRegion = new Region(idID, name, area, city, head, idDi);

            String successMessage;
            if (id != null) {
                // Обновляем существующий район
            	daoRegion.update(newRegion);
                successMessage = "Область успешно добавлена";
            } else {
                // Создаем новый район
                Long index = daoRegion.insert(newRegion);
                successMessage = "Область успешно добавлена. ID: " + index;
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/region.jsp");
			dispatcher.include(request, response);

        } catch (Exception e) {
            e.printStackTrace();            
            request.setAttribute("error", "Ошибка при обработке области: " + e.getMessage());
        }
        
		doGet(request, response);
    }
}