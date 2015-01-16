package EncryptLast;

import imgeffect.ImgEffect;
import main.MainStego;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.image.*;


public class EncryptFinal extends JFrame implements ActionListener
{
       JLabel lblTitle,lblImage;
       JButton btnEncrypt,btnImgstego,btnmainstego;
       JPanel panTitle, panImage,panBtn;
       JTextArea t;
       String imgurl,txturl,tempurl,delurl;
       public static String idLast;
       JFrame frame;


     public EncryptFinal(String idpass5)
     {
           //super("Test Frame"); 
           frame=new JFrame("Encryption");
           idLast=idpass5;
           setBackground(Color.BLACK);
           setLayout(null);
                
         
           panImage=new JPanel();
           panImage.setBounds(10,80,825,300);
 //          this.add(panImage); 
 
          panTitle=new JPanel();
          panTitle.setBounds(10,10,825,70);
          lblTitle=new JLabel("Encryption");
          lblTitle.setBounds(10,5,400,5);
          lblTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
          this.add(panTitle); 
          panTitle.add(lblTitle);
  
          panBtn=new JPanel();
          panBtn.setBounds(10,390,825,160);
  //        this.add(panBtn);
               

     
       
          btnEncrypt=new JButton("Encrypt");
          btnImgstego=new JButton("Back");
          btnmainstego=new JButton("Back to Main"); 
  
         JPanel panel3=new JPanel();
         panel3.setBounds(250,545,500,100);

          panel3.setLayout(null);
          btnEncrypt.setBounds(5,5,100,30);
          btnImgstego.setBounds(120,5,100,30);
          btnmainstego.setBounds(240,5,110,30);
//        this.add(panel3); 
           
          panel3.add(btnEncrypt);
          panel3.add(btnImgstego);
          panel3.add(btnmainstego);
      
           btnImgstego.addActionListener(this);
          btnmainstego.addActionListener(this);
          btnEncrypt.addActionListener(this);

      ImageIcon image = new ImageIcon("encryption.png");
      JLabel imageLabel = new JLabel(image);
      frame.add(imageLabel);

          frame.setSize(850,630);

      Color trans = new Color(0,0,0,0);
      panel3.setBackground(trans);
      panBtn.setBackground(trans);
      panImage.setBackground(trans);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);


      imageLabel.add(panel3);
      imageLabel.add(panBtn);
      imageLabel.add(panImage);

          frame.setVisible(true);
          frame.setResizable(false);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     } 


     public void actionPerformed(ActionEvent ae)
     {
          if(ae.getSource()==btnEncrypt)
          {
 	Steganography st= new Steganography();
               txturl=(t.getText().replaceAll("\n","\r\n"));
 	st.hide(txturl,imgurl);
	 try{              
 	String tempurl1=tempurl.replaceAll("/\\/g","\\\\");
	delurl="cmd /c del "+tempurl1;
	Process p = Runtime.getRuntime().exec(delurl);
	System.out.println(delurl);
              }catch(Exception e){System.out.println("err");}
          }
          if(ae.getSource()==btnmainstego)
         {	
	frame.dispose();
	MainStego ms=new MainStego();
         }

          if(ae.getSource()==btnImgstego)
         {
	ImgEffect ie=new ImgEffect(idLast);
	ie.FitImg(imgurl);
               ie.TxtFile(txturl);
               frame.dispose();
         }


    }
	
    public void FitTxt(String txt)
    {
         try
        {
	txturl=txt;
//	FileInputStream fis=new FileInputStream(txt);
               t=new JTextArea(7,70);
               t.setLineWrap(true);
             
//	int cb;
//	String cc="";
	
//               while((cb=fis.read())!=-1)
//	{
//	      cc = cc + (char)cb;
//	}
              
              t.setText(txt);  
              JScrollPane scrollingResult = new JScrollPane(t);
              this.panBtn.add(scrollingResult,BorderLayout.CENTER);
              this.panBtn.revalidate();
          //    fis.close();
          }catch(Exception ex)
         {
	t.setText("no massage found");
          }
    }

     public void imgscr(String img)
     {
         imgload(img);
         tempurl=img;
     }


    //Load Image into panImage
     public void imgload(String img)
     {
           imgurl=img;     
           ImageIcon image = new ImageIcon(imgurl);
 
           int wi=image.getIconWidth();
           int he=image.getIconHeight();
           int h=0,w=0,we=0,hei=0;
          
           if(he>501 || wi>501)  
           {
                 do
                {
                     h = (int)(he/2);
                     w = (int)(wi/2);
                     wi=w;
                     he=h;        

              	  }while(h>=250);             
           }
           else
          {
                 h =(int)(he/1.5);
                 w =(int)(wi/1.5);      
           }
   
   
           image = new ImageIcon(image.getImage().getScaledInstance(w,h,Image.SCALE_SMOOTH));
           lblImage = new JLabel("",image, JLabel.CENTER);
           lblImage.setBorder(BorderFactory.createEtchedBorder());
           this.panImage.add(lblImage);
           this.panImage.revalidate();

     }//end of imgload() 


}//end of EncryptionFinal class


