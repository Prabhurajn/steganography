package setting;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.sql.*;
 
public class TabbedPaneDemo extends JPanel implements ActionListener,ItemListener
{
	//JFrame jf;
	JLabel lblname,lblkey,modify,addnew,delete,lblsearch,lblmenu;
	JTextField txtname,txtkey;

	JLabel lblname2,lblkey2,lblmodify2,lbladdnew2,lbldelete2,lblsearch2,lblmenu2,lblstatus2;
	JTextField txtname2,txtkey2,txtsearch1,txtsearch2;
	JButton btnsave,btnsave2,btnnew2,btndel2,btnmodify2;
	JComboBox cmbsearch,cmbsearch1;
	JLabel lblname3,lblkey3,lblmodify3,lbladdnew3,lbldelete3,lblsearch3,lblmenu3,lblstatus3;
	JTextField txtname3,txtkey3,txtsearch3;
	JButton btnsave3,btnnew3,btndel3,btnmodify3;
    	JRadioButton r1,r2,r3,r4;
     	ButtonGroup BG1,BG2; 
	String us;
               Connection con;
               Statement st;
               ResultSet rs;	
	int counter=0;

             public TabbedPaneDemo(String user)
      	{
		initializeUI(user);
      	}
 
      	private void initializeUI(String user)
      	{
	//	jf=new JFrame();
		us=user;
        		JTabbedPane pane = new JTabbedPane();
             
             		lblname=new JLabel("User Name:");
        		lblkey=new JLabel("User Key:");
                  	txtname=new JTextField();
		txtkey=new JTextField();
		btnsave=new JButton("Save change"); 	 
		lblmenu=new JLabel("Modify Account");

                  	txtname.setBounds(140,60,120,30);
                  	txtkey.setBounds(140,110,120,30);

                  	lblkey.setBounds(40,52,100,140);
                  	lblname.setBounds(40,52,100,40);

                  	btnsave.setBounds(140,190,150,30);
              
              
       		JPanel AdminPanel = new JPanel();
                  	AdminPanel.setLayout(null);
       		AdminPanel.add(new JLabel("Admin"));
               	// Add Admin Tab
          		AdminPanel.add(lblname);   
          		AdminPanel.add(lblkey);   
                  	AdminPanel.add(txtname);   
          		AdminPanel.add(txtkey); 	  
		AdminPanel.add(btnsave); 
   		pane.addTab("Admin", AdminPanel);


             		BG1 = new ButtonGroup();
      		r1 = new JRadioButton("Private User");
      		r2 = new JRadioButton("Public User"); 
		BG1.add(r1);
		BG1.add(r2);

             		lblname2=new JLabel("User Name:");
        		lblkey2=new JLabel("User Key:");
        		lblsearch2=new JLabel("Search User:");
        		lblstatus2=new JLabel("Status:");

                  	txtname2=new JTextField();
		txtkey2=new JTextField();
		cmbsearch=new JComboBox();
		txtsearch1=new JTextField();
		btnsave2=new JButton("Modify"); 	 
		btnnew2=new JButton("Add new");
		btndel2=new JButton("Delete"); 	 
		lblmenu2=new JLabel("Modify Account");
System.out.println(user);
		if(user.equals("private"))
                            {
		     txtname.setEnabled(false); 			
		     txtkey.setEnabled(false); 			
		     btnsave.setEnabled(false); 			
		     btndel2.setEnabled(false);
      		}

		cmbsearch.setBounds(320,10,120,30);
                  	txtname2.setBounds(140,60,120,30);
                  	txtkey2.setBounds(140,110,120,30);

                  	lblsearch2.setBounds(240,10,80,30);
                  	lblkey2.setBounds(40,52,100,140);
                  	lblname2.setBounds(40,52,100,40);
                  	lblstatus2.setBounds(40,52,100,215);

                  	btnnew2.setBounds(40,190,100,30);
                  	btnsave2.setBounds(140,190,100,30);
                  	btndel2.setBounds(240,190,100,30);

                  	r1.setBounds(140,145,100,30);
                  	r2.setBounds(250,145,100,30);

       		JPanel PrivatePanel = new JPanel();
                  	PrivatePanel.setLayout(null);
        		PrivatePanel.add(new JLabel("Private"));
                  	// Add Private Tab
          		PrivatePanel.add(lblsearch2);   
          		PrivatePanel.add(lblname2);   
          		PrivatePanel.add(lblkey2);   
          		PrivatePanel.add(lblstatus2);   

               	PrivatePanel.add(txtname2);   
          		PrivatePanel.add(txtkey2); 
         		PrivatePanel.add(cmbsearch);   
	  
		PrivatePanel.add(btnsave2); 
		PrivatePanel.add(btnnew2); 
		PrivatePanel.add(btndel2); 

		PrivatePanel.add(r1); 
		PrivatePanel.add(r2); 

        		pane.addTab("Private", PrivatePanel);

             		BG2 = new ButtonGroup();
      		r3 = new JRadioButton("Private User");
      		r4 = new JRadioButton("Public User"); 
		BG2.add(r3);
		BG2.add(r4);


             		lblname3=new JLabel("User Name:");
        		lblkey3=new JLabel("User Key:");
        		lblsearch3=new JLabel("Search User:");
        		lblstatus3=new JLabel("Status:");

               	txtname3=new JTextField();
		txtkey3=new JTextField();
		cmbsearch1=new JComboBox();
		txtsearch3=new JTextField();
		btnsave3=new JButton("Modify"); 	 
		btnnew3=new JButton("Add new");
		btndel3=new JButton("Delete"); 	 

	    	cmbsearch1.setBounds(320,10,120,30);
                 	txtname3.setBounds(140,60,120,30);
                  	txtkey3.setBounds(140,110,120,30);

                  	lblsearch3.setBounds(240,10,80,30);
                  	lblkey3.setBounds(40,52,100,140);
                  	lblname3.setBounds(40,52,100,40);
                  	lblstatus3.setBounds(40,52,100,215);

                  	btnnew3.setBounds(40,190,100,30);
                	btnsave3.setBounds(140,190,100,30);
                  	btndel3.setBounds(240,190,100,30);

                  	r3.setBounds(140,145,100,30);
                  	r4.setBounds(250,145,100,30);
	
		JPanel PublicPanel = new JPanel();
                  	PublicPanel.setLayout(null);
        		PublicPanel.add(new JLabel("Public"));

      		// Add Public Tab


          		PublicPanel.add(lblsearch3);   
          		PublicPanel.add(lblname3);   
          		PublicPanel.add(lblkey3);
          		PublicPanel.add(lblstatus3);
   
          		PublicPanel.add(cmbsearch1);   
              	               PublicPanel.add(txtname3);   
          		PublicPanel.add(txtkey3);
 	  
		PublicPanel.add(btnsave3); 
		PublicPanel.add(btnnew3); 
		PublicPanel.add(btndel3); 

		PublicPanel.add(r3); 
      		PublicPanel.add(r4); 

		pane.addTab("Public", PublicPanel);
                              try
 		{
		               String database="StegoKeys.mdb";
		               Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + database + ";PWD=cegospdv";
			con=DriverManager.getConnection(url);
			String sql="select * from keys";
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=st.executeQuery(sql);
			while(rs.next())
			{
				//System.out.println(rs.getString(1));
			     int status=Integer.parseInt(rs.getString(3));
			   
			     if(status==2)
				cmbsearch.addItem(rs.getString(1));
		     
			     if(status==3)
				cmbsearch1.addItem(rs.getString(1));
		     	    if(status==1)
			    {
       		     	     if(rs.getString(1).equals(us))		
			     {
				txtname.setText(rs.getString(1));
				txtkey.setText(rs.getString(2));
							
			     }
			   }	
	  	          } 
		
		}catch(Exception e)
		{
			//System.out.println(e);
			JOptionPane.showMessageDialog(this,e);		
		}

		
                            btnsave.addActionListener(this);
                            btnsave2.addActionListener(this);
                            btnnew2.addActionListener(this);
                            btndel2.addActionListener(this);
                            btnsave3.addActionListener(this);
                            btnnew3.addActionListener(this);
                            btndel3.addActionListener(this);
                            cmbsearch.addItemListener(this);
                            cmbsearch1.addItemListener(this);
	 
        		this.setLayout(new BorderLayout());
        		this.setPreferredSize(new Dimension(450, 300));
        		this.add(pane, BorderLayout.CENTER);

        	}
 
