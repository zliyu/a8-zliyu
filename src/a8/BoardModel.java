package a8;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JPanel;

public class BoardModel implements Board{

	private int _width;
	private int _height;

	private Cell[][] _cells;


	public BoardModel(int width, int height) {
		if (width < 10 || height < 10 || width > 500 || height > 500) {
			throw new IllegalArgumentException("Illegal board geometry");
		}

		_width = width;
		_height = height;
		_cells = new Cell[width][height];


		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				_cells[x][y] = new JCell(x, y, this);

			}
		}
	}

	@Override
	public int getBoardWidth() {
		return _width;
	}


	@Override
	public int getBoardHeight() {
		return _height;
	}

	@Override
	public Cell getCellAt(int x, int y) {
		return _cells[x][y];
	}

	@Override
	public void addCellListener(CellListener cl) {
		for(Cell c : this) {
			c.addCellListener(cl);
		}

	}

	@Override
	public void removeCellListener(CellListener cl) {
		for(Cell c : this) {
			c.removeCellListener(cl);
		}

	}

	@Override
	public Iterator<Cell> iterator() {
		return new BoardIterator(this);
	}

	//clear field
	@Override
	public void clear() {
		for(Cell c: this) {
			c.clear();
		}

	}

	//random fill
	@Override
	public void random() {
		clear();

		//number of cells
		int total = _width * _height;

		//random number of cells to fill
		int num = (int)(Math.random() * total + 1);
//		System.out.println(num + "");

		//randomly fill num cells on board
		for(int i=0; i<num; i++) {
			//random row and col
			int r = (int)(Math.random() * _height);
			int c = (int)(Math.random() * _width);
			//skip cell already filled
			if(!getCellAt(c, r).isEmpty()) {
				i--;
				continue;
			} else {
				getCellAt(c, r).set();
			}
		}	

	}

}
