package decryptFinal;

import main.MainStego;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.io.*;
import imgeffect.ImgEffect;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;

public class Decryption_Final extends JFrame implements ActionListener
{
     JButton b1,b2,b3,b4;    
     JTextArea t;
     JTextField field1;
     JLabel label1;
     String img;
     JPanel panel3;
     int temp = 0;  
     JFrame frame;
     public Decryption_Final() 
     {
          //super("Decryption"); 
          frame=new JFrame("CEGOSYS-Decryption");
          //frame.setLayout(null);

          JPanel panel1=new JPanel();
          field1 = new JTextField();
          field1.setPreferredSize(new Dimension(200,25));
          b4 = new JButton("Browse Image File");
          panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20,150));
          panel1.setBounds(10,10,825,100);
          panel1.setLayout(new FlowLayout(FlowLayout.RIGHT, 20,35));
          //Border bd2=BorderFactory.createTitledBorder("Title");
          //panel1.setBorder(bd2);
          panel1.add(field1);
          panel1.add(b4);
        
          t=new JTextArea(5,72);
          JScrollPane jp=new JScrollPane(t);
          JPanel panel2=new JPanel();
          panel2.setBounds(10,420,825,130);
          //Border bd3=BorderFactory.createTitledBorder("Massage");
          //panel2.setBorder(bd3);
 
          panel3=new JPanel();
          panel3.setBounds(10,110,825,305);
          //Border bd4=BorderFactory.createTitledBorder("Image");
          //panel3.setBorder(bd4);

          b1=new JButton("Decrypt");
          b2=new JButton("Save Massage");
          b3=new JButton("Go to main menu");

          b1.addActionListener(this);        
          b2.addActionListener(this);        
          b3.addActionListener(this);        
          b4.addActionListener(this);

      ImageIcon image = new ImageIcon("encryption.png");
      JLabel imageLabel = new JLabel(image);
      frame.add(imageLabel);

          panel2.add(jp,BorderLayout.CENTER);                
          //this.add(panel1);
          //this.add(panel2); 
          //this.add(panel3);
 
          b1.setBounds(400,550,100,30);
          b2.setBounds(515,550,150,30);
          b3.setBounds(680,550,155,30);

          //this.add(b1);
          //this.add(b2);
          //this.add(b3);


 
          frame.setSize(850,630);

      Color trans = new Color(0,0,0,0);
      panel1.setBackground(trans);
      panel2.setBackground(trans);
      panel3.setBackground(trans);

      imageLabel.add(panel1);
      imageLabel.add(panel2);
      imageLabel.add(panel3);

          imageLabel.add(b1);
          imageLabel.add(b2);
          imageLabel.add(b3);

          frame.setVisible(true);
          frame.setResizable(false);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
          frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

     }

     public void actionPerformed(ActionEvent ae)
     {
           if(ae.getSource()==b1)
           {
                 Steganography st= new Steganography();
                 String msg=st.reveal(img);
                 t.setText(msg);
          }

          if(ae.getSource()==b2)
          {
                 String msg = t.getText();
	   if(msg.equals(""))
	   {
		JOptionPane.showMessageDialog(this,"There is no message to save in File...");
	    }
	   else
	   {
	               try
	               {
          		              JFileChooser filechooser = new JFileChooser();
          		              filechooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
           			FileNameExtensionFilter filter= new FileNameExtensionFilter("(*.txt;)files","txt");

           			filechooser.setFileFilter(filter);
           			int result=filechooser.showSaveDialog(this);
			String url = filechooser.getSelectedFile().getPath() + ".txt";

			FileOutputStream fos = new FileOutputStream(url);
			DataOutputStream dos = new DataOutputStream(fos);
			JOptionPane.showMessageDialog(this,"File Save Successfully at " + url);
			dos.writeUTF(msg);

			dos.close();
			fos.close();
		
	    	}catch(Exception e)
	     	{
			JOptionPane.showMessageDialog(this,"Error occured during File 					saving...");
	      	}
                 }           
         }

        if(ae.getSource()==b3)
         {
	  frame.dispose();
	  MainStego ms=new MainStego();
         }
        
         if(ae.getSource()==b4)
         {
	                    
                 JFileChooser fileChooser = new JFileChooser();
                 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                 FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.png;) files", "png");

                 fileChooser.setFileFilter(filter);  
                 int result = fileChooser.showDialog(this, "Open");

                 if (result == JFileChooser.APPROVE_OPTION) 
                {

		
                         File file = fileChooser.getSelectedFile();
                         field1.setText(file.toString());
                         img=file.getPath();                
 
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
   
   
                     image = new ImageIcon(image.getImage().getScaledInstance(h, w,Image.SCALE_SMOOTH));
	       if(temp > 0)
	       {
		panel3.remove(label1);
		panel3.repaint();
	        }
                     label1 = new JLabel("",image, JLabel.CENTER);
                     label1.setBorder(BorderFactory.createEtchedBorder());
                     panel3.add(label1);
                     panel3.revalidate();
	       temp++;                     
                  }
            }

  }//end of Actionperformed


}// end of class Decryption_Final

 
 class Steganography extends JFrame
{
	private static final int MAX_INT_LEN = 4;
	private static final int DATA_SIZE = 8;

