package Encrypt;

import main.MainStego;
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
     JRadioButton cbbrowse,cbtype;
     JTextArea txtType;
     JLabel label1,label2,label3;
     JTextField field1,field2;
     ImgEffect ie;
     String img,txt;
     public String idpass2;
     ButtonGroup BG; 
     int msg=1,chk=3;
     JPanel panel;
     JFrame frame;
     JScrollPane scrollingResult;
     
    public Encryption(String idpass) 
   {
        //super("Encryption");
        frame=new JFrame("Encryption");
        idpass2=idpass;

//       System.out.println(idpass2+" in Encryption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        Color trans = new Color(00,0,0,0);
       
//       panel.setLayout(null);

        label1 = new JLabel("Encryption");
        label1.setFont(new Font("Gill Sans MT", Font.BOLD, 30)); 

       label2 = new JLabel("Browse Text File");
       label2.setFont(new Font("Gill Sans MT", Font.BOLD, 16));

       label3 = new JLabel("Browse Image File");
       label3.setFont(new Font("Gill Sans MT", Font.BOLD, 16));  
   
       field1 = new JTextField(20);
       field2 = new JTextField(20);

       button3 = new JButton("...");
       button4 = new JButton("...");
       button1 = new JButton("Next");
       button2 = new JButton("Cancel");

       BG = new ButtonGroup();
      
       cbbrowse = new JRadioButton("By Browsing File");
       cbtype = new JRadioButton("By Mannul Type"); 
       cbbrowse.setBackground(trans);
       cbtype.setBackground(trans);

       txtType=new JTextArea(5,72);

       scrollingResult = new JScrollPane(txtType);

               
      
       BG.add(cbbrowse);
       BG.add(cbtype); 
    
                             
      label1.setBounds(320, 50, 200, 50);
      cbbrowse.setBounds(270,170,150,25);
      field1.setBounds(345, 230, 200, 25);
      cbtype.setBounds(420,170,150,25);
      field2.setBounds(345, 280, 200, 25);
      

      scrollingResult.setBounds(70,400,695,145);
      scrollingResult.setVisible(false);

      label2.setBounds(185, 215, 200, 50);
      label3.setBounds(185, 265, 200, 50);

      button1.setBounds(300, 350, 100, 25);
      button2.setBounds(420, 350, 100, 25);
      button3.setBounds(550, 230, 35, 25);
      button4.setBounds(550, 280, 35, 25);
      cbbrowse.setSelected(true);    
/*
      panel.add(label1);
      panel.add(label2);
      panel.add(label3);
      panel.add(cbbrowse);
      panel.add(field1);
      panel.add(field2);
      panel.add(button1);
      panel.add(button2);
      panel.add(button3);
      panel.add(button4);

      panel.add(cbtype);
      panel.add(txtType);
*/
            
      frame.setSize(850,630);

 //panel.setBackground(trans); 

 // imageLabel.add(panel);


      cbtype.addActionListener(this);
      cbbrowse.addActionListener(this);
      button1.addActionListener(this);
      button2.addActionListener(this);
      button3.addActionListener(this);
      button4.addActionListener(this);

      
      //setLayout(null);

        ImageIcon image = new ImageIcon("encryption.png");
        JLabel imageLabel = new JLabel(image);
        frame.add(imageLabel);
        //imageLabel.add(panel);

      imageLabel.add(label1);
      imageLabel.add(label2);
      imageLabel.add(label3);
      imageLabel.add(cbbrowse);
      imageLabel.add(field1);
      imageLabel.add(field2);
      imageLabel.add(button1);
      imageLabel.add(button2);
      imageLabel.add(button3);
      imageLabel.add(button4);

      imageLabel.add(cbtype);
      imageLabel.add(scrollingResult);

        frame.setVisible(true);
        frame.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

  }

   public void actionPerformed(ActionEvent ae)
  {

      if(cbtype.isSelected())
      {
             msg=0;
             field1.setEnabled(false);
             button3.setEnabled(false);
             scrollingResult.setVisible(true);
      }
      
      if(cbbrowse.isSelected())
      {
             msg=1;
             field1.setEnabled(true);
             button3.setEnabled(true);
             scrollingResult.setVisible(false);
      }

       if(ae.getSource() == button1)
      {
            String t1=field1.getText();
            String t2=field2.getText();

            if(msg==0)
            {
               String te=txtType.getText();
                       if(te.equals(""))
                      {
                            chk=1;
                      }
                      else
                      {
                            txt=txtType.getText();
                      }
             }
              
            if(msg==1)
            {
                 if(t1.equals(""))
                 {
                       chk=0;
                  }
            }  
          
            if(t2.equals(""))
           {
                  JOptionPane.showMessageDialog(this,"Please Browse the Image File");
           }
           else if(chk==0)
           {
                  JOptionPane.showMessageDialog(this,"Please Browse the Text File");               
                  chk=3;
           }
          else if(chk==1)
           {
                  JOptionPane.showMessageDialog(this,"Please Enter the Text");               
                  chk=3;
           }
           else
           {

                 ImgEffect ie=new ImgEffect(idpass2);
                 ie.FitImg(img);
                 ie.TxtFile(txt);
                 frame.dispose();
           }
       }

       if(ae.getSource()==button2)
       {
	frame.dispose();
	MainStego ms=new MainStego();
       }

       if(ae.getSource() == button3)
      {
                  
                 JFileChooser fileChooser = new JFileChooser();
                 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                 FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.txt) files", "txt");
                 fileChooser.setFileFilter(filter);

                 int result = fileChooser.showDialog(this, "Open");
                 if (result == JFileChooser.APPROVE_OPTION) 
                {
                         File file = fileChooser.getSelectedFile();
                         field1.setText(file.toString());
                         txt=file.getPath();
                 }
                try
                {
                      FileInputStream fis=new FileInputStream(txt);
                      int cb;
                      String cc=""; 

          	       while((cb=fis.read())!=-1)
 	      {
	           cc = cc + (char)cb;
	       }

                     txt=cc;
                    fis.close();
                 }catch(Exception e)
                {
                      JOptionPane.showMessageDialog(this,"No Message Found");
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
