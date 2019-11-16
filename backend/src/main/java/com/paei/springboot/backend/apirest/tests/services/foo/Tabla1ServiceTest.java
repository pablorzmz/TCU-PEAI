package com.paei.springboot.backend.apirest.tests.services.foo;

import com.paei.springboot.backend.apirest.dao.foo.ITabla1Dao;
import com.paei.springboot.backend.apirest.model.entity.foo.Tabla1;
import com.paei.springboot.backend.apirest.services.foo.Tabla1ServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class Tabla1ServiceTest {

    // Ojo estos son mock, si se desea hacerlos para
    // tests de integración con la BD, hacerlos autowired
    @Mock
    private ITabla1Dao iTabla1Dao;

    @InjectMocks
    private Tabla1ServiceImpl tabla1Service;

    // Se ejecuta luego de los test, como para limpiar la BD
    // y dejarla en un estado consistente
    @AfterEach
    void limpiarMockTest(){
    }

    // Se ejecuta antes de los test
    @BeforeEach
    void establecerMock(){
        // Se define una lista de tabla1 dummy para el mock
        Tabla1 datoMock1 = new Tabla1();
        Tabla1 datoMock2 = new Tabla1();
        List<Tabla1> mockTabla1  = new ArrayList<>();
        mockTabla1.add(datoMock1);
        mockTabla1.add(datoMock2);
        // Se establece que retornar
        when(iTabla1Dao.findAll()).thenReturn(mockTabla1);
    }

    @DisplayName("Test: FindAll Servicio Tabla1Service")
    @Test
    void ObtenerTodosLosTabla1(){
        // Arrage
        Tabla1 datoMock = new Tabla1();
        List<Tabla1> listaEsperada = new ArrayList<>();
        listaEsperada.add(datoMock);
        listaEsperada.add(datoMock);
        // Act
        final int tamannoEsperado = tabla1Service.findAll().size();
        // Assert
        assertEquals(listaEsperada.size(),tamannoEsperado);
    }
}
