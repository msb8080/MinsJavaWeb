package com.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取请求参数：id书名，单价price
		String bookName = request.getParameter("id");
		int price = Integer.parseInt(request.getParameter("price"));
		//获取购物车对象，应该在session
		HttpSession session = request.getSession();
		ShoppingCart sc = (ShoppingCart) session.getAttribute("sc");
		//判断浏览器中session是否存在购物车对象
		if(sc ==null) {
			sc = new ShoppingCart();
			session.setAttribute("sc", sc);
		}
		//将商品添加到购物车对象,
		sc.addToCart(bookName, price);
//		StringBuilder result = new StringBuilder();
//		result.append("{")
//			.append("\"bookName\":\""+bookName+"\""+",")
//			.append("\"totalBookNumber\":"+sc.getTotalBookNumber()+",")
//			.append("\"totalPrice\":"+sc.getTotalPrice())
//			.append("}");
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(sc);
		System.out.println(result);
		response.setContentType("text/javascript");
		response.getWriter().print(result);
		//返回添加商品名，购物车中，商品总数，商品总价格
	}

}
