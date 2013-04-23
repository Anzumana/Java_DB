package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class DialogStammdatenStatus extends DialogStammdatenS {
	public DialogStammdatenStatus(JFrame fenster, String[] datensatz, Datenbank datenbank1){
		super(fenster, datensatz, datenbank1, "Status aendern");
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
