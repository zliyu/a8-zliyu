package a8;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController implements CellListener{

	private BoardView bv;
	private GameModel gm;

	public BoardController(BoardView view, GameModel model) {
		bv = view;
		gm = model;
	}
	//getters
	public BoardView getView() {
		return bv;
	}
	
	//set size
	public void setSize(int i) {
		Board b2 = new BoardModel(i, i);
		gm.stop();
		gm.setBoard(b2);
		bv.setBoard(b2);
		gm.addCellListener(this);
		bv.revalidate();
		bv.repaint();
	}
	
	//set speed and run if already running
	//only set speed if not running
	public void setSpeedandRun(int i) {
		if(!gm.getRunning()) {
			gm.setSpeed(i);
		} else {
			gm.stop();
			gm.setSpeed(i);
			gm.go();
		}
//		try {
//			gm.getTimer().cancel();
//		} catch (Exception e) {
//			
//		}		
//		gm.setSpeed(i);
//		if(gm.getRunning()) {
//			gm.go();
//		}	
	}
	
	//dispatch other button events
	public void dispatch(ActionEvent e) {
		if(e.getActionCommand().equals("clear")) {
			pause();
			clear();
		}
		if(e.getActionCommand().equals("random")) {
			pause();
			random();
		}
		if(e.getActionCommand().equals("next")) {
			pause();
			next();
		}
		if(e.getActionCommand().equals("play")) {
			gm.go();
		}
		if(e.getActionCommand().equals("stop")) {
			pause();
		}
		
	}

	private void clear() {
		gm.getBoard().clear();
		bv.revalidate();
	}
	
	private void random() {
		gm.getBoard().random();
		bv.revalidate();
	}
	
	private void next() {
		gm.next();
		bv.revalidate();
	}
	
	private void pause() {
		gm.stop();
	}
	
	public void torus(boolean b) {
		gm.setTorus(b);
	}
	//sliders
	
	public void sethb(int i) {
		gm.setHighBirth(i);
	}
	public void setlb(int i) {
		gm.setLowBirth(i);
	}
	public void seths(int i) {
		gm.setHighSurv(i);
	}
	public void setls(int i) {
		gm.setLowSurv(i);
	}
	
	//individual spots
	@Override
	public void cellClicked(Cell c) {
		c.toggle();
	}

	@Override
	public void cellEntered(Cell c) {
		c.highlight();
	}

	@Override
	public void cellExited(Cell c) {
		c.unhighlight();
	}

	
}
