package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadFileService {

    private final ServletContext servletContext;

    public UploadFileService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleUploadFile(MultipartFile avatarFile, String targetDir) {
        String nameFile = "";
        try {
            byte[] bytes = avatarFile.getBytes();
            String rootPath = this.servletContext.getRealPath("/resources/images");

            File dir = new File(rootPath + File.separator + targetDir);
            if (!dir.exists())
                dir.mkdirs();

            nameFile = System.currentTimeMillis() + "-" + avatarFile.getOriginalFilename();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + nameFile);

            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nameFile;

    }

    public void handedRemoveFile(String fileName, String targetDir) {
        String rootPath = this.servletContext.getRealPath("/resources/images/" + targetDir + "/");

        File deleteFile = new File(rootPath + fileName);
        if (deleteFile.exists()) {
            deleteFile.delete();
        } else {
            System.out.println("file not found.");
        }
    }

}
