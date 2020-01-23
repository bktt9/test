package com.prueba.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.banco.model.Cuenta;


@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long>{

}
