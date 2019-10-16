package com.paei.springboot.backend.apirest;

import com.paei.springboot.backend.apirest.dao.real.IMaterialDao;
import com.paei.springboot.backend.apirest.services.real.IMaterialService;
import com.paei.springboot.backend.apirest.services.real.IUploadMaterialService;
import com.paei.springboot.backend.apirest.services.real.UploadMaterialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.util.UUID;


@SpringBootApplication
public class SpringbootBackendApirestPeaiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBackendApirestPeaiApplication.class, args);
    }

    @Autowired
    private IMaterialDao iMaterialDao;

    @Autowired
    private IUploadMaterialService iUploadMaterialService;

    @Override
    public void run(String... args) throws Exception {
        // Se generan las rutas para los archivos TEMPORAL hasta que se puedan insertar de verdad
        var materiales = iMaterialDao.findAll();
        for (var m: materiales ){
            String nombreArchivo = UUID.randomUUID().toString() + "_" + "nombreDeUnPDFRealQueVieneDeFrontend.pdf";
            Path ruta = iUploadMaterialService.getPath(nombreArchivo);
            m.setUrl(ruta.toString());
        }
    }
}
