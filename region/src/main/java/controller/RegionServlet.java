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
import domain.Region;
import dao.RegionDbDAO;
import dao.DistrictDbDAO;
import dao.ConnectionProperty;


/**
* Servlet implementation class RoleServlet_
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
        
        RegionDbDAO regionDao = new RegionDbDAO();
        DistrictDbDAO districtDao = new DistrictDbDAO();
        
        try {
            regions = regionDao.findAll();
            districts = districtDao.findAll();
            
            // Устанавливаем полные объекты District для каждого региона
            for (Region region : regions) {
                if (region.getDistrict() != null && region.getDistrict().getId() != null) {
                    District district = districtDao.findById(region.getDistrict().getId());
                    region.setDistrict(district);
                }
            }
            
            request.setAttribute("regions", regions);
            request.setAttribute("districts", districts);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        userPath = request.getServletPath();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/region.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RegionDbDAO dao = new RegionDbDAO();
        DistrictDbDAO districtDao = new DistrictDbDAO();
        
        try {
            String regionName = request.getParameter("regionName");
            String regionArea = request.getParameter("regionArea");
            String regionCity = request.getParameter("regionCity");
            String regionHead = request.getParameter("regionHead");
            String districtIdStr = request.getParameter("districtId");
            String idStr = request.getParameter("id");
            
            Long id = null;
            if (idStr != null && !idStr.isEmpty()) {
                id = Long.parseLong(idStr);
            }
            
            District district = null;
            if (districtIdStr != null && !districtIdStr.isEmpty()) {
                Long districtId = Long.parseLong(districtIdStr);
                district = districtDao.findById(districtId);
            }
            
            Region region = new Region(id, regionName, regionArea, regionCity, regionHead, district);
            
            if (id == null) {
                dao.insert(region);
            } else {
                dao.update(region);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/region");
    }
}
