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

import com.mio.jersey.todo.dao.BDCompra;
import com.mio.jersey.todo.dao.TodoDao;
import com.mio.jersey.todo.modelo.Compra;
import com.mio.jersey.todo.modelo.Todo;

public class CompraResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	long id;
	
	public CompraResource(UriInfo uriInfo, Request request, long id) 
	{
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Integracion de aplicaciones 
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Compra get() 
	{
		Compra compra = BDCompra.seleccionarCompra(id);
		if(compra==null)
			throw new RuntimeException("Get: Compra " + id + " no encontrada");
	
		return compra;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response put(JAXBElement<Todo> todo) 
	{ //TODO Modificar esto, que no se bien para que sirve
		Todo c = todo.getValue();
		
		return putAndGetResponse(c);
	}

	@DELETE
	public void delete() 
	{
		Compra compra = BDCompra.seleccionarCompra(id);
		if(compra!=null)
			BDCompra.eliminar(compra);
		else 
			throw new RuntimeException("Delete: Compra " + id + " no encontrada");
	}

	private Response putAndGetResponse(Todo todo) 
	{ //TODO Modificar esto, que no se bien para que sirve
		Response res;
		if(TodoDao.instance.getModel().containsKey(todo.getId()))
			res = Response.noContent().build();
		else 
			res = Response.created(uriInfo.getAbsolutePath()).build();
		
		TodoDao.instance.getModel().put(todo.getId()+"", todo);
		
		return res;
	}
} 