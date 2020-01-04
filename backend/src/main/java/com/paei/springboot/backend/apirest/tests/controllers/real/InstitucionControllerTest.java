package com.paei.springboot.backend.apirest.tests.controllers.real;

import com.paei.springboot.backend.apirest.controllers.real.InstitucionController;
import com.paei.springboot.backend.apirest.dao.real.IInstitucionDao;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.services.real.InstitucionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class InstitucionControllerTest {

    // Se inicializan las constantes que seran usadas como datos para los test
    private final String ID_INSTITUCION_1 = "Universidad_1";
    private final String ID_INSTITUCION_2 = "Universidad_2";
    private final String ID_INSTITUCION_3 = "Colegio_1";
    private final String ID_INSTITUCION_4 = "Colegio_2";
    private final String ID_INSTITUCION_5 = "Colegio_3";

    private final int TAMANO_PAGINA = 4;

    private final List<String> LISTA_ID_INSTITUCIONES = Arrays.asList(ID_INSTITUCION_1, ID_INSTITUCION_2, ID_INSTITUCION_3, ID_INSTITUCION_4, ID_INSTITUCION_5);

    // Se crea el controlador
    private InstitucionController institucionController;

    // Se crea el mock que será utilizado
    @Mock
    private IInstitucionDao iInstitucionDao;

    // Se injecta el mock al servicio
    @InjectMocks
    private InstitucionServiceImpl institucionService;


    @BeforeEach
    void inicializar(){
        // Se crean las instituciones
        Institucion institucion1 = crearInstitucion(ID_INSTITUCION_1);
        Institucion institucion2 = crearInstitucion(ID_INSTITUCION_2);
        Institucion institucion3 = crearInstitucion(ID_INSTITUCION_3);
        Institucion institucion4 = crearInstitucion(ID_INSTITUCION_4);
        Institucion institucion5 = crearInstitucion(ID_INSTITUCION_5);

        // Lista de todas las instituciones existentes
        List<Institucion> listaTodasInstituciones = Arrays.asList(institucion1, institucion2, institucion3, institucion4, institucion5);


        // Caso en el que se piden todas las instituciones listadas
        when(iInstitucionDao.findAll()).thenReturn(listaTodasInstituciones);

        // Caso de paginacion
        // Caso en el que se piden las instituciones por pagina
        when(iInstitucionDao.findAll(PageRequest.of(0, TAMANO_PAGINA))).thenReturn(new PageImpl<>(listaTodasInstituciones.subList(0, 4)));
        when(iInstitucionDao.findAll(PageRequest.of(1, TAMANO_PAGINA))).thenReturn(new PageImpl<>(listaTodasInstituciones.subList(4, 5)));

        // Se instancioa el controlador con el servicio
        institucionController = new InstitucionController(institucionService);
    }

    /**
     * Metodo para crear instituciones para las pruebas
     * @param institucionID es el identificador de la institucion
     * @return retorna una institucion con el PK teniendo el identificador que viene como argumento
     */
    private Institucion crearInstitucion(String institucionID){
        InstitucionPK institucionPK = new InstitucionPK();
        institucionPK.setNombre(institucionID);

        Institucion institucion = new Institucion();
        institucion.setInstitucionPK(institucionPK);

        return institucion;
    }

    /**
     * Metodo para probar el metodo index2 del controlador de institucion
     * Dicho metodo retorna todas las instituciones existentes
     */
    @Test
    void findAll(){
        List<Institucion> listaInstituciones = institucionController.index2();
        // Se prueba que no sea nulo
        assertNotNull(listaInstituciones);
        // Se prueba que el tamaño coincidad con el tamaño de la lista de los ids
        assertEquals(listaInstituciones.size(), LISTA_ID_INSTITUCIONES.size());
        // Se prueba que cada id de las instituciones obtenidas exista en la lista de los ids
        listaInstituciones.forEach(institucion -> {
            String id = institucion.getInstitucionPK().getNombre();
            assertTrue(LISTA_ID_INSTITUCIONES.contains(id));
        });
    }

    /**
     * Metodo para probar el metodo index del controlador de institucion
     * Dicho metodo retorna todas las instituciones existentes en dada un numero de pagina y tamaño
     */
    @Test
    void findAllPage(){
        // Se pide la pagina 1 al controlador
        Page<Institucion> pagina1Instituciones = institucionController.index(0);
        // Se prueba que no sea nulo
        assertNotNull(pagina1Instituciones);
        // Se prueba que el tamaño coincidad con el tamaño de la pagina
        assertEquals(pagina1Instituciones.getContent().size(), TAMANO_PAGINA);
        // Se prueba que cada id de las instituciones obtenidas exista en la lista de los ids
        pagina1Instituciones.getContent().forEach(institucion -> {
            String id = institucion.getInstitucionPK().getNombre();
            assertTrue(LISTA_ID_INSTITUCIONES.contains(id));
        });
        // Se prueba que las isntituciones coincidadn en posicion segun la pagina
        // Caso pagina 1
        for (int i = 0; i < TAMANO_PAGINA; i++) {
            assertTrue(LISTA_ID_INSTITUCIONES.get(i) == pagina1Instituciones.getContent().get(i).getInstitucionPK().getNombre());
        }

        // Se pide la pagina 2 al controlador
        Page<Institucion> pagina2Instituciones = institucionController.index(1);
        // Se prueba que no sea nulo
        assertNotNull(pagina2Instituciones);
        // Se prueba que el tamaño coincidad con el tamaño de la pagina, en este caso es 1 por ser la seguna pagina y ser 5 instituciones
        assertEquals(pagina2Instituciones.getContent().size(), 1);
        // Se prueba que cada id de las instituciones obtenidas exista en la lista de los ids
        pagina2Instituciones.getContent().forEach(institucion -> {
            String id = institucion.getInstitucionPK().getNombre();
            assertTrue(LISTA_ID_INSTITUCIONES.contains(id));
        });
        // Se prueba que las isntituciones coincidadn en posicion segun la pagina
        // Caso pagina 2
        assertTrue(LISTA_ID_INSTITUCIONES.get(4) == pagina2Instituciones.getContent().get(0).getInstitucionPK().getNombre());

    }

}
