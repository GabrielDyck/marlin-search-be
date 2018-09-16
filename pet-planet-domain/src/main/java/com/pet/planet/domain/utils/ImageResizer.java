package com.pet.planet.domain.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageResizer {

        public static byte[] resize(BufferedImage inputImage,int scaledWidth, int scaledHeight)
                throws IOException {
// creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth,
                    scaledHeight, inputImage.getType());

            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( outputImage, "jpg", baos );
            baos.flush();

            byte[] imageInByte = Base64.getEncoder().encode(baos.toByteArray());
            baos.close();
            return imageInByte;
        }


    }

