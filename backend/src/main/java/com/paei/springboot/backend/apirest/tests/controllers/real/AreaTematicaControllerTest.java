package com.paei.springboot.backend.apirest.tests.controllers.real;

import com.paei.springboot.backend.apirest.controllers.real.AreaTematicaController;
import com.paei.springboot.backend.apirest.dao.real.IAreaTematicaDao;
import com.paei.springboot.backend.apirest.dao.real.IInstitucionDao;
import com.paei.springboot.backend.apirest.model.entity.real.*;
import com.paei.springboot.backend.apirest.services.real.AreaTematicaServiceImpl;
import com.paei.springboot.backend.apirest.services.real.InstitucionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AreaTematicaControllerTest {


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

    // Se inicializan el controlador y los servicios necesarios
    private AreaTematicaController areaTematicaController;

    @Mock
    private IAreaTematicaDao iAreaTematicaDao;

    @Mock
    private IInstitucionDao iInstitucionDao;

    @InjectMocks
    private AreaTematicaServiceImpl areaTematicaService;

    @InjectMocks
    private InstitucionServiceImpl institucionService;


    @BeforeEach
    void inicializar(){
        // Se crea la institucion 1
        Institucion institucion1 = crearInstitucion(INSTITUCION_ID_1);
        // Se crea la institucion 2
        Institucion institucion2 = crearInstitucion(INSTITUCION_ID_2);
        // Se crea el area tematica 1
        AreaTematica areaTematica1 = crearAreaTematica(AREA_TEMATICA_ID_1, SIGLA_TEMATICA_ID_1, institucion1);
        // Se crea el area tematica 2
        AreaTematica areaTematica2 = crearAreaTematica(AREA_TEMATICA_ID_2, SIGLA_TEMATICA_ID_2, institucion1);
        // Se crea el area tematica 3
        AreaTematica areaTematica3 = crearAreaTematica(AREA_TEMATICA_ID_3, SIGLA_TEMATICA_ID_3, institucion2);

        // Lista de todas las areas tematicas
        List<AreaTematica> listaTodasAreasTematicas = Arrays.asList(areaTematica1, areaTematica2, areaTematica3);

        // Lista de las areas tematicas de la institucion 1
        List<AreaTematica> listaAreasTematicasInstitucion1 = Arrays.asList(areaTematica1, areaTematica2);

        // Lista de las areas tematicas de la institucion 2
        List<AreaTematica> listaAreasTematicasInstitucion2 = Arrays.asList(areaTematica3);

        // Dao Areas Tematicas
        when(iAreaTematicaDao.findAll()).thenReturn(listaTodasAreasTematicas);
        when(iAreaTematicaDao.findAreaTematicaByInstitucion(institucion1.getInstitucionPK())).thenReturn(listaAreasTematicasInstitucion1);
        when(iAreaTematicaDao.findAreaTematicaByInstitucion(institucion2.getInstitucionPK())).thenReturn(listaAreasTematicasInstitucion2);

        when(iAreaTematicaDao.findById(areaTematica1.getId())).thenReturn(Optional.of(areaTematica1));
        when(iAreaTematicaDao.findById(areaTematica2.getId())).thenReturn(Optional.of(areaTematica2));
        when(iAreaTematicaDao.findById(areaTematica3.getId())).thenReturn(Optional.of(areaTematica3));

        // Dao Institucion
        when(iInstitucionDao.findById(institucion1.getInstitucionPK())).thenReturn(Optional.of(institucion1));
        when(iInstitucionDao.findById(institucion2.getInstitucionPK())).thenReturn(Optional.of(institucion2));

        this.areaTematicaController = new AreaTematicaController(this.areaTematicaService, this.institucionService);
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
    @DisplayName("Buscar todas las areas tematicas")
    /**
     * Metodo que prueba que se busquen todas las areas tematicas existentes
     */
    void recuperarAreasTematica() {
        List<AreaTematica> listaAreasTematicas = this.areaTematicaController.recuperarAreasTematica();
        // Se verifica que no es nulo
        assertNotNull(listaAreasTematicas);
        // Se verifica que sea igual a la cantidad inicial
        assertEquals(listaAreasTematicas.size(), AREAS_TEMATICAS_IDS.size());
        // Se verifica que exista cada una de ellas
        listaAreasTematicas.forEach(areaTematica -> {
            Long id = areaTematica.getId().getId();
            assertTrue(AREAS_TEMATICAS_IDS.contains(id));
        });
    }

    @Test
    @DisplayName("Buscar areas tematicas por id de institucion")
    /**
     * Metodo que prueba que se encuentren las areas tematicas segun el id de una institucion
     */
    void recuperarAreasTematicasDeInstitucion() {
        // Institucion 1
        List<AreaTematica> listaAreasTematicasInst1 = this.areaTematicaController.recuperarAreasTematicasDeInstitucion(INSTITUCION_ID_1);
        // Se verifica que no es nulo
        assertNotNull(listaAreasTematicasInst1);
        // Se verifica que sea igual a la cantidad de areas establecidas en el inicializar (2)
        assertEquals(listaAreasTematicasInst1.size(), 2);
        // Se verifica que exista cada una de ellas
        listaAreasTematicasInst1.forEach(areaTematica -> {
            Long id = areaTematica.getId().getId();
            assertTrue(AREAS_TEMATICAS_IDS.contains(id));
        });

        // Institucion 2
        List<AreaTematica> listaAreasTematicasInst2 = this.areaTematicaController.recuperarAreasTematicasDeInstitucion(INSTITUCION_ID_2);
        // Se verifica que no es nulo
        assertNotNull(listaAreasTematicasInst2);
        // Se verifica que sea igual a la cantidad de areas establecidas en el inicializar (1)
        assertEquals(listaAreasTematicasInst2.size(), 1);
        // Se verifica que exista cada una de ellas
        listaAreasTematicasInst2.forEach(areaTematica -> {
            Long id = areaTematica.getId().getId();
            assertTrue(AREAS_TEMATICAS_IDS.contains(id));
        });
    }

    @Test
    @DisplayName("Buscar areas tematicas segun su id")
    /**
     * Metodo que prueba que se busque correctamente cada area tematica por su id
     */
    void recuperarAreaTematicaPorId() {
        // Area Tematica 1
        AreaTematica areaTematica1 = this.areaTematicaController.recuperarAreaTematicaPorId(AREA_TEMATICA_ID_1);
        // Se verifica que no es nulo
        assertNotNull(areaTematica1);
        // Se verifica que su id coincida con el buscado
        assertEquals(areaTematica1.getId().getId(), AREA_TEMATICA_ID_1);

        // Area Tematica 2
        AreaTematica areaTematica2 = this.areaTematicaController.recuperarAreaTematicaPorId(AREA_TEMATICA_ID_2);
        // Se verifica que no es nulo
        assertNotNull(areaTematica2);
        // Se verifica que su id coincida con el buscado
        assertEquals(areaTematica2.getId().getId(), AREA_TEMATICA_ID_2);

        // Area Tematica 3
        AreaTematica areaTematica3 = this.areaTematicaController.recuperarAreaTematicaPorId(AREA_TEMATICA_ID_3);
        // Se verifica que no es nulo
        assertNotNull(areaTematica3);
        // Se verifica que su id coincida con el buscado
        assertEquals(areaTematica3.getId().getId(), AREA_TEMATICA_ID_3);
    }

}
