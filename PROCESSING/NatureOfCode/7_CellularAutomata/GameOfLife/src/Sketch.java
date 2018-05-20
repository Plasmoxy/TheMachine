import processing.core.PApplet;

public class Sketch extends PApplet {

	public static void main(String[] args) {
		PApplet.main("Sketch");
	}
	
	
	class GameOfLife {
		
		int[][] board;
		final int columns;
		final int rows;
		final boolean cigani = false;
		
		GameOfLife(int columns, int rows) {
			this.columns = columns;
			this.rows = rows;
			board = new int[columns][rows];
			
			// initialize random states
			for (int x = 0; x < columns; x++) {
				for (int y = 0; y < rows; y++) {
					board[x][y] = parseInt(random(2));
				}
			}
		}
		
		void generate() {
			// initialize next generation array
			int[][] nextgen = new int[columns][rows];
			
			// compute new state for every cell except edge cells
			for (int x = 1; x < columns-1; x++) {
				for (int y = 1; y < rows-1; y++) {
					
					int neighbors = 0;
					
					// scan cells within 3x3 cell square around cell and add any present neighbors
					for (int dx = -1; dx <= 1; dx++) {
						for (int dy = -1; dy <= 1; dy++) {
							neighbors += board[x+dx][y+dy];
						}
					}
					
					// remove this cell from neighbors
					neighbors -= board[x][y];
					
					// --- process states ----
					
					// LONELINESS
					// if alive and less than 2 neighbors, die
					if 		(board[x][y] == 1 && neighbors < 2) {
						nextgen[x][y] = 0;
					}
					
					// OVERPOPULATION
					// if alive and more than 3 neighbors, die
					else if (board[x][y] == 1 && neighbors > 3 && !cigani) {
						nextgen[x][y] = 0;
					}
					
					// BIRTH
					// if dead and has exactly 3 neighbors, get born
					else if (board[x][y] == 0 && neighbors == 3) {
						nextgen[x][y] = 1;
					}
					
					
					// other cases, just copy the cell state (it does nothing)
					else {
						nextgen[x][y] = board[x][y];
					}
					
				}
			}
			
			// after generating next generation, update main generation
			board = nextgen;
			
		}
		
		
		void display(int cellwidth) {
			//stroke(200);
			noStroke();
			for (int x = 0; x < columns; x++) {
				for (int y = 0; y < rows; y++) {
					if (board[x][y] == 1) fill(0);
					else fill(255);
					rect(x*cellwidth, y*cellwidth, cellwidth, cellwidth);
				}
			}
		}
		
	}
	
	GameOfLife game;
	

	@Override public void settings() {
		size(800, 500);
		fullScreen();
	}

	@Override public void setup() {
		//frameRate(10);
		game = new GameOfLife(width/5, height/5);
	}

	@Override public void draw() {
		background(200);
		game.generate();
		game.display(5);
	}
}
