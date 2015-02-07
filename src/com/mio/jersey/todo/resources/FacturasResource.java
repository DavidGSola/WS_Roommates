package com.mio.jersey.todo.resources;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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
import com.mio.jersey.todo.dao.BDFactura;
import com.mio.jersey.todo.dao.BDUsuario;
import com.mio.jersey.todo.modelo.Compra;
import com.mio.jersey.todo.modelo.Factura;
import com.mio.jersey.todo.modelo.Respuesta;
import com.mio.jersey.todo.modelo.Usuario;

// Hara corresponder el recurso al URL todos
@Path("/facturas")
public class FacturasResource {

	// Permite insertar objetos contextuales en la clase,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Usuario> get() 
	{
		List<Usuario> todos = BDUsuario.listarUsuarios();
		
		return todos;
	}

	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String count() 
	{
		List<Usuario> todos = BDUsuario.listarUsuarios();
		int count = todos.size();
	
		return String.valueOf(count);
	}

	@POST
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Respuesta nueva(@FormParam("email") String usuario, @FormParam("nombre") String nombre, @FormParam("descripcion") String descripcion, @FormParam("cantidad") double cantidad, @Context HttpServletResponse servletResponse) throws IOException 
	{
		Usuario usr = BDUsuario.seleccionarUsuario(usuario);
		
		if (usr != null) {
			String fecha = new Date().getTime()+"";
			Factura f = new Factura(nombre, descripcion, fecha, cantidad, usuario);
			BDFactura.insertar(f);
			return new Respuesta(false, "Compra aniadida correctamente");
		} else {
			return new Respuesta(true, "Usuario inexistente");
		}
	}

	// Define que el siguiente parametro en el path despues de "todos" es
	// tratado como un parametro y pasado al recurso TodoResources
	// Permite escribir http://localhost:8080/com.mio.jersey.todo/rest/todos/1
	// 1 sera tratado como un parametro "todo" y pasado al recurso TodoResource
	@Path("{usuario}")
	public UsuarioResource manageUsuario(@PathParam("usuario") String email) 
	{
		return new UsuarioResource(uriInfo, request, email);
	}
}