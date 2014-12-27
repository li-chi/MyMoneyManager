import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
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

public class EditUI {
	private ArrayList<TextField> fieldList;
	static private EditUI instance;
	private Controller controller;
	private String []entries;
	private final String[] labelNames = {"Purchase Date(yyyy/mm/dd)","Bank Name(<5 characters)",
			"Capital(RMB)","Interest Rate(%)","Period(days)","Actual Return"};
	private Frame frame;
	private int num;
	
	private EditUI(Controller controller_, String []entries_, int num_) {
		this.controller = controller_;
		this.entries = entries_;
		this.num = num_;
		this.fieldList = new ArrayList<TextField>();
		frame = new Frame("Edit Investment No."+ num_);
		frame.setSize(650, 140);
		Panel panel = new Panel();
		panel.setSize(400, 120);
		panel.setLayout(new GridLayout(labelNames.length,2));
		frame.setVisible(true);
		for(int i=0;i<labelNames.length;i++) {
			panel.add(new Label(labelNames[i]));
			fieldList.add(new TextField());
			panel.add(fieldList.get(i));
			fieldList.get(i).setText(entries[i]);
		}
		
		Button confirm = new Button("Confirm");
		Button delete = new Button("Delete");
		Button cancel = new Button("Cancel");
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		Panel panel2 = new Panel();
		panel2.setSize(200, 120);
		panel2.setLayout(new GridLayout(3,1));
		frame.add(panel);
		panel2.add(confirm);
		panel2.add(delete);
		panel2.add(cancel);
		frame.add(panel2);
		frame.pack();
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controller.editInvestment(num, fieldList.get(0).getText(),fieldList.get(1).getText(),
						fieldList.get(2).getText(),fieldList.get(3).getText(),fieldList.get(4).getText(),
						fieldList.get(5).getText())) {
					close();
				}
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteInvestment(num);
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
	
	public static EditUI getInstance(Controller controller_, String []entries_, int num_) {
	      if(instance == null) {
	         instance = new EditUI(controller_, entries_, num_);
	      }
	      return instance;
	}
	
	
}
