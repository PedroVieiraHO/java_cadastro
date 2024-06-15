package br.edu.senaisp.back_cadastro.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.senaisp.back_cadastro.model.Usuario;
import br.edu.senaisp.back_cadastro.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository repository;
	
	public Usuario create(Usuario usuario) {
		return repository.save(usuario);

	}
	
	public List<Usuario> findAll() {
        return repository.findAll();
    }
	
	 public Optional<Usuario> findById(Long id) {
	        return repository.findById(id);
	    }
	 
	 public void deleteById(Long id) {
	        repository.deleteById(id);
	    }
	
}
