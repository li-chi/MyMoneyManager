import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Controller {
	static private Controller controller;
	private InvestmentManager investmentManager;
	private MainGUI ui;
	private FormatWrapper wrapper;
	
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	private final String ERROR_TYPE = "Error";
	private final String SUCCESS_TYPE = "Successful";
	private final String SUCCESS_MSG = "New Investment has been added!";
	private final String NO_DATE = "Please enter date (yyyy/mm/dd)";
	private final String DATE_ERROR = "Unrecognized date (yyyy/mm/dd)";
	private final String NO_BANK = "Please enter bank name";
	private final String BANK_ERROR = "Bank name should < 5 characters";
	private final String NO_CAPITAL = "Please enter capital";
	private final String CAPITAL_ERROR = "Capital should be a number";
	private final String NO_RATE = "Please enter interest rate";
	private final String RATE_ERROR = "Interest rate should be a number";
	private final String NO_PERIOD = "Please enter period";
	private final String PERIOD_ERROR = "Period should be a number";
	
	private enum MessageType {
		SUCCESS,ERROR
	}
	
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
		this.renew();
	}
	
	public void renew() {
		ui.clear();
		ui.display(this.wrapper.getTitle());
		ui.display(this.investmentManager.getAllInvestments());
	}
	
	public void createInvestmentWindow() {
		CreateUI.getInstance(this);
	}
	
	public void createNewInvestment(String date_, String bank_, String capital_, 
			String rate_, String period_) {
		
		MessageType msgType = MessageType.SUCCESS;
		String msg = "";
		Date date = null;
		double capital = 0,rate = 0;
		int period = 0;
		
		if (date_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_DATE + "\n";
		} else {
			try {
				date = DATE_FORMAT.parse(date_);
			} catch (ParseException e) {
				msgType = MessageType.ERROR;
				msg = msg + DATE_ERROR + "\n";
			}
		}
		
		if (bank_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_BANK + "\n";
		} else {
			if (bank_.length()>4) {
				msgType = MessageType.ERROR;
				msg = msg + BANK_ERROR + "\n";
			}
		}
	
		if (capital_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_CAPITAL + "\n";
		} else {
			try {
				capital = Double.parseDouble(capital_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + CAPITAL_ERROR + "\n";
			}
		}
		
		if (rate_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_RATE + "\n";
		} else {
			try {
				rate = Double.parseDouble(rate_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + RATE_ERROR + "\n";
			}
		}
		
		if (period_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_PERIOD + "\n";
		} else {
			try {
				period = Integer.parseInt(period_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + PERIOD_ERROR + "\n";
			}
		}
		
		if (msgType == MessageType.SUCCESS) {
			new PopupWindow(SUCCESS_TYPE,SUCCESS_MSG);
			this.investmentManager.addInvestment(date,bank_,capital,rate,period);
			this.renew();
		} else {
			new PopupWindow(ERROR_TYPE,msg);
		}
	}
	
	public static void main(String []args) {
		controller = Controller.getInstance();
		controller.run();
	}
}
