/**
 * @Author Kushal Paudyal
 * www.sanjaal.com/java
 * Last Modified On: 2009-11-20
 *
 * ImageOverLay.java
 *
 * Overlays one image over another image.
 */
 
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
 
public class ImageOverlay {
	
 
    public static void main(String args[]) {
 try{
int height,width;
        File bimg=new File("lion.jpg");
        BufferedImage bgImage = ImageIO.read(bimg);
	height=bgImage.getHeight()-266;
	width=bgImage.getWidth()-207;
 
        /**
         * Read a foreground image
         */
	File simg=new File("myFGImage.jpg");
        BufferedImage fgImage = ImageIO.read(simg);
 
        /**
         * Do the overlay of foreground image on background image
         */
        BufferedImage overlayedImage = overlayImages(bgImage, fgImage,height,width);
 
        /**
         * Write the overlayed image back to file
         */
        if (overlayedImage != null){
            //writeImage(overlayedImage, "overLayedImage1.jpg", "JPG");
	ImageIO.write(overlayedImage,"png", new File("C:\\Attach.png"));
            System.out.println("Overlay Completed...");
        }else
            System.out.println("Problem With Overlay...");
 }catch(Exception e){System.out.println("Error");}
    }
 
    /**
     * Method to overlay Images
     *
     * @param bgImage --> The background Image
     * @param fgImage --> The foreground Image
     * @return --> overlayed image (fgImage over bgImage)
     */
    public static BufferedImage overlayImages(BufferedImage bgImage,BufferedImage fgImage,int a,int b) {
 
      /**
         * Doing some preliminary validations.
         * Foreground image height cannot be greater than background image height.
         * Foreground image width cannot be greater than background image width.
         *
         * returning a null value if such condition exists.
         */

        if (fgImage.getHeight() > bgImage.getHeight()
                || fgImage.getWidth() > fgImage.getWidth()) {
            JOptionPane.showMessageDialog(null,
                    "Foreground Image Is Bigger In One or Both Dimensions"
                            + "\nCannot proceed with overlay."
                            + "\n\n Please use smaller Image for foreground");

            return null;
        }

        /**Create a Graphics  from the background image**/
        Graphics2D g = bgImage.createGraphics();
        /**Set Antialias Rendering**/
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        /**
         * Draw background image at location (0,0)
         * You can change the (x,y) value as required
         */
        g.drawImage(bgImage, 0, 0, null);
 
        /**
         * Draw foreground image at location (0,0)
         * Change (x,y) value as required.
         */
        g.drawImage(fgImage,b,a, null);
 
        g.dispose();

        return bgImage;

    }
 
    /**
     * This method reads an image from the file
     * @param fileLocation -- > eg. "C:/testImage.jpg"
     * @return BufferedImage of the file read
     */
/*    public BufferedImage readImage(String fileLocation) {
BufferedImage img = null;        
        try {

            img = ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }*/
 
    /**
     * This method writes a buffered image to a file
     * @param img -- > BufferedImage
     * @param fileLocation --> e.g. "C:/testImage.jpg"
     * @param extension --> e.g. "jpg","gif","png"
     */
    public void writeImage(BufferedImage img, String fileLocation,
            String extension) {
        try {
            BufferedImage bi = img;
            File outputfile = new File(fileLocation);
            ImageIO.write(bi, extension, outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}