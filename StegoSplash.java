import java.awt.*;
import javax.swing.*;
import main.MainStego;

public class StegoSplash extends JWindow
{
			
	public StegoSplash()
    	{
	JWindow frame = new JWindow(this);
	//final JFrame frame;
	final JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16;
		try	
		{
		JLabel background=new JLabel(new ImageIcon("stegoback.png"));
		frame.add(background);
		background.setLayout(new GridLayout(4,4));

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/3-frame.getSize().width/2, dim.height/3-frame.getSize().height/2);
	
		frame.setSize(400, 300);
  		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//frame.setLayout(new GridLayout(3,3));

		l1=new JLabel("");
		l1.setOpaque(true);
		l1.setBackground(Color.GRAY);
		l1.setVisible(false);

		l2=new JLabel("");
		l2.setOpaque(true);
		l2.setBackground(Color.GRAY);
		l2.setVisible(false);

		l3=new JLabel("");
		l3.setOpaque(true);
		l3.setBackground(Color.GRAY);
		l3.setVisible(false);

		l4=new JLabel("");
		l4.setOpaque(true);
		l4.setBackground(Color.GRAY);
		l4.setVisible(false);

		l5=new JLabel("");
		l5.setOpaque(true);
		l5.setBackground(Color.GRAY);
		l5.setVisible(false);

		l6=new JLabel("");
		l6.setOpaque(true);
		l6.setBackground(Color.GRAY);
		l6.setVisible(false);

		l7=new JLabel("");
		l7.setOpaque(true);
		l7.setBackground(Color.GRAY);
		l7.setVisible(false);

		l8=new JLabel("");
		l8.setOpaque(true);
		l8.setBackground(Color.GRAY);
		l8.setVisible(false);

		l9=new JLabel("");
		l9.setOpaque(true);
		l9.setBackground(Color.GRAY);
		l9.setVisible(false);

		l10=new JLabel("");
		l10.setOpaque(true);
		l10.setBackground(Color.GRAY);
		l10.setVisible(false);

		l11=new JLabel("");
		l11.setOpaque(true);
		l11.setBackground(Color.GRAY);
		l11.setVisible(false);

		l12=new JLabel("");
		l12.setOpaque(true);
		l12.setBackground(Color.GRAY);
		l12.setVisible(false);

		l13=new JLabel("");
		l13.setOpaque(true);
		l13.setBackground(Color.GRAY);
		l13.setVisible(false);

		l14=new JLabel("");
		l14.setOpaque(true);
		l14.setBackground(Color.GRAY);
		l14.setVisible(false);

		l15=new JLabel("");
		l15.setOpaque(true);
		l15.setBackground(Color.GRAY);
		l15.setVisible(false);

		l16=new JLabel("");
		l16.setOpaque(true);
		l16.setBackground(Color.GRAY);
		l16.setVisible(false);

		background.add(l1);
		background.add(l2);
		background.add(l3);
		background.add(l4);
		background.add(l5);
		background.add(l6);
		background.add(l7);
		background.add(l8);
		background.add(l9);
		background.add(l10);
		background.add(l11);
		background.add(l12);
		background.add(l13);
		background.add(l14);
		background.add(l15);
		background.add(l16);
		
       		Thread t = new Thread()
		{
           			public void run()
			{
               			for(int i=1;i<=20;i++)
				{
					String temp="l"+i;
                   				//System.out.println("User thread is running"+i);
					try{Thread.sleep(150);}catch(Exception ex){}
					if (i==5){l6.setVisible(true);}
					if (i==6){l15.setVisible(true);}
					if (i==7){l8.setVisible(true);}
					if (i==8){l3.setVisible(true);}
					if (i==9){l9.setVisible(true);}
					if (i==10){l2.setVisible(true);}
					if (i==11){l13.setVisible(true);}
					if (i==12){l10.setVisible(true);}
					if (i==13){l7.setVisible(true);}
					if (i==14){l12.setVisible(true);}
					if (i==15){l16.setVisible(true);}
					if (i==16){l1.setVisible(true);}
					if (i==17){l4.setVisible(true);}
					if (i==18){l14.setVisible(true);}
					if (i==19){l5.setVisible(true);}
					if (i==20){l11.setVisible(true);}
               			}
           			}
       		};
     
		t.start();
		Thread.sleep(3150);
		//System.out.println("terminating or closing java program");
		frame.setVisible(false);

		//despose();
	              MainStego ms=new MainStego();
	}catch(Exception ex)
	 {
		System.out.println(ex);
	 }
}

public static void main(String args[])
{
	StegoSplash jce=new StegoSplash();

}
}
