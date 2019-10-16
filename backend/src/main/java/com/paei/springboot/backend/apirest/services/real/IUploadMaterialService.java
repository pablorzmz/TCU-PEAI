package com.paei.springboot.backend.apirest.services.real;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadMaterialService {

    public Resource cargar (String nombreArchivo) throws MalformedURLException;

    public String almacenar (MultipartFile archivo) throws IOException;

    public boolean eliminar (String nombreArchivo);

    public Path getPath(String nombreMaterial);
}
