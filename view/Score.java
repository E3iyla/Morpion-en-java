package view;

import javax.swing.*;
import java.awt.*;

public class Score extends JPanel {
    private int valeur;          // la valeur du score
    private JLabel leScore;      // le texte affiché
    public static Font maFaconDEcrire = new Font("TimesRoman", Font.BOLD, 60); // police d'écriture


    // Constructeur -> il reçoit un entier nommé couleur (1 = rouge, 2 = jaune)
    public Score(int couleur) {
        valeur = 0; // score initial
        leScore = new JLabel("" + valeur);
        if (couleur == 1) {
            leScore.setForeground(Color.red);

        }else {
            leScore.setForeground(Color.yellow);

        }

        leScore.setFont(maFaconDEcrire);
        this.add(leScore);

    }

    // Getter et setter
    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
        leScore.setText("" + valeur); // met à jour l'affichage
    }

    public JLabel getLeScore() {
        return leScore;
    }


}
