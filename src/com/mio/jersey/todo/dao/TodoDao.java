package com.mio.jersey.todo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mio.jersey.todo.modelo.Compra;
import com.mio.jersey.todo.modelo.Todo;
import com.mio.jersey.todo.modelo.Usuario;
public enum TodoDao 
{
	instance;
  
  	private Map<String, Todo> contentProvider = new HashMap<String, Todo>();

  	private TodoDao() 
  	{
	    Todo todo = new Todo("Aprender REST");
	    todo.setDescripcion("Leer http://lsi.ugr.es/dsbcs/Documentos/Practica/practica3.html");
//	    contentProvider.put(1, todo);
	    todo = new Todo("Aprender algo sobre DSBCS");
	    todo.setDescripcion("Leer todo el material de http://lsi.ugr.es/dsbcs");
//	    contentProvider.put("2", todo);
	    
//	    BDUsuario.insertarTodo("wala","wolo");

		final String PERSISTENCE_UNIT_NAME = "antoniotoro.davidgonzalez";
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Todo todo1 = new Todo("wolo");
		em.getTransaction().begin();

		em.persist(todo1);
		
		Usuario usr = new Usuario("wala","woolo");
		em.persist(usr);
		
		em.getTransaction().commit();
		em.close();
  	}
  
  	public Map<String, Todo> getModel()
  	{
		final String PERSISTENCE_UNIT_NAME = "antoniotoro.davidgonzalez";
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u from Todo u");
		
		@SuppressWarnings("unchecked")
		List<Todo> todos = q.getResultList();

		em.close();
		
		for (Todo todo : todos) {
			contentProvider.put(todo.getId()+"", todo);
		}
  		return contentProvider;
  	}
} 