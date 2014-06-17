package imagenes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Software de filtrado de imágenes
 * Proceso digital de imágenes
 * @author Erik Martínez Licea
 */
public class Imagenes {
   
   public static final int mask1 = 0xff808080;
   public final static double blur1 [][] = {{0,.2,0},{.2,.2,.2},{0,.2,0}};
   public final static double blur2 [][] = {{0,0,1,0,0},{0,1,1,1,0},{1,1,1,1,1},{0,1,1,1,0},{0,0,1,0,0}};
   public final static double sharpen [][] = {{-1,-1,-1},{-1,9,-1},{-1,-1,-1}};
   public final static double more_sharpen [][] = {{1,1,1},{1,-7,1},{1,1,1}};
   public final static double emboss [][] = {{-2,-1,0},{-1,1,1},{0,1,2}};
   public final static double emboss2 [][] = {{-1,-1,0},{-1,0,1},{0,1,1}};
   public final static double bordes [][] = {{0,1,0},{1,-4,1},{0,1,0}};
   public final static double promedio [][] = {{1,1,1}, {1,1,1}, {1,1,1}};
   
   public final static double floyd[] = {7/16,3/16,5/16,1/16};
   
   private final static Color [] palette = {
      (new Color(0xffffff)),
      (new Color(0xc0c0c0)),
      (new Color(0x808080)),
      (new Color(0x000000)),
      (new Color(0xff0000)),
      (new Color(0x800000)),
      (new Color(0xffff00)),
      (new Color(0x808000)),
      (new Color(0x00ff00)),
      (new Color(0x008000)),
      (new Color(0x00ffff)),
      (new Color(0x008080)),
      (new Color(0x0000ff)),
      (new Color(0x000080)),
      (new Color(0xff00ff)),
      (new Color(0x800080))
   };
   
   public static BufferedImage abrirImagen(String nombre) {
      BufferedImage imagen = null;
      try {
         imagen = ImageIO.read(new File(nombre));
      } catch (IOException e) {
         System.out.println("No se ha encontrado la imagen");
      }
      return imagen;
   }
   
   private static void guardarImagen(String nombre, BufferedImage nueva) {
      try {
         File salida = new File(nombre);
         ImageIO.write(nueva, "bmp", salida);
      } catch (IOException e) {
      }
   }
   
   /*
   Escala de grises por el método tradicional haciendo el promedio
   */
   public static BufferedImage grises(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            int verde = c.getGreen();
            int azul = c.getBlue();
            int prom = (rojo + verde + azul) / 3;
            Color gris = new Color(prom, prom, prom);
            nueva.setRGB(i, j, gris.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Escala de grises por el método de multiplicar cada valor de RGB por
   constantes fijas.
   */
   public static void grises2(String nombre) {
      BufferedImage imagen = abrirImagen(nombre);
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            int verde = c.getGreen();
            int azul = c.getBlue();
            int r = (int) (rojo * .3);
            int g = (int) (verde * .59);
            int b = (int) (azul * .11);
            Color gris = new Color(r, g, b);
            nueva.setRGB(i, j, gris.getRGB());
         }
      }
      guardarImagen("grises2.bmp", nueva);
   }
   
