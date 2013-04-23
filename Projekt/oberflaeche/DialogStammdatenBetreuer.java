package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class DialogStammdatenBetreuer extends DialogStammdaten {
	public DialogStammdatenBetreuer(JFrame fenster, String[][] datensatz, String s, Methoden datenbank1) {
		super(fenster, datensatz, s, datenbank1);
		textTitel.setEnabled(false);
		textInstitut.setText(datensatz[0][2]);
		this.setVisible(true);
	}
	
	protected void setzen(String[][] datensatz, int i) {
		textInstitut.setText(datensatz[i][2]);
	}
	
	protected String[] erstellen() {
		return new String[] {textName.getText(), textPersonalnr.getText(), textInstitut.getText()};
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
		return !(textName.getText().equals(datensatz[i][0]) && textPersonalnr.getText().equals(datensatz[i][1]) && textInstitut.getText().equals(datensatz[i][2]));
	}
}
