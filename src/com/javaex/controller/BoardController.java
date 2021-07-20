package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;


@WebServlet("/bc")
public class BoardController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("보드 컨트롤러");
		
		//액션값 읽어옴
		String action = request.getParameter("action");
		
		//액션값 콘솔창에 표출
		System.out.println(action);
		if("list".equals(action)) {
			System.out.println("[리스트]");
			//리스트업무
			
			BoardDao boarddao = new BoardDao();
			List<BoardVo> boardList = boarddao.getBoardList();
			System.out.println("controller===============");
			System.out.println(boardList);
		
			request.setAttribute("bList", boardList);
			
			RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
			rd.forward(request, response);
		}
		
	
	
	}


    

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
