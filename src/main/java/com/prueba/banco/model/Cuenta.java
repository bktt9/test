package com.prueba.banco.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@NoArgsConstructor
public class Cuenta {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long idCuenta;
	@NonNull
	private String pin;
	@NonNull
	private String nombre;
	@NonNull
	private String apellido;
	@NonNull
	private String numeroSeguro;
	private Float balance;
	private Boolean estatus;
	
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private List<Transaccion> transaccionList  = new ArrayList<Transaccion>();;
	
	public Cuenta (Long idCuenta) {
		this.idCuenta = idCuenta;
	}
}