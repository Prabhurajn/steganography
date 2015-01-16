package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import Encrypt.Encryption;
import decryptFinal.Decryption_Final;
import EncryptLast.EncryptFinal;
import java.sql.*;
import java.io.*;
import  setting.TabbedPaneDemo;

public class MainStego extends JFrame implements ActionListener
{
   JFrame frame;
   JLabel label1,lblkey,lblname;
   JButton button1,button2,btnLogin,btnCancel,btnadminset,btnprvtset;
   JPasswordField txtKey,txtname;
   public String id;
   int flag=0;


   public MainStego()
    {
       //super("Stego");   
       frame = new JFrame("Stego");

       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       txtKey = new JPasswordField();
       txtname = new JPasswordField();
       btnLogin = new JButton("Login");
       btnCancel = new JButton("Exit");
       lblname=new JLabel("Enter a Name :");
       lblkey=new JLabel("Enter a  Key :"); 
       btnadminset = new JButton("Admin Setting");
       btnprvtset = new JButton("User Setting");

//for panels with Enc & Dec Buttons

       JPanel panel = new JPanel();
       Color trans = new Color(0,0,0,0);
       label1 = new JLabel("");
       label1.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
       panel.setBounds(10,5,825,350);
//       Border bd1=BorderFactory.createTitledBorder("");
   //    panel.setBorder(bd1);
       panel.setLayout(null);
	
      // txtKey.setFont(new Font("Comic Sans MS", Font.BOLD, 35));

       label1.setBounds(300,5,200,100);
       lblkey.setBounds(550,250,100,30);
       lblname.setBounds(550,200,100,30);
       txtname.setBounds(650,200,120,30);

       txtKey.setBounds(650,250,120,30);
       btnLogin.setBounds(550,300,100,30);
       btnCancel.setBounds(660,300,100,30);
            
       panel.add(label1);
       panel.add(lblname);
       panel.add(lblkey);
       panel.add(txtKey);
       panel.add(btnLogin);
       panel.add(txtname);
       panel.add(btnCancel);
           

       JPanel panel1 = new JPanel();
       button1 = new JButton("Encryption");
       button1.setEnabled(false);
       button2 = new JButton("Decryption");
       button2.setEnabled(false);
//       button1.setPreferredSize(new Dimension(100, 100));
//      button2.setPreferredSize(new Dimension(100, 100));
       panel1.setLayout(null);
       button1.setBounds(10,10,100,100);
       button2.setBounds(120,10,100,100);
       
       panel1.setBounds(300,420,230,120);
//    Border bd2=BorderFactory.createTitledBorder("");
//   panel1.setBorder(bd2);
       
       JPanel panelset=new JPanel();
       panelset.setBounds(345,550,140,50);
       panelset.add(btnadminset);
       panelset.add(btnprvtset);
       panelset.setLayout(null);
       btnadminset.setBounds(5,5,130,30);
       btnadminset.setVisible(false);
       btnprvtset.setBounds(5,5,130,30);
       btnprvtset.setVisible(false);
       panel1.add(button1);
       panel1.add(button2);

//      this.add(keypanel);
//      this.add(panel);
//      this.add(panelset);
//      this.add(panel1);


      button1.addActionListener(this);
      button2.addActionListener(this);
      btnLogin.addActionListener(this);
      btnCancel.addActionListener(this);
      btnadminset.addActionListener(this);
      btnprvtset.addActionListener(this);

      
      setLayout(null);
     ImageIcon image = new ImageIcon("main1.png");
    JLabel imageLabel = new JLabel(image);
    frame.add(imageLabel);
      frame.setSize(850,630);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);


      panel.setBackground(trans);
      panelset.setBackground(trans);
      panel1.setBackground(trans);

      imageLabel.add(panel);
      imageLabel.add(panelset);
      imageLabel.add(panel1);

      frame.setVisible(true);
      frame.setResizable(false);
    }


  public void actionPerformed(ActionEvent ae)
  {
       if(ae.getSource() == button1)
      {
	frame.dispose();
             Encryption er=new Encryption(id);
      }
       if(ae.getSource() == btnCancel)
      {
	System.exit(0);
      }

       if(ae.getSource()==button2)
       {
            frame.dispose();
            Decryption_Final er=new Decryption_Final();
       }
       if(ae.getSource()==btnLogin)
       {
	try
	{
              String database="StegoKeys.mdb";
          		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + database + ";PWD=cegospdv";
		Connection con=DriverManager.getConnection(url);

		String sql="select * from keys";
		PreparedStatement ps=con.prepareStatement(sql);

		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
      		            if(txtKey.getText().equals(rs.getString("key")))
                                          {
				id=rs.getString("uname");
				if(txtname.getText().equals(id))
				{
					int stat=Integer.parseInt(rs.getString("status"));
					flag=1;
 					if(stat==1)
   					{
					      button1.setEnabled(true);
					      button2.setEnabled(true);
	 				      btnadminset.setVisible(true);
					      txtKey.setEnabled(false);
					      btnLogin.setEnabled(false);
					}
 					else if(stat==2)
 					{
					      button1.setEnabled(true);
					      button2.setEnabled(true);
	 				      btnprvtset.setVisible(true);
					      txtname.setEnabled(false);						
					      txtKey.setEnabled(false);
					      btnLogin.setEnabled(false);

					}
					else
 					{
					      button1.setEnabled(true);
					      txtname.setEnabled(false);
					      txtKey.setEnabled(false);
					      btnLogin.setEnabled(false);
					}
				}

				//System.out.println(id+" in MainStego");
				con.close();
				break;
			}				
		}

		if(flag==0)
		{
			JOptionPane.showMessageDialog(this,"Invalid User & Key");
		}
	}catch(Exception ex)
	 {
		JOptionPane.showMessageDialog(this,"Run Time Error");
	 }
       }
       if(ae.getSource()==btnadminset)
       {
            final String user=id;
        		SwingUtilities.invokeLater(new Runnable()
		{
            			public void run()
			{
                			TabbedPaneDemo.showFrame(user);
            			}
        		});

       }

       if(ae.getSource()==btnprvtset)
       {
            final String user=id;

        		SwingUtilities.invokeLater(new Runnable()
		{
            			public void run()
			{
                			TabbedPaneDemo.showFrame(user);
            			}
        		});

       }
   }


//   public static void main(String[] args) 
  // {
       //    MainStego ms=new MainStego();
    //}
}