class Steganography extends JFrame
{
       private static final int MAX_INT_LEN = 4;
       private static final int DATA_SIZE = 8;
  
     public boolean hide(String textFnm, String imFnm)
    {
            String inputText = textFnm;
             if ((inputText == null) || (inputText.length() == 0))
                     JOptionPane.showMessageDialog(this,"Could not found text");
 
             byte[] stego = buildStego(inputText);
             BufferedImage im = loadImage(imFnm);
 
            if (im == null)
                  JOptionPane.showMessageDialog(this,"Could not found Image");
 
            byte imBytes[] = accessBytes(im);
 
             if (!singleHide(imBytes, stego)) // im is modified with the stego
                    JOptionPane.showMessageDialog(this,"Error in byte read");

           return writeImageToFile(im);
      } // end of hide()
 

     private String readTextFile(String fnm)
    {
           BufferedReader br = null;
           StringBuffer sb = new StringBuffer();

          try
         {
               br = new BufferedReader(new FileReader( new File(fnm) ));
  
               String text = null;
               while ((text = br.readLine()) != null)
               sb.append(text + "\n");
         }catch (Exception e) 
         {
      //--         System.out.println("Could not completely read " + fnm);
                JOptionPane.showMessageDialog(this,"Could not completely read");
         	 return null;
          }
          finally 
          {
                 try 
                {
                        if (br != null)
                               br.close();
                }catch (IOException e) 
                {
                   //--System.out.println("Problem closing " + fnm);
                        JOptionPane.showMessageDialog(this,"Problem closing");
                        return null;
                }
          }
          //--System.out.println("Read in " + fnm);
          return sb.toString();
     } // end of readTextFile()
  

 
    private static byte[] buildStego(String inputText)
    {

         //System.out.println(EncryptFinal.idLast+" in Steganography");
         String attach="\r\nSent By : "+EncryptFinal.idLast;
         byte[] attachBs=attach.getBytes();
         //System.out.println("attachBs = "+attachBs.length);
         String finalText=inputText.concat(attach);
         //System.out.println(finalText);

         finalText=SwapEnc(finalText);
         byte[] msgBytes = finalText.getBytes();

         byte[] lenBs = intToBytes(msgBytes.length);

         int totalLen = lenBs.length + msgBytes.length;
         //System.out.println("totalLen = "+totalLen);
         byte[] stego = new byte[totalLen]; // for holding the resulting stego 

         // combine the 2 fields into one byte array
        // public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
         
         System.arraycopy(lenBs, 0, stego, 0, lenBs.length); // length of binary message
         System.arraycopy(msgBytes, 0, stego, lenBs.length, msgBytes.length); // binary message
         // System.out.println("Num. pixels to store fragment " + i + ": " + totalLen*DATA_SIZE);
         return stego;
     } // end of buildStego()
 
