import java.util.ArrayList;
import java.util.Collections;

public class SynchronizedMethods {
	public static void main(String[] args) {
		new SynchronizedMethods();
	}
	
	private class Counter {
		
		private int a = 0;
		
		public synchronized void increment() {
			a++;
		}
		
		public synchronized void decrement() {
			a--;
		}
		
		public synchronized int get() {
			return a;
		}
		
	}
	
	
	private SynchronizedMethods() {
		
		Counter counter = new Counter();
		final int maxValue = 100;
		
		Runnable addTask = () -> {
			int t;
			while (counter.get() < maxValue) {
				counter.increment();
				try {
					Thread.sleep(1000); // each adder takes one second to increment
				} catch (InterruptedException e) {}
			}
		};


		final int adderAmmount = 30;
		ArrayList<Thread> adders = new ArrayList<>();
		
		for (int i = 0; i < adderAmmount; i++) {
			adders.add(new Thread(addTask));
		}
		
		try {
			
			for (Thread adder : adders) {
				adder.start();
			}
			
			int t;
			while ((t = counter.get()) < maxValue) {
				System.out.println(t);
				Thread.sleep(1);
			}

			System.out.println("END = " + counter.get());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
