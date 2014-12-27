import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/*
 * @author LI Chi lichi321@gmail.com
 * MyMoneyManager, a gift to my mom
 */

public class CreateUI {
	private ArrayList<TextField> fieldList;
	static private CreateUI instance;
	private Controller controller;
	private final String[] labelNames = {"Purchase Date(yyyy/mm/dd)","Bank Name(<5 characters)",
			"Capital(RMB)","Interest Rate(%)","Period(days)"};
	Frame frame;
	
	private CreateUI(Controller controller_) {
		this.controller = controller_;
		this.fieldList = new ArrayList<TextField>();
		frame = new Frame("New Investment");
		frame.setSize(400, 150);
		frame.setLayout(new GridLayout(labelNames.length+1,2));
		frame.setVisible(true);
		for(int i=0;i<labelNames.length;i++) {
			frame.add(new Label(labelNames[i]));
			fieldList.add(new TextField());
			frame.add(fieldList.get(i));
		}
		Button add = new Button("Add");
		Button cancel = new Button("Cancel");
		frame.add(add);
		frame.add(cancel);
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(controller.createNewInvestment(fieldList.get(0).getText(),fieldList.get(1).getText(),
						fieldList.get(2).getText(),fieldList.get(3).getText(),fieldList.get(4).getText())) {
					close();
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		frame.addWindowListener(new WindowAdapter(){
		      public void windowClosing(WindowEvent e){ 
		    	  close();
		      }
		    });
	}
	
	void close() {
		instance = null;
		frame.setVisible(false);
		frame.dispose();
	}
	
	public static CreateUI getInstance(Controller controller_) {
	      if(instance == null) {
	         instance = new CreateUI(controller_);
	      }
	      return instance;
	}
	
	
}
