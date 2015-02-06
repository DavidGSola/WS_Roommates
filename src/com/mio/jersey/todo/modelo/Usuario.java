package com.mio.jersey.todo.modelo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Usuario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	
	private String nombre;
	
	private String email;
	
	private List<Compra> compras = new ArrayList<>();

//	private List<Factura> mFacturas = new ArrayList<>();
	
	public Usuario()
	{
	
	}
  
	public Usuario (String nombre, String email)
	{
//		this.id = id;
		this.nombre = nombre;
		this.email = email;
	}
 
	public long getId() {
		return id;
	}

	public void setId(long id) {
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
  
	public String getEmail() 
	{
		return email;
	}
  
	public void setEmail(String email) 
	{
		this.email = email;
	}

	@ManyToMany
	public List<Compra> getCompras() 
	{
		return compras;
	}

	public void setCompras(List<Compra> compras) 
	{
		this.compras = compras;
	}

//	@ManyToMany
//	public List<Factura> getFacturas() 
//	{
//		return mFacturas;
//	}
//
//	public void setFacturas(List<Factura> facturas) 
//	{
//		this.mFacturas = facturas;
//	}
}