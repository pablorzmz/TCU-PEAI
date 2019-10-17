package com.paei.springboot.backend.apirest.services.real;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface IUploadMaterialService {

    public Resource cargar (String nombreArchivo) throws MalformedURLException;

    /**
     * Metodo que permite almacenar un archivo de material en el servidor
     * @param archivo El archivo a alamacenar
     * @return El nombre del archivo creado o bien, una excepcion
     * @throws IOException
     */
    public String almacenar (MultipartFile archivo) throws IOException;

    public boolean eliminar (String nombreArchivo);

    /**
     * Devuelve la ruta en el servidor en la que está almacenado el archivo
     * @param nombreMaterial Nombre del material para contruir la ruta
     * @return La ruta en el server del archivo construida.
     */
    public Path getPath(String nombreMaterial);
}
