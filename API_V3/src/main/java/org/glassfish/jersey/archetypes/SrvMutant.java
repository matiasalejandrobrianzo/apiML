package org.glassfish.jersey.archetypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/mutant/*")
public class SrvMutant  extends HttpServlet {
	
	private Gson _gson = null;
	private ClssSecuenciaADN _Secc;
	private ClssMutant _data;
	private SrvDAO _dao;
	public SrvMutant()
	{
		super();
		_gson = new Gson();
		_Secc=new ClssSecuenciaADN();
		_data= new ClssMutant();
		_dao= new SrvDAO();
	}
		
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 processRequest(request, response);	    	
	 }	 
	
	  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	     
	        try {
	        	ClssStatus status= new ClssStatus();
	        	
	        	 BufferedReader reader =request.getReader();
	        	 _data = _gson.fromJson(reader, ClssMutant.class);
	        	 
	        	//Valido la cadena JSON si esta compuesta por (A,T,C,G)
		    	  if(_data.getDna()!=null && _Secc.SecuenciaValida(_data.getDna()) )
		           {
		    		  if( _Secc.isMutant(_data.getDna()))
			              	{
			              		//Agrego en BD
			  	        		_dao.insert(1,0);          		 
			      	            status.setSuccess(true);
			      	            status.setStatus("HTTP 200-OK");
			              	}else
			              	{
			              		//Agrego en BD
			              		_dao.insert(0,1);
			              		status.setSuccess(false);
			      	            status.setStatus("403-Forbidden");
			              	}		    		  
		           }
		    	  else
		    	  {  
			    		status.setDescription("El Formato json es invalido,O la secuencia de ADN es Invalida:solo pueden ser: (A,T,C,G)");
			            status.setStatus("403");	
		    	  }
		    	  
	        	StatusRequest(request,response,status);	            
	            
	        } catch (Exception ex) {
	            
	            ClssStatus status = new ClssStatus();	 
	            status.setDescription("Se produjo un error, revisar los datos Enviados");
	            status.setStatus("403-Forbidden");
	            StatusRequest(request,response,status);
	        }
	    }
	  protected void StatusRequest(HttpServletRequest request, HttpServletResponse response,ClssStatus status)
	            throws ServletException, IOException {		 
		  
	      response.setContentType("application/json");          	            
        response.getOutputStream().print(_gson.toJson(status));
        response.getOutputStream().flush();
		 
	 }
	}

