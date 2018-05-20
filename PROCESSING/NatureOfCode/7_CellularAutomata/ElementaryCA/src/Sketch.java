import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}

	class Automaton {
		int[] cells;
		int[] ruleset;
		int cellWidth = 1;
		int generation = 0;
		
		Automaton() {
			
			cells = new int[width/cellWidth];
			ruleset = new int[]{0, 1, 1, 1, 1, 0, 0, 0}; // to get binary rule ( 90, 30) count from left
			
			for (int i = 0; i < cells.length; i++) {
				cells[i] = 0;
			}
			
			cells[cells.length/2] = 1; // middle cell is 1
		}


		void drawCells() {
			for (int i = 0; i < cells.length; i++) {
				if (cells[i] ==1) {
					fill(0);
					stroke(0);
				}
				else {
					fill(255);
					stroke(255);
				}
				
				rect(i*cellWidth, generation*cellWidth, cellWidth, cellWidth);
			}
		}
		int applyRuleset(int left, int middle, int right) {
			String s = "" + left + middle + right;
			int index = Integer.parseInt(s, 2); // get from binary number string
			return ruleset[index];
		}

		// compute next generation
		void generate() {
			int[] nextgen = new int[cells.length];

			// skip edges
			for (int i = 1; i < cells.length-1; i++) {
				int left = cells[i-1];
				int middle = cells[i];
				int right = cells[i+1];

				nextgen[i] = applyRuleset(left, middle, right);
			}

			cells = nextgen;
			generation++;
		}
	}
	
	Automaton ca;

	@Override public void settings() {
		size(1000, 700);
		fullScreen();
	}

	@Override public void setup() {
		background(200);
		surface.setTitle("Sierpinski triangle cellular automaton");
		
		ca = new Automaton();
		ca.drawCells();
		
	}

	@Override public void draw() {
		if (ca.generation < height/ca.cellWidth) {
			ca.generate();
			ca.drawCells();
			System.out.println(ca.generation);
		}
	}
}
