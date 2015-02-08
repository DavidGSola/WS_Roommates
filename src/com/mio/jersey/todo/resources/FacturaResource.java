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

import com.mio.jersey.todo.dao.BDFactura;
import com.mio.jersey.todo.dao.BDUsuario;
import com.mio.jersey.todo.modelo.Factura;
import com.mio.jersey.todo.modelo.Respuesta;
import com.mio.jersey.todo.modelo.Usuario;

public class FacturaResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	long id;
	
	public FacturaResource(UriInfo uriInfo, Request request, long id) 
	{
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	public Factura get() 
	{
		Factura fct = BDFactura.seleccionarFactura(id);
		if(fct==null)
			throw new RuntimeException("Get: Factura " + id + " no encontrada");
	
		return fct;
	}

	// rest/facturas/{factura}/{usuario}
	@PUT
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	@Path("{usuario}")
	public Respuesta put(@PathParam("usuario") String email) 
	{ 
		Factura f = BDFactura.seleccionarFactura(id);
		Usuario u = BDUsuario.seleccionarUsuario(email);
		
		BDFactura.marcarPagada(f, u);
		
		Respuesta r = new Respuesta(false, "Factura actualizada correctamente");
		return r;
	}

//	@PUT
//	@Consumes(MediaType.APPLICATION_XML)
//	public Response put(JAXBElement<Factura> factura) 
//	{ 
//		Factura c = factura.getValue();
//		
//		return putAndGetResponse(c);
//	}

	@DELETE
	public Respuesta delete() 
	{
		Factura fct = BDFactura.seleccionarFactura(id);
		if(fct!=null) {
			BDFactura.eliminar(fct);
			return new Respuesta(false, "Factura eliminada correctamente");
		}
		else 
			return new Respuesta(true, "Factura no existente");
	}

//	private Response putAndGetResponse(Factura factura) 
//	{
//		Response res;
//		
//		if(BDFactura.seleccionarFactura(factura.getId()) != null)
//			res = Response.noContent().build();
//		else 
//			res = Response.created(uriInfo.getAbsolutePath()).build();
//		
////		TodoDao.instance.getModel().put(factura.getId()+"", factura);
////		BDFactura.
//		
//		return res;
//	}
} 