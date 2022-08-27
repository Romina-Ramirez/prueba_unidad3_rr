package com.uce.edu.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoSencillo;
import com.uce.edu.demo.repository.modelo.ProductoStock;
import com.uce.edu.demo.repository.modelo.ReporteVenta;
import com.uce.edu.demo.service.IDetalleService;
import com.uce.edu.demo.service.IProductoService;
import com.uce.edu.demo.service.IVentaService;

@SpringBootApplication
public class PruebaUnidad3RrApplication implements CommandLineRunner {

	private static final Logger logger = Logger.getLogger(PruebaUnidad3RrApplication.class);

	@Autowired
	private IProductoService productoService;

	@Autowired
	private IVentaService ventaService;

	@Autowired
	private IDetalleService detalleService;

	public static void main(String[] args) {
		SpringApplication.run(PruebaUnidad3RrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// 1. Insertar 1 producto por dos ocasiones
		Producto producto = new Producto();
		producto.setCategoria("Lacteos");
		producto.setCodigoBarras("LAC001");
		producto.setNombre("Yogurt");
		producto.setPrecio(new BigDecimal(3.5));
		producto.setStock(5);
		
		this.productoService.ingresarProducto(producto);
		
		Producto producto1 = new Producto();
		producto1.setCategoria("Lacteos");
		producto1.setCodigoBarras("LAC001");
		producto1.setNombre("Yogurt");
		producto1.setPrecio(new BigDecimal(3.5));
		producto1.setStock(6);
		
		this.productoService.ingresarProducto(producto1);
		
		// 2. Realizar 1 venta con un producto
		List<ProductoSencillo> productos = new ArrayList<>();

		ProductoSencillo sencillo = new ProductoSencillo();
		sencillo.setCantidad(2);
		sencillo.setCodigoBarras("SNA001");

		productos.add(sencillo);

		this.ventaService.realizarVenta(productos, "1723069801", "001");
		
		// 3. Consultar producto a partir de su código de barras
		
		ProductoStock ps = this.productoService.consultarStock("SNA001");
		logger.info("Stock del producto: " + ps);
		
		// 4. Imprimir reporte de ventas
		List<ReporteVenta> reportes = this.detalleService.generarReporteVentas(LocalDateTime.of(2022, 8, 26, 20, 11, 58, 69074), "Snacks", 2);
		logger.info("Reporte de Venta: " + reportes);

	}

}
