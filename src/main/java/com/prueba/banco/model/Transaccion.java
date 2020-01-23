package com.prueba.banco.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transaccion {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long idTransaccion;
	private Date fecha;
	private String tipo;
	private String descripcion;
	private Float monto;
	
  
	
	public Transaccion(String tipo, String descripcion, Float monto, Date fecha) {
		super();
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.monto = monto;
		this.fecha = fecha;
	}
	
}
