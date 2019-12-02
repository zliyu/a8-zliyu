package a8;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		//Create top-level window
		//		JFrame main_frame = new JFrame();
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Game of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create panel for content
		//		JPanel top_panel = new JPanel();
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);

		//Control panel
		ControlView control_view = new ControlView();
		top_panel.add(control_view, BorderLayout.WEST);


		//Game board panel
		GameModel model = new GameModel(30);
		BoardView board_view = new BoardView(model.getBoard());
		BoardController controller = new BoardController(board_view, model);

		model.addCellListener(controller);
		control_view.addController(controller);

		top_panel.add(controller.getView(), BorderLayout.CENTER);

		//make main frame visible
		//main_frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		main_frame.pack();
		main_frame.setVisible(true);		

	}
}