   /*
   Filtro que transforma una imagen a tonos rojos.
   */
   public static BufferedImage filtroRojo(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            Color red = new Color(rojo, 0, 0);
            nueva.setRGB(i, j, red.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Filtro que transforma una imagen a tonos azules.
   */
   public static BufferedImage filtroAzul(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int azul = c.getBlue();
            Color blue = new Color(0, 0, azul);
            nueva.setRGB(i, j, blue.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Filtro que transforma una imagen a tonos verdes.
   */
   public static BufferedImage filtroVerde(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int verde = c.getGreen();
            Color green = new Color(0, verde, 0);
            nueva.setRGB(i, j, green.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Aplica el filtro del color que recibe como parametro en RGB
   */
   public static BufferedImage filtroRGB(BufferedImage imagen, int r, int g, int b) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            int verde = c.getGreen();
            int azul = c.getBlue();
            Color rgb = new Color((rojo + r)/2, (verde + g)/2, (azul + b)/2);
            nueva.setRGB(i, j, rgb.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Filtro que transforma una imagen a tonos amarillos.
   */
   public static BufferedImage filtroAmarillo(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            int verde = c.getGreen();
            Color yellow = new Color(rojo, verde, 0);
            nueva.setRGB(i, j, yellow.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Filtro que transforma una imagen a tonos magenta.
   */
   public static BufferedImage filtroMagenta(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            int azul = c.getBlue();
            Color magenta = new Color(rojo, 0, azul);
            nueva.setRGB(i, j, magenta.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Filtro que transforma una imagen a tonos cian.
   */
   public static BufferedImage filtroCian(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int azul = c.getBlue();
            int verde = c.getGreen();
            Color cian = new Color(0, verde, azul);
            nueva.setRGB(i, j, cian.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Método que crea una imágen con colores aleatorios.
   */
   public void ruido() {
      BufferedImage nueva = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < 500; i++) {
         for (int j = 0; j < 500; j++) {
            int randomRed = (int) (Math.random() * 255);
            int randomGreen = (int) (Math.random() * 255);
            int randomBlue = (int) (Math.random() * 255);
            Color ruido = new Color(randomRed, randomGreen, randomBlue);
            nueva.setRGB(i, j, ruido.getRGB());
         }
      }
      guardarImagen("ruido.bmp", nueva);
   }
   
   /*
   Filtro que regresa el alto contraste de una imagen.
   */
   public static BufferedImage contraseAlto(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            int verde = c.getGreen();
            int azul = c.getBlue();
            int numColor = (rojo * 256) + (verde * 16) + azul;
            Color contraste;
            if (numColor > 40535) {
               contraste = new Color(255, 255, 255);
            } else {
               contraste = new Color(0, 0, 0);
            }
            nueva.setRGB(i, j, contraste.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Filtro que regresa el contraste inverso de una imagen.
   */
   public static BufferedImage contraseInverso(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            int verde = c.getGreen();
            int azul = c.getBlue();
            int numColor = (rojo * 256) + (verde * 16) + azul;
            Color contraste;
            if (numColor < 40535) {
               contraste = new Color(255, 255, 255);
            } else {
               contraste = new Color(0, 0, 0);
            }
            nueva.setRGB(i, j, contraste.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Método auxiliar para obtener el color promedio de una imagen dado la posición
   del primer pixel y el tamano deseado de la región (debe ser divisible entre
   el ancho y largo de la imagen).
   */
   public static Color colorPromedio(BufferedImage imagen, int x, int y, int cambio) {
      int rojoPromedio = 0;
      int verdePromedio = 0;
      int azulPromedio = 0;
      int cota_X = x + cambio;
      int cota_Y = y + cambio;
      for (int i = x; i < cota_X; i++) {
         for (int j = y; j < cota_Y; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            rojoPromedio += c.getRed();
            verdePromedio += c.getGreen();
            azulPromedio += c.getBlue();
         }
      }
      int cambio_2 = (int) (Math.pow(cambio, 2));
      rojoPromedio /= cambio_2;
      verdePromedio /= cambio_2;
      azulPromedio /= cambio_2;
      return new Color(rojoPromedio, verdePromedio, azulPromedio);
   }
   
   /*
   Recibe el color promedio y lo pinta en la nueva imagen.
   */
   private static void coloreaMosaico(BufferedImage imagen, int x, int y, int cambio, Color color) {
      int cota_X = x + cambio;
      int cota_Y = y + cambio;
      for (int i = x; i < cota_X; i++) {
         for (int j = y; j < cota_Y; j++) {
            imagen.setRGB(i, j, color.getRGB());
         }
      }
   }
   
   /*
   Filtro que realiza el filtro mosaico de una imagen y el tamano de nxn (size) del
   tamano de los mosaicos.
   */
   public static BufferedImage mosaico(BufferedImage imagen, int size) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < (width - size); i++) {
         for (int j = 0; j < (height - size); j++) {
            Color prom = Imagenes.colorPromedio(imagen, i, j, size);
            Imagenes.coloreaMosaico(nueva, i, j, size, prom);
            j += (size - 1);
         }
         i += (size - 1);
      }
      return nueva;
   }
   
   /*
   Filtro que pasa una imagen con los colores inversos.
   */
   public static BufferedImage negativo(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = 255 - (c.getRed());
            int verde = 255 - (c.getGreen());
            int azul = 255 - (c.getBlue());
            Color inv = new Color(rojo, verde, azul);
            nueva.setRGB(i, j, inv.getRGB());
         }
      }
      return nueva;
   }
   
   /*
   Filtro que transforma una imagen a sepia.
   */
   public static BufferedImage sepia(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int rojo = c.getRed();
            int verde = c.getGreen();
            int azul = c.getBlue();
            int rojo1 = (int) (rojo * .393) + (int) (verde * .769) + (int) (azul * .189);
            int verde1 = (int) (rojo * .349) + (int) (verde * .686) + (int) (azul * .168);
            int azul1 = (int) (rojo * .272) + (int) (verde * .534) + (int) (azul * .131);
            if (rojo1 > 255) {
               rojo1 = 255;
            }
            if (verde1 > 255) {
               verde1 = 255;
            }
            if (azul1 > 255) {
               azul1 = 255;
            }
            Color sepia = new Color(rojo1, verde1, azul1);
            nueva.setRGB(i, j, sepia.getRGB());
         }
      }
      return nueva;
   }
   
   public static void rotar(String nombre, double angulo) {
      double sin = Math.abs(Math.sin(Math.toRadians(angulo)));
      double cos = Math.abs(Math.cos(Math.toRadians(angulo)));
      BufferedImage imagen = abrirImagen(nombre);
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      int neww = (int) Math.floor(width*cos + height*sin);
      int newh = (int) Math.floor(height*cos + width*sin);
      BufferedImage nueva = new BufferedImage(neww, newh, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = nueva.createGraphics();
      g.translate((neww-width)/2, (newh-height)/2);
      g.rotate(Math.toRadians(angulo), width/2, height/2);
      g.drawRenderedImage(imagen, null);
      g.dispose();
      guardarImagen("rotar.bmp", nueva);
   }
   
   public static BufferedImage resize(BufferedImage imagen, int w, int h) {
      BufferedImage nueva = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
      Graphics2D bgr = nueva.createGraphics();
      bgr.drawImage((Image)imagen, 0, 0, w, h, null);
      bgr.dispose();
      return nueva;
   }
   
   public static BufferedImage recursivas(BufferedImage imagen, int matrix) {
      BufferedImage rs = resize(imagen, matrix, matrix);
      int widht = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(widht, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D gr = nueva.createGraphics();
      for (int i = 0; i < (widht - matrix); i++) {
         for (int j = 0; j < (height - matrix); j++) {
            Color average = colorPromedio(imagen, i, j, matrix);
            int r = average.getRed();
            int g = average.getGreen();
            int b = average.getBlue();
            BufferedImage av = filtroRGB(rs, r, g, b);
            gr.drawImage(av, i, j, matrix, matrix, null);
            j += matrix;
         }
         i += matrix;
      }
      return nueva;
   }
   
   public static BufferedImage mask(BufferedImage imagen, int mask) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            nueva.setRGB(i, j, imagen.getRGB(i, j) & mask);
         }
      }
      return nueva;
   }
   
   public static BufferedImage contraste(BufferedImage imagen, float contrast) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            float p = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
            int r = (int) (p + (c.getRed() - p) * contrast);
            int g = (int) (p + (c.getGreen() - p) * contrast);
            int b = (int) (p + (c.getBlue() - p) * contrast);
            if (r > 255) {
               r = 255;
            } else if (r < 0) {
               r = 0;
            }
            if (g > 255) {
               g = 255;
            } else if (g < 0) {
               g = 0;
            }
            if (b > 255) {
               b = 255;
            } else if (b < 0) {
               b = 0;
            }
            Color nuevo = new Color(r, g, b);
            nueva.setRGB(i, j, nuevo.getRGB());
         }
      }
      return nueva;
   }
    
   public static BufferedImage brillo(BufferedImage imagen, int brillo) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int r = c.getRed() + brillo;
            int g = c.getGreen() + brillo;
            int b = c.getBlue() + brillo;
            if (r > 255) {
               r = 255;
            }
            if (g > 255) {
               g = 255;
            }
            if (b > 255) {
               b = 255;
            }
            Color nuevo = new Color(r, g, b);
            nueva.setRGB(i, j, nuevo.getRGB());
         }
      }
      return nueva;
   }
   
   public static Color convolucion(BufferedImage image, double [][] matriz, double brillo) {
      double rojo_total = 0d;
      double verde_total = 0d;
      double azul_total = 0d;
      for (int i = 0; i < matriz.length; i++) {
         for (int j = 0; j < matriz.length; j++) {
            int color = image.getRGB(i, j);
            Color c = new Color(color);
            rojo_total += c.getRed() * matriz[i][j];
            verde_total += c.getGreen() * matriz[i][j];
            azul_total += c.getBlue() * matriz[i][j];
         }
      }
      rojo_total = Math.abs(rojo_total /= brillo);
      verde_total = Math.abs(verde_total /= brillo);
      azul_total = Math.abs(azul_total /= brillo);
      if (rojo_total > 255) {
         rojo_total = 255;
      }
      if (verde_total > 255) {
         verde_total = 255;
      }
      if (azul_total > 255) {
         azul_total = 255;
      }
      return new Color((int) rojo_total, (int) verde_total, (int) azul_total);
   }
   
   public static BufferedImage aplicarFiltro(BufferedImage imagen, double[][] matriz, double brillo) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width - (matriz.length - 1); i++) {
         for (int j = 0; j < height - (matriz.length - 1); j++) {
            BufferedImage subimagen = imagen.getSubimage(i, j, matriz.length, matriz.length);
            Color cnv = convolucion(subimagen, matriz, brillo);
            nueva.setRGB(i, j, cnv.getRGB());
         }
      }
      return nueva;
   }
   
   private static Color closestColor(Color[] paleta, Color color) {
      double d_actual = 256d;
      Color closestColor = null;
      for (int i = 0; i < paleta.length; i++) {
         int r = paleta[i].getRed() - color.getRed();
         int g = paleta[i].getGreen() - color.getGreen();
         int b = paleta[i].getBlue() - color.getBlue();
         double distancia = Math.sqrt(Math.pow(r, 2) + Math.pow(g, 2) + Math.pow(b, 2));
         if (distancia < d_actual) {
            d_actual = distancia;
            closestColor = new Color(paleta[i].getRed(), paleta[i].getGreen(), paleta[i].getBlue());
         }
      }
      return closestColor;
   }
   
   public static BufferedImage dithering(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            Color nuevo = closestColor(palette, c);
            nueva.setRGB(i, j, nuevo.getRGB());
         }
      }
      return nueva;
   }
   
   public static void blending(String nombre, String nombre2, float alpha) {
      BufferedImage imagen = abrirImagen(nombre);
      BufferedImage imagen2 = abrirImagen(nombre2);
      int width = Math.max(imagen.getWidth(), imagen2.getWidth());
      int heigth = Math.max(imagen.getHeight(), imagen2.getHeight());
      BufferedImage combined = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = combined.createGraphics();
      g.drawImage(imagen, null, 0, 0);
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f -alpha));
      g.drawImage(imagen2, null, 0, 0);
      g.dispose();
      guardarImagen("blending.bmp", combined);
   }
   
   public static BufferedImage oleo(BufferedImage imagen, int matriz) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width - (matriz - 1); i++) {
         for (int j = 0; j < height - (matriz - 1); j++) {
            BufferedImage subimagen = imagen.getSubimage(i, j, matriz, matriz);
            Color cnv = intensityColor(subimagen, matriz, 60);
            nueva.setRGB(i, j, cnv.getRGB());
         }
      }
      return nueva;
   }
   
   private static int maximo(int[] arr) {
      int max = 0;
      for (int i = 0; i < arr.length; i++) {
         if (arr[i] > max) {
            max = arr[i];
         }
      }
      return max;
   }
   
   public static Color intensityColor(BufferedImage image, int radio, int intensity) {
      int rojo_arr [] = new int[(int) Math.pow(radio, 2)];
      int verde_arr [] = new int[(int) Math.pow(radio, 2)];
      int azul_arr [] = new int[(int) Math.pow(radio, 2)];
      int k = 0;
      for (int i = 0; i < image.getWidth(); i++) {
         for (int j = 0; j < image.getHeight(); j++) {
            int color = image.getRGB(i, j);
            Color c = new Color(color);
            rojo_arr[k] = c.getRed();
            verde_arr[k] = c.getGreen();
            azul_arr[k] = c.getBlue();
            k++;
         }
      }
      int r = maximo(rojo_arr);
      int g = maximo(verde_arr);
      int b = maximo(azul_arr);
      return new Color(r, g, b);
   }
   
   public static BufferedImage luzNegra(BufferedImage imagen) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < width; i++) {
         for (int j = 0; j < height; j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            int r = c.getRed();
            int g = c.getGreen();
            int b = c.getBlue();
            Color n = new Color(b, r, g);
            nueva.setRGB(i, j, n.getRGB());
         }
      }
      return nueva;
   }
   
   public static BufferedImage semitonos(BufferedImage imagen, int matrix) {
      BufferedImage gray = grises(imagen);
      int width = gray.getWidth();
      int height = gray.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < (width - matrix); i++) {
         for (int j = 0; j < (height - matrix); j++) {
            int color = gray.getRGB(i, j);
            Color c = new Color(color);
            int red = c.getRed();
            if (red > 204) {
               coloreaMosaico(nueva, i, j, matrix, new Color(204, 204, 204));
            } else
            if (red < 204 && red > 153) {
               coloreaMosaico(nueva, i, j, matrix, new Color(153, 153, 153));
            } else
            if (red < 153 && red > 102) {
               coloreaMosaico(nueva, i, j, matrix, new Color(102, 102, 102));
            } else
            if (red < 102 && red > 51) {
               coloreaMosaico(nueva, i, j, matrix, new Color(51, 51, 51));
            } else {
               coloreaMosaico(nueva, i, j, matrix, new Color(0, 0, 0));
            }        
            j += (matrix - 1);
         }
         i += (matrix - 1);
      }
      return nueva;
   }
   
   public static BufferedImage floydSteinberg(BufferedImage imagen, double[] matrix) {
      int width = imagen.getWidth();
      int height = imagen.getHeight();
      BufferedImage nueva = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 1; i < (width-matrix.length); i++) {
         for (int j = 1; j < (height-matrix.length); j++) {
            int color = imagen.getRGB(i, j);
            Color c = new Color(color);
            Color cc = closestColor(palette, c);
            int error = c.getRGB() - cc.getRGB();
            Color new_color0 = multColor(cc, matrix[0], error);
            Color new_color1 = multColor(cc, matrix[1], error);
            Color new_Color2 = multColor(cc, matrix[2], error);
            Color new_Color3 = multColor(cc, matrix[3], error);
            nueva.setRGB(i-1, j-1, cc.getRGB());
            nueva.setRGB(i-1, j, cc.getRGB());
            nueva.setRGB(i, j-1, cc.getRGB());
            nueva.setRGB(i, j, cc.getRGB());
            nueva.setRGB(i-1, j+1, cc.getRGB());
            nueva.setRGB(i+1, j-1, cc.getRGB());
            nueva.setRGB(i+1, j, new_color0.getRGB());
            nueva.setRGB(i-1, j, new_color1.getRGB());
            nueva.setRGB(i, j+1, new_Color2.getRGB());
            nueva.setRGB(i+1, j+1, new_Color3.getRGB());
            j += (matrix.length -1);
         }
         i += (matrix.length - 1);
      }
      return nueva;
   }
   
   private static Color multColor(Color c, double matrix, int error){
      int r = (int) ((double) c.getRed() + error * matrix);
      int g = (int) ((double) c.getGreen() + error * matrix);
      int b = (int) ((double) c.getBlue() + error * matrix);
      return new Color(r,g,b);
   }
}