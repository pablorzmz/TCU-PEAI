package com.paei.springboot.backend.apirest.tests.controllers.real;

import antlr.collections.List;
import com.paei.springboot.backend.apirest.controllers.real.CursoController;
import com.paei.springboot.backend.apirest.dao.real.IAreaTematicaDao;
import com.paei.springboot.backend.apirest.dao.real.ICursoDao;
import com.paei.springboot.backend.apirest.dao.real.IGrupoDao;
import com.paei.springboot.backend.apirest.dao.real.IUsuarioDao;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.AreaTematicaServiceImpl;
import com.paei.springboot.backend.apirest.services.real.CursoServiceImpl;
import com.paei.springboot.backend.apirest.services.real.GrupoServiceImpl;
import com.paei.springboot.backend.apirest.services.real.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest()
public class CursoControllerTest {

    private final int GRUPO_NUMERO_1          = 1;
    private final String GRUPO_PERIODO_TIEMPO = "periodo_tiempo_1";
    private final Long CURSO_ID_1             = 666L;
    private final Long CURSO_ID_2             = 777L;
    private final Long AREA_TEMATICA_ID_1     = 999L;
    private final String USUARIO_1            = "usuario_1";

    @Mock
    private IGrupoDao iGrupoDao;

    @Mock
    private ICursoDao iCursoDao;

    @Mock
    private IUsuarioDao iUsuarioDao;

    @Mock
    private IAreaTematicaDao iAreaTematicaDao;

    @InjectMocks
    private GrupoServiceImpl iGrupoService;

    @InjectMocks
    private CursoServiceImpl iCursoService;

    @InjectMocks
    private UsuarioService iUsuarioService;

    @InjectMocks
    private AreaTematicaServiceImpl iAreaTematicaService;

    void establecerDatosMock(){
        // Usuario
        Usuario usuario = new Usuario();
        UsuarioPK usuarioPK = new UsuarioPK( USUARIO_1 );
        usuario.setUsuarioPK( usuarioPK );
        // Areas temáticas
        AreaTematica areaTematica = new AreaTematica();
        AreaTematicaPK areaTematicaPK = new AreaTematicaPK();
        areaTematicaPK.setId( AREA_TEMATICA_ID_1 );
        areaTematica.setId( areaTematicaPK );
        // Cursos
        Curso curso1 = new Curso();
        Curso curso2 = new Curso();
        curso1.setId( CURSO_ID_1 );
        curso2.setId( CURSO_ID_2 );
        curso1.setAreaTematica( areaTematica );
        curso2.setAreaTematica( areaTematica );
        var cursoList = new ArrayList<Curso>();
        cursoList.add( curso1 );
        cursoList.add( curso2 );
        Page<Curso> cursoPage = new PageImpl<>( cursoList );
        // Set de cada mock
        when( iCursoDao.findAll(PageRequest.of( 0, 4))).thenReturn( cursoPage );
        when( iAreaTematicaDao.findById( areaTematica.getId() ) ).thenReturn(java.util.Optional.of(areaTematica));
        when( iCursoDao.findCursosByAreaTematica( areaTematica.getId() ) ).thenReturn( cursoList );
        when( iUsuarioDao.findByNombreUsuario( eq( usuario.getUsuarioPK().getNombreUsuario() ) )).thenReturn( usuario );
        when ( iGrupoDao.save( any() ) ).thenReturn( new Grupo() );
        when ( iCursoDao.save( any() ) ).thenReturn( new Curso() );
    }

    @Test
    void obtenerPaginaCurso(){
        // Arrage
        establecerDatosMock();
        CursoController  cursoController = new CursoController();
        cursoController.establecerMock( iCursoService, iAreaTematicaService, iGrupoService, iUsuarioService );
        // Act
        var resultado = cursoController.index( 0 );
        // Assert
        assertNotNull( resultado );
        assertEquals( 2, resultado.getNumberOfElements() );
        assertEquals( 1, resultado.getTotalPages() );
    }

    @Test
    void obtenerCursosPorAreaTematica(){
        // Arrage
        establecerDatosMock();
        CursoController cursoController = new CursoController();
        cursoController.establecerMock( iCursoService, iAreaTematicaService, iGrupoService, iUsuarioService );
        // Act
        var resultado = cursoController.cursosPorArea( AREA_TEMATICA_ID_1 );
        // Assert
        assertNotNull( resultado );
        assertEquals( 2, resultado.size() );
    }

    @Test
    void crearNuevoCurso(){
        // Arrage
        establecerDatosMock();
        CursoController cursoController = new CursoController();
        cursoController.establecerMock( iCursoService, iAreaTematicaService, iGrupoService, iUsuarioService );
        // Act
        var resultado = cursoController.create2( new Curso(), AREA_TEMATICA_ID_1, USUARIO_1 );
        // Assert
        assertNotNull( resultado );
    }
}
