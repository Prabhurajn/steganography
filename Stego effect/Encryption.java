package Encrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import imgeffect.ImgEffect;
import java.io.*;
import javax.swing.filechooser.*;

public class Encryption extends JFrame implements ActionListener
{
     JButton button1,button2,button3,button4;
     JLabel label1;
     JTextField field1,field2;
     ImgEffect ie;
     String img,txt;
  
  public Encryption() 
   {
       super("Encryption");
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       JPanel panel = new JPanel();
       panel.setLayout(null);
       label1 = new JLabel("Encryption");
       label1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
     
      field1 = new JTextField(20);
//      field1.setEnabled(false);
      field2 = new JTextField(20);
//      field2.setEnabled(false);
      button3 = new JButton("Browse Text File");
      button4 = new JButton("Browse Image File");
      button1 = new JButton("Next");
      button2 = new JButton("Cancel");
 
      label1.setBounds(300, 50, 200, 50);
      field1.setBounds(200, 200, 200, 25);
      field2.setBounds(200, 250, 200, 25);
      
      button1.setBounds(300, 320, 100, 25);
      button2.setBounds(420, 320, 100, 25);
      button3.setBounds(420, 200, 150, 25);
      button4.setBounds(420, 250, 150, 25);
    
      panel.add(label1);
      panel.add(field1);
      panel.add(field2);
      panel.add(button1);
      panel.add(button2);
      panel.add(button3);
      panel.add(button4);
      
      button1.addActionListener(this);
      button2.addActionListener(this);
      button3.addActionListener(this);
      button4.addActionListener(this);

      this.add(panel);
      

    //  setLayout(null);
      setSize(850,630);
      setVisible(true);
      setResizable(false);
  }

   public void actionPerformed(ActionEvent ae)
  {
       if(ae.getSource() == button1)
      {
            String t1=field1.getText();
            String t2=field2.getText();
            if(t1.equals(""))
           {
                  JOptionPane.showMessageDialog(this,"Please Browse the Text File");
           }
           else if(t2.equals(""))
           {
                  JOptionPane.showMessageDialog(this,"Please Browse the Image File");
           }
           else
           {
                 ie=new ImgEffect();
                 ie.FitImg(img);
                 ie.TxtFile(txt);
                 dispose();
           }
       }

       if(ae.getSource()==button2)
       {
       }

       if(ae.getSource() == button3)
      {
           JFileChooser fileChooser = new JFileChooser();
           fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

           FileNameExtensionFilter filter = new FileNameExtensionFilter(
           "(*.txt) files", "txt");
          fileChooser.setFileFilter(filter);

           int result = fileChooser.showDialog(this, "Open");
           if (result == JFileChooser.APPROVE_OPTION) 
          {
                File file = fileChooser.getSelectedFile();
                field1.setText(file.toString());
                txt=file.getPath();
	 
           }
      }

       if(ae.getSource()==button4)
       {
            JFileChooser fileChooser = new JFileChooser();
           fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

           FileNameExtensionFilter filter = new FileNameExtensionFilter(
           "(*.bmp;*.png;*.jpg;*.jpeg) files", "bmp","png","jpg","jpeg");
          fileChooser.setFileFilter(filter);

           int result = fileChooser.showDialog(this, "Open");
           if (result == JFileChooser.APPROVE_OPTION) 
          {
                File file = fileChooser.getSelectedFile();
                field2.setText(file.toString());
                img=file.getPath();                
           }

       }

   }
}
