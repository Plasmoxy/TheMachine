import java.io.File;

public class Utility {
	
	public Utility() {
		System.loadLibrary("hello");
	}
	
	private native void hello();

	public static void main(String[] args) {
		
		Utility util = new Utility();
		util.hello();
		
	}
	
}
