package com.prueba.banco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.banco.model.Transaccion;
import com.prueba.banco.repository.TransaccionRepository;


@Service
public class TransaccionService {

	@Autowired
	TransaccionRepository transDao;
	
	public void tranProceso(Transaccion tran) {
		Transaccion tranResp = transDao.save(tran);
		
	}
}
