package EncryptLast;
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
   JLabel l1,label1;
   JButton bt;
   JPanel panel1, panel,panel2;
   JTextArea t;
   String imgurl,txturl;
   public EncryptFinal()
    {
         super("Test Frame"); 
                                
 
           setBackground(Color.BLACK);
           setLayout(null);
                
         
          panel=new JPanel();
          panel.setBounds(10,90,825,300);
          Border bd=BorderFactory.createTitledBorder("Image...");
          panel.setBorder(bd);
          this.add(panel); 
 
         panel1=new JPanel();
         panel1.setBounds(10,10,825,80);
         Border bd1=BorderFactory.createTitledBorder("Title...");
         panel1.setBorder(bd1);
         l1=new JLabel("Encryption");
         l1.setBounds(10,5,400,5);
         l1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
         this.add(panel1); 
         panel1.add(l1);
  
         panel2=new JPanel();
         panel2.setBounds(10,390,825,160);
         Border bd2=BorderFactory.createTitledBorder("Text...");
         panel2.setBorder(bd2);
         this.add(panel2); 
 
         bt=new JButton("Encrypt");
         JPanel panel3=new JPanel();
         panel3.setBounds(390,550,150,50);
         Border bd3=BorderFactory.createTitledBorder("");
         panel3.setBorder(bd3);
         GridLayout gl = new GridLayout(1,1);
         panel3.setLayout(gl);
         this.add(panel3); 
         panel3.add(bt);
 
         bt.addActionListener(this);
         setSize(850,630);
         setVisible(true);
         setResizable(true);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void actionPerformed(ActionEvent ae)
    {
         if(ae.getSource()==bt)
         {
	Steganography st= new Steganography();
              txturl=(t.getText().replaceAll("\n","\r\n"));
              System.out.println(txturl);
	st.hide(txturl,imgurl);
        
         }
    }
	
    public void FitTxt(String txt)
    {
        try
       {

	FileInputStream fis=new FileInputStream(txt);
               t=new JTextArea(7,70);
               t.setLineWrap(true);
             
	int cb;
	String cc="";
	
               while((cb=fis.read())!=-1)
	{
	      cc = cc + (char)cb;
	}
              
              t.setText(cc);  
              JScrollPane scrollingResult = new JScrollPane(t);
              this.panel2.add(scrollingResult,BorderLayout.CENTER);
              this.panel2.revalidate();
              fis.close();
         }catch(Exception ex)
         {
	t.setText("no massage found");
         }
    }

    public void imgscr(String img)
    {
        imgload(img);
    }


//Load Image into panel
   public void imgload(String img)
    {
          imgurl=img;     
          ImageIcon image = new ImageIcon(img);
          int wi=image.getIconWidth();
          int he=image.getIconHeight();
          int h=0,w=0;
     
          if((he>400 && he<600) || (wi>400 && he<600))
          {
                 h = (int)(wi/1.5);
                 w = (int)(he/1.5);
           }
           else if(he>601 || wi>601)  
          {
                do
               {
                      h = (int)(wi/3);
                      w = (int)(he/3);
        
                 }while(h>=500);
           }
          else
          {
                 h =wi;
                 w =he;      
          }
   
   
           image = new ImageIcon(image.getImage().getScaledInstance(h, w,                                               Image.SCALE_SMOOTH));
           label1 = new JLabel("",image, JLabel.CENTER);
           label1.setBorder(BorderFactory.createEtchedBorder());
           this.panel.add(label1);
           this.panel.revalidate();

    }//end of imgload() 


}
  class Steganography
  {
       private static final int MAX_INT_LEN = 4;
       private static final int DATA_SIZE = 8;
// number of image bytes required to store one stego byte
 

 
 public static boolean hide(String textFnm, String imFnm)
/* hide message read from textFnm inside image read from imFnm;
the resulting image is stored in <inFnm>Msg.png */
       {
// read in the message
             String inputText = textFnm;
             if ((inputText == null) || (inputText.length() == 0))
                 return false;
 
              byte[] stego = buildStego(inputText);
 
// access the image's data as a byte array
             BufferedImage im = loadImage(imFnm);
             if (im == null)
                return false;
             byte imBytes[] = accessBytes(im);
 
             if (!singleHide(imBytes, stego)) // im is modified with the stego
                  return false;
 
// store the modified image in <fnm>Msg.png
            String fnm = getFileName(imFnm);
            return writeImageToFile( fnm + "Msg.png", im);
      } // end of hide()
 

 
     private static String readTextFile(String fnm)
// read in fnm, returning it as a single string
    {
         BufferedReader br = null;
         StringBuffer sb = new StringBuffer();

          try {
              br = new BufferedReader(new FileReader( new File(fnm) ));
 
              String text = null;
              while ((text = br.readLine()) != null)
              sb.append(text + "\n");
             }
             catch (Exception e) {
         System.out.println("Could not completely read " + fnm);
return null;
}
finally {
try {
if (br != null)
br.close();
}
catch (IOException e) {
//--System.out.println("Problem closing " + fnm);
return null;
}
}
//--System.out.println("Read in " + fnm);
return sb.toString();
} // end of readTextFile()
 

 
private static byte[] buildStego(String inputText)
/* Build a stego (a byte array), made up of 2 fields:
<size of binary message>
<binary message> */
{
// convert data to byte arrays
byte[] msgBytes = inputText.getBytes();
byte[] lenBs = intToBytes(msgBytes.length);
 
int totalLen = lenBs.length + msgBytes.length;
byte[] stego = new byte[totalLen]; // for holding the resulting stego 

// combine the 2 fields into one byte array
// public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
System.arraycopy(lenBs, 0, stego, 0, lenBs.length); // length of binary message
System.arraycopy(msgBytes, 0, stego, lenBs.length, msgBytes.length); // binary message
 
// System.out.println("Num. pixels to store fragment " + i + ": " + totalLen*DATA_SIZE);
return stego;
} // end of buildStego()
 

 
private static byte[] intToBytes(int i)
// split integer i into a MAX_INT_LEN-element byte array
{
// map the parts of the integer to a byte array
byte[] integerBs = new byte[MAX_INT_LEN];
integerBs[0] = (byte) ((i >>> 24) & 0xFF);
integerBs[1] = (byte) ((i >>> 16) & 0xFF);
integerBs[2] = (byte) ((i >>> 8) & 0xFF);
integerBs[3] = (byte) (i & 0xFF);
 
// for (int j=0; j < integerBs.length; j++)
// System.out.println(" integerBs[ " + j + "]: " + integerBs[j]);
 
return integerBs;
} // end of intToBytes()
 

 
private static BufferedImage loadImage(String imFnm)
// read the image from the imFnm file
{
BufferedImage im = null;
try {
im = ImageIO.read( new File(imFnm) );
//--System.out.println("Read " + imFnm);
}
catch (IOException e)
{ System.out.println("Could not read image from " + imFnm); }
 
return im;
} // end of loadImage()
 

 
private static byte[] accessBytes(BufferedImage image)
// access the data bytes in the image
{
WritableRaster raster = image.getRaster();
DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
return buffer.getData();
} // end of accessBytes()
 

 
private static boolean singleHide(byte[] imBytes, byte[] stego)
// store stego in image bytes
{
int imLen = imBytes.length;
//--System.out.println("Byte length of image: " + imLen);
 
int totalLen = stego.length;
//--System.out.println("Total byte length of message: " + totalLen);
 
// check that the stego will fit into the image
// multiply stego length by number of image bytes required to store one stego byte
if ((totalLen*DATA_SIZE) > imLen) {
System.out.println("Image not big enough for message");
return false;
}
 
hideStego(imBytes, stego, 0); // hide at start of image
return true;
} // end of singleHide()

 

private static void hideStego(byte[] imBytes, byte[] stego, int
 
offset)
// store stego in image starting at byte posn offset
{
for (int i = 0; i < stego.length; i++) { // loop through stego
int byteVal = stego[i];
for(int j=7; j >= 0; j--) { // loop through the 8 bits of each stego byte
int bitVal = (byteVal >>> j) & 1;
 
// change last bit of image byte to be the stego bit
imBytes[offset] = (byte)((imBytes[offset] & 0xFE) | bitVal);
offset++;
}
}
} // end of hideStego()
 

 

private static String getFileName(String fnm)
// extract the name from the filename without its suffix
{
int extPosn = fnm.lastIndexOf('.');
if (extPosn == -1) {
System.out.println("No extension found for " + fnm);
return fnm; // use the original file name
}
 
return fnm.substring(0, extPosn);
} // end of getFileName()
 

 

private static boolean writeImageToFile(String outFnm, BufferedImage
 
im)
// save the im image in a PNG file called outFnm
{
if (!canOverWrite(outFnm))
return false;
 
try {
ImageIO.write(im, "png", new File(outFnm));
//--System.out.println("Image written to PNG file: " + outFnm);
 JOptionPane.showMessageDialog("Your Message is Encypted and is completly safe");
return true;
}
catch(IOException e)
{ System.out.println("Could not write image to " + outFnm);
return false;
}
} // end of writeImageToFile();
 

 
private static boolean canOverWrite(String fnm)
/* If fnm already exists, get a response from the
user about whether it should be overwritten or not. */
{
File f = new File(fnm);
if (!f.exists())
return true; // can overewrite since the file is new
 
// prompt the user about whether the file can be overwritten
Scanner in = new Scanner(System.in);
String response;
System.out.print("File " + fnm + " already exists. ");
 while (true) {
System.out.print("Overwrite (y|n)? ");
response = in.nextLine().trim().toLowerCase();
if (response.startsWith("n")) // no
return false;
else if (response.startsWith("y")) // yes
return true;
}
} // end of canOverWrite()
}

