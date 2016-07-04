/*
 * the game window
 * 
 */
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class gameWin extends JPanel {
	private JButton dots[];
	public static ImageIcon[] BlocksIcon = new ImageIcon[39];
	private map gamemap;
	private boolean isSelected = false;
	private Point lastPoint = new Point(0, 0);
	private ActionListener listener;
	
	public gameWin()
	{
	
		createPanel();
		dots = new JButton[170];
		listener = new buttonAction(); 
		
		for(int i=0;i<dots.length;i++){
			dots[i] = new JButton();
			//dots[i].setBorder(null);
			dots[i].setActionCommand("" + i);   //actioncommand use for find button's point by pressing button
			add(dots[i]);
			dots[i].addActionListener(listener);   //for buttons add actionlistener
		}
		loadIcons();
		
		//map map = new map();
		//map.getMap();
		//paint();
		
	}
	
	/*create a panel for game*/
	public void createPanel()
	{
		setBorder(new TitledBorder(new EtchedBorder(),"GAME")); // set panel's border
		setLayout(new GridLayout(10,17,2,2));
		
	}
	
	/*save image in a list */
	private void loadIcons()  
	{         
		try{
			for (int i = 0; i < 39; i++) 
			     BlocksIcon[i] =  new ImageIcon("images/"+i+".gif");
			}
		catch(Exception e){}
	}
	
	/* draw image in the panel by using map*/
	public void paint() 
	{                 
		for (int row = 0; row < 10; row++) 
			for (int col = 0; col < 17; col++) 
			{
				int index = row * 17 + col;
		        
		        if (gamemap.getMap()[row][col] > 0) 
		        {
		          dots[index].setIcon(BlocksIcon[gamemap.getMap()[row][col] - 1]);
		          dots[index].setEnabled(true);
		        }
		        else {
		          dots[index].setIcon(null);
		          dots[index].setEnabled(false);
		        }
		    }
	}
	
	/* clean two point*/
	void cleanBlock(Point a, Point b) {   
	    //paint();

	    int offset;
	    offset = a.x * 17 + a.y;
	    dots[offset].setIcon(null);
	    dots[offset].setEnabled(false);

	    offset = b.x * 17 + b.y;
	    dots[offset].setIcon(null);
	    dots[offset].setEnabled(false);

	    
	  }
	
	public void start(int i) 
	{
		if (i==1)
		{
			gamemap = new map(6);
			gamemap.createArray();
			gamemap.setMap(1);
			gamemap.initMap();
		//isPlaying = true;
	    //limitTime = map.getCount() * Setting.limitScore;
			paint();
		}
		else if (i==2)
		{
			gamemap = new map(10);
			gamemap.createArray();
			gamemap.setMap(3);
			gamemap.initMap();
		//isPlaying = true;
	    //limitTime = map.getCount() * Setting.limitScore;
			paint();
		}
		if (i==3)
		{
			gamemap = new map(21);
			gamemap.createArray();
			gamemap.setMap(2);
			gamemap.initMap();
		//isPlaying = true;
	    //limitTime = map.getCount() * Setting.limitScore;
			paint();
		}
		
	}
	
	/* for stop game */
	public void stop()
	{
		paint();
	}
	
	/* the actionlistener for buttons and to sava the fist button's point and second button's point */
	class buttonAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			int offset = Integer.parseInt(button.getActionCommand());
			int row, col;
			row = Math.round(offset / 17);
			col = offset - row * 17;
			if (map.getMap()[row][col] < 1) { 
				return;
			}
	   
			if (isSelected) {  //if first button's point have saved,then judge the second button weather same with first button 
				System.out.println("exist a point");     //if not same button ,save as second button
				System.out.println("last point:  " + lastPoint.x + ", " + lastPoint.y +	" value: " + map.getMap()[lastPoint.x][lastPoint.y]);

				if (lastPoint.x == row && lastPoint.y == col) { //last point
	        
					System.out.println("the same point ,cancel the selected state ");
					button.setBorder(game.unSelected);
					isSelected = false;
				}
				else {    //judge two button can or not remove
					System.out.println("not same point");
					Point current = new Point(row, col);
					System.out.println(" point:  " + row + ", " + col + " value:  " + map.getMap()[row][col] );
					//map.setTest(false);
					if (map.test(lastPoint, current)) {        //the case two button can remove
						System.out.println("two point can clean");
	            
						dots[row * 17 + col].setBorder(game.unSelected);

						map.cleanBlock(current, lastPoint);
						cleanBlock(current, lastPoint);
						dots[lastPoint.x * 17 + lastPoint.y].setBorder(game.unSelected);
						lastPoint = new Point(0, 0);
						isSelected = false;

	           
					}
					else {      //the case two button can not remove
						System.out.println("two point can not clean");
						dots[lastPoint.x * 17 + lastPoint.y].setBorder(game.unSelected);
						button.setBorder(game.Selected);
						lastPoint.x = row;
						lastPoint.y = col;
						isSelected = true;

	            
					}
				}}
			else {          //the first button is not saved,so save as first button,then set "isSelected" is true
				System.out.println("no saved point ,save this");
	        	System.out.println("current point:  " + row + ", " + col + "value: " + map.getMap()[row][col]);
	        	button.setBorder(game.Selected);
	            lastPoint.x = row;
	            lastPoint.y = col;
	            isSelected = true;
	          }
	    
	}
	    
	}
	
}
