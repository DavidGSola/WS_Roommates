package com.mio.jersey.todo.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.mio.jersey.todo.dao.BDCompra;
import com.mio.jersey.todo.dao.BDUsuario;
import com.mio.jersey.todo.modelo.Compra;
import com.mio.jersey.todo.modelo.Respuesta;
import com.mio.jersey.todo.modelo.Usuario;

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

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Compra get() 
	{
		Compra compra = BDCompra.seleccionarCompra(id);
		if(compra==null)
			throw new RuntimeException("Get: Compra " + id + " no encontrada");
	
		return compra;
	}

	// rest/facturas/{compra}/{usuario}
	@PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	@Path("{usuario}")
	public Respuesta put(@PathParam("usuario") String email) 
	{ 
		Compra c = BDCompra.seleccionarCompra(id);
		Usuario u = BDUsuario.seleccionarUsuario(email);
		
		Respuesta r;
		if (c.getUsuario().getId() == u.getId()) {
			BDCompra.marcarComprado(c);
			r = new Respuesta(false, "Compra marcada como comprada"); 
		} else {
			r = new Respuesta(true, "Al usuario "+email+" no le toca realizar esa compra");
		}
		return r;
	}
	
//	@PUT
//	@Consumes(MediaType.APPLICATION_XML)
//	public Respuesta put(JAXBElement<Compra> compra) 
//	{ 
//		Compra c = compra.getValue();
//		
//		return putAndGetResponse(c);
//	}

	@DELETE
	public Respuesta delete() 
	{
		Compra compra = BDCompra.seleccionarCompra(id);
		if(compra!=null) {
			BDCompra.eliminar(compra);
			return new Respuesta(false, "Delete: Compra " + id + " eliminada");
		}
		else 
			return new Respuesta(false, "Delete: Compra " + id + " no encontrada");
	}

//	private Response putAndGetResponse(Compra compra) 
//	{ 
//		Response res;
//		if(BDCompra.seleccionarCompra(compra.getId()) != null)
//			res = Response.noContent().build();
//		else 
//			res = Response.created(uriInfo.getAbsolutePath()).build();
//		
////		TodoDao.instance.getModel().put(compra.getId()+"", compra);
//		
//		return res;
//	}
} 