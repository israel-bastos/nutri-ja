package br.com.grupo.nutrija.application.domain.user.entity.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class UploadImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(UploadImageUtil.class);

    private static boolean success = Boolean.FALSE;

    private UploadImageUtil(){}

    public static boolean doUploadImage(MultipartFile file) {

        if(!file.isEmpty()) {
            String filename = file.getOriginalFilename();

            try {

                String filenameDirectory = "C:\\Users\\israel.bastos\\Desktop\\Projeto\\" +
                        "projeto-intellij\\workspace\\estacio\\nutri-ja\\src\\main\\resources\\" +
                        "static\\img\\uploads";

                File dir = new File(filenameDirectory);

                if (!dir.exists()) {
                    logger.warn("failed to create directory at {}", dir.getAbsolutePath());
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + filename);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(file.getBytes());
                stream.close();

                success = Boolean.TRUE;

                logger.info("saved at: {} ", serverFile.getAbsolutePath());
                logger.info("uploaded filename: {} ", filename);

            } catch(IOException io){
                logger.error(io.getMessage());
            }

        } else {
            logger.warn("failed to upload the image file {}", file.getOriginalFilename());
        }

        return success;
    }
}
