package com.example.data_jpa_vintage.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.data_jpa_vintage.controller.ClienteController;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private static final String UPLOADS_FOLDER = "uploads";

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathFoto = getPath(filename);
        logger.info("pathFoto: " + pathFoto);
        Resource recurso = null;

        recurso = new UrlResource(pathFoto.toUri());
        if (!recurso.exists() || !recurso.isReadable()) {
            throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
        }

        return recurso;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // String roothString =
        // "C://Users//roberto.bravo//Desktop//proy//curso-springboot//almacen";

        Path rootPath = getPath(uniqueFilename);

        logger.info("rootPath: " + rootPath);
        logger.info("rootAbsolutPath: " + rootPath);

        Files.copy(file.getInputStream(), rootPath);
        return uniqueFilename;

    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File archivo = rootPath.toFile();
        if (archivo.exists() && archivo.canRead()) {
            if (archivo.delete()) {
                return true;
            }

        }
        return false;
    }

    public Path getPath(String filename) {

        return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void init() throws IOException {
        if (!Files.exists(Paths.get(UPLOADS_FOLDER))) {
            logger.info("Creando directorio uploads");
            Files.createDirectory(Paths.get(UPLOADS_FOLDER));

        } else {
            logger.info("No se pudo crear el directorio, porque ya existe");
        }
    }

}
