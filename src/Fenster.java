package oberflaeche;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;


public class Fenster extends JFrame{
	private final Datenbank datenbank;
	private JMenuBar menue;
	private JMenu datei;
	private JMenuItem neueAbschlussarbeit, bearbeitenAbschlussarbeit, /*# */	entfernenAbschlussarbeit,	/* #*/ bearbeitenStammdatenStatus, bearbeitenStammdatenStudiengang, bearbeitenProf, bearbeitenBetr, beenden;
    	private JScrollPane scrollenv;
    	private JScrollPane scrollenh;
	private JTable tabelle;

	AbstractTableModel dataModel = new AbstractTableModel() {
		public int getColumnCount() {
			return 8;
		}
		public int getRowCount() {
			return datenbank.anzahlAbschlussarbeiten();
		}
		public Object getValueAt(int zeile, int spalte) {
			return datenbank.datenListe(zeile+1)[spalte];
		}
		public String getColumnName(int spalte) {
			switch(spalte) {
			case 0: return "Abschlussarbeit Titel";
			case 1: return "Matrikelnummer";
			case 2: return "Betreuer";
			case 3: return "Pruefer1";
			case 4: return "Pruefer2";
			case 5: return "Anmeldedatum";
			case 6: return "Status";
			case 7: return "Note";
	/*		case 8: return "Studiengang";
			case 9: return "Semester";
			case 10: return "Professor Name";
			case 11: return "";
			case 12: return "Professor Titel";
			case 13: return "Professor Institut";
			case 14: return "Professor Name";
			case 15: return "Professor Personalnummer";
			case 16: return "Professor Titel";
			case 17: return "Professor Institut";
			case 18: return "Betreuer Name";
			case 19: return "";
			case 20: return "Betreuer Institut";
*/
			default: return "";
			}
		}
		
	};

	public Fenster(Datenbank datenbank1){
		datenbank = datenbank1;
		this.setTitle("Liste Abschlussarbeiten");
		this.setSize(500, 500);
		menue = new JMenuBar();
		datei = new JMenu("Datei");
		neueAbschlussarbeit = new JMenuItem("Neue Abschlussarbeit");
		bearbeitenAbschlussarbeit = new JMenuItem("Abschlussarbeit bearbeiten");
/*# */	entfernenAbschlussarbeit = new JMenuItem("Abschlussarbeit entfernen");	/* #*/
		bearbeitenStammdatenStatus = new JMenuItem("Stammdaten bearbeiten: Status");
		bearbeitenStammdatenStudiengang = new JMenuItem("Stammdaten bearbeiten: Studiengang");
		bearbeitenProf = new JMenuItem("Professordaten bearbeiten");
		bearbeitenBetr = new JMenuItem("Betreuerdaten bearbeiten");
		beenden = new JMenuItem("Beenden");
	    	tabelle = new JTable(dataModel);
		scrollenv = new JScrollPane();
		scrollenh = new JScrollPane();
		scrollenv.setViewportView(tabelle);
		scrollenv.setPreferredSize(new Dimension(3000,10));
		scrollenh.setViewportView(scrollenv);
		getContentPane().add(scrollenh);
		
		final JFrame f = this;
		datei.addMenuListener(new MenuListener() {
			public void menuSelected(MenuEvent e) {
				if (tabelle.getSelectedRow() != -1) {
					bearbeitenAbschlussarbeit.setEnabled(true);
/*# */				entfernenAbschlussarbeit.setEnabled(true);	/* #*/
				} else {
					bearbeitenAbschlussarbeit.setEnabled(false);
/*# */				entfernenAbschlussarbeit.setEnabled(false);	/* #*/
				}
			}
			public void menuDeselected(MenuEvent e) {		    }
			public void menuCanceled(MenuEvent e) {		    }
		});
		neueAbschlussarbeit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogAbschlussarbeit(f, null, datenbank);
				dataModel.fireTableDataChanged();
			}
		});
		bearbeitenAbschlussarbeit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabelle.getSelectedRow() != -1)
					new DialogAbschlussarbeit(f, datenbank.datenListe(tabelle.getSelectedRow()+1), datenbank);
				dataModel.fireTableDataChanged();
			}
		});
/*# */
		entfernenAbschlussarbeit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tabelle.getSelectedRow() != -1)
					A.loeschenAbschlussarbeit(datenbank.datenListe(tabelle.getSelectedRow()+1)[0], datenbank);
				dataModel.fireTableDataChanged();
			}
		});
/* #*/
		bearbeitenStammdatenStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogStammdatenStatus(f, datenbank.status(), datenbank);
			}
		});
		bearbeitenStammdatenStudiengang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogStammdatenStudiengang(f, datenbank.studiengaenge(), datenbank);
			}
		});
		bearbeitenProf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogStammdatenProf(f, datenbank.datenProf(), "Professor", datenbank);
			}
		});
		bearbeitenBetr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DialogStammdatenBetreuer(f, datenbank.datenBetreuer(), "Betreuerdaten", datenbank);
			}
		});
		beenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		datei.add(neueAbschlussarbeit);
		datei.add(bearbeitenAbschlussarbeit);
/*# */	datei.add(entfernenAbschlussarbeit);	/* #*/
		datei.add(bearbeitenStammdatenStatus);
		datei.add(bearbeitenStammdatenStudiengang);
		datei.add(bearbeitenProf);
		datei.add(bearbeitenBetr);
        datei.add(beenden);
        menue.add(datei);
        this.setJMenuBar(menue);
        
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
