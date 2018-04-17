public class Utility {
	
	public Utility() {
		System.load("S:\\GIT\\TheMachine\\JNI\\JNIJunk\\src\\hello.dll");
	}
	
	private native int hello(int a, int b);

	public static void main(String[] args) {
		
		Utility util = new Utility();
		System.out.println(util.hello(4, 2));
		
	}
	
}
