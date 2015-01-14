package com.mio.jersey.todo.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Compra 
{
	private Usuario usuario;

	private String id;
	
	private String nombre;
	
	private String descripcion;
	
	private String fecha;
	
	private boolean urgente;
	
	public Compra()
	{
		
	}
	
	public Compra(Usuario usuario, String id, String nombre, String descripcion, String fecha)
	{
		this.usuario = usuario;
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public Usuario getUsuario() 
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario) 
	{
		this.usuario = usuario;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}

	public String getFecha() 
	{
		return fecha;
	}

	public void setFecha(String fecha) 
	{
		this.fecha = fecha;
	}

	public boolean isUrgente() 
	{
		return urgente;
	}

	public void setUrgente(boolean urgente) 
	{
		this.urgente = urgente;
	}
	
	
	
}
