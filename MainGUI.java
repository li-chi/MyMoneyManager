import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
 * @author LI Chi lichi321@gmail.com
 * MyMoneyManager, a gift to my mom
 */

public class MainGUI {
	static private MainGUI instance;
	private Button create;
	private Button edit;
	private TextField editNum;
	private Button exit;
	private Frame frame;
	private TextArea output;
	Controller controller;
	
	private MainGUI(Controller controller_) {
		this.controller = controller_;
		this.create = new Button("Create");
		this.edit = new Button("Edit");
		this.editNum = new TextField(5);
		this.exit = new Button("Exit");
		
		Panel panel = new Panel();
		Panel panel1 = new Panel();
		panel1.setSize(100, 300);
		panel1.add(create);
		Panel panel2 = new Panel();
		panel2.setSize(100, 300);
		panel2.add(editNum);
		panel2.add(edit);
		Panel panel3 = new Panel();
		panel3.setSize(100, 100);
		panel3.add(exit);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.setLayout(new GridLayout(1,3));
		panel.setSize(100, 800);
		
		
		this.frame = new Frame("My Money Manager");
		frame.addWindowListener(new WindowAdapter(){
		      public void windowClosed(WindowEvent e){ System.exit(0); }
		      public void windowClosing(WindowEvent e){ 
		    	  frame.setVisible(false);
		    	  frame.dispose();
		      }
		    });
		frame.add(panel);
		output = new TextArea("",50,105,TextArea.SCROLLBARS_VERTICAL_ONLY);
		output.setFont(new Font("Monospaced", Font.BOLD, 12));
		frame.add(output);
		frame.setSize(800, 800);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		frame.setVisible(true);
		
		this.exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}});
		
		this.create.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.createInvestmentWindow();
			}
		});
		
		this.edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.createEditWindow(editNum.getText());
			}
		});
	}
	
	public void display(String msg) {
		output.append(msg+'\n');
	}
	
	public void clear() {
		output.setText("");
	}
	
	public static MainGUI getInstance(Controller controller_) {
	      if(instance == null) {
	         instance = new MainGUI(controller_);
	      }
	      return instance;
	}
}
