package com.paei.springboot.backend.apirest.tests.services.real;

import com.paei.springboot.backend.apirest.dao.real.IInstitucionDao;
import com.paei.springboot.backend.apirest.model.entity.real.Institucion;
import com.paei.springboot.backend.apirest.model.entity.real.InstitucionPK;
import com.paei.springboot.backend.apirest.services.real.IInstitucionService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Se usa pensando en futuros tests de inserciones o borrados
public class InstitucionServiceTest {

    // Se setea el Dao de institucion
    @Autowired
    private IInstitucionDao iInstitucionDao;

    // Se setea el servicio a probar
    @Autowired
    private IInstitucionService iInstitucionService;

    private final String ID_INSTITUCION_1 = "Universidad_1";
    private final String ID_INSTITUCION_2 = "Universidad_2";
    private final String ID_INSTITUCION_3 = "Colegio_1";

    private final int TAMANO_PAGINA = 4;

    private final List<String> LISTA_ID_INSTITUCIONES = Arrays.asList(ID_INSTITUCION_1, ID_INSTITUCION_2, ID_INSTITUCION_3);

    @BeforeEach
    /**
     * Metodo para inicializar los test
     * Se insertan los datos extras para probar
     */
    private void inicializar(){
        // Se crean y se insertan las instituciones
        Institucion institucion1 = crearInstitucion(ID_INSTITUCION_1);
        iInstitucionDao.save(institucion1);

        Institucion institucion2 = crearInstitucion(ID_INSTITUCION_2);
        iInstitucionDao.save(institucion2);

        Institucion institucion3 = crearInstitucion(ID_INSTITUCION_3);
        iInstitucionDao.save(institucion3);
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
     * Metodo que prueba que el metodo findAll del servicio de institucion funcione correctamente
     * Dicho metodo deberia retornar todas las instituciones existentes en el sistema
     */
    @Test
    void findAll(){
        // Se obtienen todas las instituciones
        List<Institucion> listaInstituciones = iInstitucionService.findAll();
        // Se prueba que la lista no es nula
        assertNotNull(listaInstituciones);
        // Se prueba que el tamaño de la lista sea correcto
        assertTrue(listaInstituciones.size() >= LISTA_ID_INSTITUCIONES.size());
        // Se prueba que las instituciones insertadas al inicio aparezcan en la lista obtenida
        AtomicInteger institucionesInsertadas = new AtomicInteger();
        listaInstituciones.forEach(institucion -> {
            String id = institucion.getInstitucionPK().getNombre();
            // Si el ID de la institucion existe en la lista de IDS constante, entonces se incrementa el contador
            if(LISTA_ID_INSTITUCIONES.contains(id)){
                institucionesInsertadas.getAndIncrement();
            };
        });
        // Se verifica que el contador y el tamaño de la lista de los ids sea el mismo
        assertTrue(institucionesInsertadas.intValue() == LISTA_ID_INSTITUCIONES.size());

    }

    /**
     * Metodo que prueba que el metodo findAll paginado del servicio de institucion funcione correctamente
     * Dicho metodo deberia retornar todas las instituciones existentes dada una pagina
     */
    @Test
    void findAllPage(){
        // Se obtienen todas las instituciones
        List<Institucion> listaInstituciones = iInstitucionService.findAll();
        // Se prueba que la lista no es nula
        assertNotNull(listaInstituciones);
        // Se prueba que el tamaño de la lista sea correcto
        assertTrue(listaInstituciones.size() >= LISTA_ID_INSTITUCIONES.size());
        // Contiene la pagina con las instituciones
        Page<Institucion> paginaInstituciones;
        // Contiene la lista de instituciones opbtenida de la pagina
        List<Institucion> paginaListada = null;
        // Se prueba que los ids de las instituciones paginadas coincidan con la lista de todas las intituciones
        // Cada vez que se puede pedir una pagina se hace  la verificacion
        // En este caso cada 4 instituciones se pide una pagina nueva
        // Se empieza por la pagina 0
        int pagina = 0;
        for (int i = 0; i<listaInstituciones.size(); i++){
            if(i % TAMANO_PAGINA  == 0){
                paginaInstituciones = iInstitucionService.findAll(PageRequest.of(pagina, TAMANO_PAGINA));
                paginaListada = paginaInstituciones.getContent();
                pagina++;
            }
            assertEquals(listaInstituciones.get(i).getInstitucionPK().getNombre(), paginaListada.get(i % TAMANO_PAGINA).getInstitucionPK().getNombre());
        }
    }

    /**
     * Metodo que prueba que el metodo getInstitucion del servicio de institucion funcione correctamente
     * Dicho metodo deberia retornar la institucion con el PK que se le pase como parametro
     * Se prueba con las institcuiones insertadas en el inicializar
     */
    @Test
    void getInstitucion(){
        // Prueba 1
        InstitucionPK institucionPK1 = new InstitucionPK();
        institucionPK1.setNombre(ID_INSTITUCION_1);

        Institucion institucion1 = iInstitucionService.getInstitucion(institucionPK1);
        // Se prueba que no sea nulo
        assertNotNull(institucion1);
        // Se prueba que los identificadores sean iguales
        assertEquals(ID_INSTITUCION_1, institucion1.getInstitucionPK().getNombre());

        // Prueba 2
        InstitucionPK institucionPK2 = new InstitucionPK();
        institucionPK2.setNombre(ID_INSTITUCION_2);

        Institucion institucion2 = iInstitucionService.getInstitucion(institucionPK2);
        // Se prueba que no sea nulo
        assertNotNull(institucion2);
        // Se prueba que los identificadores sean iguales
        assertEquals(ID_INSTITUCION_2, institucion2.getInstitucionPK().getNombre());

        // Prueba 3
        InstitucionPK institucionPK3 = new InstitucionPK();
        institucionPK3.setNombre(ID_INSTITUCION_3);

        Institucion institucion3 = iInstitucionService.getInstitucion(institucionPK3);
        // Se prueba que no sea nulo
        assertNotNull(institucion3);
        // Se prueba que los identificadores sean iguales
        assertEquals(ID_INSTITUCION_3, institucion3.getInstitucionPK().getNombre());
    }

}
