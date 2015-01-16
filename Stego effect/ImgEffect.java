package imgeffect;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import EncryptLast.EncryptFinal;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ImgEffect extends JFrame implements ActionListener
{
    JLabel l1,label1; 
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    JPanel panel3;
   EncryptFinal ef;  
   String imgurl,txturl,tempurl,delurl;

    public ImgEffect()
    {
          super("MyFrm");
                                   
 
          setBackground(Color.BLUE);
          setLayout(null);
                
          l1=new JLabel("Image Processing...");        
          b1=new JButton("Black & White");
          b2 = new JButton("Sepia");	
          b3 = new JButton("Charcoal");
          b4 = new JButton("Negative");
          b5 = new JButton("Brightness");
          b6 = new JButton("Darkness");
          b7 = new JButton("Contrast");
          b8 = new JButton("Reset");
		
         JPanel panel=new JPanel();
         GridLayout gl=new GridLayout(8,1);
         panel.setLayout(gl);
         panel.setBounds(10,110,170,400);
         Border bd1=BorderFactory.createTitledBorder("ToolBox...");
         panel.setBorder(bd1);
         panel.add( b1);
         panel.add( b2);
         panel.add( b3);
         panel.add( b4);
         panel.add( b5);
         panel.add( b6);
         panel.add( b7);
         panel.add( b8);
 

         b1.addActionListener(this);
         b2.addActionListener(this);
         b3.addActionListener(this);
         b4.addActionListener(this);
         b5.addActionListener(this);
         b6.addActionListener(this);
         b8.addActionListener(this);

        JPanel panel1=new JPanel();
        panel1.setBounds(10,10,825,100);
        Border bd2=BorderFactory.createTitledBorder("---Stegnography---");
        panel1.setBorder(bd2);
        l1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
       panel1.add(l1);
 
        JPanel panel2=new JPanel();
        panel2.setBounds(10,510,825,80);
        Border bd3=BorderFactory.createTitledBorder("Title...");
        panel2.setBorder(bd3);
        b9=new JButton("Apply");
        b10 = new JButton("Cancel");	
        panel2.add( b9);
        panel2.add( b10);
 
 
        panel3=new JPanel();
        panel3.setBounds(180,110,650,400);
        Border bd4=BorderFactory.createTitledBorder("Title...");
        panel3.setBorder(bd4);

        this.add(panel1);
        this.add(panel); 
        this.add(panel2); 
        this.add(panel3); 
    
         b9.addActionListener(this);   
        setSize(850,630);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

     public void FitImg(String img)
     {
//          JOptionPane.showMessageDialog(this,img);
          imgurl=img;
          imgload(img);

     }
    
     public void TxtFile(String txt)
     {
//          JOptionPane.showMessageDialog(this,txt);
            txturl=txt;
     }

	
     public void actionPerformed(ActionEvent ae)
    {
          if(ae.getSource()==b9)
         {
	try
	{
	 ef=new EncryptFinal();
               ef.FitTxt(txturl);
               ef.imgscr(tempurl);
	//String tempurl1=tempurl.replaceAll("/\\/g","\\\\");
	 //delurl="cmd /c del "+tempurl1;
	 //Process p = Runtime.getRuntime().exec(delurl);
	 //System.out.println(delurl);
               dispose();
	}catch(Exception ex)
	 {
		System.out.println(ex);
	 }
          }
         
         if(ae.getSource()==b1)
         {
	 try
                   {
                             //colored image path
                             BufferedImage image = ImageIO.read(new  
                                                File(imgurl));

                             //getting width and height of image
                             double image_width = image.getWidth();
                             double image_height = image.getHeight();

                             BufferedImage bimg = null;
                             BufferedImage imga = image;

                             //drawing a new image      
                             bimg = new BufferedImage((int)image_width, (int)image_height,
                                                                    BufferedImage.TYPE_BYTE_GRAY);
                             Graphics2D gg = bimg.createGraphics();
                             gg.drawImage(imga, 0, 0, imga.getWidth(null), imga.getHeight(null), null);

                             //saving black and white image onto drive
                             String temp = "tempo";
                             File fi = new File(imgurl + temp);
                             ImageIO.write(bimg,"jpg", fi);
		  tempurl=imgurl+temp;
		  imgload(tempurl);
		//delurl="cmd /c del "+tempurl+"*.jpgtempo";
		//System.out.println(delurl);

		//Process p = Runtime.getRuntime().exec(delurl);
                   }
                   catch (Exception e)
                   {
                             System.out.println(e);
                   }
         }

	if(ae.getSource()==b2)
	{
		try
		{
                             BufferedImage image = ImageIO.read(new File(imgurl));
		  BufferedImage imgsep = applySepiaFilter(image,20);

                             //saving black and white image onto drive
                             String temp = "tempo";
                             File fi = new File(imgurl + temp);
                             ImageIO.write(imgsep,"jpg", fi);
		  tempurl=imgurl+temp;
		  imgload(tempurl);

		Process p = Runtime.getRuntime().exec("cmd /c del G:\\*.jpgtempo");
		  
		}catch(Exception e)
		  {
			System.out.println("Error : "+e);
		  }
	}

	if(ae.getSource()==b3)
	{
		try
		{
                             BufferedImage image = ImageIO.read(new File(imgurl));

                             //getting width and height of image
                             int image_width = image.getWidth();
                             int image_height = image.getHeight();

                             BufferedImage bimg = null;
                             BufferedImage imga = image;

		 //drawing a new image      
                             bimg = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_BYTE_BINARY);

                             Graphics2D gg = bimg.createGraphics();
                             gg.drawImage(imga, 0, 0, imga.getWidth(null), imga.getHeight(null), null);

                             //saving black and white image onto drive
                             String temp = "tempo";
                             File fi = new File(imgurl + temp);
                             ImageIO.write(bimg,"jpg", fi);
		  tempurl=imgurl+temp;
		  imgload(tempurl);


		Process p = Runtime.getRuntime().exec("cmd /c del G:\\*.jpgtempo");
		  
		}catch(Exception e)
		  {
			System.out.println("Error : "+e);
		  }
	}

          if(ae.getSource()==b4)
	{
		try
		{
                             BufferedImage image = ImageIO.read(new File(imgurl));
		  BufferedImage imgneg = getNegativeImage(image);

                             String temp = "tempo";
                             File fi = new File(imgurl + temp);
                             ImageIO.write(imgneg,"jpg", fi);
		  tempurl=imgurl+temp;
		  imgload(tempurl);

		Process p = Runtime.getRuntime().exec("cmd /c del G:\\*.jpgtempo");
		  
		}catch(Exception e)
		  {
			System.out.println("Error : "+e);
		  }
	}

          if(ae.getSource()==b5)
	{	
		try
		{
		BufferedImage src=ImageIO.read(new File(imgurl));
		BufferedImage dest=changeBrightDark(src,1.5f);
		String temp = "tempo";
                            File fi = new File(imgurl + temp);
                            ImageIO.write(dest,"jpg", fi);
		 tempurl=imgurl+temp;
		 imgload(tempurl);
		}catch(Exception ex)
		 {
		
		 }
	}

          if(ae.getSource()==b6)
	{	
		try
		{
		BufferedImage src=ImageIO.read(new File(imgurl));
		BufferedImage dest=changeBrightDark(src,.8f);
		String temp = "tempo";
                            File fi = new File(imgurl + temp);
                            ImageIO.write(dest,"jpg", fi);
		 tempurl=imgurl+temp;
		 imgload(tempurl);
		}catch(Exception ex)
		 {
		
		 }
	}

          if(ae.getSource()==b8)
	{	
		try
		{
		BufferedImage src=ImageIO.read(new File(imgurl));
		String temp = "tempo";
                            File fi = new File(imgurl + temp);
                            ImageIO.write(src,"jpg", fi);
		 tempurl=imgurl+temp;
		 imgload(tempurl);
		}catch(Exception ex)
		 {
		
		 }
	}

}

//Load Image into panel
   public void imgload(String img)
    {
 Component[]OldImage=panel3.getComponents();  
  
 if(OldImage.length!=0)  
 {  
  panel3.remove(OldImage.length-1);  
  panel3.revalidate();  
  validate();  
  repaint();  
 } 

          ImageIcon image = new ImageIcon(img);
          int wi=image.getIconWidth();
          int he=image.getIconHeight();
          int h=0,w=0;
     
          if(he>601 || wi>601)  
          {
                do
               {
                      h = (int)(he/2);
                      w = (int)(wi/2);
                      he=h;
                      wi=w;
                 }while(h>=500);
           }
        
        if((he>400 && he<600) || (wi>400 && wi<600))
          {
                 h = (int)(wi/1.5);
                 w = (int)(he/1.5);
           }
          else
          {
                 h =wi;
                 w =he;      
          }
   
   
           image = new ImageIcon(image.getImage().getScaledInstance(h, w,Image.SCALE_SMOOTH));
           label1 = new JLabel("",image, JLabel.CENTER);
           label1.setBorder(BorderFactory.createEtchedBorder());
           this.panel3.add(label1);
           this.panel3.revalidate();

    }//end of imgload() 

public BufferedImage applySepiaFilter(BufferedImage img, int sepiaIntensity) {
    // Play around with this. 20 works well and was recommended
    // by another developer. 0 produces black/white image
    int sepiaDepth = 20;

    int w = img.getWidth();
    int h = img.getHeight();

    WritableRaster raster = img.getRaster();

    // We need 3 integers (for R,G,B color values) per pixel.
    int[] pixels = new int[w*h*3];
    raster.getPixels(0, 0, w, h, pixels);

    // Process 3 ints at a time for each pixel.
    // Each pixel has 3 RGB colors in array
    for (int i=0;i<pixels.length; i+=3) {
        int r = pixels[i];
        int g = pixels[i+1];
        int b = pixels[i+2];

        int gry = (r + g + b) / 3;
        r = g = b = gry;
        r = r + (sepiaDepth * 2);
        g = g + sepiaDepth;

        if (r>255) r=255;
        if (g>255) g=255;
        if (b>255) b=255;

        // Darken blue color to increase sepia effect
        b-= sepiaIntensity;

        // normalize if out of bounds
        if (b<0) b=0;
        if (b>255) b=255;

        pixels[i] = r;
        pixels[i+1]= g;
        pixels[i+2] = b;
    }
	//raster.setPixels(0, 0, w, h, pixels);

            raster.setPixels(0,0,w,h,pixels);
            return img;

}

  

public BufferedImage getNegativeImage(BufferedImage img) {
        int w1 = img.getWidth();
        int h1 = img.getHeight();
        // int value[][] = new int[w1][h1];
        BufferedImage gray = new BufferedImage(w1, h1, 1);
        int value, alpha, r, g, b;
        for (int i = 0; i < w1; i++) {
            for (int j = 0; j < h1; j++) {
                value = img.getRGB(i, j); // store value
                alpha = getAlpha(value);
                r = 255 - getRed(value);
                g = 255 - getGreen(value);
                b = 255 - getBlue(value);

                value = createRGB(alpha, r, g, b);
                gray.setRGB(i, j, value);
            }
        }
        return gray;
    }

    public static int createRGB(int alpha, int r, int g, int b) {
        int rgb = (alpha << 24) + (r << 16) + (g << 8) + b;
        return rgb;
    }

    public static int getAlpha(int rgb) {
        return (rgb >> 24) & 0xFF;
    }

    public static int getRed(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    public static int getGreen(int rgb) {
        return (rgb >> 8) & 0xFF;
    }

    public static int getBlue(int rgb) {
        return rgb & 0xFF;
    }

		//for brightness
   		public BufferedImage changeBrightDark(BufferedImage src,float val){
       		RescaleOp brighterOp = new RescaleOp(val, 0, null);
       		return brighterOp.filter(src,null);}
}