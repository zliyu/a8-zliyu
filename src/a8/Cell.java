package a8;

import java.awt.Color;

public interface Cell {
	
	int getSpotX();
	int getSpotY();
	Board getBoard();
	
	boolean isEmpty();
	void set();
	void clear();
		
	default void toggle() {
		if(isEmpty()) {
			set();
		} else {
			clear();
		}
	}
	
	boolean isHighlight();	
	void highlight();
	void unhighlight();	
	
	default void toggleHighlight() {
		if(isHighlight()) {
			unhighlight();
		} else {
			highlight();
		}
	}

	void addCellListener(CellListener l);
	void removeCellListener(CellListener l);
}
