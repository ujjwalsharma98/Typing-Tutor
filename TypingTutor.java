package game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {
	JLabel lblTimer;
	JLabel lblScore;
	JLabel lblWord;
	JTextField txtWord;
	JButton btnStart;
	JButton btnStop;

	Timer timer;
	int time;
	int score;
	boolean running;
	String[] data;

	public TypingTutor(String feeder) {
		super.setSize(1920, 1080);
		super.setTitle("Typing Tutor");

		GridLayout grid = new GridLayout(3, 2);
		super.setLayout(grid);

		Font font = new Font("Comic Sans MS", 1, 150);

		lblTimer = new JLabel("Timer:");
		lblTimer.setFont(font);
		super.add(lblTimer);

		lblScore = new JLabel("Score:");
		lblScore.setFont(font);
		super.add(lblScore);

		lblWord = new JLabel("");
		lblWord.setFont(font);
		super.add(lblWord);

		txtWord = new JTextField("");
		txtWord.setFont(font);
		super.add(txtWord);

		btnStart = new JButton("Start");
		btnStart.setFont(font);
		btnStart.addActionListener(this);
		super.add(btnStart);

		btnStop = new JButton("Stop");
		btnStop.setFont(font);
		btnStop.addActionListener(this);
		super.add(btnStop);

		init();

		data = feeder.split(" ");
		super.setVisible(true);
	}

	public void init() {
		timer = new Timer(1000, this);
		time = 50;
		score = 0;
		running = false;

		lblTimer.setText("Timer:" + time);
		lblScore.setText("Score:" + score);

		lblWord.setText("");

		txtWord.setText("");
		txtWord.setEnabled(false);

		btnStart.setText("Start");
		btnStart.setEnabled(true);

		btnStop.setText("Stop");
		btnStop.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			handleStart();
		} else if (e.getSource() == btnStop) {
			handleStop();
		} else if (e.getSource() == timer) {
			handleTimer();
		}
	}

	private void handleStart() {
		if (running == false) {
			timer.start();
			running = true;

			txtWord.setEnabled(true);
			btnStart.setText("Pause");
			btnStop.setEnabled(true);
		} else {
			timer.stop();
			running = false;

			txtWord.setEnabled(false);
			btnStart.setText("Resume");
			btnStop.setEnabled(false);
		}
	}

	private void handleStop() {
		timer.stop();
		
		int choice = JOptionPane.showConfirmDialog(this, "Restart?");
		if(choice == JOptionPane.YES_OPTION){
			init();
			handleStart();
		} else {
			super.dispose();
		}
	}

	private void handleTimer() {
		if (time > 0) {
			time--;

			if (lblWord.getText().equals(txtWord.getText())) {
				score++;
			}

			lblTimer.setText("Timer:" + time);
			lblScore.setText("Score:" + score);

			lblWord.setText(data[(int) (Math.random() * data.length)]);
			txtWord.setText("");
			txtWord.setFocusable(true);
		} else {
			handleStop();
		}
	}
}
