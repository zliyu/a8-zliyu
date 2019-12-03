package a8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class BoardView extends JPanel {
	
	private static final int DEFAULT_SCREEN_WIDTH = 950;
	private static final int DEFAULT_SCREEN_HEIGHT = 950;

	private Board _board;
//	private int _height;
//	private int _width;
	
	public BoardView(Board board) {
		_board = board;
		paint(board);
	}
	
	public BoardView(int w, int h) {
		this(new BoardModel(w, h));
	}
	
	public void setBoard(Board b) {
		_board = b;
		this.removeAll();
		paint(b);
		this.revalidate();
	}
	
	public Board getBoard() {
		return _board;
	}
	
	private void paint(Board b) {
		int _height = b.getBoardHeight();
		int _width = b.getBoardWidth();
		this.setLayout(new GridLayout(_height, _width));
		
		Dimension preferred_size = new Dimension(DEFAULT_SCREEN_WIDTH/_width, DEFAULT_SCREEN_HEIGHT/_height);
		for (int y=0; y<_height; y++) {
			for (int x=0; x<_width; x++) {
				((JCell)b.getCellAt(x, y)).setPreferredSize(preferred_size);
				//add to board
				this.add((JCell)b.getCellAt(x, y));
			}			
		}
	}
}

