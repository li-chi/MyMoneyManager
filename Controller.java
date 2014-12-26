
public class Controller {
	static private Controller controller;
	private InvestmentManager investmentManager;
	private MainGUI ui;
	private FormatWrapper wrapper;
	
	private Controller() {
		this.ui = MainGUI.getInstance(this);
		this.wrapper = FormatWrapper.getInstance();
		this.investmentManager = InvestmentManager.getInstance(this.wrapper);
	}
	
	public static Controller getInstance() {
	      if(controller == null) {
	         controller = new Controller();
	      }
	      return controller;
	}
	
	void run() {
		System.out.println(this.wrapper.getTitle());
		this.investmentManager.addInvestment();
		System.out.println(this.investmentManager.getAllInvestments());
	}
	
	public static void main(String []args) {
		controller = Controller.getInstance();
		controller.run();
	}
}
