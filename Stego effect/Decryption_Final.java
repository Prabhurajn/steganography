import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.swing.filechooser.*;
import java.io.*;

public class Decryption extends JFrame implements ActionListener
{

  JButton b1,b2,b3;    
  JTextArea t;
   public Decryption() 
   {
        super(""); 
 
        setLayout(null);
        JPanel panel1=new JPanel();
        panel1.setBounds(10,10,825,100);
        Border bd2=BorderFactory.createTitledBorder("Title");
        panel1.setBorder(bd2);
        
        t=new JTextArea(8,72);
        JScrollPane jp=new JScrollPane(t);
 
        JPanel panel2=new JPanel();
        panel2.setBounds(10,370,825,170);
        Border bd3=BorderFactory.createTitledBorder("Massage");
        panel2.setBorder(bd3);
 
        JPanel panel3=new JPanel();
        panel3.setBounds(10,110,825,260);
        Border bd4=BorderFactory.createTitledBorder("Image");
        panel3.setBorder(bd4);

        b1=new JButton("Decrypt");
        b2=new JButton("Save Massage");
        b3=new JButton("Exit");
        b1.addActionListener(this);        
        b2.addActionListener(this);        
        b3.addActionListener(this);        

        panel2.add(jp,BorderLayout.CENTER);                
        this.add(panel1);
        this.add(panel2); 
        this.add(panel3); 
        b1.setBounds(455,550,100,30);
        b2.setBounds(570,550,150,30);
        b3.setBounds(735,550,100,30);

        this.add(b1);
        this.add(b2);
        this.add(b3);
 
        setSize(850,630);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

public void actionPerformed(ActionEvent ae)
{
       if(ae.getSource()==b1)
         {
            
                        
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
		JOptionPane.showMessageDialog(this,"Error occured during File saving...");
	      }
              }           
         }

       if(ae.getSource()==b3)
         {
	System.exit(0);
         }

}

public static void main(String as[] )

{

          Decryption ob=new Decryption();

}

}
