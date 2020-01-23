package com.prueba.banco.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.prueba.banco.model.Cuenta;
import com.prueba.banco.model.Transaccion;
import com.prueba.banco.repository.CuentaRepository;
import com.prueba.banco.repository.TransaccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = {"/api/v1"})
public class TransaccionController {
    private static final Logger log = LoggerFactory.getLogger(TransaccionController.class);

	@Autowired
	TransaccionRepository tranDao;
	
	@Autowired
	CuentaRepository cuentaDao;

	@RequestMapping(value = "/transaccion", method = RequestMethod.POST)
	public ResponseEntity<Transaccion> transaccion(	@RequestParam String tipo,
			@RequestParam String desc,
			@RequestParam String monto,
			@RequestParam String cuenta
			){
			Cuenta cuen = cuentaDao.findById(Long.parseLong(cuenta))
					.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada:" + cuenta));;
		
				Transaccion tran = new Transaccion(tipo, desc, Float.parseFloat(monto), new Date());
				cuen.getTransaccionList().add(tran);
				cuen.setBalance(cuen.getBalance()+ tran.getMonto());
				cuentaDao.saveAndFlush(cuen);
				return new ResponseEntity<Transaccion>(tran,HttpStatus.OK);
		}
		
		@GetMapping("all_trans")
		public ResponseEntity<List<Transaccion>> allTransaccion(	
				){
	
			return new ResponseEntity<List<Transaccion>>(tranDao.findAll(),HttpStatus.OK);
	}
	
		@GetMapping("all_cuenta")
		public ResponseEntity<List<Cuenta>> allCuenta(	
				){
			return new ResponseEntity<List<Cuenta>>(cuentaDao.findAll(),HttpStatus.OK);
	}
}
