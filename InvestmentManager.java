import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;


public class InvestmentManager {
	LinkedList<Investment> investmentList;
	static private InvestmentManager instance;
	private FormatWrapper wrapper;
	private final double DAYS_IN_YEAR = 365.0f;
	
	private InvestmentManager(FormatWrapper formatWrapper_) {
		this.investmentList = new LinkedList<Investment>();
		this.wrapper = formatWrapper_;
	}
	
	public static InvestmentManager getInstance(FormatWrapper formatWrapper_) {
	      if(instance == null) {
	         instance = new InvestmentManager(formatWrapper_);
	      }
	      return instance;
	}
	
	//Date purchaseDate_, Date expireDate_, double capital_, double interestRate_,
	//int period_, double dailyInterest_, double expectedReturn_, double actualReturn_, String bank_
	public void addInvestment() {
		this.investmentList.add(new Investment(new Date(),new Date(),5000,0.0311,60,50.00,300,"ICBC"));
		this.investmentList.add(new Investment(new Date(),new Date(),10000,0.1,100,40.22,1001,"HSBC"));
		this.investmentList.add(new Investment(new Date(),new Date(),300000,0.123,354,354.32,10010,"HSBC"));
	}
	
	public void addInvestment(Date purchase, String bank, double capital, double rate, int period) {
		rate = rate / 100.0f;
		double daily = capital * rate / DAYS_IN_YEAR;
		double expected = daily * period;
		Calendar c = Calendar.getInstance();
		c.setTime(purchase);
		c.add(Calendar.DATE, period);
		Date expire = c.getTime();
		this.investmentList.add(new Investment(purchase,expire,capital,rate,period,daily,expected,bank));

	}
	
	public String getAllInvestments() {
		String allInvestments = "";
		for(int i=0;i<this.investmentList.size();i++) {
			allInvestments = allInvestments + 
					this.wrapper.wrapInvestment(i,investmentList.get(i)) + "\n";
		}
		return allInvestments;
	}
}
