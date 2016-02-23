<%@page import ="com.bigdata2016.guestbook.vo.GuestbookVo" %>
<%@page import ="com.bigdata2016.guestbook.dao.GuestbookDao" %>
<%
	request.setCharacterEncoding("UTF-8");

	GuestbookVo vo = new GuestbookVo();
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String message = request.getParameter("content");

	
	GuestbookDao dao = new GuestbookDao();
	dao.insert(vo);
	
	response.sendRedirect("/guestbook2");
%>

<h1>성공했습니다.</h1>