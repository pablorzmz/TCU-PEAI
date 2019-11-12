package tests.controllers.foo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TablasControllerTest {

    // Se guarda el puerto aleatorio para el test
    @LocalServerPort
    private int puertoLocalTest;

    @Autowired
    private TestRestTemplate testRestTemplate;

    // Esta es una forma de hacerlo, simulando una petición real
    // Podría hacerse también teniendo una instancia de cada controlador
    // y llamando a los métodos, pero la gracia es simular solicitudes
    // OJO esto prueba un simple get, ahora con un put, o algo que tenga
    // en el cuerpo nuevas estructuras hay que investigar
    // Acá adjunto un ejemplo del post en donde, como en frontend, se crean HttpHeader
    // https://howtodoinjava.com/spring-boot2/testing/testresttemplate-post-example/
    @DisplayName("TestController: Respuesta de solicitud simple get")
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

}
