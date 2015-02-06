package com.mio.jersey.todo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.mio.jersey.todo.dao.TodoDao;
import com.mio.jersey.todo.modelo.Todo;

public class UsuarioResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String email;
	
	public UsuarioResource(UriInfo uriInfo, Request request, String email) 
	{
		this.uriInfo = uriInfo;
		this.request = request;
		this.email = email;
	}

	//Integracion de aplicaciones 
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Todo getUsuario() 
	{
		Todo todo = TodoDao.instance.getModel().get(email);
		if(todo==null)
			throw new RuntimeException("Get: Todo con " + email + " no se ha encontrado");
	
		return todo;
	}

	// Para el navegador
	@GET
	@Produces(MediaType.TEXT_XML)
	public Todo getTodoHTML() 
	{
		Todo todo = TodoDao.instance.getModel().get(email);
		if(todo==null)
			throw new RuntimeException("Get: Todo con " + email + " no se ha encontrado");
		
		return todo;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(JAXBElement<Todo> todo) 
	{
		Todo c = todo.getValue();
		
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteTodo() 
	{
		Todo c = TodoDao.instance.getModel().remove(email);
		if(c==null)
			throw new RuntimeException("Delete: Todo con " + email + " no se ha encontrado");
	}

	private Response putAndGetResponse(Todo todo) 
	{
		Response res;
		if(TodoDao.instance.getModel().containsKey(todo.getId()))
			res = Response.noContent().build();
		else 
			res = Response.created(uriInfo.getAbsolutePath()).build();
		
		TodoDao.instance.getModel().put(todo.getId()+"", todo);
		
		return res;
	}
} 