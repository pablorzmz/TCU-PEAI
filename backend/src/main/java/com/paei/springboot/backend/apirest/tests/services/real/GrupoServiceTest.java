package com.paei.springboot.backend.apirest.tests.services.real;

import com.paei.springboot.backend.apirest.dao.real.*;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.IGrupoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance( TestInstance.Lifecycle.PER_CLASS )
public class GrupoServiceTest {

    private final String INSTITUCION_ID_1 = "GrupoServiceTest_Inst_1";
    private final Long AREA_TEMATICA_ID_1 = 1234L;
    private final String CURSO_NOMBRE_1 =  "GrupoServiceTest_Curso_1";
    private final String USUARIO_NOMBRE_1 =  "GrupoServiceTest_USUARIO_1";
    private final int GRUPO_NUMERO_1 = 1;
    private final int GRUPO_NUMERO_2 = 2;
    private final int GRUPO_NUMERO_3 = 3;
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
        List<Grupo> grupos = iGrupoDao.findGruposByCurso( curso.getId() );
        // Grupos
        grupos.forEach( grupo -> {
            iGrupoDao.delete( grupo );
        } );
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
        Map<String, Object> resultado = crearNuevoGrupo( GRUPO_NUMERO_1, GRUPO_PERIODO_TIEMPO, true );
        // Obtener grupos de un curso
        List<Grupo> grupos = iGrupoService.getGruposCurso(  ( (Curso)resultado.get( "curso" )).getId() );
        // Assert cantidad
        AtomicBoolean existeGrupo = new AtomicBoolean(false);
        grupos.forEach( grupo -> {
            Grupo grupoResultado = ( (Grupo)resultado.get( "grupo" ));
            if ( grupo.getId().getNumero() == grupoResultado.getId().getNumero() ){
                existeGrupo.set( true );
            }
        } );
        assertTrue( existeGrupo.get() );
    }

    @DisplayName( "TestEncontrarGrupoPorId" )
    @Test
    void EncontrarGrupoPorId(){
        Map<String, Object> resultado = crearNuevoGrupo( GRUPO_NUMERO_2, GRUPO_PERIODO_TIEMPO, true );
        Grupo grupo = iGrupoService.findById( ((Grupo)resultado.get("grupo")).getId() );
        assertNotEquals( null, grupo );
        assertEquals( ((Grupo)resultado.get("grupo")).getId(), grupo.getId() );
    }

    @DisplayName( "EstablecerGrupoACurso" )
    @Test
    void establecerGrupoACurso(){
        Map<String, Object> resultado = crearNuevoGrupo( 0, "", false );
        GrupoPK grupoPK = new GrupoPK();
        grupoPK.setCurso( ((Curso)resultado.get("curso")).getId() );
        grupoPK.setNumero( GRUPO_NUMERO_3 );
        grupoPK.setPeriodoTiempo( GRUPO_PERIODO_TIEMPO );
        Grupo grupo = new Grupo();
        grupo.setId( grupoPK );
        grupo.setCurso( ((Curso)resultado.get("curso")) );
        grupo.setUsuario( ((Usuario)resultado.get("usuario")) );
        Grupo nuevoGrupo  = iGrupoService.setGrupoCurso( grupo );
        assertNotEquals( grupo, nuevoGrupo );
    }

    Map<String,Object> crearNuevoGrupo(int numero, String periodoTiempo, boolean crearGrupo ){
        Map< String, Object > resultado = new HashMap<>();
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
        if ( crearGrupo ){
            Grupo grupo = new Grupo();
            GrupoPK grupoPK = new GrupoPK();
            grupoPK.setCurso( curso.getId() );
            grupoPK.setNumero( numero );
            grupoPK.setPeriodoTiempo( periodoTiempo );
            grupo.setId( grupoPK );
            grupo.setUsuario( usuario );
            grupo.setCurso( curso );
            Grupo nuevoGrupo = iGrupoDao.save(grupo);
            resultado.put( "grupo", nuevoGrupo );
        }
        // Set de los resultados y return
        resultado.put( "curso", curso );
        resultado.put( "usuario", usuario );
        return resultado;
    }
}
