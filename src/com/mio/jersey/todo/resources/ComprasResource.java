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

import com.mio.jersey.todo.dao.BDCompra;
import com.mio.jersey.todo.dao.BDUsuario;
import com.mio.jersey.todo.modelo.Compra;
import com.mio.jersey.todo.modelo.Respuesta;
import com.mio.jersey.todo.modelo.Usuario;

@Path("/compras")
public class ComprasResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Compra> get() 
	{
		List<Compra> lista = BDCompra.listarCompras();
		
		return lista;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String count() 
	{
		List<Compra> lista =  BDCompra.listarCompras();
		int count = lista.size();
	
		return String.valueOf(count);
	}

	@POST
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Respuesta nueva(@FormParam("email") String usuario, @FormParam("descripcion") String descripcion, @FormParam("nombre") String nombre, @Context HttpServletResponse servletResponse) throws IOException 
	{
		Usuario usr = BDUsuario.seleccionarUsuario(usuario);
		
		if (usr != null) {
			String fecha = new Date().getTime()+"";
			Compra c = new Compra(usr, nombre, descripcion, fecha);
			BDCompra.insertar(c);
			return new Respuesta(false, "Compra aniadida correctamente");
		} else {
			return new Respuesta(true, "Usuario inexistente");
		}
	}

	@Path("{compra}")
	public CompraResource manage(@PathParam("compra") long id) 
	{
		return new CompraResource(uriInfo, request, id);
	}
}