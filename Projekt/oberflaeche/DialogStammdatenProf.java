package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class DialogStammdatenProf extends DialogStammdaten {
	public DialogStammdatenProf(JFrame fenster, String[][] datensatz, String s, Methoden datenbank1) {
		super(fenster, datensatz, s, datenbank1);
		textTitel.setText(datensatz[0][2]);
		textInstitut.setText(datensatz[0][3]);
		this.setVisible(true);
	}
	
	protected void setzen(String[][] datensatz, int i) {
		textTitel.setText(datensatz[i][2]);
		textInstitut.setText(datensatz[i][3]);
	}

	protected String[] erstellen() {
			return new String[] {textName.getText(), textPersonalnr.getText(), textTitel.getText(), textInstitut.getText()};
	}
	
	protected boolean hinzufuegen(String[] s) {
		return datenbank.hinzufuegenProfessor(s);
	}
	
	protected boolean aendern(String s1, String[] s2) {
		return datenbank.aendernProfessor(s1, s2);
	}
	
	protected boolean loeschen(String s) {
		return datenbank.loeschenProfessor(s);
	}
	
	protected boolean geaendert(String[][] datensatz, int i) {
		return !(textName.getText().equals(datensatz[i][0]) && textPersonalnr.getText().equals(datensatz[i][1]) && textTitel.getText().equals(datensatz[i][2]) && textInstitut.getText().equals(datensatz[i][3]));
	}
}
