package com.confetystudios.redisspringboot.controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.confetystudios.redisspringboot.entitys.Usuario;
import com.confetystudios.redisspringboot.repository.UsuarioRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping("Redis")
public class UsaurioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	Logger logger = LoggerFactory.getLogger(UsaurioController.class);
	
	@PutMapping
	public ResponseEntity<?> saveOne(){
		logger.info("UsuariosController.saveOne()");
		try {
			Usuario usuarioSave = new Usuario("2", "Douglas","Valle@aaa");
			Usuario usuarioSaveResponse  = usuarioRepository.save(usuarioSave);
			
			return new ResponseEntity<Usuario>(usuarioSaveResponse, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> findById(){
		logger.info("UsuariosController.findById())");
		try {
			List<Usuario> usuarioSaveResponse  = (List<Usuario>) usuarioRepository.findAll();
			return new ResponseEntity<List<Usuario>>(usuarioSaveResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable() String id){
		logger.info("UsuariosController.findById(String id))");
		try {
			Optional<Usuario> usuarioSaveResponse  =usuarioRepository.findById(id);
			return new ResponseEntity<Optional<Usuario>>(usuarioSaveResponse, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/2S")
	public ResponseEntity<?> saveOne2(){
		logger.info("UsuariosController.saveOne()");
		try {
		
			Usuario usuarioSave = new Usuario("2", "Douglas","Valle@aaa");
			Gson gson = new Gson();		
			HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
			String str = gson.toJson(usuarioSave).toString();
			hashOperations.put("USUARIO", "2" , str);
			Map<String, Long> expires = new HashMap<>();
			long expirar = 1000;
			//Asignar expir data en redis.
			hashOperations.getOperations().expire("USUARIO", Duration.ofSeconds(15));
			return new ResponseEntity<Usuario>(usuarioSave, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
		}
	}
	
	 @GetMapping("/get")
		public ResponseEntity<?> findById22(){
			logger.info("UsuariosController.findById())");
			try {
				//Consulta de usuario
				HashOperations<String, String, Usuario> hashOperations = redisTemplate.opsForHash();
				System.out.println(hashOperations.get("USUARIO", "1"));
				Usuario usuarioSave = new Usuario("2", "Douglas","Valle@aaa");	
				return new ResponseEntity<Usuario>(usuarioSave, HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.BAD_REQUEST);
			}
		}
}
