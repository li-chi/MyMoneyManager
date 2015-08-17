import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
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
	private Button displayAll;
	private Button search;
	private Frame frame;
	private TextArea output;
	private TextField from;
	private TextField to;
	Controller controller;
	
	private MainGUI(Controller controller_) {
		this.controller = controller_;
		this.create = new Button("Create");
		this.edit = new Button("Edit");
		this.editNum = new TextField(5);
		this.exit = new Button("Exit");
		this.displayAll = new Button("Display All");
		this.search = new Button("Search");
		this.from = new TextField(10);
		this.to = new TextField(10);
		Panel panelUp = new Panel();
		Panel panel1 = new Panel();
		panel1.setSize(20, 100);
		panel1.add(create);
		Panel panel2 = new Panel();
		panel2.setSize(20, 300);
		panel2.add(editNum);
		panel2.add(edit);
		Panel panel3 = new Panel();
		panel3.setSize(20, 100);
		panel3.add(exit);
		Panel panel = new Panel();
		panel.setSize(40,800);
		panel.setLayout(new GridLayout(2,1));
		panelUp.add(panel1);
		panelUp.add(panel2);
		panelUp.add(panel3);
		panelUp.setLayout(new GridLayout(1,3));
		panelUp.setSize(20, 800);
		Panel panel4 = new Panel();
		panel4.setSize(20,800);
		panel4.add(new Label("From(yyyy/mm/dd)"));
		panel4.add(from);
		panel4.add(new Label("To(yyyy/mm/dd)"));
		panel4.add(to);
		panel4.add(search);
		panel4.add(displayAll);
		panel.add(panelUp);
		panel.add(panel4);
		
		
		this.frame = new Frame("My Money Manager");
		frame.addWindowListener(new WindowAdapter(){
		      public void windowClosing(WindowEvent e){ 
		    	  close();
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
				close();
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
		
		this.displayAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.displayAll();
			}
		});
		
		this.search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.search(from.getText(),to.getText());
			}
		});
	}
	
	void close() {
		instance = null;
		frame.setVisible(false);
		frame.dispose();
		controller.exit();
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
