package com.paei.springboot.backend.apirest.auth;

import com.paei.springboot.backend.apirest.dao.real.IInstitucionPerfilPermisoDao;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPerfilPermiso;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPerfilUsuario;
import com.paei.springboot.backend.apirest.model.entity.real.Usuario;
import com.paei.springboot.backend.apirest.services.real.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InformacionAdicionalToken implements TokenEnhancer {

    private Logger logger = LoggerFactory.getLogger(InformacionAdicionalToken.class);

    @Autowired
    public IUsuarioService iUsuarioService;

    @Autowired
    public IInstitucionPerfilPermisoDao iInstitucionPerfilPermisoService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        // Se recupera el usuario de la base de datos
        Usuario usuario = iUsuarioService.findUsuarioByNombreUsuario(oAuth2Authentication.getName());

        // Se recupera la información adicional del usuario
        Map<String, Object> info = new HashMap<>();
        info.put("nombre",usuario.getNombre());
        info.put("apellidos",usuario.getApellidos());
        
        // Se recuperan los perfiles y las instalaciones correspondientes
        Set<InstitucionPerfilUsuario> listaInstalacionesPerfiles = usuario.getInstitucionPerfilUsuarios();
        Map<String, List<String>> perfilesInstituciones = new HashMap<>();
        for (InstitucionPerfilUsuario ipu: listaInstalacionesPerfiles) {
            // Si no existe la llave se agrega
            perfilesInstituciones.computeIfAbsent(ipu.getPerfil().getNombre(), k -> new ArrayList<>());
            // Se reemplaza espacio en blanco por guión en el nombre de la institucion
            perfilesInstituciones.get(ipu.getPerfil().getNombre()).add(ipu.getInstitucion().getInstitucionPK().getNombre());
        }
        // Finalmentes se agrega el diccionario a la info adicional
        info.put("perfiles_instituciones",perfilesInstituciones);
        // Se obtienen los permisos por cada perfil e institucion del usuario
        Map<String, List<String>> perfilesInstitucionesPermisos = new HashMap<>();
        for (InstitucionPerfilUsuario ipu: listaInstalacionesPerfiles) {
            List<InstitucionPerfilPermiso> permisosInstitucionPerfil = iInstitucionPerfilPermisoService.findInstitucionPerfilPermisoByInstitucionAndPerfil(ipu.getPerfil(),ipu.getInstitucion());
            for (InstitucionPerfilPermiso permiso: permisosInstitucionPerfil) {
                // Se reemplaza espacio en blanco por * en el nombre de la institucion
                String llaveTemporal =ipu.getPerfil().getNombre()+"*"+ipu.getInstitucion().getInstitucionPK().getNombre().replace(" ","-");
                // Si no existe la llave se crea
                perfilesInstitucionesPermisos.computeIfAbsent(llaveTemporal, k -> new ArrayList<>());
                perfilesInstitucionesPermisos.get(llaveTemporal).add(permiso.getPermiso().getId().getId().toString());
            }
        }
        // Se recuperan los permisos por perfil en cada institucion
        info.put("perfiles_instituciones_permisos",perfilesInstitucionesPermisos);

        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(info);

        return oAuth2AccessToken;
    }
}