        	public static void showFrame(String user)
        	{
        		JPanel panel = new TabbedPaneDemo(user);
        		panel.setOpaque(true);
              	JFrame frame = new JFrame("Account Settings");
//        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		frame.setContentPane(panel);
        		frame.pack();
        		frame.setVisible(true);
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//frame.setLocation(dim.width/3-frame.getSize().width/2, dim.height/3-frame.getSize().height/2);

             
      	}

	public void actionPerformed(ActionEvent ae)
              {
		if(ae.getSource()==btnsave)
		{
  	                   try
		    {
	               
	          		
	 		if(txtname.getText().equals("")||txtkey.getText().equals(""))
	                           {		
	         		              JOptionPane.showMessageDialog(this,"Please Enter a Text");
	             	             }			
			else
			{
		
				String t1=""+txtname.getText();
				String t2=""+txtkey.getText();
				Statement st=con.createStatement();
		                           	String sql="update keys set key='"+t2+"', uname='"+t1+"' where uname='"+us+"'";
				int cnt=st.executeUpdate(sql);

				if(cnt>0)
				{
		         		              JOptionPane.showMessageDialog(this,"Save Changes");
				}
				else
				{
		         		              JOptionPane.showMessageDialog(this,"Not Save");		
				}

			}
			this.revalidate();
              	     	     }catch(Exception e)
		      {
        		 	JOptionPane.showMessageDialog(this,"Error");		
	    	       }
		}




		if(ae.getSource()==btnnew2)
		{
			
			if(counter>0)
			{
			   try
			     {
				String stt=txtname2.getText();
				int count=0;
				rs.first();
			
				while(rs.next())
				{
					if(rs.getString(1).equals(stt))
					{	
	 					count=1;
						break;
					}
				}
			              
			          if(count==0)
			          {		
				String t1=""+txtname2.getText();
				String t2=""+txtkey2.getText();
				String rb=""+2;
				
				String sql="insert into keys values('"+t1+"','"+t2+"','"+rb+"')";
				Statement st1=con.createStatement();
				int cnt=st1.executeUpdate(sql);

				if(cnt>0)
				{
		         		              JOptionPane.showMessageDialog(this,"Save Changes");
				}
				else
				{
 		         		              JOptionPane.showMessageDialog(this,"Not Save");		
   				 }
 				 counter=0;
			              }
			              else
			              {
		         		              JOptionPane.showMessageDialog(this,"Record already exists");		
		                              }

			         }catch(Exception ex)
			         {
				JOptionPane.showMessageDialog(this,ex);
			          }								

			}
			else
			{
				txtname2.setText("");
				txtkey2.setText("");
				r1.setSelected(true);
				r2.setEnabled(false);
				btnnew2.setText("Save");					
				txtname2.requestFocus();
				counter=counter+1;

			 } 			
	
		}

		if(ae.getSource()==btndel2)
		{
			try
			     {
				String t1=""+txtname2.getText();
				
				//Statement st=con.createStatement();
		                           	String sql="delete from keys where uname='"+t1+"'";
				int cnt=st.executeUpdate(sql);

				if(cnt>0)
				{
		         		              JOptionPane.showMessageDialog(this,"Delete Successfully");
				}
				else
				{
		         		              JOptionPane.showMessageDialog(this,"Not Delete");		
				}
				
			      }catch(Exception ex)
			       {
				JOptionPane.showMessageDialog(this,ex);
			       }								


		}
		if(ae.getSource()==btnsave2)
		{
		
			   try
			     {
				String t1=""+txtname2.getText();
				String t2=""+txtkey2.getText();
				String rb;
				if(r1.isSelected())
					rb=""+2;
				else	
					rb=""+3;
				
				//Statement st=con.createStatement();
		                           	String sql="update keys set key='"+t2+"', uname='"+t1+"',status='"+rb+"' where uname='"+t1+"'";
				int cnt=st.executeUpdate(sql);

				if(cnt>0)
				{
		         		              JOptionPane.showMessageDialog(this,"Save Changes");
				}
				else
				{
		         		              JOptionPane.showMessageDialog(this,"Not Save");		
				}
				
			      }catch(Exception ex)
			       {
				JOptionPane.showMessageDialog(this,ex);
			       }								

			
		}
		
		if(ae.getSource()==btnnew3)
		{
			
			if(counter>0)
			{
			   try
			     {
				String stt=txtname3.getText();
				int count=0;
				rs.first();
			
				while(rs.next())
				{
					if(rs.getString(1).equals(stt))
					{	
	 					count=1;
						break;
					}
				}
			              
			          if(count==0)
			          {		
				String t1=""+txtname3.getText();
				String t2=""+txtkey3.getText();
				String rb=""+3;
				
				String sql="insert into keys values('"+t1+"','"+t2+"','"+rb+"')";
				Statement st1=con.createStatement();
				int cnt=st1.executeUpdate(sql);

				if(cnt>0)
				{
		         		              JOptionPane.showMessageDialog(this,"Save Changes");
				}
				else
				{
 		         		              JOptionPane.showMessageDialog(this,"Not Save");		
   				 }
 				 counter=0;
			              }
			              else
			              {
		         		              JOptionPane.showMessageDialog(this,"Record already exists");		
		                              }

			         }catch(Exception ex)
			         {
				JOptionPane.showMessageDialog(this,ex);
			          }								

			}
			else
			{
				txtname3.setText("");
				txtkey3.setText("");
				r3.setEnabled(false);
				r4.setSelected(true);
				btnnew3.setText("Save");					
				txtname3.requestFocus();
				counter=counter+1;

			 } 			
	
		}

		if(ae.getSource()==btndel3)
		{
			try
			     {
				String t1=""+txtname3.getText();
				
				//Statement st=con.createStatement();
		                           	String sql="delete from keys where uname='"+t1+"'";
				int cnt=st.executeUpdate(sql);

				if(cnt>0)
				{
		         		              JOptionPane.showMessageDialog(this,"Delete Successfully");
				}
				else
				{
		         		              JOptionPane.showMessageDialog(this,"Not Delete");		
				}
				
			      }catch(Exception ex)
			       {
				JOptionPane.showMessageDialog(this,ex);
			       }								


		}
		if(ae.getSource()==btnsave3)
		{
		
			   try
			     {
				String t1=""+txtname3.getText();
				String t2=""+txtkey3.getText();
				String rb;
				if(r3.isSelected())
					rb=""+2;
				else	
					rb=""+3;
				
				//Statement st=con.createStatement();
		                           	String sql="update keys set key='"+t2+"', uname='"+t1+"',status='"+rb+"' where uname='"+t1+"'";
				int cnt=st.executeUpdate(sql);

				if(cnt>0)
				{
		         		              JOptionPane.showMessageDialog(this,"Save Changes");
				}
				else
				{
		         		              JOptionPane.showMessageDialog(this,"Not Save");		
				}
				
			      }catch(Exception ex)
			       {
				JOptionPane.showMessageDialog(this,ex);
			       }								

			
		}		

	}


public void itemStateChanged(ItemEvent ie)
{
		if(ie.getSource()==cmbsearch)
		{ 
	 	      try
		     {
 			
			String stt=(String)cmbsearch.getSelectedItem();
			int count=0;
			rs.first();
			
			while(rs.next())
			{
			
				int status=Integer.parseInt(rs.getString(3));
				if(rs.getString(1).equals(stt))
				{	
	 				txtname2.setText(rs.getString(1));
					txtkey2.setText(rs.getString(2));
					r1.setSelected(true);
					break;
				}
			}
				
			
		  }catch(Exception ex)
		 {
			JOptionPane.showMessageDialog(this,ex);
		 }

		}

		if(ie.getSource()==cmbsearch1)
		{ 
	 	      try
		     {
 			
			String stt=(String)cmbsearch1.getSelectedItem();
			int count=0;
			rs.first();
			
			while(rs.next())
			{
				int status=Integer.parseInt(rs.getString(3));
				if(rs.getString(1).equals(stt))
				{	
					txtname3.setText(rs.getString(1));
					txtkey3.setText(rs.getString(2));
					r4.setSelected(true);
					break;
				}
			}
				
			
		  }catch(Exception ex)
		 {
			JOptionPane.showMessageDialog(this,ex);
		 }

		}

}

}



