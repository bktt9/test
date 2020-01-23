package com.prueba.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.banco.model.Transaccion;


@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{

}
