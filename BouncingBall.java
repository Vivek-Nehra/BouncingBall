import java.awt.*;
import java.awt.event.*;


class WinListener implements WindowListener{
	public void windowClosing(WindowEvent e){
		Frame fobj = (Frame) e.getSource();
		System.out.println("Closing window");
		fobj.dispose();
		}

	public void windowClosed(WindowEvent e)	{}
	public void windowOpened(WindowEvent e)	{}
	public void windowActivated(WindowEvent e)	{}
	public void windowDeactivated(WindowEvent e)	{}
	public void windowDeiconified(WindowEvent e)	{}
	public void windowIconified(WindowEvent e)	{}
	}

class BouncingBall extends Frame implements Runnable,ActionListener,MouseListener{
	Button red,blue,yellow,green,button_color;
	String screen_color;
	Thread tobj;
	Label lbl;
	Color cobj[]={Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW};

	BouncingBall(){
		setLayout(new FlowLayout());
		red = new Button("Red");
		blue = new Button("Blue");
		yellow = new Button("Yellow");
		green = new Button("Green");
		lbl = new Label("----------------------");


		red.addActionListener(this);			addMouseListener(this);
		blue.addActionListener(this);			addMouseListener(this);
		yellow.addActionListener(this);			addMouseListener(this);
		green.addActionListener(this);			addMouseListener(this);
		

		this.addWindowListener( new WinListener());
		
		add(red);	add(blue);
		add(green);	add(yellow);
		lbl.setBackground(Color.WHITE);		add(lbl);	button_color=null;

		setSize(500,500);
		setVisible(true);

		tobj = new Thread(this);
		tobj.setDaemon(true);
		tobj.start();

		
		}


	public void actionPerformed(ActionEvent e){
		button_color = (Button) e.getSource();
		lbl.setText(button_color.getLabel());
		}


	public void mouseReleased(MouseEvent e){
		//System.out.println("Mouse released");
		}
	public void mouseClicked(MouseEvent e){
		//System.out.println("Mouse clicked");
		}
	public void mouseExited(MouseEvent e){
		//System.out.println("Mouse exited");
		}
	public void mouseEntered(MouseEvent e){
		//System.out.println("Mouse entered");
		}
	public void mousePressed(MouseEvent e){
		if ( button_color!=null && button_color.getLabel().equals(screen_color)){
			lbl.setText("Wins");
			System.out.println(screen_color + " Wins!!!");
			//System.out.println(Thread.currentThread());
			//tobj.interrupt();
			try{	Thread.sleep(2000);	}	catch(InterruptedException excep)	{}
			lbl.setText("Starting Again");
			try{	Thread.sleep(2000);	}	catch(InterruptedException excep)	{}
			lbl.setText("-----------");
			screen_color=null;
			button_color=null;
			}
		}

	public void paint(Graphics g){
		//System.out.println(Thread.currentThread());
		java.util.Random rndm = new java.util.Random();
		g.setColor(cobj[rndm.nextInt(4)]);
		int value_x = rndm.nextInt(400);
		int value_y = rndm.nextInt(400);
		g.fillOval(value_x,value_y,30,30);
		g.fillOval(value_x+40,value_y,30,30);


		Color current_color = g.getColor();
		if (current_color == Color.RED)		screen_color = "Red";
		else if (current_color == Color.BLUE)	screen_color = "Blue";
		else if (current_color == Color.GREEN)	screen_color = "Green";
		else if (current_color == Color.YELLOW)	screen_color = "Yellow";
		}
		


	public void run(){
		while(true){
			try{	Thread.sleep(500);	}	catch(InterruptedException excp)	{return;}
			repaint();
			}
			
		
}

	public static void main(String args[]){
		new BouncingBall();	
		}
	}
	
