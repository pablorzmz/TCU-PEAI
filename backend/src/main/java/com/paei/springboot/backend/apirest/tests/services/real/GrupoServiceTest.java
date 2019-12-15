package com.paei.springboot.backend.apirest.tests.services.real;

import com.paei.springboot.backend.apirest.dao.real.*;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.IGrupoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class GrupoServiceTest {

    private final String INSTITUCION_ID_1 = "GrupoServiceTest_Inst_1";
    private final Long AREA_TEMATICA_ID_1 = 1234L;
    private final String CURSO_NOMBRE_1 =  "GrupoServiceTest_Curso_1";
    private final String USUARIO_NOMBRE_1 =  "GrupoServiceTest_USUARIO_1";
    private final int GRUPO_NUMERO = 1;
    private final String GRUPO_PERIODO_TIEMPO = "GrupoServiceTest_Periodo_1";

    // Acceso al dao para poder insertar los valores reales en la base de datos y probar
    @Autowired
    private IGrupoDao iGrupoDao;

    @Autowired
    private IInstitucionDao iInstitucionDao;

    @Autowired
    private ICursoDao iCursoDao;

    @Autowired
    private IAreaTematicaDao iAreaTematicaDao;

    @Autowired
    private IUsuarioDao iUsuarioDao;

    @Autowired
    private ISiglaTematicaDao iSiglaTematicaDao;

    // instancia del servicio para probar
    @Autowired
    private IGrupoService iGrupoService;

    // Método que permite insertar los valores en la db necesarios para las pruebas
    @BeforeAll
    void establecerValoresTests(){
        // Crear e insertar las instituciones
        Institucion institucion = new Institucion();
        InstitucionPK institucionPK = new InstitucionPK();
        institucionPK.setNombre( INSTITUCION_ID_1 );
        institucion.setInstitucionPK( institucionPK );
        // Crear e insertar las areas temática
        AreaTematicaPK areaTematicaPK =  new AreaTematicaPK();
        areaTematicaPK.setId( AREA_TEMATICA_ID_1 );
        AreaTematica areaTematica = new AreaTematica();
        SiglaTematica siglaTematica =  new SiglaTematica();
        SiglaTematicaPK siglaTematicaPK = new SiglaTematicaPK();
        siglaTematicaPK.setId( 1234L );
        siglaTematica.setId( siglaTematicaPK );
        // Set data
        areaTematica.setId( areaTematicaPK );
        areaTematica.setInstitucion( institucion );
        areaTematica.setSiglaTematica( siglaTematica );
        // Crear e insertar los cursos
        Curso curso1 = new Curso();;
        curso1.setNombre( CURSO_NOMBRE_1 );
        curso1.setAreaTematica(  areaTematica );
        iCursoDao.saveAndFlush( curso1 );
        // Crear un usuario
        Usuario usuario = new Usuario();
        UsuarioPK usuarioPK = new UsuarioPK();
        usuarioPK.setNombreUsuario( USUARIO_NOMBRE_1 );
        usuario.setUsuarioPK( usuarioPK );
        iUsuarioDao.save( usuario );
    }

    // Método que hace la limpieza de los test en la base de datos
    @AfterAll
    void limpiarValoresTest(){
        // Se limpiarn todos los valores que se insertaron previamente
        InstitucionPK institucionPK = new InstitucionPK();
        institucionPK.setNombre( INSTITUCION_ID_1 );
        AreaTematica areaTematica = iAreaTematicaDao.findAreaTematicaByInstitucion( institucionPK ).get( 0 );
        Curso curso =  iCursoDao.findCursosByAreaTematica( areaTematica.getId() ).get( 0 );
        // Grupos
        iGrupoDao.deleteById( new GrupoPK( curso.getId(), GRUPO_NUMERO, GRUPO_PERIODO_TIEMPO ) );
        //Usuarios
        Usuario usuario = new Usuario();
        usuario.setUsuarioPK( new UsuarioPK( USUARIO_NOMBRE_1 ) );
        iUsuarioDao.delete( usuario );
        // Cursos
        iCursoDao.delete( curso );
        // Areas tematicas y sigla
        iAreaTematicaDao.delete( areaTematica );
        iSiglaTematicaDao.delete( areaTematica.getSiglaTematica() );
        // Institucicones
        iInstitucionDao.delete( areaTematica.getInstitucion() );
    }

    @Test
    @DisplayName("CrearNuevoGrupoCurso")
    void CrearNuevoGrupoCursoYObtenerGrupos(){
        // Encontrar el curso por el area tematica
        InstitucionPK institucionPK = new InstitucionPK();
        institucionPK.setNombre( INSTITUCION_ID_1 );
        AreaTematica areaTematica = iAreaTematicaDao.findAreaTematicaByInstitucion( institucionPK ).get( 0 );
        // Assert no null
        assertNotEquals( null, areaTematica );
        // Encontrar el curso
        Curso curso =  iCursoDao.findCursosByAreaTematica( areaTematica.getId() ).get( 0 );
        // Assert no null
        assertNotEquals( null, curso );
        // Encontrar usuario
        Usuario usuario = iUsuarioDao.findByNombreUsuario( USUARIO_NOMBRE_1 );
        // Assert no null
        assertNotEquals( null, usuario );
        // Crea un nuevo grupo
        Grupo grupo = new Grupo();
        GrupoPK grupoPK = new GrupoPK();
        grupoPK.setCurso( curso.getId() );
        grupoPK.setNumero( GRUPO_NUMERO );
        grupoPK.setPeriodoTiempo( GRUPO_PERIODO_TIEMPO );
        grupo.setId( grupoPK );
        grupo.setUsuario( usuario );
        grupo.setCurso( curso );
        iGrupoDao.save( grupo );
        // Obtener grupos de un curso
        List<Grupo> grupos = iGrupoService.getGruposCurso( curso.getId() );
        // Assert cantidad
        assertEquals( 1, grupos.size() );
    }
}
