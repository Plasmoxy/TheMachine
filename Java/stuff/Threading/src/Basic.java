
public class Basic {
	public static void main(String[] args) {
		new Basic();
	}
	
	
	private void slepp(long ms) {
		try { Thread.sleep(ms); } catch (InterruptedException e) {}
	}
	
	private Runnable printer = () -> {
		try {
			while (true) {
				System.out.println("XD");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("THREAD INTERRUPTED");
			return;
		}
	};
	
	
	private Basic() {
		
		Thread t = new Thread(printer);
		t.start();
		
		slepp(4000);
		t.interrupt();

		System.out.println("END");
	}
}


