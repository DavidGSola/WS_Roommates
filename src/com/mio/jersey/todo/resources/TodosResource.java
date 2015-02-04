package com.mio.jersey.todo.resources;

import java.io.IOException;
import java.util.ArrayList;
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

import com.mio.jersey.todo.dao.TodoDao;
import com.mio.jersey.todo.modelo.Todo;

// Hara corresponder el recurso al URL todos
@Path("/todos")
public class TodosResource {

	// Permite insertar objetos contextuales en la clase,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Devolvera la lista de los "todos" en el navegador del usuario
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Todo> getTodosBrowser() 
	{
		List<Todo> todos = new ArrayList<Todo>();
		todos.addAll(TodoDao.instance.getModel().values());
		
		return todos;
	}

	// Devolvera la lista de los "todos" en las aplicaciones
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Todo> getTodos() 
	{
		List<Todo> todos = new ArrayList<Todo>();
		todos.addAll(TodoDao.instance.getModel().values());
		
		return todos;
	}

	// Devuelve el numero de "todos" que hay
	// Utilizar http://localhost:8080/mio.com.jersey.todo/rest/todos/count
	// para obtener el numero total de registros
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() 
	{
		int count = TodoDao.instance.getModel().size();
	
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTodo(@FormParam("id") String id, @FormParam("resumen") String resumen, @FormParam("descripcion") String descripcion, @Context HttpServletResponse servletResponse) throws IOException 
	{
		Todo todo = new Todo(Integer.parseInt(id), resumen);
		if (descripcion != null) 
		todo.setDescripcion(descripcion);
		
		TodoDao.instance.getModel().put(id, todo);
		
		servletResponse.sendRedirect("../crear_todo.html");
	}

	// Define que el siguiente parametro en el path despues de "todos" es
	// tratado como un parametro y pasado al recurso TodoResources
	// Permite escribir http://localhost:8080/com.mio.jersey.todo/rest/todos/1
	// 1 sera tratado como un parametro "todo" y pasado al recurso TodoResource
	@Path("{todo}")
	public TodoResource getTodo(@PathParam("todo") String id) 
	{
		return new TodoResource(uriInfo, request, id);
	}
}