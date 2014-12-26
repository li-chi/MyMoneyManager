import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Investment {
	private Date purchaseDate;
	private Date expireDate;
	private double capital;
	private double interestRate;
	private int period;
	private double dailyInterest;
	private double expectedReturn;
	private double actualReturn;
	private String bank;
	
	public Investment(Date purchaseDate_, Date expireDate_, double capital_, double interestRate_,
			int period_, double dailyInterest_, double expectedReturn_, double actualReturn_, String bank_) {
		this.purchaseDate = purchaseDate_;
		this.expireDate = expireDate_;
		this.capital = capital_;
		this.interestRate = interestRate_;
		this.period = period_;
		this.dailyInterest = dailyInterest_;
		this.expectedReturn = expectedReturn_;
		this.bank = bank_;
	}
	
	public Date getPurchaseDate() {return this.purchaseDate;}
	public Date getExpireDate() {return this.expireDate;}
	public double getCapital() {return this.capital;}
	public double getInterestRate() {return this.interestRate;}
	public int getPeriod() {return this.period;}
	public double getDailyInterest() {return this.dailyInterest;}
	public double getExpectedReturn() {return this.expectedReturn;}
	public double getActualReturn() {return this.actualReturn;}
	public String getBank() {return this.bank;}
	
}
