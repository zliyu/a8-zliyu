package a8;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameModel {

	private Board board;
//	private int w;
//	private int h;

	private int birth_high;
	private int birth_low;
	private int surv_high;
	private int surv_low;

	private boolean torus;
	
	private int speed;
	private Timer timer;
	private boolean running;

	//constructors
	public GameModel(Board bm, int bh, int bl, int sh, int sl, boolean t) {
		board = bm;
//		w = board.getBoardWidth();
//		h = board.getBoardHeight();
		
		birth_high = bh;
		birth_low = bl;
		surv_high = sh;
		surv_low = sl;
		torus = t;
		speed = 5;
		running = false;
	}

	public GameModel(Board bm) {
		this(bm, 3, 3, 3, 2, false);
	}
	
	public GameModel(int size) {
		this(new BoardModel(size, size), 3, 3, 3, 2, false);
	}
	
	//getter
	public Board getBoard() {
		return board;
	}

	//setter for change game configuration
	public void setBoard(Board b) {
		board = b;
	}
	
	public void setHighBirth(int i) {
		birth_high = i;
	}

	public void setLowBirth(int i) {
		birth_low = i;
	}
	
	public void setHighSurv(int i) {
		surv_high = i;
	}
	
	public void setLowSurv(int i) {
		surv_low = i;
	}
	
	public void setTorus(boolean b) {
		torus = b;
	}
	
	//game control
	//next frame: update board - update cells[][]
	public void next() {
		ArrayList<Cell> toToggle = new ArrayList<Cell>();
		for(Cell c : getBoard()) {
			if(change(c)) {
				toToggle.add(c);			
			}
		}
		for(Cell c : toToggle) {
			c.toggle();
		}
	}
	
	//determine if a cell will change state
	private boolean change(Cell c) {
		int num = count(c);
		//empty cell is born if within birth thresholds
		if(c.isEmpty()) {
			if(num <= birth_high && num >= birth_low) {
				return true;
			}
		}
		//not empty cells die if out of survive thresholds
		if(!c.isEmpty()) {
			if(num < surv_low || num > surv_high) {
				return true;
			}
		}
		return false;
	}
	
	//count how many live cells is around a certain cell
	private int count(Cell c) {
		int num = 0;
		int x = c.getSpotX();
		int y = c.getSpotY();
		
		//cells in the middle
		if(x>0 && x<board.getBoardWidth()-1 && y>0 && y<board.getBoardHeight()-1) {
			for(int i=-1; i<2; i++) {
				for(int j=-1; j<2; j++) {
					if(i==0 && j==0) {
						continue;
					}
					if(!board.getCellAt(x+i, y+j).isEmpty()) {
						num++;
					}
				}	
			}
			return num;
		}
		
		//cells on sides
		//count method differ based on if torus
		
		//not torus
		if(!torus) {
			num = 0;
			for(int i=-1; i<2; i++) {
				for(int j=-1; j<2; j++) {
					if(i==0 && j==0) {
						continue;
					}
					try {
						if(!board.getCellAt(x+i, y+j).isEmpty()) {
							num++;
						}
					} catch (Exception e){
						continue;
					}
				}	
			}
			return num;
		}
		//torus
		else {
			num = 0;
			for(int i=-1; i<2; i++) {
				for(int j=-1; j<2; j++) {
					if(i==0 && j==0) {
						continue;
					}
					int x0 = x+i;
					int y0 = y+j;
					if(x0 == -1) {x0 = board.getBoardWidth()-1;}
					if(y0 == -1) {y0 = board.getBoardHeight()-1;}
					if(x0 == board.getBoardWidth()) {x0 = 0;}
					if(y0 == board.getBoardHeight()) {y0 = 0;}
					if(!board.getCellAt(x0, y0).isEmpty()) {
						num++;
					}
				}	
			}
			return num;
		}		
	}
	
	public void addCellListener(CellListener cl) {
		getBoard().addCellListener(cl);
	}
	
	
	public Timer getTimer() {
		return timer;
	}
	
	public void go() {
		if(!running) {
			makeTimer();
			running = true;
		}
	}
	
	public void stop() {
		try {
			timer.cancel();
		} catch(Exception e) {
			return;
		}
		running = false;
	}
	
	
	public void makeTimer() {
		TimerTask task = new TimerTask() {
		      public void run() {
		        next();
		      }
		};
		
		//Schedules the timer to do the TimerTask every (1/speed) seconds
		Timer timer = new Timer();
		timer.schedule(task, 0, (1000/speed));
		this.timer = timer;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int i) {
		speed = i;
	}
	
	public boolean getRunning() {
		return running;
	}
}


