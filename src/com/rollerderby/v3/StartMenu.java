package com.rollerderby.v3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartMenu {
	
	static MainGame mg;
	public static ImageIcon img = new ImageIcon("wheel.png");
	public static int width = 600, height = 500;
	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	static JLabel label = new JLabel();
	static JButton play = new JButton();
	static JButton exit = new JButton();
	
	public static void main(String[] args) {
		label.setIcon(new ImageIcon("ts.png"));
		label.setBounds(0, 0, width, height);
		
		play.setBounds(375, 260, 140, 70);
		play.setOpaque(false);
		play.setContentAreaFilled(false);
		play.setBorderPainted(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				mg = new MainGame();
			}
		});
		
		exit.setBounds(375, 335, 140, 70);
		exit.setOpaque(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int warning = JOptionPane.YES_NO_OPTION;
				int result = JOptionPane.showConfirmDialog(null,  "Are you sure you want to exit?", "Warning!", warning);
				
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		panel.add(label);
		panel.add(play);
		panel.add(exit);
		panel.setLayout(null);
		
		frame.add(panel);
		frame.setIconImage(img.getImage());
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}