package com.mio.jersey.todo.resources;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

@Path("/facturas")
public class FacturasResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Factura> get() 
	{
		List<Factura> lista = BDFactura.listarFacturas();
		
		return lista;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String count() 
	{
		List<Factura> lista = BDFactura.listarFacturas();
		int count = lista.size();
	
		return String.valueOf(count);
	}

	@POST
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Respuesta nueva(@FormParam("email") String usuario, @FormParam("nombre") String nombre, @FormParam("descripcion") String descripcion, @FormParam("cantidad") double cantidad, @Context HttpServletResponse servletResponse) throws IOException 
	{
		Usuario usr = BDUsuario.seleccionarUsuario(usuario);
		List<Usuario> usuarios = BDUsuario.listarUsuarios();
		
		if (usr != null) {
			String fecha = new Date().getTime()+"";
			Factura f = new Factura(nombre, descripcion, fecha, cantidad, usuarios);
			BDFactura.insertar(f);
			return new Respuesta(false, "Factura aniadida correctamente");
		} else {
			return new Respuesta(true, "Usuario inexistente");
		}
	}

	// rest/facturas/{factura}
	@Path("{factura}")
	public FacturaResource manage(@PathParam("factura") long id) 
	{
		return new FacturaResource(uriInfo, request, id);
	}
}