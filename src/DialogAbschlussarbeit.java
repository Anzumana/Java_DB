package oberflaeche;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class DialogAbschlussarbeit extends JDialog {
    private final Datenbank datenbank;
    private JPanel p;
    private JLabel text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11, text12, text13, text14, text15, text16;
    private JTextField titel, anmeldedatum, nameStudent, matrikelnummer, semester;
    private JComboBox art, status, note, studiengang, nameBetreuer, nameProf1, nameProf2;
    private JMenuBar menue;
    private JMenu datei;
    private JMenuItem beenden, speichern;
    
    public DialogAbschlussarbeit(JFrame fenster, final String[] datensatz, Datenbank datenbank1){
        super(fenster, true);
	datenbank = datenbank1;
        final JDialog dialog = this;
	p = new JPanel();
        text1 = new JLabel("Abschlussarbeit");
	text2 = new JLabel("Titel");
	text3 = new JLabel("Art");
	text4 = new JLabel("Status");
	text5 = new JLabel("Anmeldedatum");
	text6 = new JLabel("Note");
	text7 = new JLabel("Student");
	text8 = new JLabel("Name");
	text9 = new JLabel("Matrikelnummer");
	text10 = new JLabel("Studiengang");
	text11 = new JLabel("Semester");
	text12 = new JLabel("Betreuer");
	text13 = new JLabel("Betreuer");
	text14 = new JLabel("Professor");
	text15 = new JLabel("Pruefer 1");
	text16 = new JLabel("Pruefer 2");
	titel = new JTextField();
	anmeldedatum = new JTextField();
	nameStudent = new JTextField();
	matrikelnummer = new JTextField();
	semester = new JTextField();
	art = new JComboBox(new String[] {"Auswaehlen", "Bachelorarbeit", "Masterarbeit", "Diplomarbeit"});
	status = new JComboBox(auswaehlenDazufuegen(datenbank.status()));
	note = new JComboBox(new String[]{"0.0", "1.0", "1.3", "1.7", "2.0", "2.3", "2.7", "3.0", "3.3", "3.7", "4.0", "5.0"});//noteDaten);
	studiengang = new JComboBox(auswaehlenDazufuegen(datenbank.studiengaenge()));
	nameBetreuer = new JComboBox(ersteElemente(datenbank.datenBetreuer()));
	nameProf1 = new JComboBox(ersteElemente(datenbank.datenProf()));
	nameProf2 = new JComboBox(ersteElemente(datenbank.datenProf()));
	menue = new JMenuBar();
	datei = new JMenu("Datei");
	beenden = new JMenuItem("Beenden");
	speichern = new JMenuItem("Speichern");
        this.setTitle("Abschlussarbeiten");
        art.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getID() == ItemEvent.ITEM_STATE_CHANGED
                        && e.getStateChange() == ItemEvent.SELECTED) {
                    if(((String)e.getItem()).equals("Bachelorarbeit")) {
                        nameProf2.setEnabled(false);
                    } else {
                        nameProf2.setEnabled(true);
                    }
                }
            }
        });

        p.setLayout(new GridBagLayout());
        p.add(text1, new GridBagConstraints(0,0,6,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text2, new GridBagConstraints(0,1,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(titel, new GridBagConstraints(1,1,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(text3, new GridBagConstraints(2,1,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(art, new GridBagConstraints(3,1,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(text4, new GridBagConstraints(4,1,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(status, new GridBagConstraints(5,1,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text5, new GridBagConstraints(0,2,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(anmeldedatum, new GridBagConstraints(1,2,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(text6, new GridBagConstraints(2,2,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(note, new GridBagConstraints(3,2,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text7, new GridBagConstraints(0,3,6,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text8, new GridBagConstraints(0,4,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(nameStudent, new GridBagConstraints(1,4,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(text9, new GridBagConstraints(2,4,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(matrikelnummer, new GridBagConstraints(3,4,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(text10, new GridBagConstraints(4,4,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(studiengang, new GridBagConstraints(5,4,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text11, new GridBagConstraints(0,5,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(semester, new GridBagConstraints(1,5,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text12, new GridBagConstraints(0,6,6,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text13, new GridBagConstraints(0,7,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(nameBetreuer, new GridBagConstraints(1,7,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text14, new GridBagConstraints(0,8,6,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));

        p.add(text15, new GridBagConstraints(0,9,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(nameProf1, new GridBagConstraints(1,9,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(text16, new GridBagConstraints(2,9,1,1,0,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));
        p.add(nameProf2, new GridBagConstraints(3,9,1,1,1,1,GridBagConstraints.CENTER ,GridBagConstraints.HORIZONTAL,new Insets(0,0,0,0),0,0));


        if (datensatz != null) {
/*            titel.setText(datensatz[0]);
            anmeldedatum.setText(datensatz[4]);
            nameStudent.setText(datensatz[6]);
            matrikelnummer.setText(datensatz[7]);
            semester.setText(datensatz[9]);
            studiengang.setSelectedItem(datensatz[8]);
            art.setSelectedItem(datensatz[1]);
            status.setSelectedItem(datensatz[2]);
            note.setSelectedItem(datensatz[5]);
            studiengang.setSelectedItem(datensatz[8]);
            nameBetreuer.setSelectedItem(datensatz[18]);
            nameProf1.setSelectedItem(datensatz[10]);
            nameProf2.setSelectedItem(datensatz[14]);
*/  
            titel.setText(datensatz[0]);
            matrikelnummer.setText(datensatz[1]);
            nameBetreuer.setSelectedItem(datensatz[2]);
            nameProf1.setSelectedItem(datensatz[3]);
            nameProf2.setSelectedItem(datensatz[4]);
            anmeldedatum.setText(datensatz[5]);
            status.setSelectedItem(datensatz[6]);
            note.setSelectedItem(datensatz[7]);
	    String[] student = datenbank.datenStudent(datensatz[1]);
            nameStudent.setText(student[1]);
            semester.setText(student[2]);
            studiengang.setSelectedItem(student[3]);
	    
  	}
        this.setContentPane(p);

        speichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(art.getSelectedIndex() == 0 || status.getSelectedIndex() == 0 || studiengang.getSelectedIndex() == 0 || nameProf1.getSelectedIndex() == 0 || (nameProf2.isEnabled() == true && nameProf2.getSelectedIndex() == 0) || nameBetreuer.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(dialog, "Sie haben ein Feld nicht ausgewaehlt.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
            	}
                String[] datensatz1 = new String[21];
                datensatz1[0] = titel.getText();
                datensatz1[1] = (String) art.getSelectedItem();
                datensatz1[2] = (String) status.getSelectedItem();
                datensatz1[4] = anmeldedatum.getText();
                datensatz1[5] = (String) note.getSelectedItem();
                datensatz1[6] = nameStudent.getText();
                datensatz1[7] = matrikelnummer.getText();
                datensatz1[8] = (String) studiengang.getSelectedItem();
                datensatz1[9] = semester.getText();
                
                datensatz1[11] = datenbank.datenProf()[nameProf1.getSelectedIndex()-1][1];
                if (nameProf2.isEnabled() == true) {
                	datensatz1[15] = datenbank.datenProf()[nameProf2.getSelectedIndex()-1][1];
                }
                datensatz1[18] = datenbank.datenBetreuer()[nameBetreuer.getSelectedIndex()-1][1];
/*# */
			String fehler = null;
			if (datensatz == null) {
				fehler = A.hinzufuegenAbschlussarbeit(datensatz1, datenbank);
			} else {
				fehler = A.aendernAbschlussarbeit(datensatz1, datensatz[0], datenbank);
			}
/* #*/
//#                String fehler = A.speichernObjekt(datensatz1,datenbank);
                if (fehler == null) {
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(dialog, "" + fehler, "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        beenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();//schliesst Fenster, Reseourcen Freigabe
            }
        });
        datei.add(speichern);
        datei.add(beenden);
        menue.add(datei);
        this.setJMenuBar(menue);

        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private String[] auswaehlenDazufuegen(String[] s) {
        String[] s1 = new String[s.length+1];
        s1[0] = "Auswaehlen";
        System.arraycopy(s, 0, s1, 1, s.length);
        return s1;
    }
    private String[] ersteElemente(String[][] s) {
        String[] s1 = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            s1[i] = s[i][0];
        }
        return auswaehlenDazufuegen(s1);
    }
}



