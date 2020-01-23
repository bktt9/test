package com.prueba.banco.controller;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prueba.banco.model.Cuenta;
import com.prueba.banco.model.Transaccion;
import com.prueba.banco.repository.CuentaRepository;
import com.prueba.banco.repository.TransaccionRepository;

@Controller
@RequestMapping("/service")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	CuentaRepository cuentaDao;
	
	@Autowired
	TransaccionRepository tranDao;
	
	@GetMapping("/")
    public String main(Model model ) {
		model.addAttribute("cuenta", new Cuenta());
        return "index";
    }
	
	@PostMapping("/buscar")
    public String buscar(Model model, Cuenta cuenta) {
		Cuenta c = cuentaDao.findById(cuenta.getIdCuenta())
				.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada:" + cuenta.getIdCuenta()));
		model.addAttribute("cuenta",c);
		log.info(c.toString());
		return "balance";
    }
	
	@PostMapping("/crear_cuenta")
    public String crearCuenta(@Valid Cuenta cuenta, BindingResult result, Model model) {
		   cuenta.setEstatus(Boolean.TRUE);
		   log.info(cuenta.toString());
	       cuentaDao.save(cuenta);
		 return "index";
    }
	
	@GetMapping("/cerrar_cuenta")
    public String cerrarCuenta(String id, Model model) {
		Cuenta c = cuentaDao.findById(Long.parseLong(id))
				.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada:" + id));
	   	c.setEstatus(Boolean.FALSE);
	    cuentaDao.save(c);
		 return "index";
    }
	
	@PostMapping("/deposito/{id}")
	public String deposito(@PathVariable("id") long id, Transaccion tran, Model model) {
	
		Cuenta c = cuentaDao.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada:" + id));
		tran.setFecha(new Date());
		tran.setTipo("deposit");
		tran.setDescripcion("Deposito");
		c.getTransaccionList().add(tran);
		c.setBalance(c.getBalance()+tran.getMonto());
		cuentaDao.save(c);
		return "balance";
	}
	
	@PostMapping("/retiro/{id}")
	public String retiro(@PathVariable("id") long id, Transaccion tran, Model model) {
		Cuenta c = cuentaDao.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada:" + id));
		tran.setFecha(new Date());
		tran.setTipo("withdrawal");
		tran.setDescripcion("Retiro");

		c.getTransaccionList().add(tran);
		c.setBalance(c.getBalance()-tran.getMonto());
		if (c.getBalance() < 0f) {
			new IllegalArgumentException("Saldo insuficiente" );
		}else{
			cuentaDao.save(c);
		}
		return "balance";
	}
	
}
