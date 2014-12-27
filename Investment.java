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
			int period_, double dailyInterest_, double expectedReturn_, String bank_) {
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
	
	public void setPurchaseDate(Date purchase) {this.purchaseDate = purchase;}
	public void setExpireDate(Date expire) {this.expireDate = expire;}
	public void setCapital(double capital_) {this.capital = capital_;}
	public void setInterestRate(double interest) {this.interestRate = interest;}
	public void setPeriod(int period_) {this.period = period_;}
	public void setDailyInterest(double daily) {this.dailyInterest = daily;}
	public void setExpectedReturn(double expect) {this.expectedReturn = expect;}
	public void setActualReturn(double actual) {this.actualReturn = actual;}
	public void setBank(String bank_) {this.bank = bank_;}
}
