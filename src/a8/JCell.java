package a8;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class JCell extends JLabel implements Cell, MouseListener{
	private int x;
	private int y;
	private Board board;
	private boolean empty;
	private boolean hl;
	
	//observers
	private List<CellListener> cell_listeners;
	
	public JCell(int x, int y, Board board) {
		this.x = x;
		this.y = y;
		this.board = board;
		empty = true;
		hl = false;
		
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
			
        Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
        this.setBorder(border);
		
		cell_listeners = new ArrayList<CellListener>();
		addMouseListener(this);	
	}

	@Override
	public int getSpotX() {
		return x;
	}

	@Override
	public int getSpotY() {
		return y;
	}

	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public void set() {
		this.setBackground(Color.BLACK);
		empty = false;
//		this.toggleHighlight();
		this.revalidate();
	}

	@Override
	public void clear() {
		this.setBackground(Color.WHITE);		
		empty = true;
//		this.toggleHighlight();
		this.revalidate();
	}

	@Override
	public boolean isEmpty() {
		return empty;
	}

	@Override
	public void highlight() {
		if(isEmpty()) {
			Border border = BorderFactory.createLineBorder(Color.GREEN, 1);
	        this.setBorder(border);
	        this.revalidate();
		} else {
			Border border = BorderFactory.createLineBorder(Color.RED, 1);
	        this.setBorder(border);
	        this.revalidate();
		}
		hl = true;		
	}

	@Override
	public void unhighlight() {
		Border border = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1);
        this.setBorder(border);    
        this.revalidate();
        hl = false;
	}
	
	@Override
	public boolean isHighlight() {
		return hl;
	}

	@Override
	public void addCellListener(CellListener l) {
		cell_listeners.add(l);
		
	}

	@Override
	public void removeCellListener(CellListener l) {
		cell_listeners.remove(l);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		for (CellListener cl : cell_listeners) {
			cl.cellClicked(this);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		for (CellListener cl : cell_listeners) {
			cl.cellEntered(this);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		for (CellListener cl : cell_listeners) {
			cl.cellExited(this);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// None
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// None
	}
}
