package com.mio.jersey.todo.modelo;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario 
{
	private String nombre;
	
	private String email;
	
	private ArrayList<Compra> mCompras;

	private ArrayList<Factura> mFacturas;
	
	public Usuario()
	{
	
	}
  
	public Usuario (String nombre, String email)
	{
		this.nombre = nombre;
		this.email = email;
	}
 
	public String getNombre() 
	{
		return nombre;
	}
  
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
  
	public String getEmail() 
	{
		return email;
	}
  
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public ArrayList<Compra> getCompras() 
	{
		return mCompras;
	}

	public void setCompras(ArrayList<Compra> compras) 
	{
		this.mCompras = compras;
	}

	public ArrayList<Factura> getFacturas() 
	{
		return mFacturas;
	}

	public void setFacturas(ArrayList<Factura> facturas) 
	{
		this.mFacturas = facturas;
	}
}