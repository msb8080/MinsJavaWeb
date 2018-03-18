<%
	System.out.println(this.getServletConfig().getServletContext().getRealPath("/"));
   response.sendRedirect("../EmployeeServlet?method=listLocations");
%>