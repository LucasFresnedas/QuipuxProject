package com.example.quipuxproject.usuario.mapper;


import com.example.quipuxproject.usuario.dto.request.UsuarioRequest;
import com.example.quipuxproject.usuario.dto.response.UsuarioResponse;
import com.example.quipuxproject.usuario.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequest request);
    UsuarioResponse toResponse(Usuario usuario);
}
