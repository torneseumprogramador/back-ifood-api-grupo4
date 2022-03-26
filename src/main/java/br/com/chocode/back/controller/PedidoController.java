package br.com.chocode.back.controller;

import br.com.chocode.back.model.Pedido;
import br.com.chocode.back.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private IPedidoService service;

	@GetMapping("")
	public ResponseEntity<List<Pedido>> findAll() {
		return ResponseEntity.status(200).body(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id) {
		return ResponseEntity.status(200).body(service.findById(id));
	}

	@PostMapping("/{id_pedido}/entregador/{id_entregador}")
	public String texto3() {
		return "ok";
	}

	@PutMapping("/{id}/status")
	public String texto4() {
		return null;
	}

}