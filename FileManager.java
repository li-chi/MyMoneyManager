import java.io.BufferedReader;
import java.io.File;
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

public class FileManager {
	
	Gson gson;
	Type type; 
	public FileManager() {
		this.gson = new Gson();
		this.type = new TypeToken<LinkedList<Investment>>(){}.getType();
	}
	
	public void saveFile(LinkedList<Investment> ins) {
		gson = new Gson();
		String gsonStr = gson.toJson(ins);
		try {
			FileWriter writer = new FileWriter("file.json");
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
				new FileReader("file.json"));
			ins = gson.fromJson(br,type);
		} catch (IOException e) {
			//File file = new File("file.json");
			//e.printStackTrace();
		}
		return ins;
	}
}
