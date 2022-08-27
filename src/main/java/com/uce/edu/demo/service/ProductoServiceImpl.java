package com.uce.edu.demo.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uce.edu.demo.repository.IProductoRepository;
import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoStock;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepository productoRepository;

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void ingresarProducto(Producto producto) {
		Long cant = this.productoRepository.leerCantidadDatos(producto.getCodigoBarras());

		if (cant == 0) {
			this.productoRepository.crear(producto);
		} else {
			Producto p = this.productoRepository.leerPorCodigoBarras(producto.getCodigoBarras());
			p.setStock(producto.getStock() + p.getStock());
			this.productoRepository.actualizar(p);
		}

	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public ProductoStock consultarStock(String codBarras) {
		Producto p = this.productoRepository.leerStock(codBarras);

		ProductoStock ps = new ProductoStock();
		ps.setCategoria(p.getCategoria());
		ps.setCodigoBarras(codBarras);
		ps.setNombre(p.getNombre());
		ps.setStock(p.getStock());

		return ps;
	}

}
