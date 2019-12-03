package a8;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ControlView extends JPanel implements ActionListener, ChangeListener {

	private JLabel size_label;
	private JSlider size_slider;
	private JLabel birth_label1;
	private JSlider birth_slider1;
	private JLabel birth_label2;
	private JSlider birth_slider2;
	private JLabel surv_label1;
	private JSlider surv_slider1;
	private JLabel surv_label2;
	private JSlider surv_slider2;
	private JCheckBox torus_check;
	private JLabel speed_label;
	private JSlider speed_slider;

	private List<BoardController> controllers;

	public ControlView() {
		controllers = new ArrayList<BoardController>();

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(new JLabel(" "));
		//size
		size_label = new JLabel("Size of the field: 30");

		size_slider = new JSlider(10, 500, 30);	
		size_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//JSlider slider= (JSlider)e.getSource();
				int size = size_slider.getValue();
				size_label.setText("Size of the field: " + size);
			}
		});

		JButton size_button = new JButton("Confirm Size");
		size_button.setActionCommand("resize");
		size_button.addActionListener(this);

		this.add(size_label);
		this.add(size_slider);
		this.add(size_button);
		this.add(new JLabel("Resizing the field will also clear the field"));
		this.add(new JLabel("Larger field takes longer to load"));
		this.add(new JLabel(" "));

		//birth threshold
		this.add(new JLabel("Birth Thresholds:"));
		birth_label1 = new JLabel("Low birth Threshold: 3");
		birth_label2 = new JLabel("High birth Threshold: 3");

		birth_slider1 = new JSlider(0, 8, 3);
		birth_slider2 = new JSlider(0, 8, 3);
		birth_slider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int max = Math.max(birth_slider1.getValue(), birth_slider2.getValue());
				int min = Math.min(birth_slider1.getValue(), birth_slider2.getValue());
				birth_label1.setText("Low birth Threshold: " + min);
				birth_label2.setText("High birth Threshold: " + max);
			}
		});
		birth_slider2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int max = Math.max(birth_slider1.getValue(), birth_slider2.getValue());
				int min = Math.min(birth_slider1.getValue(), birth_slider2.getValue());
				birth_label1.setText("Low birth Threshold: " + min);
				birth_label2.setText("High birth Threshold: " + max);
			}
		});

		birth_slider1.addChangeListener(this);
		birth_slider2.addChangeListener(this);

		this.add(birth_label1);
		this.add(birth_label2);
		this.add(birth_slider1);
		this.add(birth_slider2);
		this.add(new JLabel(" "));

		//death threshold
		this.add(new JLabel("Survive Thresholds:"));
		surv_label1 = new JLabel("Low survive Threshold: 2");
		surv_label2 = new JLabel("High survive Threshold: 3");

		surv_slider1 = new JSlider(0, 8, 2);
		surv_slider2 = new JSlider(0, 8, 3);
		surv_slider1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int max = Math.max(surv_slider1.getValue(), surv_slider2.getValue());
				int min = Math.min(surv_slider1.getValue(), surv_slider2.getValue());
				surv_label1.setText("Low survive Threshold: " + min);
				surv_label2.setText("High survive Threshold: " + max);
			}
		});
		surv_slider2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int max = Math.max(surv_slider1.getValue(), surv_slider2.getValue());
				int min = Math.min(surv_slider1.getValue(), surv_slider2.getValue());
				surv_label1.setText("Low survive Threshold: " + min);
				surv_label2.setText("High survive Threshold: " + max);
			}
		});

		surv_slider1.addChangeListener(this);
		surv_slider2.addChangeListener(this);

		this.add(surv_label1);
		this.add(surv_label2);
		this.add(surv_slider1);
		this.add(surv_slider2);
		this.add(new JLabel(" "));


		//torus mode
		torus_check = new JCheckBox("Torus Mode", false);
		torus_check.setFont(new Font("Dialog", Font.BOLD, 18));
		this.add(torus_check);
		torus_check.setActionCommand("torus");
		torus_check.addActionListener(this);
		this.add(new JLabel(" "));

		//actions
		//button: clear field
		JButton clear_button = new JButton("Clear Field");
		this.add(clear_button);
		clear_button.setActionCommand("clear");
		clear_button.addActionListener(this);
		this.add(new JLabel(" "));

		//button: fill field randomly
		JButton random_button = new JButton("Random Configuration");
		this.add(random_button);
		random_button.setActionCommand("random");
		random_button.addActionListener(this);
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));

		//button: next frame
		JButton next_button = new JButton("Next Frame");
		this.add(next_button);
		next_button.setActionCommand("next");
		next_button.addActionListener(this);
		this.add(new JLabel(" "));

		//slider: adjust speed
		speed_label = new JLabel("5 Frames per Second");
		speed_slider = new JSlider(1, 100, 5);
		speed_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int frame = speed_slider.getValue();
				speed_label.setText(frame + " Frames per Second");
			}
		});
		speed_slider.addChangeListener(this);
		this.add(speed_label);
		this.add(speed_slider);

		//button: auto play
		JButton play_button = new JButton("Auto Play");
		this.add(play_button);
		play_button.setActionCommand("play");
		play_button.addActionListener(this);
		this.add(new JLabel(" "));

		//button: stop
		JButton stop_button = new JButton("Pause");
		this.add(stop_button);
		stop_button.setActionCommand("stop");
		stop_button.addActionListener(this);


		
		//ui
		
		Dimension btn = new Dimension(300, 30);
		for(Object c : this.getComponents()) {
			if(c instanceof JButton) {
				((JButton) c).setFont(new Font("Dialog", Font.BOLD, 15));
				((JButton) c).setMaximumSize(btn);
			}
			if(c instanceof JLabel) {
				((JLabel)c).setFont(new Font("Dialog", Font.BOLD, 18));
			}
		}

	}

	public void addController(BoardController bc) {
		controllers.add(bc);
	}

	public void removeController(BoardController bc) {
		controllers.remove(bc);
	}



	//send button events to controller
	@Override
	public void actionPerformed(ActionEvent e) {
		for(BoardController bc : controllers) {
			if (e.getActionCommand().equals("resize")){
				bc.setSize(size_slider.getValue());
			} 
			else if (e.getActionCommand().equals("torus")){
				bc.torus(torus_check.isSelected());
			} else {
				bc.dispatch(e);
			}
		}
	}

	//sliders
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(birth_slider1) || e.getSource().equals(birth_slider2)) {
			int max = Math.max(birth_slider1.getValue(), birth_slider2.getValue());
			int min = Math.min(birth_slider1.getValue(), birth_slider2.getValue());
			for(BoardController bc : controllers) {
				bc.sethb(max);
				bc.setlb(min);
			}
		}
		if(e.getSource().equals(surv_slider1) || e.getSource().equals(surv_slider2)) {
			int max = Math.max(surv_slider1.getValue(), surv_slider2.getValue());
			int min = Math.min(surv_slider1.getValue(), surv_slider2.getValue());
			for(BoardController bc : controllers) {
				bc.seths(max);
				bc.setls(min);
			}
		}
		if(e.getSource().equals(speed_slider)) {
			for(BoardController bc : controllers) {
				bc.setSpeedandRun(speed_slider.getValue());
			}
		}
	}

}
