import java.text.SimpleDateFormat;
import java.util.Date;


public class Investment {
	static private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/mm/dd");
	static private final String OUTPUT_FORMAT = "%s %s %d";
	private Date purchaseDate;
	private Date expireDate;
	private double capital;
	private double interestRate;
	private int date;
	private double dateInterest;
	private double expectedReturn;
	private double actualReturn;
	private String bank;
	
	public Investment() {
		
	}
	
	public String toString() {
		return String.format(OUTPUT_FORMAT, DATE_FORMAT.format(purchaseDate),bank,capital);
	}
}
