import java.text.NumberFormat;
import java.text.SimpleDateFormat;


public class FormatWrapper {
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	private NumberFormat PERCENT_FORMAT = NumberFormat.getPercentInstance();
	
	private final String NO = "No";
	private final String PURCHASE = "Purchase Date";
	private final String BANK = "Bank";
	private final String CAPITAL = "Capital";
	private final String INTEREST = "Rate";
	private final String PERIOD = "Period";
	private final String DAILY = "Daily";
	private final String EXPIRE = "Due Date";
	private final String EXPECT = "Expected Return";
	private final String ACTUAL = "Actual Return";
	
	private String TITLE = String.format(
			"%-4s" 	//No
		+ "%-15s" 	//purchase date
		+ "%-6s"	//bank name
		+ "%-11s"	//capital
		+ "%-8s"	//interest rate
		+ "%-8s"	//period
		+ "%-8s"	//daily return
		+ "%-12s" 	//expire date
		+ "%-16s"	//expected return
		+ "%-15s",	//actual return
		NO,PURCHASE,BANK,CAPITAL,INTEREST,PERIOD,DAILY,EXPIRE,EXPECT,ACTUAL);
	private final String INVESTMENT_FORMAT = 
			"%-4d"
			+ "%-15s"		
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
	
	public String wrapInvestment(int num, Investment investment) {
		return String.format(INVESTMENT_FORMAT, 
				num+1,
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
	
	public String[] getEditString(Investment investment) {
		String []str = new String[6];
		str[0] = this.DATE_FORMAT.format(investment.getPurchaseDate());
		str[1] = investment.getBank();
		str[2] = String.format("%.2f", investment.getCapital());
		str[3] = String.format("%.2f", investment.getInterestRate()*100.0);
		str[4] = String.format("%d", investment.getPeriod());
		str[5] = String.format("%.2f", investment.getActualReturn());
		return str;
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
