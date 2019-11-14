package com.paei.springboot.backend.apirest.tests.controllers.foo;

import com.paei.springboot.backend.apirest.controllers.foo.TablasController;
import com.paei.springboot.backend.apirest.dao.foo.ITabla1Dao;
import com.paei.springboot.backend.apirest.model.entity.foo.Tabla1;
import com.paei.springboot.backend.apirest.services.foo.Tabla1ServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TablasControllerTest {

    // Se guarda el puerto aleatorio para el test
    @LocalServerPort
    private int puertoLocalTest;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Mock
    private ITabla1Dao iTabla1Dao;

    @InjectMocks
    private Tabla1ServiceImpl tabla1Service;

    // Esta es una forma de hacerlo, simulando una petición real (Prueba de integración con BD real)
    // Podría hacerse también teniendo una instancia de cada controlador
    // y llamando a los métodos, pero la gracia es simular solicitudes
    // OJO esto prueba un simple get, ahora con un put, o algo que tenga
    // en el cuerpo nuevas estructuras hay que investigar
    // Acá adjunto un ejemplo del post en donde, como en frontend, se crean HttpHeader
    // https://howtodoinjava.com/spring-boot2/testing/testresttemplate-post-example/
    @DisplayName("TablasControllerTest: ( Integración ) Respuesta de solicitud simple get")
    @Test
    public void obtenerStringTonto() throws Exception{
        // Arrage
        final String resultadoEsperado = "STRING TONTO";
        final String endPointTest = new URL("http://localhost:" + puertoLocalTest + "/api/test_tonto").toString();
        // Act
        ResponseEntity<String> response = testRestTemplate.getForEntity(endPointTest, String.class);
        // Assert
        assertEquals(resultadoEsperado, response.getBody());
    }

    @DisplayName("TablasControllerTest: ( Unidad con Mock ) Get ResponseEntity con info tablas 1.")
    @Test
    public void ObtenerDatosTabla1Mock() {
        // Arrage
        TablasController tablasController = new TablasController();
        tablasController.establecerMock( this.generarMock() );
        // Act
        ResponseEntity<?> resultado =  tablasController.retornoDatosTabla1();
        var response = (HashMap<String,Object>)resultado.getBody();
        // Assert
        assert response != null;
        assertEquals(((ArrayList<Tabla1>)response.get("datos")).size(),2);
    }

    private Tabla1ServiceImpl generarMock(){
        // Se define una lista de tabla1 dummy para el mock
        Tabla1 datoMock1 = new Tabla1();
        datoMock1.setId(1);
        Tabla1 datoMock2 = new Tabla1();
        datoMock2.setId(2);
        List<Tabla1> mockTabla1  = new ArrayList<>();
        mockTabla1.add(datoMock1);
        mockTabla1.add(datoMock2);
        // Se establece que retornar
        when(iTabla1Dao.findAll()).thenReturn(mockTabla1);
        return this.tabla1Service;
    }
}
