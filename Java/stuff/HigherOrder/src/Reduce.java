import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Reduce {

	public static void main(String[] args) {
		
		List<Integer> arr = Arrays.asList(1, 6, 3, 2);
		System.out.println(arr);
		
		int sum = arr.stream().reduce(0, (acc, val) -> acc + val);
		System.out.println("sum = " + sum);
	}
	
}
