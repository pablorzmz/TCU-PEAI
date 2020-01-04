package com.paei.springboot.backend.apirest.tests.services.real;

import com.paei.springboot.backend.apirest.dao.real.IAreaTematicaDao;
import com.paei.springboot.backend.apirest.dao.real.ICursoDao;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.CursoServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class CursoServiceTest {

    // Valores constantes de la clase
    private final String INSTITUCION = "CursoServiceTest_Inst_1";
    private final Long AREA_TEMATICA = 0133L;

    @Autowired
    private ICursoDao iCursoDao;

    @Autowired
    private IAreaTematicaDao iAreaTematicaDao;

    @Autowired
    private CursoServiceImpl cursoService;

    @BeforeAll
    void establecerValoresTests() {
        // Institucion
        Institucion institucion = new Institucion();
        InstitucionPK institucionPK = new InstitucionPK();
        institucionPK.setNombre(INSTITUCION);
        institucion.setInstitucionPK(institucionPK);
        // Area Tematica
        AreaTematica areaTematica = new AreaTematica();
        AreaTematicaPK areaTematicaPK = new AreaTematicaPK();
        areaTematicaPK.setId(AREA_TEMATICA);
        areaTematica.setId(areaTematicaPK);
        areaTematica.setInstitucion(institucion);
        SiglaTematica siglaTematica =  new SiglaTematica();
        SiglaTematicaPK siglaTematicaPK = new SiglaTematicaPK();
        siglaTematicaPK.setId( 1234L );
        siglaTematica.setId( siglaTematicaPK );
        areaTematica.setSiglaTematica( siglaTematica );
        // Curso
        Curso curso = new Curso();
        curso.setAreaTematica(areaTematica);
        iCursoDao.save(curso);
    }

    @Test
    void ObtenerCursoPorId() {
        // Arrage
        AreaTematica areaTematica = iAreaTematicaDao.findAreaTematicaByInstitucion(new InstitucionPK(INSTITUCION)).get(0);
        Curso curso = iCursoDao.findCursosByAreaTematica(areaTematica.getId()).get(0);
        // Act
        var cursoBusqueda = cursoService.findyId(curso.getId()).get();
        // Assert
        assertNotNull(cursoBusqueda);
        assertEquals(curso.getId(), cursoBusqueda.getId());
    }

    @Test
    void obtenerCursoPorAreaTematica(){
        // Arrage
        AreaTematica areaTematica = iAreaTematicaDao.findAreaTematicaByInstitucion( new InstitucionPK( INSTITUCION ) ).get(0);
        // Act
        var cursos = cursoService.findCursosByAreaTematica( areaTematica.getId() );
        // Assert
        assertNotNull( cursos );
        assertEquals( cursos.size(), 1 );
    }

    @Test
    void ObtenerCursoPorPK(){
        // Arrage
        AreaTematica areaTematica = iAreaTematicaDao.findAreaTematicaByInstitucion( new InstitucionPK( INSTITUCION ) ).get(0);
        // Act
        Curso curso = iCursoDao.findCursosByAreaTematica( areaTematica.getId() ).get(0);
        // Assert
        Curso cursoBusqueda = cursoService.getCurso( curso.getId() );
        assertNotNull(cursoBusqueda);
        assertEquals(curso.getId(), cursoBusqueda.getId());
    }

    @Test
    void verificarUsuarioEstudiante(){
        // Arrage
        AreaTematica areaTematica = iAreaTematicaDao.findAreaTematicaByInstitucion( new InstitucionPK( INSTITUCION ) ).get(0);
        // Act
        Curso curso = iCursoDao.findCursosByAreaTematica( areaTematica.getId() ).get(0);
        curso.setGrupos( new ArrayList<>());
        var resultado = cursoService.usuarioImparteCurso( new Usuario(), curso );
        // Assert
        assertEquals( resultado, false );
    }

    @Test
    void obtenerPaginaCurso(){
        // Arrage
        AreaTematica areaTematica = iAreaTematicaDao.findAreaTematicaByInstitucion( new InstitucionPK( INSTITUCION ) ).get(0);
        // Act
        Curso curso = iCursoDao.findCursosByAreaTematica( areaTematica.getId() ).get(0);
        var pagina = cursoService.findAll(PageRequest.of( 1, 1 ));
        // Assert
        assertNotNull( pagina );
        assertEquals( pagina.getNumberOfElements(), 1 );
    }

    @AfterAll
    void limpiarValoresTests(){
        // Se limpiarn todos los valores que se insertaron previamente
        InstitucionPK institucionPK = new InstitucionPK();
        institucionPK.setNombre( INSTITUCION );
        AreaTematica areaTematica = iAreaTematicaDao.findAreaTematicaByInstitucion( institucionPK ).get( 0 );
        iCursoDao.findCursosByAreaTematica( areaTematica.getId() ).forEach( curso -> {
            iCursoDao.delete( curso );
        } );
    }

}
