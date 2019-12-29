package com.paei.springboot.backend.apirest.tests.services.real;

import com.paei.springboot.backend.apirest.dao.real.*;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.IAreaTematicaService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Se usa pensando en futuros tests de inserciones o borrados
public class AreaTematicaServiceTest {

    //Se inicializan las constantes para los datos a ser insertados antes de cada test
    private final Long AREA_TEMATICA_ID_1 = 9991L;
    private final Long AREA_TEMATICA_ID_2 = 9992L;
    private final Long AREA_TEMATICA_ID_3 = 9993L;

    List<Long> AREAS_TEMATICAS_IDS = Arrays.asList(AREA_TEMATICA_ID_1, AREA_TEMATICA_ID_2, AREA_TEMATICA_ID_3);

    private final Long SIGLA_TEMATICA_ID_1 = 8881L;
    private final Long SIGLA_TEMATICA_ID_2 = 8882L;
    private final Long SIGLA_TEMATICA_ID_3 = 8883L;

    private final String INSTITUCION_ID_1 = "Institucion_1";
    private final String INSTITUCION_ID_2 = "Institucion_2";



    // Se crean los Daos para hacer las inserciones para los tests
    @Autowired
    private IAreaTematicaDao iAreaTematicaDao;

    @Autowired
    private IInstitucionDao institucionDao;

    @Autowired
    private ISiglaTematicaDao iSiglaTematicaDao;

    // Se crea el servicio de Area Tematica, para probarlo
    @Autowired
    private IAreaTematicaService iAreaTematicaService;


    /**
     * Metodo para inicilaizar los valores para cada test
     * La limpieza la hace el DirtiesContext
     */
    @BeforeEach
    void inicializar (){

        // Se crea la institucion 1
        Institucion institucion1 = crearInstitucion(INSTITUCION_ID_1);

        // Se crea la institucion 2
        Institucion institucion2 = crearInstitucion(INSTITUCION_ID_2);

        // Se crea el area tematica 1
        AreaTematica areaTematica1 = crearAreaTematica(AREA_TEMATICA_ID_1, SIGLA_TEMATICA_ID_1, institucion1);
        iAreaTematicaDao.save(areaTematica1);

        // Se crea el area tematica 2
        AreaTematica areaTematica2 = crearAreaTematica(AREA_TEMATICA_ID_2, SIGLA_TEMATICA_ID_2, institucion1);
        iAreaTematicaDao.save(areaTematica2);

        // Se crea el area tematica 3
        AreaTematica areaTematica3 = crearAreaTematica(AREA_TEMATICA_ID_3, SIGLA_TEMATICA_ID_3, institucion2);
        iAreaTematicaDao.save(areaTematica3);

    }

    /**
     * Metodo que crea un area tematica
     * @param area_tematica_id es el id del area tematica
     * @param sigla_tematica_id es la sigla tematica asociada
     * @return un area tematica con el id y la sigla tematica que entren como argumentos
     */
    private AreaTematica crearAreaTematica(Long area_tematica_id, Long sigla_tematica_id, Institucion institucion) {
        AreaTematicaPK areaTematicaPK = new AreaTematicaPK();
        areaTematicaPK.setId(area_tematica_id);

        SiglaTematicaPK siglaTematicaPK = new SiglaTematicaPK();
        siglaTematicaPK.setId(sigla_tematica_id);

        SiglaTematica siglaTematica = new SiglaTematica();
        siglaTematica.setId(siglaTematicaPK);

        AreaTematica areaTematica = new AreaTematica();
        areaTematica.setId(areaTematicaPK);
        areaTematica.setSiglaTematica(siglaTematica);

        areaTematica.setInstitucion(institucion);

        return areaTematica;
    }

    /**
     * Metodo que crea una institucion
     * @param institucion_id es el id de la institucion a crear
     * @return una institucion que tiene asociado el id que entre como argumento
     */
    private Institucion crearInstitucion(String institucion_id){
        InstitucionPK institucionPK = new InstitucionPK();
        institucionPK.setNombre(institucion_id);

        Institucion institucion = new Institucion();
        institucion.setInstitucionPK(institucionPK);

        return institucion;
    }

