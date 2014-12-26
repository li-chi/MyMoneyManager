import java.text.NumberFormat;
import java.text.SimpleDateFormat;


public class FormatWrapper {
	static private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	static private NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance();
	
	static private final String PURCHASE = "Purchase Date";
	static private final String BANK = "Bank";
	static private final String CAPITAL = "Capital";
	static private final String INTEREST = "Rate";
	static private final String PERIOD = "Period";
	static private final String DAILY = "Daily";
	static private final String EXPIRE = "Due Date";
	static private final String EXPECT = "Expected Return";
	static private final String ACTUAL = "Actual Return";
	
	static private String TITLE = String.format(
		"%-15s" 	//purchase date
		+ "%-6s"	//bank name
		+ "%-11s"	//capital
		+ "%-8s"	//interest rate
		+ "%-8s"	//period
		+ "%-8s"	//daily return
		+ "%-12s" 	//expire date
		+ "%-16s"	//expected return
		+ "%-15s",	//actual return
		PURCHASE,BANK,CAPITAL,INTEREST,PERIOD,DAILY,EXPIRE,EXPECT,ACTUAL);
	static private final String INVESTMENT_FORMAT = 
			"%-15s"		
			+ "%-6s"
			+ "%-11.2f"
			+ "%-8s"
			+ "%-8d"
			+ "%-8.2f"
			+ "%-12s"
			+ "%-16.2f"
			+ "%-15.2f";
	public String getTitle() {
		return TITLE;
	}
	
	public String wrapInvestment(Investment investment) {
		return String.format(INVESTMENT_FORMAT, 
				this.DATE_FORMAT.format(investment.getPurchaseDate()),
				investment.getBank(),
				investment.getCapital(),
				this.PERCENT_FORMAT.format(investment.getInterestRate()),
				investment.getPeriod(),
				investment.getDailyInterest(),
				this.DATE_FORMAT.format(investment.getExpireDate()),
				investment.getExpectedReturn(),
				investment.getActualReturn()
				);
	}
	
	static private FormatWrapper instance;
	private FormatWrapper() {
		this.PERCENT_FORMAT.setMaximumFractionDigits(2);
		this.PERCENT_FORMAT.setMinimumFractionDigits(2);
		
	}
	
	public static FormatWrapper getInstance() {
	      if(instance == null) {
	         instance = new FormatWrapper();
	      }
	      return instance;
	}
}
