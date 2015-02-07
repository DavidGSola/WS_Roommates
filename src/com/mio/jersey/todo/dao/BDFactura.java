package com.mio.jersey.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.mio.jersey.todo.modelo.Factura;
import com.mio.jersey.todo.modelo.Usuario;

public class BDFactura {
	private static final String PERSISTENCE_UNIT_NAME = "antoniotoro.davidgonzalez";
	private static EntityManagerFactory factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	/**
	 * Inserta una factura en la BD
	 * @param compra Factura a insertar
	 */
	public static void insertar(Factura factura) {
		EntityManager em = factoria.createEntityManager();
		em.getTransaction().begin();

		em.persist(factura);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Marca una factura como pagada por un usuario concreto
	 * @param factura Factura a marcar como pagada
	 * @param usuario Usuario que ha pagado la factura
	 */
	public static void marcarPagada(Factura factura, Usuario usuario) {
		EntityManager em = factoria.createEntityManager();
			
		Query q = em.createQuery(
				"SELECT u from Factura u WHERE u.id = :idFactura")
				.setParameter("idFactura", factura.getId());
		
		Factura antiguo = (Factura) q.getSingleResult();

		em.getTransaction().begin();
		
		List<Usuario> usuariosAPagar = antiguo.getUsuarios();

		// Se obtiene el id del usuario siguiente a partir del anterior
		for(int i=0; i<usuariosAPagar.size(); i++)
		{
			Usuario u = usuariosAPagar.get(i);
			if(usuario.getId() == u.getId())
				antiguo.quitarUsuario(i);
		}

		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Elimina una factura de la BD.
	 * @param compra Factura a eliminar
	 */
	public static void eliminar(Factura factura) {
		EntityManager em = factoria.createEntityManager();

		em.getTransaction().begin();

		Query q = em.createQuery(
				"SELECT u from Factura u WHERE u.id = :idFactura")
				.setParameter("idFactura", factura.getId());
		
		Factura almacenado = (Factura) q.getSingleResult();
		
		em.remove(almacenado);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Recupera una Factura de la BD.
	 * @param id Id de la Factura que se quiere recuperar
	 * @return La Factura si se ha encontrado, <tt>null</tt> en caso contrario.
	 */
	public static Factura seleccionarFactura(long id) {
		EntityManager em = factoria.createEntityManager();

		Query q = em.createQuery(
				"SELECT u from Factura u WHERE u.id = :idFactura")
				.setParameter("idFactura", id);
		
		Factura factura;
		try {
			factura = (Factura) q.getSingleResult();
		} catch (NoResultException e) {
			factura = null;
		} finally {
			em.close();
		}
		return factura;
	}
	
	/**
	 * Lista todas las Facturas de la BD.
	 * @return Lista que contiene las facturas
	 */
	public static List<Factura> listarFacturas() {
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u from Factura u");
		
		@SuppressWarnings("unchecked")
		List<Factura> lista = q.getResultList();

		em.close();
		
		return lista;
	}
	
}
