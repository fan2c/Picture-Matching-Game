import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;


public class game {
	public static Border unSelected = BorderFactory.createLineBorder(Color.gray, 1); // not selected style
    public static Border Selected = BorderFactory.createLineBorder(Color.red, 1); // selected style
    
	public static void main(String[] args){
		JFrame fr = new frame();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
		fr.setTitle("사천성");
		fr.setResizable(false); 
		
		
		
	}

}
