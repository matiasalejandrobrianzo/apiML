package org.glassfish.jersey.archetypes;

import java.io.IOException;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/stats")
public class SrvStats extends HttpServlet {
	
	private Gson _gson = null;
	private ClassResADN _adn ;
	private SrvDAO _dao;
	
	public SrvStats()
	{
		_gson= new Gson();
		
		_dao= new SrvDAO();
	}
	
	  @Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
			  throws IOException {
		  try {
			  	
			  _adn = new ClassResADN();			  
			  List<ClassRegistro> reg= _dao.getRegistro();			   
			   _adn.CalculoPromedio(reg);
			   
			   response.setContentType("application/json"); 
			   response.getOutputStream().print(_gson.toJson(_adn));
			   response.getOutputStream().flush();  
			
		} catch (Exception e) {			
	            ClssStatus status = new ClssStatus();	 
	            status.setDescription("Se produjo un error");
	            status.setStatus("403-Forbidden");
	            response.getOutputStream().print(_gson.toJson(status));
	            response.getOutputStream().flush();
		}		  
	  }
}