   public static String SwapEnc(String str)
   {
     
          String str1="";
          int no=str.length();
          
          for(int i=0;i<str.length();i++)
          {
              if (i%2==0)
                      str1=str1+(char)((int)str.charAt(i)+2);      
	else
                      str1=str1+(char)((int)str.charAt(i)-2);                     
          }
          return str1;
    }//end of SwapEnc

 
     private static byte[] intToBytes(int i)
    {
          byte[] integerBs = new byte[MAX_INT_LEN];
          integerBs[0] = (byte) ((i >>> 24) & 0xFF);
          integerBs[1] = (byte) ((i >>> 16) & 0xFF);
          integerBs[2] = (byte) ((i >>> 8) & 0xFF);
          integerBs[3] = (byte) (i & 0xFF);
 
          // for (int j=0; j < integerBs.length; j++)
          // System.out.println(" integerBs[ " + j + "]: " + integerBs[j]);
 
          return integerBs;
     } // end of intToBytes()
 

 
     private BufferedImage loadImage(String imFnm)
    {
          BufferedImage im = null;
          BufferedImage fgImage = null;
          BufferedImage overImage = null;
          try 
         {
               im = ImageIO.read( new File(imFnm) );
	fgImage=ImageIO.read(new File("logo.png"));
	overImage=overlayImages(im, fgImage);
          }catch (IOException e)
          {
               //-- System.out.println("Could not read image from " + imFnm); 
                      JOptionPane.showMessageDialog(this,"Could not read image");
           }
 
         return overImage;
      } // end of loadImage()
 
//Overlay images
    public static BufferedImage overlayImages(BufferedImage bgImage,BufferedImage fgImage) 
   {
 
       Graphics2D g = bgImage.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(bgImage, 0, 0, null);
        int h=bgImage.getHeight()-fgImage.getHeight();
        int w=bgImage.getWidth()-fgImage.getWidth();
  
        g.drawImage(fgImage,w,h, null);
 
        g.dispose();

        return bgImage;
    }

 
    private static byte[] accessBytes(BufferedImage image)
   {
         WritableRaster raster = image.getRaster();
         DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
         return buffer.getData();
    } // end of accessBytes()
 

 
    private boolean singleHide(byte[] imBytes, byte[] stego)
   {
          int imLen = imBytes.length;
          //--System.out.println("Byte length of image: " + imLen);
 
          int totalLen = stego.length;
         //--System.out.println("Total byte length of message: " + totalLen);
 
         // check that the stego will fit into the image
         // multiply stego length by number of image bytes required to store one stego byte
        
         if ((totalLen*DATA_SIZE) > imLen) 
         {
                //--System.out.println("Image not big enough for message");
                  JOptionPane.showMessageDialog(this,"Image not big enough for message");
                  return false;
         }
 
        hideStego(imBytes, stego, 0); // hide at start of image
        return true;
     } // end of singleHide()

 

     private static void hideStego(byte[] imBytes, byte[] stego, int offset)
     {
          for (int i =0; i<stego.length; i++) 
          { 
                int byteVal = stego[i];
                for(int j=7; j>=0; j--) 
                {
                      int bitVal = (byteVal >>> j) & 1;
                     // change last bit of image byte to be the stego bit
 
                      imBytes[offset] = (byte)((imBytes[offset] & 0xFE) | bitVal);
                     offset++;
                 }
            }
      } // end of hideStego()
 


    private boolean writeImageToFile( BufferedImage im)
    {
          /*if (!canOverWrite(outFnm))
             return false;
 
             try {
                   ImageIO.write(im, "png", new File(outFnm));
                  //--System.out.println("Image written to PNG file: " + outFnm);
                 JOptionPane.showMessageDialog("Your Message is Encypted and is completly safe");
                return true;
                }catch(IOException e)
                   {
                           System.out.println("Could not write image to " + outFnm);
                           return false;
                    }*/
      try
      {
               JFileChooser filechooser = new JFileChooser();
               filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
           
          	//FileNameExtensionFilter filter= new FileNameExtensionFilter("(*.png;)files","png");
           	//filechooser.setFileFilter(filter);
           	int result=filechooser.showSaveDialog(this);
	          String url = filechooser.getSelectedFile().getPath() + ".png";
              
	//FileOutputStream fos = new FileOutputStream(url);
	//DataOutputStream dos = new DataOutputStream(fos);
               ImageIO.write(im,"png", new File(url));
            	JOptionPane.showMessageDialog(this,"File Save Successfully at " + url);
               return true;
	//dos.writeUTF(msg);
	//dos.close();
	//fos.close();
       }catch(Exception ex)
       {
             return false;
       }
    } // end of writeImageToFile();
 

 
   private boolean canOverWrite(String fnm)
   {
         File f = new File(fnm);
         if (!f.exists())
                return true; // can overewrite since the file is new
         // prompt the user about whether the file can be overwritten
 
         JOptionPane.showMessageDialog(this,"File is already exists.");
         int result=JOptionPane.showConfirmDialog((Component)null,"OverWrite (y|n)?","OverWrite (y|n)?",JOptionPane.YES_NO_OPTION);
         //--Scanner in = new Scanner(System.in);
         //--String response;
         //--System.out.print("File " + fnm + " already exists. ");
          //--while (true) {
          //--System.out.print("Overwrite (y|n)? ");
          //--response = in.nextLine().trim().toLowerCase();
         if (result==JOptionPane.YES_OPTION) // yes
                   return true;
         else  // no
              return false;

    } // end of canOverWrite()

}//end of Stegnography

