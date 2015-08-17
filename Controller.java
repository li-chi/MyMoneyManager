import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @author LI Chi lichi321@gmail.com
 * MyMoneyManager, a gift to my mom
 */

public class Controller {
	static private Controller controller;
	private InvestmentManager investmentManager;
	private MainGUI ui;
	private FormatWrapper wrapper;
	private MessageType msgType;
	private String msg;
	
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	private final String ERROR_TYPE = "Error";
	private final String SUCCESS_TYPE = "Successful";
	private final String ADD_MSG = "New Investment has been added!";
	private final String EDIT_MSG = "The investment has been edited!";
	private final String DELETE_MSG = "The investment has been deleted!";
	private final String NO_DATE = "Please enter date (yyyy/mm/dd)";
	private final String NO_FROM = "Please enter From (yyyy/mm/dd)";
	private final String NO_TO = "Please enter To (yyyy/mm/dd)";
	private final String DATE_ERROR = "Unrecognized date (yyyy/mm/dd)";
	private final String NO_BANK = "Please enter bank name";
	private final String BANK_ERROR = "Bank name should < 5 characters";
	private final String NO_CAPITAL = "Please enter capital";
	private final String CAPITAL_ERROR = "Capital should be a number";
	private final String NO_RATE = "Please enter interest rate";
	private final String RATE_ERROR = "Interest rate should be a number";
	private final String NO_PERIOD = "Please enter period";
	private final String PERIOD_ERROR = "Period should be an integer";
	private final String ACTUAL_ERROR = "Actual return should be a number";
	private final String NO_EDIT = "Please enter edit number";
	private final String EDIT_ERROR = "Edit Number should an integer(>0)";
	
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
		this.investmentManager.init();
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
	
	public boolean createNewInvestment(String date_, String bank_, String capital_, 
			String rate_, String period_) {
		
		msgType = MessageType.SUCCESS;
		msg = "";
		Date date = null;
		double capital = 0,rate = 0;
		int period = 0;
		
		if (date_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_DATE + "<br/>";
		} else {
			try {
				date = DATE_FORMAT.parse(date_);
			} catch (ParseException e) {
				msgType = MessageType.ERROR;
				msg = msg + DATE_ERROR + "<br/>";
			}
		}
		
		if (bank_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_BANK + "<br/>";
		} else {
			if (bank_.length()>4) {
				msgType = MessageType.ERROR;
				msg = msg + BANK_ERROR + "<br/>";
			}
		}
	
		if (capital_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_CAPITAL + "<br/>";
		} else {
			try {
				capital = Double.parseDouble(capital_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + CAPITAL_ERROR + "<br/>";
			}
		}
		
		if (rate_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_RATE + "<br/>";
		} else {
			try {
				rate = Double.parseDouble(rate_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + RATE_ERROR + "<br/>";
			}
		}
		
		if (period_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_PERIOD + "<br/>";
		} else {
			try {
				period = Integer.parseInt(period_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + PERIOD_ERROR + "<br/>";
			}
		}
		
		if (msgType == MessageType.SUCCESS) {
			this.investmentManager.addInvestment(date,bank_,capital,rate,period);
			this.renew();
			new PopupWindow(SUCCESS_TYPE,ADD_MSG);
			return true;
		} else {
			new PopupWindow(ERROR_TYPE,msg);
			return false;
		}
	}
	
	public void createEditWindow(String numStr) {
		int num = 0;
		msgType = MessageType.SUCCESS;
		msg = "";
		
		if (numStr.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_EDIT + "<br/>";
		} else {
			try {
				num = Integer.parseInt(numStr);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + EDIT_ERROR + "<br/>";
			}
		}
		
		if (num < 1) {
			msgType = MessageType.ERROR;
			msg = msg + EDIT_ERROR + "<br/>";
		}
		
		if (msgType == MessageType.SUCCESS) {
			
			EditUI.getInstance(this,this.wrapper.getEditString
					(this.investmentManager.getInvestment(num-1)),num);
		} else {
			new PopupWindow(ERROR_TYPE,msg);
		}
	}
	
	public boolean editInvestment(int num, String date_, String bank_, String capital_, 
			String rate_, String period_, String actual_) {
		msgType = MessageType.SUCCESS;
		msg = "";
		Date date = null;
		double capital = 0,rate = 0, actual = 0;
		int period = 0;
		
		if (date_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_DATE + "<br/>";
		} else {
			try {
				date = DATE_FORMAT.parse(date_);
			} catch (ParseException e) {
				msgType = MessageType.ERROR;
				msg = msg + DATE_ERROR + "<br/>";
			}
		}
		
		if (bank_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_BANK + "<br/>";
		} else {
			if (bank_.length()>4) {
				msgType = MessageType.ERROR;
				msg = msg + BANK_ERROR + "<br/>";
			}
		}
	
		if (capital_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_CAPITAL + "<br/>";
		} else {
			try {
				capital = Double.parseDouble(capital_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + CAPITAL_ERROR + "<br/>";
			}
		}
		
		if (rate_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_RATE + "<br/>";
		} else {
			try {
				rate = Double.parseDouble(rate_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + RATE_ERROR + "<br/>";
			}
		}
		
		if (period_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_PERIOD + "<br/>";
		} else {
			try {
				period = Integer.parseInt(period_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + PERIOD_ERROR + "<br/>";
			}
		}
		
		if (actual_.equals("")) {
			actual = 0.0;
		} else {
			try {
				actual = Double.parseDouble(actual_);
			} catch (NumberFormatException e) {
				msgType = MessageType.ERROR;
				msg = msg + ACTUAL_ERROR + "<br/>";
			}
		}
		
		if (msgType == MessageType.SUCCESS) {
			this.investmentManager.editInvestment(num-1,date,bank_,capital,rate,period,actual);
			this.renew();
			new PopupWindow(SUCCESS_TYPE,EDIT_MSG);
			return true;
		} else {
			new PopupWindow(ERROR_TYPE,msg);
			return false;
		}
	}
	
	public void deleteInvestment(int num) {
		this.investmentManager.deleteInvestment(num-1);
		this.renew();
		new PopupWindow(SUCCESS_TYPE,DELETE_MSG);
	}
	
	public void exit() {
		this.investmentManager.saveBackup();
		System.exit(0);
	}
	
	public void displayAll() {
		this.renew();
	}
	
	public void search(String from_, String to_) {
		Date from = null;
		Date to = null;
		String msg = "";
		MessageType msgType = MessageType.SUCCESS;
		
		if (from_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_FROM + "<br/>";
		} else {
			try {
				from = DATE_FORMAT.parse(from_);
			} catch (ParseException e) {
				msgType = MessageType.ERROR;
				msg = msg + DATE_ERROR + "<br/>";
			}
		}
		
		if (to_.equals("")) {
			msgType = MessageType.ERROR;
			msg = msg + NO_TO + "<br/>";
		} else {
			try {
				to = DATE_FORMAT.parse(to_);
			} catch (ParseException e) {
				msgType = MessageType.ERROR;
				msg = msg + DATE_ERROR + "<br/>";
			}
		}
		
		if (msgType == MessageType.SUCCESS) {
			ui.clear();
			ui.display(this.wrapper.getTitle());
			ui.display(this.investmentManager.searchByDate(from,to));
		} else {
			new PopupWindow(ERROR_TYPE,msg);
			
		}
	}
	
	public static void main(String []args) {
		controller = Controller.getInstance();
		controller.run();
	}
}
