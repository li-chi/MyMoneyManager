import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PopupWindow {
	
	Frame frame;
	
	public PopupWindow(String type, String msg) {
		frame = new Frame(type);
		frame.addWindowListener(new WindowAdapter(){
		      public void windowClosing(WindowEvent e){ 
		    	  frame.setVisible(false);
		    	  frame.dispose();
		      }
		    });
		Label label = new Label(msg);
		if (type.equals("Successful")) {
			label.setForeground(Color.BLUE);
		} else {
			label.setForeground(Color.RED);
		}
		
		Button ok = new Button("OK");
		frame.add(label);
		frame.add(ok);
		frame.pack();
		frame.setSize(250, 150);
		frame.setLayout(new GridLayout(2,1));
		frame.setVisible(true);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
}
