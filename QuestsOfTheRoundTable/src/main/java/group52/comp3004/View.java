package group52.comp3004;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class View {
	
	//Constructor
	public View(Model model) {
		initView();
	}
	
	public void initView() {
		JFrame frame = new JFrame("Quests of the Round Table");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void render() {
		
	}
}
