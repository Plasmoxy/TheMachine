public class Joined {
	public static void main(String[] args) {
		new Joined();
	}
	
	private int[] values = new int[3];
	
	private Thread generator = new Thread(() -> {
		try {
			
			for (int i = 0; i < values.length; i++) {
				values[i] = (int)(Math.random()*10);
				System.out.println("GENERATOR : generated number " + i + " = " + values[i]);
				Thread.sleep(1000);
			}
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	});
	
	
	// consumer waits for generator to complete
	private Thread consumer = new Thread(() -> {
		try {
			generator.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.print("CONSUMER SHOWS NEW ARRAY = [ ");
		for (int v: values) {
			System.out.print(v + " ");
		}
		System.out.println("]");
		
	});
	
	
	private Joined() {

		consumer.start(); // consumer will execute only after the generator finishes so it won't execute even here
		generator.start();
		
	}
	
}
