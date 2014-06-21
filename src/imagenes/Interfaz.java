package imagenes;

import static imagenes.Imagenes.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Interfaz grafica del photoshop
 *
 * @author Erik Martínez Licea
 */
public class Interfaz extends JFrame {
   
   private JMenuBar menubar;
   private JMenu archivo;
   private JMenu color;
   private JMenu convolucion;
   private JMenu basicos;
   private JMenu dithering;
   private JMenu contraste;
   private JMenu otros;
   
   private JMenuItem abrir;
   private JMenuItem salir;
   private JMenuItem rojo;
   private JMenuItem verde;
   private JMenuItem azul;
   private JMenuItem magenta;
   private JMenuItem cian;
   private JMenuItem amarillo;
   private JMenuItem blur;
   private JMenuItem sharpen;
   private JMenuItem sharpen2;   
   private JMenuItem emboss;   
   private JMenuItem emboss2;
   private JMenuItem bordes;   
   private JMenuItem grises;   
   private JMenuItem sepia;   
   private JMenuItem inverso;   
   private JMenuItem luznegra;
   private JMenuItem semitonos;
   private JMenuItem dither;
   private JMenuItem floyd;
   private JMenuItem mosaicos;
   private JMenuItem contrast;
   private JMenuItem contrastealto;
   private JMenuItem contrasteinverso;
   private JMenuItem brillo;
   private JMenuItem mascara;
   private JMenuItem oleo;
   private JMenuItem recursivas;
   
   private JFileChooser choose;
   private BufferedImage actual;
   private JLabel img;

   public Interfaz() {
      setSize(1200, 700);
      setTitle("PhotoEditor");
      setResizable(false);
      setLocationRelativeTo(null);
      initComponents();
   }

