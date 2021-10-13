package br.com.treinaweb.ediaristas.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
  private final Path uploadFolder = Paths.get("uploads");

  public String save(MultipartFile file) throws IOException {
    if (!Files.exists(uploadFolder)) {
      Files.createDirectory(uploadFolder);
    }

    var originalFile = file.getOriginalFilename();
    var extension = originalFile.split("\\.")[1];
    var filename = UUID.randomUUID().toString() + "." + extension;

    Files.copy(file.getInputStream(), uploadFolder.resolve(filename));

    return filename;
  }

  public Resource load(String filename) throws MalformedURLException {
    var filePath = uploadFolder.resolve(filename);

    return new UrlResource(filePath.toUri());
  }
}
