package com.confetystudios.redisspringboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.confetystudios.redisspringboot.entitys.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>  {

}
