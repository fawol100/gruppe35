package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Level.Levellist;

public class Menu extends JFrame {
	// Constructor
	public Menu() {
		super("MainMenu");

		JMenu start = new JMenu("Start");
		start.setMnemonic('S');

		JMenu options = new JMenu("Options");
		options.setMnemonic('O');

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('X');

		// Action listener f�r Level items
		// JMenuItem[] levelItems = new JMenuItem[Levellist.list.size()];
		/*
		 * for(int i=0;i<Levellist.list.size();i++){ JMenuItem levelItem = new
		 * JMenuItem("Level" + (i+1)); levelItem.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent e) {
		 * System.out.println("Starte Level"+ (i+1)); } }); }
		 */

		JMenuItem l1Item = new JMenuItem("Level 1");
		start.add(l1Item);
		
		l1Item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		// Action listener f�r Exit
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(start);
		bar.add(options);
		bar.add(exitItem);

		getContentPane();
		setSize(200, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		// Levels laden
		Levellist.load();
		Menu app = new Menu();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}