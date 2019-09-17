package com.paei.springboot.backend.apirest.services.real;

import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioDao iUsuarioDao;

    // Logger para registrar posibles errores
    private  Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // Primero se encuentra el usuario
        Usuario usuario = iUsuarioDao.findBNombreUsuario(s);

        // En caso de que no exista el usuario
        if (usuario == null ){
            logger.error("Error no existe el usuario en el sistema: " + s);
            throw new UsernameNotFoundException("Error no existe el usuario en el sistema: " + s);
        }

        // Se obtienen los perfiles o roles del usuario
        List<GrantedAuthority> perfiles = usuario.getInstitucionPerfilUsuarios()
                .stream()
                .map(ipu -> new SimpleGrantedAuthority(
                        ipu.getPerfil().getNombre()))
                .peek( authority -> logger.info("Perfil en institucion:" + authority.getAuthority()) )
                .collect(Collectors.toList());

        // Se construye un usuario de la interfaz para Auth2
        return new User(
                 usuario.getUsuarioPK().getNombreUsuario(),
                usuario.getSalt(),usuario.getHabilitado(),
                true,
                true,
                true,
                perfiles);
    }
}
