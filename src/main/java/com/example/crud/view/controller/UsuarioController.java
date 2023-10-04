package com.example.crud.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.services.UsuarioService;
import com.example.crud.shared.UsuarioDTO;
import com.example.crud.view.model.UsuarioRequest;
import com.example.crud.view.model.UsuarioResponse;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;


    ModelMapper mapper = new ModelMapper();
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obterUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.obterUsuarios();

        List<UsuarioResponse> usuarioResposta = usuarios.stream()
        .map( usuarioDto -> mapper.map(usuarioDto, UsuarioResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(usuarioResposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obterPorId(@PathVariable Integer id) {
        Optional<UsuarioDTO> usuarioDto = usuarioService.obterPorId(id);
        UsuarioResponse UsuarioRes = mapper.map(usuarioDto.get(), UsuarioResponse.class);

        return new ResponseEntity<>((UsuarioRes), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody UsuarioRequest usuarioReq) {
        UsuarioDTO usuarioDto = mapper.map(usuarioReq, UsuarioDTO.class);

        usuarioDto = usuarioService.cadastrar(usuarioDto);

        UsuarioResponse usuarioRes = mapper.map(usuarioDto, UsuarioResponse.class);

        return new ResponseEntity<>(usuarioRes, HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
        usuarioService.excluirUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@RequestBody UsuarioRequest usuarioReq, @PathVariable Integer id) {
        UsuarioDTO usuarioDto = mapper.map(usuarioReq, UsuarioDTO.class);

        usuarioDto = usuarioService.atualizarUsuario(usuarioDto, id);

        UsuarioResponse usuarioRes = mapper.map(usuarioDto, UsuarioResponse.class);

        return new ResponseEntity<>(usuarioRes, HttpStatus.OK);
    }
}
