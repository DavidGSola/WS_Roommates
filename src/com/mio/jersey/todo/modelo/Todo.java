package com.mio.jersey.todo.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
	private String resumen;
	private String descripcion;
 
	public Todo()
	{
	
	}
  
	public Todo (String resumen)
	{
//		this.id = id;
		this.resumen = resumen;
	}
  
	public long getId() 
	{
		return id;
	}
  
	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getResumen() 
	{
		return resumen;
	}
  
	public void setResumen(String resumen) 
	{
		this.resumen = resumen;
	}
  
	public String getDescripcion() 
	{
		return descripcion;
	}
  
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
}