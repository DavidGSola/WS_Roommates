package com.mio.jersey.todo.modelo;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Factura 
{
//	private ArrayList<Usuario> usuarios;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	private String nombre;
	
	private String descripcion;
	
	private String fechaCreacion;
	
	private double cantidad;

//	public ArrayList<Usuario> getUsuarios() 
//	{
//		return usuarios;
//	}
//
//	public void setUsuarios(ArrayList<Usuario> usuarios) 
//	{
//		this.usuarios = usuarios;
//	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
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
