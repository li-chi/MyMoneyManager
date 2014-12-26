import java.awt.Button;
import java.awt.Frame;
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
		      public void windowClosed(WindowEvent e){ System.exit(0); }
		      public void windowClosing(WindowEvent e){ 
		    	  frame.setVisible(false);
		    	  frame.dispose();
		      }
		    });
		Label label = new Label(msg);
		Button ok = new Button("OK");
		frame.add(label);
		frame.add(ok);
		frame.setSize(100, 100);
		frame.setVisible(true);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
