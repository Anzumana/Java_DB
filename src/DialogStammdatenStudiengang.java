package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

public class DialogStammdatenStudiengang extends DialogStammdatenS {
	public DialogStammdatenStudiengang(JFrame fenster, String[] datensatz, Datenbank datenbank1){
		super(fenster, datensatz, datenbank1, "Studiengang aendern");
	}
	protected boolean hinzufuegen(String s) {
		return datenbank.hinzufuegenStudiengang(s);
	}
	protected boolean aendern(String s1, String s2) {
		return datenbank.aendernStudiengangbezeichnung(s1, s2);
	}
	protected boolean loeschen(String s) {
		System.out.println("z20 + " + s);
		return datenbank.loeschenStudiengang(s);
	}
}
