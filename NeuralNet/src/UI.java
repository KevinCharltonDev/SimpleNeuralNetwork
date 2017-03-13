import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UI {
	private JFrame mainFrame;
	private JPanel topPanel;
	private JPanel botPanel;
	public UI(){
	      prepareGUI();
	   }
	 
	private void prepareGUI(){
	      mainFrame = new JFrame("Java Swing Examples");
	      mainFrame.setSize(900,400);
	      mainFrame.setLayout(new GridLayout(2,1));
	      mainFrame.setLocation(400, 200);
	      mainFrame.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	      });   
	      topPanel = new JPanel();
	      topPanel.setLayout(new FlowLayout());
	      botPanel = new JPanel();
	      botPanel.setLayout(new GridLayout(2,1));
	      new JLabel("Simple Neural Network", JLabel.CENTER);
	      mainFrame.setVisible(true);  
	      
	      mainFrame.add(topPanel);
	      mainFrame.add(botPanel);
	      
	   }
	
	public void update(){
	      mainFrame.setVisible(true);  
	   }
	
	public void topAdd(Component c){
		topPanel.add(c);
	}
	
	public void botAdd(Component c){
		botPanel.add(c);
	}
	
}
