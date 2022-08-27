package com.uce.edu.demo.service;

import java.util.List;

import com.uce.edu.demo.repository.modelo.ProductoSencillo;

public interface IVentaService {

	public void realizarVenta(List<ProductoSencillo> productos, String cedula, String numeroVenta);

}
