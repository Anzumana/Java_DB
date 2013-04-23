package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class DialogStammdatenBetreuer extends DialogStammdaten {
	public DialogStammdatenBetreuer(JFrame fenster, String[][] datensatz, String s, Datenbank datenbank1) {
		super(fenster, datensatz, s, datenbank1);
		textTitel.setEnabled(false);
		this.setVisible(true);
	}
	
	protected void setzen(String[][] datensatz, int i) {
		textInstitut.setText(datensatz[i][2]);
	}
	
	protected String[] erstellen() {
		return new String[] {textPersonalnr.getText(), textName.getText(), textInstitut.getText()};
	}
	
	protected boolean hinzufuegen(String[] s) {
		return datenbank.hinzufuegenBetreuer(s);
	}
	
	protected boolean aendern(String s1, String[] s2) {
		return datenbank.aendernBetreuer(s1, s2);
	}
	
	protected boolean loeschen(String s) {
		return datenbank.loeschenBetreuer(s);
	}
	
	protected boolean geaendert(String[][] datensatz, int i) {
		return !(textName.getText().equals(datensatz[i][1]) && textPersonalnr.getText().equals(datensatz[i][0]) && textInstitut.getText().equals(datensatz[i][2]));
	}
}
