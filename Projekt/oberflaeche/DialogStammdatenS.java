package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public abstract class DialogStammdatenS extends JDialog {
	protected Methoden datenbank;
	private JPanel p;
	private JComboBox feld;
	private JTextField text;
	private JMenuBar menue;
	private JMenu datei;
	private JMenuItem aendern, hinzufuegen, loeschen, beenden;
	
	public DialogStammdatenS(JFrame fenster, String[] datensatz, Methoden datenbank1, String titel){
		super(fenster, true);
		datenbank = datenbank1;
		final JDialog dialog = this;
		p = new JPanel();
		text = new JTextField(null);
		menue = new JMenuBar();
		datei = new JMenu("Datei");
		aendern = new JMenuItem("Aendern");
		hinzufuegen = new JMenuItem("Hinzufuegen");
		loeschen = new JMenuItem("Loeschen");
		beenden = new JMenuItem("Beenden");
		this.setTitle(titel);
		feld = new JComboBox(datensatz);
		text.setText((String)feld.getSelectedItem());
		feld.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getID() == ItemEvent.ITEM_STATE_CHANGED 
						&& e.getStateChange() == ItemEvent.SELECTED) {
					text.setText((String)e.getItem());
				}
			}
		});
		
		datei.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				hinzufuegen.setEnabled(false);
				aendern.setEnabled(false);
				loeschen.setEnabled(false);
				if (feld.getSelectedItem().equals(text.getText()) || text.getText().equals("")) {
					loeschen.setEnabled(true);
				} else {
					hinzufuegen.setEnabled(true);
					aendern.setEnabled(true);
				}
			}
			public void menuDeselected(MenuEvent e) {	}
			public void menuCanceled(MenuEvent e) {		}
		});
		
		hinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(hinzufuegen(text.getText())) {
						dispose();
					}else {
						JOptionPane.showMessageDialog(dialog, "Hinzufuegen geht nicht.", "Fehler", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		aendern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(aendern((String)feld.getSelectedItem(), text.getText())) {
						dispose();
					} else {
						JOptionPane.showMessageDialog(dialog, "Aendern geht nicht.", "Fehler", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		loeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(datenbank.loeschenStatus((String)feld.getSelectedItem())) {
						dispose();
					} else {
						JOptionPane.showMessageDialog(dialog, "Loeschen geht nicht.", "Fehler", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		datei.add(hinzufuegen);
		datei.add(aendern);
		datei.add(loeschen);
		datei.add(beenden);
        menue.add(datei);
        this.setJMenuBar(menue);
        
		p.setLayout(new GridLayout(1,2));
		p.add(feld);
		p.add(text);
		this.setContentPane(p);
        
		this.pack();
		this.setSize(300, getHeight());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
	}
	protected abstract boolean hinzufuegen(String s);
	protected abstract boolean aendern(String s1, String s2);
	protected abstract boolean loeschen(String s);
}