   private void initComponents() {
      menubar = new JMenuBar();
      
      archivo = new JMenu("Archivo");
      color = new JMenu("Color");
      convolucion = new JMenu("Convolucion");
      basicos = new JMenu("Basicos");
      dithering = new JMenu("Dithering");
      contraste = new JMenu("Brillo y Contraste");
      otros = new JMenu("Otros");
      
      abrir = new JMenuItem("Abrir ...");
      salir = new JMenuItem("Salir");
      rojo = new JMenuItem("Filtro Rojo");
      verde = new JMenuItem("Filtro Verde");
      azul = new JMenuItem("Filtro Azul");
      cian = new JMenuItem("Filtro Cian");
      magenta = new JMenuItem("Filtro Magenta");
      amarillo = new JMenuItem("Filtro Amarillo");
      blur = new JMenuItem("Blur");
      sharpen = new JMenuItem("Sharpen");
      sharpen2 = new JMenuItem("More Sharpen");
      emboss = new JMenuItem("Emboss");
      emboss2 = new JMenuItem("More Emboss");
      bordes = new JMenuItem("Bordes");
      grises = new JMenuItem("Grises");
      sepia = new JMenuItem("Sepia");
      inverso = new JMenuItem("Negativo");    
      luznegra = new JMenuItem("Luz Negra");
      semitonos = new JMenuItem("Semitonos");
      dither = new JMenuItem("Dither");
      floyd  = new JMenuItem("Floyd Steinberg");
      mosaicos  = new JMenuItem("Mosaicos"); 
      contrast  = new JMenuItem("Contraste");
      contrastealto = new JMenuItem("Alto contraste");
      contrasteinverso = new JMenuItem("Contraste inverso");
      brillo = new JMenuItem("Brillo");
      oleo = new JMenuItem("Oleo");
      mascara = new JMenuItem("Mascara");
      recursivas = new JMenuItem("Recursivas");
      
      
      img = new JLabel();
      
      setJMenuBar(menubar);
      menubar.add(archivo);
      menubar.add(color);
      menubar.add(convolucion);
      menubar.add(basicos);
      menubar.add(dithering);
      menubar.add(contraste);
      menubar.add(otros);
      
      archivo.add(abrir);
      archivo.add(salir);
      color.add(rojo);
      color.add(verde);
      color.add(azul);
      color.add(cian);
      color.add(magenta);
      color.add(amarillo);
      convolucion.add(blur);
      convolucion.add(sharpen);
      convolucion.add(sharpen2);
      convolucion.add(emboss);
      convolucion.add(emboss2);
      convolucion.add(bordes);
      basicos.add(grises);
      basicos.add(sepia);
      basicos.add(inverso);
      basicos.add(luznegra);
      dithering.add(semitonos);
      dithering.add(dither);
      dithering.add(floyd);
      dithering.add(mosaicos);
      contraste.add(contrast);
      contraste.add(contrastealto);
      contraste.add(contrasteinverso);
      contraste.add(brillo);
      otros.add(oleo);
      otros.add(mascara);
      otros.add(recursivas);
      getContentPane().add(img);
      
      rojo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            rojoActionPerformed(e);
         }       
      });
      
      verde.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            verdeActionPerformed(e);
         }       
      });
      
      azul.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            azulActionPerformed(e);
         }       
      });
      
      magenta.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            magentaActionPerformed(e);
         }        
      });
      
      cian.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            cianActionPerformed(e);
         }       
      });
      
      amarillo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            amarilloActionPerformed(e);
         }    
      });
      
      
      
      abrir.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            abrirActionPerformed(e);
         }
      });
      
      salir.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            salirActionPerformed(evt);
         }
      });
      
      blur.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            blurActionPerformed(evt);
         }
      });
      
      sharpen.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            sharpenActionPerformed(evt);
         }
      });
      
      sharpen2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            sharpen2ActionPerformed(evt);
         }
      });
      
      emboss.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            embossActionPerformed(evt);
         }
      });
      
      emboss2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            emboss2ActionPerformed(evt);
         }
      });
      
      bordes.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            bordesActionPerformed(evt);
         }
      });
      
      grises.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            grisesActionPerformed(evt);
         }
      });
      
      sepia.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            sepiaActionPerformed(evt);
         }
      });
      
      luznegra.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            luznegraActionPerformed(evt);
         }
      });
      
      inverso.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            inversoActionPerformed(evt);
         }
      });
      
      semitonos.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            semitonosActionPerformed(evt);
         }
      });
      
      dither.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            ditherActionPerformed(evt);
         }
      });
      
      floyd.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            floydActionPerformed(evt);
         }
      });
      
      mosaicos.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            mosaicosActionPerformed(evt);
         }
      });
      
      contrast.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            contrastActionPerformed(evt);
         }
      });
      
      contrastealto.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            contrastealtoActionPerformed(evt);
         }
      });
      
      contrasteinverso.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            contrasteinversoActionPerformed(evt);
         }
      });
      
      brillo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            brilloActionPerformed(evt);
         }
      });
      
      oleo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            oleoActionPerformed(evt);
         }
      });
      
      mascara.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            mascaraActionPerformed(evt);
         }
      });
      
      recursivas.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
            recursivasActionPerformed(evt);
         }
      });
      
      addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent evt) {
            exitForm(evt);
         }
      });
   }
   
   private void salirActionPerformed(ActionEvent evt) {
      System.exit(0);
   }
   
   private void rojoActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = filtroRojo(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void verdeActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = filtroVerde(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void azulActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = filtroAzul(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void magentaActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = filtroMagenta(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void cianActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = filtroCian(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void amarilloActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = filtroAmarillo(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void blurActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = aplicarFiltro(actual, Imagenes.blur2, 13);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void sharpenActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = aplicarFiltro(actual, Imagenes.sharpen, 1);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void sharpen2ActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = aplicarFiltro(actual, Imagenes.more_sharpen, 1);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void embossActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = aplicarFiltro(actual, Imagenes.emboss, 1);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void bordesActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = aplicarFiltro(actual, Imagenes.bordes, 1);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void emboss2ActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = aplicarFiltro(actual, Imagenes.emboss2, 1);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void grisesActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = grises(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void sepiaActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = sepia(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void luznegraActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = luzNegra(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void inversoActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = negativo(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void semitonosActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = semitonos(actual, 2);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void ditherActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = dithering(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void floydActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = floydSteinberg(actual, Imagenes.floyd);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void mosaicosActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = mosaico(actual, 4);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void contrastActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = contraste(actual, 1.4f);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void contrastealtoActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = contraseAlto(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void contrasteinversoActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = contraseInverso(actual);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void brilloActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = brillo(actual, 10);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void oleoActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = oleo(actual, 5);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void mascaraActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = mask(actual, mask1);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void recursivasActionPerformed(ActionEvent evt) {
      if (actual == null) {
         return;
      }
      BufferedImage nueva = recursivas(actual, 10);
      actual = nueva;
      ImageIcon icon = new ImageIcon(nueva);
      img.setIcon(icon);
   }
   
   private void abrirActionPerformed(ActionEvent evt) {
      choose = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "bmp", "jpg", "png");
      choose.setFileFilter(filter);
      String path = "";
      int opcion = choose.showOpenDialog(this);
      if (opcion == JFileChooser.APPROVE_OPTION) {
         path = choose.getSelectedFile().getAbsolutePath();
      }
      actual = abrirImagen(path);
      ImageIcon icon = new ImageIcon(path);
      img.setIcon(icon);
   }

   private void exitForm(WindowEvent evt) {
      System.exit(0);
   }

   public static void main(String[] args) {
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
              | UnsupportedLookAndFeelException e) {
         System.out.println("No se pudo establecer el aspecto deseado " + e);
      }
      new Interfaz().setVisible(true);
   }
}