import java.net.URL;

public class Library {
	
	public Library() {
		System.load(System.getProperty("user.dir") + "\\JNI\\VS\\Testing\\x64\\Debug\\Testing.dll");
	}
	
	public native void hello();
	public native int[] someRandoms(int size);
	
}
