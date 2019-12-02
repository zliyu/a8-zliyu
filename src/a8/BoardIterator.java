package a8;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoardIterator implements Iterator<Cell> {
	
	private Board _board;
	int _x;
	int _y;

	public BoardIterator(Board board) {
		_board = board;
		_x = 0;
		_y = 0;
	}
	
	@Override
	public boolean hasNext() {
		return (_y < _board.getBoardHeight());
	}

	@Override
	public Cell next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Cell c = _board.getCellAt(_x, _y);
		if (_x < _board.getBoardWidth()-1) {
			_x++;
		} else {
			_x = 0;
			_y++;
		}
		return c;
	}
}
