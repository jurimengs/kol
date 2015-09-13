package com.server.sent.event;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServerSentEvent
 */
public class ServerSentEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServerSentEvent() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("From Get....................");
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String name = (String) headers.nextElement();
			System.out.println(name+":"+request.getHeader(name));	
		}
		System.out.println("--------------------------------");
		
		response.setHeader("Content-Type", "text/event-stream");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding ("UTF-8");
		
		String id = new Date().toString();
		response.getWriter().println("id:"+id);
		response.getWriter().println("data:server-sent event is working.");
		response.getWriter().println("data:test server-sent event multi-line data");
		response.getWriter().println();
		response.getWriter().flush();
	}

}
