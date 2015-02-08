package com.mio.jersey.todo.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mio.jersey.todo.modelo.Compra;
import com.mio.jersey.todo.modelo.Usuario;

public class BDCompra {
	private static final String PERSISTENCE_UNIT_NAME = "antoniotoro.davidgonzalez";
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	/**
	 * Inserta una compra en la BD
	 * @param compra Compra a insertar
	 */
	public static void insertar(Compra compra) {
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();

		em.persist(compra);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Marca como urgente una compra
	 * @param compra Compra a marcar como urgente
	 */
	public static void marcarUrgente(Compra compra) {
		EntityManager em = factoria.createEntityManager();

		Query q = em.createQuery(
				"SELECT u from Compra u WHERE u.id = :idCompra")
				.setParameter("idCompra", compra.getId());
		
		Compra antiguo = (Compra) q.getSingleResult();

		em.getTransaction().begin();
		
		antiguo.setUrgente(true);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Marca una compra como realizada y pasa a ser responsabilidad
	 * del siguiente usuario
	 * @param compra Compra a marcar como realizada
	 */
	public static void marcarComprado(Compra compra) {
		EntityManager em = factoria.createEntityManager();
			
		Query q = em.createQuery(
				"SELECT u from Compra u WHERE u.id = :idCompra")
				.setParameter("idCompra", compra.getId());
		
		Compra antiguo = (Compra) q.getSingleResult();

		em.getTransaction().begin();
		
		antiguo.setUrgente(false);
		
		// Se actualiza la fecha a la actual
		antiguo.setFecha(new Date().getTime()+"");
	
		Usuario usuarioAnterior = antiguo.getUsuario();
		List<Usuario> usuarios = BDUsuario.listarUsuarios();

		// Se obtiene el id del usuario siguiente a partir del anterior
		int indexUsuarioSiguiente = -1;
		for(int i=0; i<usuarios.size(); i++)
		{
			Usuario u = usuarios.get(i);
			if(usuarioAnterior.getId() == u.getId())
				indexUsuarioSiguiente = (i+1)%usuarios.size();
		}
		
		antiguo.setUsuario(usuarios.get(indexUsuarioSiguiente));
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Elimina una compra de la BD.
	 * @param compra Compra a eliminar
	 */
	public static void eliminar(Compra compra) {
		EntityManager em = factoria.createEntityManager();

		em.getTransaction().begin();

		Query q = em.createQuery(
				"SELECT u from Compra u WHERE u.id = :idCompra")
				.setParameter("idCompra", compra.getId());
		
		Compra almacenado = (Compra) q.getSingleResult();
		
		em.remove(almacenado);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Recupera una Compra de la BD.
	 * @param id Id de la compra que se quiere recuperar
	 * @return La Compra si se ha encontrado, <tt>null</tt> en caso contrario.
	 */
	public static Compra seleccionarCompra(long id) {
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery(
				"SELECT u from Compra u WHERE u.id = :idCompra")
				.setParameter("idCompra", id);
		
		Compra compra;
		try {
			compra = (Compra) q.getSingleResult();
		} catch (NoResultException e) {
			compra = null;
		} finally {
			em.close();
		}
		return compra;
	}
	
	/**
	 * Lista todas las compras de la BD.
	 * @return Lista que contiene las compras
	 */
	public static List<Compra> listarCompras() {
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u from Compra u");
		
		@SuppressWarnings("unchecked")
		List<Compra> lista = q.getResultList();

		em.close();
		
		return lista;
	}
	
}
