package org.example.website;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@RestController
public class ImageController {
    // http://localhost:8080/image?url=https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHHxC5yGDj1VKzg6uRmHKJzb0KOLAQVJnkHA&s&text=spongebob
    @GetMapping("/image")
    public void addText(@RequestParam String url,
                        @RequestParam String text,
                        HttpServletResponse response) throws IOException {

        BufferedImage image = ImageIO.read(new URL(url));

        Graphics2D g2d = image.createGraphics();
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.RED);
        g2d.setBackground(Color.BLACK);

        FontMetrics fm = g2d.getFontMetrics();
        String retardText = textRetard(text);
        int textWidth = fm.stringWidth(retardText);
        int textHeight = fm.getHeight();

        int x = (image.getWidth() - textWidth) / 2;
        int y = (image.getHeight() - textHeight) / 2 + fm.getAscent(); // Ascent = vycentrovani obrazku

        g2d.drawString(retardText, x, y);
        g2d.dispose();

        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());
    }


    private String textRetard(String text) {
        String result = "";
        int letterIndex = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                result += (letterIndex % 2 == 0) ? Character.toLowerCase(c) : Character.toUpperCase(c);
                letterIndex++;
            } else {
                result += c;
            }
        }
        return result;
    }
}
