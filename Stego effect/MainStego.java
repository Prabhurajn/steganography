import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import Encrypt.Encryption;
import Decrypt.Decryption;

public class MainStego extends JFrame implements ActionListener
{
   JLabel label1;
   JButton button1,button2;

    MainStego()
    {
       super("Stego");   
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       JPanel panel = new JPanel();

       label1 = new JLabel("Stegnography");
       label1.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
       panel.setBounds(10,10,825,100);
       Border bd1=BorderFactory.createTitledBorder("---stegnography---");
       panel.setBorder(bd1);
       panel.add(label1);


       JPanel panel1 = new JPanel();

       button1 = new JButton("Encryption");
       button2 = new JButton("Decryption");
       button1.setPreferredSize(new Dimension(100, 100));
       button2.setPreferredSize(new Dimension(100, 100));
       panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20,150));
       
       panel1.setBounds(10,110,825,475);
       Border bd2=BorderFactory.createTitledBorder("---Select---");
       panel1.setBorder(bd2);
       


       panel1.add(button1);
       panel1.add(button2);

      this.add(panel);
      this.add(panel1);

      button1.addActionListener(this);
      button2.addActionListener(this);
      setLayout(null);
      setSize(850,630);
      setVisible(true);
      setResizable(false);
    }


  public void actionPerformed(ActionEvent ae)
  {
       if(ae.getSource() == button1)
      {
	dispose();
             Encryption er=new Encryption();
      }

       if(ae.getSource()==button2)
       {
          dispose();
           Decryption er=new Decryption();
       }

   }


   public static void main(String[] args) 
   {
           MainStego ms=new MainStego();
    }
}





