package com.paei.springboot.backend.apirest.controllers;

import com.paei.springboot.backend.apirest.model.entity.*;
import com.paei.springboot.backend.apirest.services.ITabla123Service;
import com.paei.springboot.backend.apirest.services.ITabla1Service;
import com.paei.springboot.backend.apirest.services.ITabla2Service;
import com.paei.springboot.backend.apirest.services.ITabla3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Tabla1 ta2 = new Tabla1();
        ta2.setId(2);
        Tabla1 ta3 = new Tabla1();
        ta3.setId(3);
        // Tablas 2
        Tabla2 tb1 = new Tabla2();
        tb1.setId(1);
        Tabla2 tb2 = new Tabla2();
        tb2.setId(2);
        Tabla2 tb3 = new Tabla2();
        tb3.setId(3);
        // Tablas 3
        Tabla3 tc1 = new Tabla3();
        tc1.setId(1);
        Tabla3 tc2 = new Tabla3();
        tc2.setId(2);
        Tabla3 tc3 = new Tabla3();
        tc3.setId(3);
        // Tablas 123
        Tabla123 tabla123 = new Tabla123();
        Tabla123Id tabla123Id = new Tabla123Id();
        tabla123Id.setTabla1(ta1);
        tabla123Id.setTabla2(tb1);
        tabla123Id.setTabla3(tc1);
        tabla123.setPrimaryKeyTabla123(tabla123Id);

        // Se guardan los cambios

        iTabla1Service.save(ta1);
        iTabla2Service.save(tb1);
        iTabla3Service.save(tc1);
        iTabla123Service.save(tabla123);

        return iTabla1Service.findAll();
    }
}
