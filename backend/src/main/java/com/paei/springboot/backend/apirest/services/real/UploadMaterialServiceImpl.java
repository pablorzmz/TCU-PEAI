package com.paei.springboot.backend.apirest.services.real;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadMaterialServiceImpl implements IUploadMaterialService {

    private final static String DIRECTORIO_UPLOAD_MATERIAL = "uploads\\materiales";

    /**
     * Método que recupera un documento del servidor
     * @param nombreArchivo es el nombre del archivo que se desea recuperar
     * @return el contenido del material en formato Resource
     * @throws MalformedURLException si la dirección es erronea
     */
    @Override
    public Resource cargar(String nombreArchivo) throws MalformedURLException {
        // Se obtiene la ruta según el nombre del archivo
        Path rutaArchivo = getPath(nombreArchivo);
        // Se obtiene el archivo
        Resource recurso = recurso = new UrlResource(rutaArchivo.toUri());

        // Si no existe o no se puede leer se retorna null
        if (!recurso.exists() && !recurso.isReadable()){
            return null;
        }
        return recurso;
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
        // Si el archivo existe y se tiene permiso de escritura, se elimina
        if ( nombreArchivo != null && nombreArchivo.length() > 0 ) {
            Path rutaFotoAnterior = getPath(nombreArchivo);
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() &&  archivoFotoAnterior.canRead() ){
                archivoFotoAnterior.delete();
                return true;
            }
        }
        // No se pudo eliminar
        return false;
    }

    @Override
    public Path getPath(String nombreMaterial) {
        return Paths.get(DIRECTORIO_UPLOAD_MATERIAL).resolve(nombreMaterial).toAbsolutePath();
    }
}
