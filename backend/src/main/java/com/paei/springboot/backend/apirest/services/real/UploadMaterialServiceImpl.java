package com.paei.springboot.backend.apirest.services.real;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadMaterialServiceImpl implements IUploadMaterialService {

    private final static String DIRECTORIO_UPLOAD_MATERIAL = "uploads\\materiales";

    @Override
    public Resource cargar(String nombreArchivo) throws MalformedURLException {
        return null;
    }

    @Override
    public String almacenar(MultipartFile archivo) throws IOException {
        // obtener el nombre original random único
        String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
        // Ruta para almacenar los archivos
        Path rutaArchivo = getPath(nombreArchivo);
        // Se guarda
        Files.copy(archivo.getInputStream(),rutaArchivo);
        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String nombreArchivo) {
        return false;
    }

    @Override
    public Path getPath(String nombreMaterial) {
        return Paths.get(DIRECTORIO_UPLOAD_MATERIAL).resolve(nombreMaterial).toAbsolutePath();
    }
}