    @Test
    @DisplayName("Buscar areas tematicas por id")
    /**
     * Metodo que prueba que se busquen las areas tematicas por id
     * Se buscan las que se insertan en el inicializar
     */
    void encontrarAreasTematicas() {
        // Prueba 1
        AreaTematicaPK areaTematicaPK1 = new AreaTematicaPK();
        areaTematicaPK1.setId(AREA_TEMATICA_ID_1);

        iAreaTematicaService.getAreaTematica(areaTematicaPK1);

        assertNotNull(areaTematicaPK1);
        assertEquals(areaTematicaPK1.getId(), AREA_TEMATICA_ID_1);

        // Prueba 2
        AreaTematicaPK areaTematicaPK2 = new AreaTematicaPK();
        areaTematicaPK2.setId(AREA_TEMATICA_ID_2);

        iAreaTematicaService.getAreaTematica(areaTematicaPK2);

        assertNotNull(areaTematicaPK2);
        assertEquals(areaTematicaPK2.getId(), AREA_TEMATICA_ID_2);

        // Prueba 3
        AreaTematicaPK areaTematicaPK3 = new AreaTematicaPK();
        areaTematicaPK3.setId(AREA_TEMATICA_ID_3);

        iAreaTematicaService.getAreaTematica(areaTematicaPK3);

        assertNotNull(areaTematicaPK3);
        assertEquals(areaTematicaPK3.getId(), AREA_TEMATICA_ID_3);
    }

    @Test
    @DisplayName("Buscar areas tematicas por id de institucion")
    /**
     * Metodo que prueba que se encuentren las areas tematicas segun el pk de una institucion
     */
    void buscarAreasTematicasPorInstitucion() {
        // Esta institucion tiene asociadas dos areas tematicas
        InstitucionPK institucionPK1 = new InstitucionPK();
        institucionPK1.setNombre(INSTITUCION_ID_1);

        List<AreaTematica> listaAreasTematicas1 = iAreaTematicaService.getAreaTematicaPorInstitucion(institucionPK1);

        assertNotNull(listaAreasTematicas1);
        assertEquals(listaAreasTematicas1.size(), 2);

        // Esta institucion tiene asociadas un area tematicas
        InstitucionPK institucionPK2 = new InstitucionPK();
        institucionPK2.setNombre(INSTITUCION_ID_2);

        List<AreaTematica> listaAreasTematicas2 = iAreaTematicaService.getAreaTematicaPorInstitucion(institucionPK2);

        assertNotNull(listaAreasTematicas2);
        assertEquals(listaAreasTematicas2.size(), 1);
    }

    @Test
    @DisplayName("Buscar todas las areas tematicas")
    /**
     * Metodo que prueba que se encuentren las areas tematicas insertadas al inicio, además de las ya existentes.
     * Verifica que entre todas las areas tematicas se encuentren las insertadas en el inicializar
     */
    void buscarTodosAreasTematicasPorInstitucion() {

        List<AreaTematica> listaAreasTematicas = iAreaTematicaService.getAreasTematicas();

        assertNotNull(listaAreasTematicas);
        // Se verifica que la cantidad de areasTematicas sean mayor o igual a 3 (las insertadas en el inicializar)
        assert(listaAreasTematicas.size() >= 3);

        // Se verifica que en la lista existan las insertadas en el inicializar
        int areasTematicas = 0;
        for (int i = 0; i < listaAreasTematicas.size(); i++){
            AreaTematica areaTematica = listaAreasTematicas.get(i);
            if (AREAS_TEMATICAS_IDS.contains(areaTematica.getId().getId())) {
                areasTematicas++;
            }
        }
        assertEquals(areasTematicas, AREAS_TEMATICAS_IDS.size());
    }
}
