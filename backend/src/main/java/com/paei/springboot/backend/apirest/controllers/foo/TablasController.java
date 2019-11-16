package com.paei.springboot.backend.apirest.controllers.foo;

import com.paei.springboot.backend.apirest.model.entity.foo.*;
import com.paei.springboot.backend.apirest.services.foo.ITabla123Service;
import com.paei.springboot.backend.apirest.services.foo.ITabla1Service;
import com.paei.springboot.backend.apirest.services.foo.ITabla2Service;
import com.paei.springboot.backend.apirest.services.foo.ITabla3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class TablasController {

    @Autowired
    private ITabla1Service iTabla1Service;

    @Autowired
    private ITabla2Service iTabla2Service;

    @Autowired
    private ITabla3Service iTabla3Service;

    @Autowired
    private ITabla123Service iTabla123Service;

    @GetMapping("/test")
    public List<Tabla1> ternaria()
    {
        // Tablas 1
        Tabla1 ta1 = new Tabla1();
        ta1.setId(1);
        // Tablas 2
        Tabla2 tb1 = new Tabla2();
        tb1.setId(1);
        // Tablas 3
        Tabla3 tc1 = new Tabla3();
        tc1.setId(1);
        // Tablas 123
        Tabla123 tabla123 = new Tabla123();
        Tabla123Id tabla123Id = new Tabla123Id();
        tabla123Id.setTabla1(ta1);
        tabla123Id.setTabla2(tb1);
        tabla123Id.setTabla3(tc1);
        tabla123.setPrimaryKeyTabla123(tabla123Id);

        // Se guardan los cambios
        iTabla2Service.save(tb1);
        iTabla3Service.save(tc1);
        ta1.addTabla123(tabla123);
        iTabla1Service.save(ta1);


        return iTabla1Service.findAll();
    }

    @GetMapping("/test_tonto")
    public String retornoStringTonto(){
        return "STRING TONTO";
    }

    @GetMapping("/test_tonto_2")
    public ResponseEntity<?> retornoDatosTabla1(){
        Map<String, Object> response = new HashMap<>();
        response.put("datos",this.iTabla1Service.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void establecerMock(ITabla1Service iTabla1Service){
        this.iTabla1Service = iTabla1Service;
    }

}
