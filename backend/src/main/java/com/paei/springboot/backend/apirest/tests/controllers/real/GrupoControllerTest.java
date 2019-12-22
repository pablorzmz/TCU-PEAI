package com.paei.springboot.backend.apirest.tests.controllers.real;



import com.paei.springboot.backend.apirest.controllers.real.GrupoController;
import com.paei.springboot.backend.apirest.dao.real.ICursoDao;
import com.paei.springboot.backend.apirest.dao.real.IGrupoDao;
import com.paei.springboot.backend.apirest.dao.real.IUsuarioDao;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
public class GrupoControllerTest {

    private final String USUARIO1_NOMBRE       = "usuario1_grupo_test";
    private final String USUARIO2_NOMBRE       = "usuario2_grupo_test";
    private final int GRUPO_NUMERO_1          = 1;
    private final int GRUPO_NUMERO_2          = 2;
    private final String GRUPO_PERIODO_TIEMPO = "periodo_tiempo_1";
    private final Long CURSO_ID_1             = 666L;
    private final Long CURSO_ID_2             = 667L;

    @Mock
    private IGrupoDao iGrupoDao;

    @Mock
    private ICursoDao iCursoDao;

    @Mock
    private IUsuarioDao iUsuarioDao;

    @InjectMocks
    private GrupoServiceImpl iGrupoService;

    @InjectMocks
    private CursoServiceImpl iCursoService;

    @InjectMocks
    private UsuarioService iUsuarioService;

    void establecerMock(){
        Map< String, Object > resultado = new HashMap<>();
        // Crear un usuario para el mock
        UsuarioPK usuarioPK1 = new UsuarioPK();
        UsuarioPK usuarioPK2 = new UsuarioPK();
        usuarioPK1.setNombreUsuario( this.USUARIO1_NOMBRE );
        usuarioPK2.setNombreUsuario( this.USUARIO2_NOMBRE );
        Usuario usuario1     = new Usuario();
        Usuario usuario2     = new Usuario();
        usuario1.setUsuarioPK( usuarioPK1 );
        usuario2.setUsuarioPK( usuarioPK2 );
        // Crear un curso para el mock
        Curso curso1 = new Curso();
        Curso curso2 = new Curso();
        curso1.setId( CURSO_ID_1 );
        curso2.setId( CURSO_ID_2 );
        // Crear grupos para el curso 1
        GrupoPK grupoPK_1 = new GrupoPK( curso1.getId(), GRUPO_NUMERO_1, GRUPO_PERIODO_TIEMPO );
        Grupo grupo1      = new Grupo();
        GrupoPK grupoPK_2 = new GrupoPK( curso2.getId(), GRUPO_NUMERO_2, GRUPO_PERIODO_TIEMPO );
        Grupo grupo2      = new Grupo();
        grupo1.setUsuario( usuario1 );
        grupo2.setUsuario( usuario1 );
        grupo1.setCurso( curso1 );
        grupo2.setCurso( curso1 );
        grupo1.setId( grupoPK_1 );
        grupo2.setId( grupoPK_2 );
        // Establecer los grupos para este usuario
        UsuarioGrupoInscritoPK usuarioGrupoInscritoPK1 = new UsuarioGrupoInscritoPK();
        UsuarioGrupoInscritoPK usuarioGrupoInscritoPK2 = new UsuarioGrupoInscritoPK();
        UsuarioGrupoInscrito usuarioGrupoInscrito1     = new UsuarioGrupoInscrito();
        UsuarioGrupoInscrito usuarioGrupoInscrito2     = new UsuarioGrupoInscrito();
        usuarioGrupoInscritoPK1.setGrupoPk( grupoPK_1);
        usuarioGrupoInscritoPK1.setUsuarioPK( usuarioPK1 );
        usuarioGrupoInscritoPK2.setGrupoPk( grupoPK_2);
        usuarioGrupoInscritoPK2.setUsuarioPK( usuarioPK1 );
        usuarioGrupoInscrito1.setUsuarioGrupoInscritoPK( usuarioGrupoInscritoPK1 );
        usuarioGrupoInscrito2.setUsuarioGrupoInscritoPK( usuarioGrupoInscritoPK2 );
        List<UsuarioGrupoInscrito> usuarioGrupoInscritoList = new ArrayList<>();
        usuarioGrupoInscritoList.add( usuarioGrupoInscrito1 );
        usuarioGrupoInscritoList.add( usuarioGrupoInscrito2 );
        usuario1.setUsuarioGrupoInscritos( usuarioGrupoInscritoList );
        usuario2.setUsuarioGrupoInscritos( new ArrayList<UsuarioGrupoInscrito>() );
        // Establecer los grupos para el curso
        List<Grupo> grupoList = new ArrayList<>();
        grupoList.add( grupo1 );
        grupoList.add( grupo2 );
        curso1.setGrupos( grupoList );
        curso2.setGrupos( new ArrayList<Grupo>() );
        // Poner los eventos para el mock
        when( iCursoDao.findById( eq( curso1.getId() ) )).thenReturn( java.util.Optional.of(curso1) );
        when( iCursoDao.findById( eq( curso2.getId() ) )).thenReturn( java.util.Optional.of(curso2) );
        when( iUsuarioDao.findByNombreUsuario( eq( usuario1.getUsuarioPK().getNombreUsuario() ) )).thenReturn( usuario1 );
        when( iUsuarioDao.findByNombreUsuario( eq( usuario2.getUsuarioPK().getNombreUsuario() ) )).thenReturn( usuario2 );
    }

    @Test
    @DisplayName( "TestRecupararGruposCursoProfesor" )
    void recuperarGruposCursoProfesor(){
        // Arrage
        establecerMock();
        GrupoController grupoController = new GrupoController();
        grupoController.establecerMock( this.iGrupoService, this.iCursoService, this.iUsuarioService );
        // Act
        List<Grupo> gruposResultado = grupoController.recuperarGruposDeCurso( CURSO_ID_1, USUARIO1_NOMBRE );
        // Assert
        assertNotNull( gruposResultado );
        assertEquals( 2, gruposResultado.size() );
    }

    @Test
    @DisplayName( "TestRecupararGruposCursoEstudiante" )
    void recuperarGruposCursoEstudiante(){
        // Arrage
        establecerMock();
        GrupoController grupoController = new GrupoController();
        grupoController.establecerMock( this.iGrupoService, this.iCursoService, this.iUsuarioService );
        // Act
        List<Grupo> gruposResultado = grupoController.recuperarGruposDeCurso( CURSO_ID_2, USUARIO2_NOMBRE );
        // Assert
        assertNotNull( gruposResultado );
        assertEquals( 0, gruposResultado.size() );
    }
}
