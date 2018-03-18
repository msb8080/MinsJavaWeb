package com.servlets;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.Department;
import com.beans.Employee;
import com.beans.Location;
import com.dao.BaseDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName  = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private BaseDao baseDao = new BaseDao();
	protected void listLocations(HttpServletRequest request,HttpServletResponse response) {
		String sql = "SELECT location_id locationId,city FROM locations";
		List<Location> locations = baseDao.getForList(sql, Location.class);
		request.setAttribute("locations", locations);
		try {
//			response.sendRedirect("");
			request.getRequestDispatcher("WEB-INF/pages/employee.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	protected void listDepartments(HttpServletRequest request,HttpServletResponse response) {
		String locationId = request.getParameter("locationId");
		System.out.println(locationId);
		String sql = "SELECT department_Id departmentId,department_name departmentName "
				+ "FROM departments d "+"WHERE d.location_id = ?";
		List<Department> departments = baseDao.getForList(sql, Department.class, Integer.parseInt(locationId));
		ObjectMapper mapper = new ObjectMapper();
		try {
			String results = mapper.writeValueAsString(departments);
			System.out.println(results);
			response.setContentType("text/javascript");
			response.getWriter().write(results);
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
	protected void listEmployees(HttpServletRequest request,HttpServletResponse response) {
		String departmentId = request.getParameter("departmentId");
		System.out.println(departmentId);
		String sql = "SELECT employee_id employeeId,last_name lastName"
				+ " FROM employees e "+"WHERE e.department_id = ?";
		List<Employee> employees = baseDao.getForList(sql, Employee.class, Integer.parseInt(departmentId));
		ObjectMapper mapper = new ObjectMapper();
		try {
			String results = mapper.writeValueAsString(employees);
			System.out.println(results);
			response.setContentType("text/javascript");
			response.getWriter().write(results);
		} catch (IOException e) {
			e.printStackTrace();	
		}
	}
	//根据员工Id查询出员工信息
	protected void listEmployeeInfo(HttpServletRequest request,HttpServletResponse response) {
		//获取要查询的Id，并打印到控制台
		String employeeId = request.getParameter("employeeId");
		System.out.println(employeeId);
		//使用dao进行查询
		String sql = "SELECT employee_id employeeId,last_name lastName,email email,salary salary "
				+"FROM employees WHERE employee_id = ?";
		//将查询结果编程JSON字符串，并响应给客户端
		Employee employeeinfo = baseDao.get(sql, Employee.class, Integer.parseInt(employeeId));
		ObjectMapper mapper = new ObjectMapper();
		try {
			String result = mapper.writeValueAsString(employeeinfo);
			response.setContentType("text/javascript");
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

		
}
