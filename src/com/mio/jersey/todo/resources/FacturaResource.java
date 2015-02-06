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

import com.mio.jersey.todo.dao.BDUsuario;
import com.mio.jersey.todo.dao.TodoDao;
import com.mio.jersey.todo.modelo.Factura;
import com.mio.jersey.todo.modelo.Todo;
import com.mio.jersey.todo.modelo.Usuario;

public class FacturaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	public FacturaResource(UriInfo uriInfo, Request request, String email) 
	{
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = email;
	}

	//Integracion de aplicaciones 
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Factura get() 
	{
		Factura fct = new Factura();
		if(fct==null)
			throw new RuntimeException("Get: Factura " + id + " no encontrada");
	
		return fct;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response put(JAXBElement<Todo> todo) 
	{
		Todo c = todo.getValue();
		
		return putAndGetResponse(c);
	}

	@DELETE
	public void delete() 
	{
		Factura usr = new Factura();
		if(usr!=null)
			;//eliminar
		else 
			throw new RuntimeException("Delete: Todo con " + id + " no se ha encontrado");
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