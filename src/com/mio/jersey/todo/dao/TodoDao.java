package com.mio.jersey.todo.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mio.jersey.todo.modelo.Todo;
public enum TodoDao 
{
	instance;
  
  	private Map<String, Todo> contentProvider = new HashMap<String, Todo>();

  	private TodoDao() 
  	{
	    Todo todo = new Todo(1, "Aprender REST");
	    todo.setDescripcion("Leer http://lsi.ugr.es/dsbcs/Documentos/Practica/practica3.html");
//	    contentProvider.put(1, todo);
	    todo = new Todo(2, "Aprender algo sobre DSBCS");
	    todo.setDescripcion("Leer todo el material de http://lsi.ugr.es/dsbcs");
//	    contentProvider.put("2", todo);
	    
//	    BDUsuario.insertarTodo("wala","wolo");

		final String PERSISTENCE_UNIT_NAME = "antoniotoro.davidgonzalez";
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Todo todo1 = new Todo(1, "wolo");
		em.getTransaction().begin();

		em.persist(todo1);
		
		em.getTransaction().commit();
		em.close();
  	}
  
  	public Map<String, Todo> getModel()
  	{
  		return contentProvider;
  	}
} 