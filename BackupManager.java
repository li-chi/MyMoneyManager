import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
 * @author LI Chi lichi321@gmail.com
 * MyMoneyManager, a gift to my mom
 */

public class BackupManager {
	
	Gson gson;
	Type type; 
	public BackupManager() {
		this.gson = new Gson();
		this.type = new TypeToken<LinkedList<Investment>>(){}.getType();
	}
	
	public void saveFile(LinkedList<Investment> ins) {
		gson = new Gson();
		String gsonStr = gson.toJson(ins);
		try {
			FileWriter writer = new FileWriter("investment_data_backup.json");
			writer.write(gsonStr);
			writer.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<Investment> loadFile() {
		LinkedList<Investment> ins = null;
		try {
			BufferedReader br = new BufferedReader(
				new FileReader("investment_data_backup.json"));
			ins = gson.fromJson(br,type);
		} catch (IOException e) {
		}
		return ins;
	}
}
