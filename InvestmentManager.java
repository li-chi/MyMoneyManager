import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

/*
 * @author LI Chi lichi321@gmail.com
 * MyMoneyManager, a gift to my mom
 */

public class InvestmentManager {
	LinkedList<Investment> investmentList;
	static private InvestmentManager instance;
	private FormatWrapper wrapper;
	private final double DAYS_IN_YEAR = 365.0f;
	private FileManager fm;
	
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
	public void init() {
		this.fm = new FileManager();
		this.investmentList = fm.loadFile();
		if (this.investmentList == null) {
			this.investmentList = new LinkedList<Investment>();
		}
		/*
		this.investmentList.add(new Investment(new Date(),new Date(),5000,0.0311,60,50.00,300,"ICBC"));
		this.investmentList.add(new Investment(new Date(),new Date(),10000,0.1,100,40.22,1001,"HSBC"));
		this.investmentList.add(new Investment(new Date(),new Date(),300000,0.123,354,354.32,10010,"HSBC"));
		*/
		//fm.saveFile(this.investmentList);
		
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
		this.fm.saveFile(this.investmentList);
	}
	
	public void editInvestment(int num, Date purchase, String bank, double capital, double rate, 
			int period, double actual) {
		rate = rate / 100.0f;
		double daily = capital * rate / DAYS_IN_YEAR;
		double expected = daily * period;
		Calendar c = Calendar.getInstance();
		c.setTime(purchase);
		c.add(Calendar.DATE, period);
		Date expire = c.getTime();
		investmentList.get(num).setActualReturn(actual);
		investmentList.get(num).setBank(bank);
		investmentList.get(num).setCapital(capital);
		investmentList.get(num).setDailyInterest(daily);
		investmentList.get(num).setExpectedReturn(expected);
		investmentList.get(num).setExpireDate(expire);
		investmentList.get(num).setInterestRate(rate);
		investmentList.get(num).setPeriod(period);
		investmentList.get(num).setPurchaseDate(purchase);
		this.fm.saveFile(this.investmentList);
	}
	
	public void deleteInvestment(int num) {
		this.investmentList.remove(num);
		this.fm.saveFile(this.investmentList);
	}
	
	public String getAllInvestments() {
		Collections.sort(this.investmentList);
		double returns = 0;
		double daily = 0;
		String allInvestments = "";
		for(int i=0;i<this.investmentList.size();i++) {
			allInvestments = allInvestments + 
					this.wrapper.wrapInvestment(i,investmentList.get(i)) + "\n";
			returns += this.investmentList.get(i).getActualReturn();
			if (this.investmentList.get(i).getExpireDate().after(new Date())) {
				daily += this.investmentList.get(i).getDailyInterest();
			}
		}
		allInvestments += "Total Actual Return: " + String.format("%.2f", returns) + "\n";
		allInvestments += "Today's Return: " + String.format("%.2f", daily);
		return allInvestments;
	}
	
	public int getNumOfInvestments() {
		return investmentList.size();
	}
	
	public Investment getInvestment(int num) {
		return investmentList.get(num);
	}
	
	public void saveBackup() {
		BackupManager bm = new BackupManager();
		LinkedList<Investment> backup = bm.loadFile();
		if (backup == null) {
			bm.saveFile(this.investmentList);
		} else {
			if (backup.size()/2 < this.investmentList.size()) {
				bm.saveFile(this.investmentList);
			}
		}
	}

}