    	public  String reveal(String imFnm)
    	{
		BufferedImage im = loadImage(imFnm);
       		if (im == null)
		{
			JOptionPane.showMessageDialog(this,"No image Found");
		}
	
        		byte[] imBytes = accessBytes(im);
		
 
		// get msg length at the start of the image
       		int msgLen = getMsgLength(imBytes, 0);
       		if (msgLen == -1)
		{
			JOptionPane.showMessageDialog(this,"no msg found");
		}
 
		// get message located after the length info in the image
		String msg = getMessage(imBytes, msgLen, MAX_INT_LEN*DATA_SIZE);
 		
		
		if (msg != null)
		{
			return msg;
		}
		else 
		{

			String str="error";
			return str;
		}
	} // end of reveal()


	private  int getMsgLength(byte[] imBytes, int offset)
	// retrieve binary message length from the image
	{
		byte[] lenBytes = extractHiddenBytes(imBytes, MAX_INT_LEN, offset);
		// get the binary message length as a byte array
		if (lenBytes == null)
			return -1;

		// convert the byte array into an integer
		int msgLen = ((lenBytes[0] & 0xFF) << 24) |
		((lenBytes[1] & 0xFF) << 16) |
		((lenBytes[2] & 0xFF) << 8) |
		(lenBytes[3] & 0xFF);

 
		if ((msgLen <= 0) || (msgLen > imBytes.length))
		{
			JOptionPane.showMessageDialog(this,"Incorrect message length");
			return -1;
		}
 
		return msgLen;
	} // end of getMsgLength()
 

 
	private  String getMessage(byte[] imBytes, int msgLen, int offset)
	/* Extract a binary message of size msgLen from the image, and
	convert it to a string*/
	{
		byte[] msgBytes = extractHiddenBytes(imBytes, msgLen, offset);
		// the message is msgLen bytes long
		if (msgBytes == null)
		return null;
 
		String msg = new String(msgBytes);
		msg=SwapDec(msg);

		// check the message is all characters
		if (isPrintable(msg))
		{
			return msg;
		}
		else
			return null;
	} // end of getMessage()
 
	public  String SwapDec(String str1)
   	{
		String str2="";
          		int no=str1.length();

          		for(int i=0;i<str1.length();i++)
          		{
              		if (i%2==0)
                      			str2=str2+(char)((int)str1.charAt(i)-2);      
			else
                      			str2=str2+(char)((int)str1.charAt(i)+2); 
          		}
       		return str2;
   	}

 
	private  byte[] extractHiddenBytes(byte[] imBytes, int size, int offset)
	// extract 'size' hidden data bytes, starting from 'offset' in the image bytes
	{
		int finalPosn = offset + (size*DATA_SIZE);
		if (finalPosn > imBytes.length) 
		{
			//JOptionPane.showMessageDialog(this,"End of image reached");
			return null;
		}
 
		byte[] hiddenBytes = new byte[size];
 
		for (int j =0; j <size; j++)
		{ 
			// loop through each hidden byte
			for (int i=0; i<DATA_SIZE ; i++)
			{ 
				// make one hidden byte from DATA_SIZE image bytes
				hiddenBytes[j] = (byte) ((hiddenBytes[j] << 1) | (imBytes[offset] & 1));
				// shift existing 1 left; store right most bit of image byte
				offset++;
			}
		}
		return hiddenBytes;
	} // end of extractHiddenBytes()
  
	private  boolean isPrintable(String str)
	// is the string printable?
	{
		for (int i=0; i < str.length(); i++)
		{
			if (!isPrintable(str.charAt(i)))
			//JOptionPane.showMessageDialog(this,"Unprintable character found");
			return false;
		}
		return true;
	} // end of isPrintable()
 

 
	private  boolean isPrintable(int ch)
	// is ch a 7-bit ASCII character that could (sensibly) be printed?
	{
		if (Character.isWhitespace(ch)) // whitespace, 7-bit
    			return true;
		else if ((ch > 0) && (ch < 127))
    			return true;

		return false;
	} // end of isPrintable()
 


	private  BufferedImage loadImage(String imFnm)
	// read the image from the imFnm file
	{
		BufferedImage im = null;
		try
		{
			im = ImageIO.read( new File(imFnm) );
		}catch (IOException e)
		  {
			//JOptionPane.showMessageDialog(this,"Could not read image from " + imFnm); 
		  }
		return im;

	} // end of loadImage()

	private  byte[] accessBytes(BufferedImage image)
	// access the data bytes in the image
	{
		WritableRaster raster = image.getRaster();
		DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
		return buffer.getData();
	} // end of accessBytes()

	private  boolean canOverWrite(String fnm)
	/* If fnm already exists, get a response from the
	user about whether it should be overwritten or not. */
	{
		File f = new File(fnm);
		if (!f.exists())
		return true; // can overewrite since the file is new
 
		// prompt the user about whether the file can be overwritten
		Scanner in = new Scanner(System.in);
		String response;
		//JOptionPane.showMessageDialog(this,"File " + fnm + " already exists. ");
		while (true)
		{
			//JOptionPane.showMessageDialog(this,"Overwrite (y|n)? ");
			response = in.nextLine().trim().toLowerCase();
			if (response.startsWith("n")) // no
				return false;
			else if (response.startsWith("y")) // yes
				return true;
		}
	} // end of canOverWrite()

}// end of Steganography
