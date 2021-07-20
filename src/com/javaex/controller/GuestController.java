package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;




@WebServlet("/gbc")
public class GuestController extends HttpServlet {


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러");
		
		//액션값 읽어옴
		String action = request.getParameter("action");
		
		//액션값 콘솔창에 표출
		System.out.println(action);
	
		if("list".equals(action)) {
			System.out.println("[글쓰기폼]");
			//리스트업무
			
			//리스트
			GuestbookDao guestbookdao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookdao.getGuestbookList(); 
			
			System.out.println("controller===============");
			System.out.println(guestbookList);
		
			request.setAttribute("gList", guestbookList);
			
			//html작업 ----> jsp에게 시킨다. --> forword 포워드p
			RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/guestbook/addlist.jsp");
			rd.forward(request, response);
		
		}else if("add".equals(action)) {
			System.out.println("[등록]");
			
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");
			
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookInsert(guestbookVo);
			System.out.println(guestbookVo);
			
			response.sendRedirect("/mysite/gbc?action=list");
		}else if ("dform".equals(action)) {
			System.out.println("[삭제폼]");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(request, response);
			
		}else if ("delete".equals(action)) {
			System.out.println("[삭제]");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			
			request.setCharacterEncoding("UTF-8");
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			GuestbookVo guestbookVo = new GuestbookVo();
			guestbookVo.setNo(no);
			guestbookVo.setPassword(password);
			
			guestbookDao.guestbookDelete(guestbookVo);
			
			response.sendRedirect("/mysite/gbc?action=list");
			
		}
		
		
		
		
		
	
	}


	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
