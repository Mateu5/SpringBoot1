package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;
import service.ClienteService;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteservice;
	
	@GetMapping
	public ResponseEntity<Iterable<Cliente>> buscarTodos(){
		return ResponseEntity.ok(clienteservice.buscarTodos());
	}
	
	@GetMapping
	public ResponseEntity<Cliente> buscarPorId(@PathVariable long id){
		return ResponseEntity.ok(clienteservice.buscarPorId(id));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){
		clienteservice.inserir(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable long id, @RequestBody Cliente cliente){
		clienteservice.atualizar(id, cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable long id){
		clienteservice.deletar(id);
		return ResponseEntity.ok().build();
	}
	

	
}
