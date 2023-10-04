package com.example.crud.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.model.Entities.Usuario;
import com.example.crud.model.exception.ResourceNotFoundException;
import com.example.crud.repository.UsuarioRepository;
import com.example.crud.shared.UsuarioDTO;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    ModelMapper mapper = new ModelMapper();

    /**
     * Metodo que devolve todos os usuarios cadastrados no banco!
     * @return Lista de usuarios
     */
    public List<UsuarioDTO> obterUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuarioDto = usuarios.stream()
        .map(usuario -> mapper.map(usuario, UsuarioDTO.class))
        .collect(Collectors.toList());
        
        return usuarioDto;
    }

    /**
     * Metodo que devolve o usuario obtido pelo Id!
     * @param id do usuario a localizar!
     * @return Usuario encontrado!
     */
    public Optional<UsuarioDTO> obterPorId(Integer id ) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel encontrar usuario pois ele nao existe!");
        }
        UsuarioDTO usuariodto = mapper.map(usuario.get(), UsuarioDTO.class);
        return Optional.of(usuariodto);
    }

    /**
     * Metodo que cadastra o usuario no banco de dados!
     * @param usuarioDto dados do usuario a ser cadastrado!
     * @return Usuario cadastrado!
     */
    public UsuarioDTO cadastrar(UsuarioDTO usuarioDto) {
        usuarioDto.setId(null);
        // usuarioDto.setPostagens(null);

        Usuario usuario = mapper.map(usuarioDto, Usuario.class);

        usuarioRepository.save(usuario);

        usuarioDto.setId(usuario.getId());

        return usuarioDto;
    }

    /**
     * Metodo que exclui o usuario obtido pelo Id!
     * @param id do usuario a ser excluido!
     */
    public void excluirUsuario(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel excluir usuario pois ele nao existe!");
        }
        usuarioRepository.deleteById(id);
    }

    /**
     * Metodo que atualiza os dados do usuario no banco de dados!
     * @param usuarioDto dados do usuario a ser atualizado!
     * @param id do usuario a ser atualizado!
     * @return Usuario atualizado!
     */
    public UsuarioDTO atualizarUsuario(UsuarioDTO usuarioDto, Integer id) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel atualizar usuario pois ele nao existe!");
        }

        usuarioDto.setId(id);
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);
        
        usuarioRepository.save(usuario);
        return usuarioDto;
    }
}
