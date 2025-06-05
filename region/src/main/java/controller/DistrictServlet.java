package controller;

import domain.District;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.DistrictDbDAO;
import dao.ConnectionProperty;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class DistrictServlet
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
		String userPath;
		List<District> districts;

		DistrictDbDAO daoDistrict = new DistrictDbDAO();

		try {
			districts = daoDistrict.findAll();
			request.setAttribute("districts", districts);

		} catch (Exception e) {
			e.printStackTrace();
		}

		userPath = request.getServletPath();
		
		if ("/district".equals(userPath)) {
			request.getRequestDispatcher("/views/district.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DistrictDbDAO daoDistrict = new DistrictDbDAO();

		try {
			// Получаем параметры из формы
			String name = request.getParameter("nameDistrict");
			String area = request.getParameter("areaDistrict");
			String year = request.getParameter("yearDistrict");
			String numberOfPeopleStr = request.getParameter("peopleDistrict");
			String id = request.getParameter("id");

			// Преобразуем числовые параметры
			int numberOfPeople = 0;
			if (numberOfPeopleStr != null && !numberOfPeopleStr.isEmpty()) {
				numberOfPeople = Integer.parseInt(numberOfPeopleStr);
			}

			Long idID = null;
	        if (id != null && !id.isEmpty()) {
	            String digitsOnlyId = id.replaceAll("\\D+", "");
	            if (!digitsOnlyId.isEmpty()) {
	                idID = Long.parseLong(digitsOnlyId);
	            }
	        }
			
			// Создаем новый район
			District newDistrict = new District();

			newDistrict.setId(idID);
			newDistrict.setnamedistrict(name);
			newDistrict.setDistrictArea(area);
			newDistrict.setDistrictYear(year);
			newDistrict.setDistrictNumberOfPeople(numberOfPeople);

			// Добавляем или обновляем район в базе данных
			Long index = daoDistrict.insert(newDistrict);
			System.out.println("Район успешно добавлен. ID: " + index);

			// Перенаправляем на страницу с обновленным списком
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/district.jsp");
			dispatcher.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Ошибка при обработке района: " + e.getMessage());
		}

		doGet(request, response);
	}
}