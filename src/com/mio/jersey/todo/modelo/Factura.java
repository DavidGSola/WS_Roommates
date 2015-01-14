package com.mio.jersey.todo.modelo;

import java.util.ArrayList;

public class Factura 
{
	private ArrayList<Usuario> usuarios;
	
	private String id;
	
	private String nombre;
	
	private String descripcion;
	
	private String fechaCreacion;
	
	private double cantidad;

	public ArrayList<Usuario> getUsuarios() 
	{
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) 
	{
		this.usuarios = usuarios;
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

	public String getFechaCreacion() 
	{
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) 
	{
		this.fechaCreacion = fechaCreacion;
	}

	public double getCantidad() 
	{
		return cantidad;
	}

	public void setCantidad(double cantidad) 
	{
		this.cantidad = cantidad;
	}
}
