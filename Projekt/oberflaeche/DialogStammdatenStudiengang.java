package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class DialogStammdatenStudiengang extends DialogStammdatenS {
	public DialogStammdatenStudiengang(JFrame fenster, String[] datensatz, Methoden datenbank1){
		super(fenster, datensatz, datenbank1, "Studiengang aendern");
	}
	protected boolean hinzufuegen(String s) {
		return datenbank.hinzufuegenStatus(s);
	}
	protected boolean aendern(String s1, String s2) {
		return datenbank.aendernStatus(s1, s2);
	}
	protected boolean loeschen(String s) {
		return datenbank.loeschenStatus(s);
	}
}
