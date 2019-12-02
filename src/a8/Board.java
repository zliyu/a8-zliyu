package a8;

import java.util.Iterator;

public interface Board extends Iterable<Cell> {
	
	int getBoardWidth();

	int getBoardHeight();

	Cell getCellAt(int x, int y);

	//add/remove listener to all cells on board
	void addCellListener(CellListener cl);

	void removeCellListener(CellListener cl);

	Iterator<Cell> iterator();
	
	void clear();
	
	void random();
}
