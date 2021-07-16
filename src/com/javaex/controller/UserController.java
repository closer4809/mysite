package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("[UserController]");
    	
    	
    	//파라미터 가져오기(업무구분)
    	String action = request.getParameter("action");
    	
    	//액션값 콘솔창에 표출
		System.out.println(action);
    	
    	if("joinForm".equals(action)) {
    		System.out.println("[UserController.joinForm]");

    		//회원가입폼 포워드
	    	WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
    	}else if("join".equals(action)) {
    		System.out.println("[UserController.join]");
    	
    		//*****회원가입*****

    		//파라미터 꺼내기
    		String id = request.getParameter("id");
    		String pw = request.getParameter("pw");
    		String name = request.getParameter("name");
    		String gender = request.getParameter("gender");
    		System.out.println(id+","+pw+","+name+","+gender);
    		
    		//vo만들기
    		UserVo userVo = new UserVo(id, pw, name, gender);
    		System.out.println(userVo);
    		
    		
    		//*dao.insert(vo) --> db저장
    		UserDao userDao = new UserDao();
    		int count = userDao.userInsert(userVo);
    		//포워드
    		
            WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
    		
    	}else if("loginForm".equals(action)) {
    		System.out.println("[UserController.loginForm]");
    		//로그인폼 포워
    		WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
    	}else if("login".equals(action)) {
    		System.out.println("[UserController.login]");
    		
    		//파라미터에 값 꺼내
    		String id = request.getParameter("id");
    		String password = request.getParameter("pw");
    		
    		System.out.println(id+", "+password);
    		
    		//dao 회원정보 조회하기(세션 저장용)
    		UserDao uesrDao = new UserDao();
    		UserVo userVo = uesrDao.getUser(id, password);
    		
    		if(userVo !=null) {
	    		//성공일때(아이디 비번 맞을때) 세션에 저장
	    		HttpSession session = request.getSession();
	    		session.setAttribute("authUser", userVo);
	    	
	    		//리다이렉트 --> 실패일때 작성해야함!
	    		WebUtil.redirect(request, response, "/mysite/main");
    		}else {
    			System.out.println("로그인 실패");
    			//리다이렉트 
                //리다이렉트 -로그인폼 페이지
                WebUtil.redirect(request, response, "/mysite/user?action=loginForm&result=fail");
             
    		}
    	}else if("logout".equals(action)) {
    		System.out.println("[UserController.logout]");
    		
    		HttpSession session = request.getSession();
    		session.removeAttribute("authUser");
    		session.invalidate();
    		
    		WebUtil.redirect(request, response, "/mysite/main");
    	}else if("modifyForm".equals(action)) {
			System.out.println("[UserController.modifyForm]");
			
			/*
			HttpSession session = request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			int no = authUser.getNo();
			
			UserDao userDao = new UserDao();
			UserVo userVo = userDao.getUser(no);
			
			request.setAttribute("uVo", userVo);
			*/
			
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
    	}else if("modify".equals(action)) {
			System.out.println("[UserController.modify]");
			
			
			HttpSession session = request.getSession();
			int no = ((UserVo)session.getAttribute("authUser")).getNo();
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(no,password, name, gender);
			
			UserDao userDao = new UserDao();
			userDao.modifyUser(userVo);
			
			((UserVo)session.getAttribute("authUser")).setName(name);
			
			
			WebUtil.redirect(request, response, "/mysite/main");
		}
    
    	
	}
    	
    

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
