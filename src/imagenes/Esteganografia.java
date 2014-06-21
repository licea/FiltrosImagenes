package imagenes;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Oculta un mensaje en una imagen
 * @author Erik
 */
public class Esteganografia {
   
   private final char separador = '^';
   
   public static String intercambiar(String cadena) {
      int x = cadena.length();
      char c[] = cadena.toCharArray();
      if (c[x-1] == '0') {
         c[x-1] = '1';
      } else {
         c[x-1] = '0';
      }
      return new String(c);
   }
   
   public static String binary(String texto) {
      String cad = "";
      for (int i = 0; i < texto.length(); i++) {
         String caracter = Integer.toBinaryString(texto.charAt(i));
         if (caracter.length() == 6) {
            caracter = "0".concat(caracter);
         }
         cad += caracter;
      }
      return cad;
   }
   
   public String revelar(String nombre) {
      String texto = "";
      try {
         BufferedImage imagen = ImageIO.read(new File(nombre));
         int width = imagen.getWidth();
         int height = imagen.getHeight();
         String bits = "";   
         for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
               int color = imagen.getRGB(i, j);
               Color c = new Color(color);
               String r = Integer.toBinaryString(c.getRed());
               int rlen = r.length() - 1;
               bits += r.charAt(rlen);
               if (bits.length() == 7) {
                  char letra = (char) Integer.parseInt(bits, 2);
                  if (letra == separador) {
                     return texto;
                  }
                  texto += letra;
                  bits = "";
               }
            }
         }
      } catch (IOException e) {
         System.out.println("No se ha encontrado la imagen");
      }
      return texto;
   }
   
   public void ocultar(String nombre, String texto) {
      try {
         BufferedImage imagen = ImageIO.read(new File(nombre));
         int width = imagen.getWidth();
         int height = imagen.getHeight();
         texto += separador;
         String cadena = binary(texto);
         int k = 0;
         for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
               if (k == cadena.length()) {
                  break;
               }
               int color = imagen.getRGB(i, j);
               Color c = new Color(color);
               String r = Integer.toBinaryString(c.getRed());
               int lenRed = r.length() - 1;
               int g = c.getGreen();
               int b = c.getBlue();
               int rn;
               if (r.charAt(lenRed) != cadena.charAt(k)) {
                  rn = Integer.parseInt(intercambiar(r),2);
               } else {
                  rn = c.getRed();
               }
               Color nuevo = new Color(rn, g, b);
               imagen.setRGB(i, j, nuevo.getRGB());
               k++;
            }
         }
         File salida = new File("mensaje.bmp");
         ImageIO.write(imagen, "bmp", salida);
      } catch (IOException e) {
         System.out.println("No se ha encontrado la imagen.");
      }
   }
   
   public static void main(String[] args) {
      Esteganografia e = new Esteganografia();
      String h = "Este es mi primer mensaje oculto";
      e.ocultar("tswift.bmp", h);
      String f = e.revelar("mensaje.bmp");
      System.out.println(f);
   }
}