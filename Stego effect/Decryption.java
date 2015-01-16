package Decrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import imgeffect.ImgEffect;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;

public class Decryption implements ActionListener
{
   JFrame frame;
   JButton button1,button2,button3;
   JLabel label1;
   JPanel panel;
   JTextField field1;
     String img;  
   public Decryption() 
   {
      frame = new JFrame("---Decryption---");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      panel = new JPanel();
      panel.setLayout(null);
      label1 = new JLabel("Decryption");
      label1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
      
      field1 = new JTextField(20);
  //    field1.setEnabled(false);
     
      button1 = new JButton("Decrypt");
      button2 = new JButton("Cancel");
      button3 = new JButton("Browse Image File");

      label1.setBounds(300, 50, 200, 50);

      field1.setBounds(200, 250, 200, 25);

      button1.setBounds(300, 320, 100, 25);
      button2.setBounds(420, 320, 100, 25);
      button3.setBounds(420, 250, 150, 25);

      panel.add(label1);
      panel.add(field1);
      panel.add(button1);
      panel.add(button2);
      panel.add(button3);

      button1.addActionListener(this);
      button2.addActionListener(this);
      button3.addActionListener(this);

      frame.add(panel);
      frame.setSize(850,630);
      frame.setVisible(true);
     frame.setResizable(false);
  }

   public void actionPerformed(ActionEvent ae)
  {
       if(ae.getSource() == button1)
      {
		Steganography st= new Steganography();
		//st.hide(args[0],args[1]);
		st.reveal(img);
       }

       if(ae.getSource()==button2)
       {
       }

       if(ae.getSource() == button3)
      {
            JFileChooser fileChooser = new JFileChooser();
           fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

           FileNameExtensionFilter filter = new FileNameExtensionFilter(
           "(*.png;) files", "png");
          fileChooser.setFileFilter(filter);

           int result = fileChooser.showDialog(frame, "Open");
           if (result == JFileChooser.APPROVE_OPTION) 
          {
                File file = fileChooser.getSelectedFile();
                field1.setText(file.toString());
                img=file.getPath();                
               JOptionPane.showMessageDialog(frame,img);
           }
      }

   }
}


class Steganography
{
     private static final int MAX_INT_LEN = 4;
     private static final int DATA_SIZE = 8;

    public static boolean reveal(String imFnm)
/* Retrieve the hidden message from imFnm from the beginning
of the image after first extractibg its length information.
The extracted message is stored in <imFnm>.txt
*/
    {
// get the image's data as a byte array
        BufferedImage im = loadImage(imFnm);
       if (im == null)
           return false;
        byte[] imBytes = accessBytes(im);
 //        System.out.println("Byte length of image: " + imBytes.length);
 
// get msg length at the start of the image
       int msgLen = getMsgLength(imBytes, 0);
       if (msgLen == -1)
        return false;
//System.out.println("Byte length of message: " + msgLen);
 
// get message located after the length info in the image
String msg = getMessage(imBytes, msgLen, MAX_INT_LEN*DATA_SIZE);
 

if (msg != null) {
int Po = imFnm.lastIndexOf('/');
int extPosn=imFnm.lastIndexOf('.');
String str=imFnm.substring(Po+1,extPosn);
System.out.println(str);
String fnm = str;//getFileName(imFnm);
//String f=fnm.substring(f.lastIndexOf('/')+1,f.length());
return writeStringToFile(fnm + ".txt", msg); // save message in a text file
}
else {
System.out.println("No message found");
return false;
}
} // end of reveal()

 

private static int getMsgLength(byte[] imBytes, int offset)
// retrieve binary message length from the image
{
byte[] lenBytes = extractHiddenBytes(imBytes, MAX_INT_LEN, offset);
// get the binary message length as a byte array
if (lenBytes == null)
return -1;
 
// for (int j=0; j < lenBytes.length; j++)
// System.out.println(" lenBytes[ " + j + "]: " + lenBytes[j]);
 
// convert the byte array into an integer
int msgLen = ((lenBytes[0] & 0xff) << 24) |
((lenBytes[1] & 0xff) << 16) |
((lenBytes[2] & 0xff) << 8) |
(lenBytes[3] & 0xff);
// System.out.println("Message length: " + msgLen);
 
if ((msgLen <= 0) || (msgLen > imBytes.length)) {
System.out.println("Incorrect message length");
return -1;
}
// else
// System.out.println("Revealed message length: " + msgLen);
 
return msgLen;
} // end of getMsgLength()
 

 
private static String getMessage(byte[] imBytes, int msgLen, int offset)
/* Extract a binary message of size msgLen from the image, and
convert it to a string
*/
{
byte[] msgBytes = extractHiddenBytes(imBytes, msgLen, offset);
// the message is msgLen bytes long
if (msgBytes == null)
return null;
 
String msg = new String(msgBytes);
 
// check the message is all characters
if (isPrintable(msg)) {
// System.out.println("Found message: \"" + msg + "\"");
return msg;
}
else
return null;
} // end of getMessage()
 

 
private static byte[] extractHiddenBytes(byte[] imBytes, int size, int offset)
// extract 'size' hidden data bytes, starting from 'offset' in the image bytes
{
int finalPosn = offset + (size*DATA_SIZE);
if (finalPosn > imBytes.length) {
System.out.println("End of image reached");
return null;
}
 
byte[] hiddenBytes = new byte[size];
 
for (int j = 0; j < size; j++) { // loop through each hidden byte
for (int i=0; i < DATA_SIZE; i++) { // make one hidden byte from DATA_SIZE image bytes
hiddenBytes[j] = (byte) ((hiddenBytes[j] << 1) |
 
(imBytes[offset] & 1));
// shift existing 1 left; store right most bit of image byte
offset++;
}
}
return hiddenBytes;
} // end of extractHiddenBytes()
 

 
private static boolean isPrintable(String str)
// is the string printable?
{
for (int i=0; i < str.length(); i++)
if (!isPrintable(str.charAt(i))) {
System.out.println("Unprintable character found");
return false;
}
return true;
} // end of isPrintable()
 

 
private static boolean isPrintable(int ch)
// is ch a 7-bit ASCII character that could (sensibly) be printed?
{
//System.out.println(ch);
if (Character.isWhitespace(ch) && (ch < 127)) // whitespace, 7-bit
    return true;
else if ((ch > 32) && (ch < 127))
    return true;
else if((char)ch=='\n')
   return true;

 
return false;
} // end of isPrintable()
 

 
	private static boolean writeStringToFile(String outFnm, String msgStr)
	// write the message string into the outFnm text file
	{
	if (!canOverWrite(outFnm))
	return false;
 
	try {
	FileWriter out = new FileWriter( new File(outFnm) );
	out.write(msgStr);
	out.close();
	//System.out.println("Message written to " + outFnm);
	return true;
	}
	catch(IOException e)
	{ System.out.println("Could not write message to " + outFnm);
	return false;
	}
	} // end of writeStringToFile()
    


private static BufferedImage loadImage(String imFnm)
// read the image from the imFnm file
{
BufferedImage im = null;
try {
im = ImageIO.read( new File(imFnm) );
//System.out.println("Read " + imFnm);
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

/*private static String getFileName(String fnm)
// extract the name from the filename without its suffix
{
int Po = fnm.lastIndexOf('/');
int extPosn=fnm.IndexOf(po,'.');
if (extPosn == -1) {
System.out.println("No extension found for " + fnm);
return fnm; // use the original file name
}
*/

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