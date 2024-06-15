package br.edu.senaisp.back_cadastro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.senaisp.back_cadastro.model.Usuario;
import br.edu.senaisp.back_cadastro.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	UsuarioService service;
	
	@PostMapping
	public Usuario create(@RequestBody Usuario usuario) {
		return service.create(usuario);
		
	}
	
	 @GetMapping
	    public List<Usuario> getAllUsers() {
	        return service.findAll();
	    }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
	        Optional<Usuario> usuario = service.findById(id);
	        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

	  @PutMapping("/{id}")
	    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario usuarioDetalhe) {
	        Optional<Usuario> usuario = service.findById(id);
	        if (usuario.isPresent()) {
	            Usuario updatedUser = usuario.get();
	            updatedUser.setName(usuarioDetalhe.getName());
	            updatedUser.setEmail(usuarioDetalhe.getEmail());
	            updatedUser.setPassword(usuarioDetalhe.getPassword());
	            service.create(updatedUser);
	            return ResponseEntity.ok(updatedUser);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
}

	  
	  @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	        if (service.findById(id).isPresent()) {
	            service.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	  
}