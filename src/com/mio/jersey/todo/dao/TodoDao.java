package com.mio.jersey.todo.dao;

import java.util.HashMap;
import java.util.Map;
import com.mio.jersey.todo.modelo.Todo;
public enum TodoDao 
{
	instance;
  
  	private Map<String, Todo> contentProvider = new HashMap<String, Todo>();
  
  	private TodoDao() 
  	{
	    Todo todo = new Todo("1", "Aprender REST");
	    todo.setDescripcion("Leer http://lsi.ugr.es/dsbcs/Documentos/Practica/practica3.html");
	    contentProvider.put("1", todo);
	    todo = new Todo("2", "Aprender algo sobre DSBCS");
	    todo.setDescripcion("Leer todo el material de http://lsi.ugr.es/dsbcs");
	    contentProvider.put("2", todo);
  	}
  
  	public Map<String, Todo> getModel()
  	{
  		return contentProvider;
  	}
} 