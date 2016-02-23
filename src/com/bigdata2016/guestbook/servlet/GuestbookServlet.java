package com.bigdata2016.guestbook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2016.guestbook.dao.GuestbookDao;
import com.bigdata2016.guestbook.vo.GuestbookVo;

@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException	{
			request.setCharacterEncoding("UTF-8");	
			String actionName = request.getParameter("a");
			
		if("add".equals(actionName)) {
			request.setCharacterEncoding("UTF-8");
			
			GuestbookVo vo = new GuestbookVo();
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String message = request.getParameter("content");

			vo.setName(name);
			vo.setPassword(password);
			vo.setMessage(message);
			
			GuestbookDao dao = new GuestbookDao();
			dao.insert(vo);
			
			response.sendRedirect("/guestbook2");
				
		}else if("deleteform".equals(actionName)){
			
			//포워딩, request 연장
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
		}else if("delete".equals(actionName)){
			 String no = request.getParameter("no");
			   String password = request.getParameter("password");
			   
			   GuestbookVo vo = new GuestbookVo();
			   vo.setNo(Long.parseLong(no));
			   vo.setPassword(password);
			   
			   GuestbookDao dao = new GuestbookDao();
			   dao.delete(vo);
			   
			   response.sendRedirect("/guestbook2");
		}else {
				/*index(default)action */
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();
			
			//view에 전달할 객체를 request범위에 저장
			request.setAttribute("list", list);
			
			//포워딩, request 연장
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}


}
