import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class frame extends JFrame{
	private static final int FRAME_WIDTH =800;
	private static final int FRAME_HEIGHT =500;
	private JPanel panelEmu,panelEmu1,panelEmu2;
	private JButton BtStart,BtReset,BtExit,BtWait;
	private JLabel label1,label2,label3,label4;
	private JComboBox mapCombo;
	private ActionListener listener;
	private boolean isplaying =false;
	public final gameWin gp = new gameWin();
	public frame(){
		
		createFrame();
		
		//final JPanel gPanel = new gameWin();  // create gameWin object
		//gPanel.setVisible(false);
		add(gp,BorderLayout.CENTER);
		add(panelEmu,BorderLayout.EAST);
		
		clock cl= new clock();
		final Thread thread = new Thread(cl);
		
		
		
		/*a ActionListener event for listener menu button */
		class ChoiceListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if(e.getSource() == BtStart){
					if(mapCombo.getSelectedIndex()==0){
						gp.start(1);
						label2.setText("BLOCK 24");
					
					}else if(mapCombo.getSelectedIndex()==1){
						gp.start(3);
						label2.setText("BLOCK 84");
					}else if(mapCombo.getSelectedIndex()==2){
						gp.start(2);
						label2.setText("BLOCK 40");
					}
					isplaying = true;
					
					thread.start();
				}else if(e.getSource()==BtWait){
					if(isplaying){
						gp.setVisible(false);
						BtWait.setText("Return");
						isplaying = false;
						
					}else {
						gp.setVisible(true);
						BtWait.setText("Wait");
						isplaying = true;
					}
					
				}else if(e.getSource()==BtReset){
					if(isplaying){
						if(mapCombo.getSelectedIndex()==0){
						
						gp.start(1);
						
						}else if(mapCombo.getSelectedIndex()==1){
							gp.start(3);
							
						}else if(mapCombo.getSelectedIndex()==2){
							gp.start(2);
							
						}
					   
					}
					
				}else if(e.getSource() == BtExit)
				{			
					if(JOptionPane.showConfirmDialog(panelEmu,"Are you sure?")==JOptionPane.YES_OPTION)
					{
						System.exit(0);
					}
				}
			}
		}
		
		listener = new ChoiceListener();  //create listener and add it
		BtExit.addActionListener(listener);
		BtStart.addActionListener(listener);
		BtWait.addActionListener(listener);
		BtReset.addActionListener(listener);
		mapCombo.addActionListener(listener);
		
		
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		
	}
	public void createFrame(){
		//panel = new JPanel();
		//panelWin = new JPanel();
		panelEmu = new JPanel();
		panelEmu1 = new JPanel();
		panelEmu2 = new JPanel();
		
		//panelWin.setBorder(new TitledBorder(new EtchedBorder(),"GAME")); // set panel's border
		panelEmu.setBorder(new TitledBorder(new EtchedBorder(),"EMNU"));
		panelEmu1.setBorder(new EtchedBorder());
		panelEmu2.setBorder(new EtchedBorder());
		//panelWin.setLayout(new GridLayout(10,17,2,2));
		panelEmu.setLayout(new GridLayout(0,1,0,1));
		panelEmu1.setLayout(new GridLayout(0,1,0,13));
		panelEmu2.setLayout(new GridLayout(0,1,0,18));
		
		BtStart = new JButton("START"); //menu button
		BtWait = new JButton("WAIT");
		BtReset = new JButton("RESET");
		BtExit = new JButton("EXIT");
		
		
		label1 = new JLabel();             //display clock
		label2 = new JLabel("level");
		label3 = new JLabel("By pxf version 1.0");
		label4 = new JLabel();             //no use
		label1.setFont(new java.awt.Font("Serif", 3, 20)); //set font

	    
	    String[] mapc = {"default","LOVE","HEART"};
 		mapCombo = new JComboBox(mapc);
 		mapCombo.setEditable(true);
  		
		
		panelEmu1.add(label1);
		panelEmu1.add(label2);
		panelEmu1.add(mapCombo);
		panelEmu1.add(label4);
		
		
		panelEmu2.add(BtStart);
		panelEmu2.add(BtWait);
		panelEmu2.add(BtReset);
		panelEmu2.add(BtExit);
		panelEmu2.add(label3);
		
		panelEmu.add(panelEmu1);
		panelEmu.add(panelEmu2);
			
		setLayout(new BorderLayout());
		
		
	}
	/* dispaly clock with use thread  */
	public class clock implements Runnable{
		public void run()
		{
    
			Calendar startCalendar = Calendar.getInstance();
			long startTime = startCalendar.getTime().getTime(); // get start time
			long endTime = startTime + 2*60 * 1000; // from start time + 2mins
			long nowTime, leftTime;
			Calendar now;
    
			while(isplaying)
			{
				
				now = Calendar.getInstance();
				nowTime = now.getTime().getTime();  //get current time
				leftTime = (endTime - nowTime) / 1000; 
     
        
        
				label1.setText(leftTime + "sec.");   
        
        
				if(leftTime == 0)
				{
					JOptionPane.showMessageDialog(panelEmu, "NO TIME,GAME OVER", "", JOptionPane.OK_OPTION);
					gp.setVisible(false);
					isplaying = false;
					//break;
				}
        
				try
				{
					Thread.sleep(1000);   // 1 second
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
}
