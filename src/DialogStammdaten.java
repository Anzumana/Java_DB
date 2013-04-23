package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public abstract class DialogStammdaten extends JDialog {
	protected Datenbank datenbank;
	private String[][] datensatz;
	private JPanel p;
	private JLabel platzhalter, text1, text2, text3, text4;
	private JComboBox feld;
	protected JTextField textName, textPersonalnr, textInstitut, textTitel = new JTextField();
	private JMenuBar menue;
	private JMenu datei;
	private JMenuItem aendern, hinzufuegen, loeschen, beenden;
	
	public DialogStammdaten(JFrame fenster, String[][] datensatz1, String s, Datenbank datenbank1){
		super(fenster, true);
		datenbank = datenbank1;
		datensatz = datensatz1;
		p = new JPanel();
		platzhalter = new JLabel();
		text1 = new JLabel("Name");
		text2 = new JLabel("Personalnummer");
		text3 = new JLabel("Institut");
		text4 = new JLabel("Titel");
		feld = new JComboBox();
		textName = new JTextField();
		textPersonalnr = new JTextField();
		textInstitut = new JTextField();
		textTitel = new JTextField();
		menue = new JMenuBar();
		datei = new JMenu("Datei");
		aendern = new JMenuItem("Aendern");
		hinzufuegen = new JMenuItem("Hinzufuegen");
		loeschen = new JMenuItem("Loeschen");
		beenden = new JMenuItem("Beenden");
		final JDialog dialog = this;
		this.setTitle(s);
		feld = new JComboBox(ersteElemente(datensatz));
		textName.setText(datensatz[0][1]);
		textPersonalnr.setText(datensatz[0][0]);
		setzen(datensatz, feld.getSelectedIndex());
		
		feld.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getID() == ItemEvent.ITEM_STATE_CHANGED 
						&& e.getStateChange() == ItemEvent.SELECTED) {
					textName.setText((String)e.getItem());
					textPersonalnr.setText(datensatz[feld.getSelectedIndex()][0]);
					setzen(datensatz, feld.getSelectedIndex());
				}
			}
		});
		
		datei.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				hinzufuegen.setEnabled(false);
				aendern.setEnabled(false);
				loeschen.setEnabled(false);
				
					if (!geaendert(datensatz, feld.getSelectedIndex())) {
						loeschen.setEnabled(true);
					} else if (!(textName.getText().equals("") || textPersonalnr.getText().equals(""))){
						hinzufuegen.setEnabled(true);
						aendern.setEnabled(true);
					}
				
			}
			public void menuDeselected(MenuEvent e) {	}
			public void menuCanceled(MenuEvent e) {		}
		});
		
		hinzufuegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] daten = erstellen();
				if(hinzufuegen(daten)) {
					dispose();
				}else {
					JOptionPane.showMessageDialog(dialog, "Hinzufuegen geht nicht.", "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		aendern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] daten = erstellen();
				if(aendern(datensatz[feld.getSelectedIndex()][0], daten)) {
					dispose();
				}else {
					JOptionPane.showMessageDialog(dialog, "Aendern geht nicht.", "Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] daten = erstellen();
				if(loeschen(datensatz[feld.getSelectedIndex()][0])) {
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
        
		p.setLayout(new GridLayout(2,5));
		p.add(platzhalter);
		p.add(text1);
		p.add(text2);
		p.add(text3);
		p.add(text4);
		p.add(feld);
		p.add(textName);
		p.add(textPersonalnr);
		p.add(textInstitut);
		p.add(textTitel);
		this.setContentPane(p);
        
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private String[] ersteElemente(String[][] s) {
		String[] s1 = new String[s.length];
		for (int i = 0; i < s.length; i++) {
			s1[i] = s[i][1];
		}
		return s1;
	}
	abstract protected void setzen(String[][] datensatz, int i);
	abstract protected String[] erstellen();
	abstract protected boolean hinzufuegen(String[] s);
	abstract protected boolean aendern(String s1, String[] s2);
	abstract protected boolean loeschen(String s);
	abstract protected boolean geaendert(String[][] datensatz, int i);
}
