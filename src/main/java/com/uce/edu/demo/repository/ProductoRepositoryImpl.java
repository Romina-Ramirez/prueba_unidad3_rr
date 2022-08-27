package com.uce.edu.demo.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;

@Transactional
@Repository
public class ProductoRepositoryImpl implements IProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void crear(Producto producto) {
		this.entityManager.persist(producto);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Producto producto) {
		this.entityManager.merge(producto);
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto leerPorCodigoBarras(String codBarras) {
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codBarras", Producto.class);
		myQuery.setParameter("codBarras", codBarras);
		return myQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public long leerCantidadDatos(String codBarras) {
		Query myQuery = this.entityManager
				.createQuery("SELECT COUNT(p) FROM Producto p WHERE p.codigoBarras = :codBarras");
		myQuery.setParameter("codBarras", codBarras);
		return (long) myQuery.getSingleResult();
	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto leerStock(String codBarras) {
		Query myNativeQuery = this.entityManager
				.createNativeQuery("SELECT * FROM producto WHERE prod_codigo_barras = :codBarras", Producto.class);
		myNativeQuery.setParameter("codBarras", codBarras);
		return (Producto) myNativeQuery.getSingleResult();
	}

}
