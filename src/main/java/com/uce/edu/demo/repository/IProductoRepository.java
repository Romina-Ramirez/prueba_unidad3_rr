package com.uce.edu.demo.repository;

import com.uce.edu.demo.repository.modelo.Producto;

public interface IProductoRepository {

	public void crear(Producto producto);

	public void actualizar(Producto producto);

	public Producto leerPorCodigoBarras(String codBarras);

	public long leerCantidadDatos(String codBarras);

	public Producto leerStock(String codBarras);

}
