
public class MainGUI {
	static private MainGUI instance;
	
	private MainGUI(Controller controller_) {
		
	}
	
	public static MainGUI getInstance(Controller controller_) {
	      if(instance == null) {
	         instance = new MainGUI(controller_);
	      }
	      return instance;
	}
}
