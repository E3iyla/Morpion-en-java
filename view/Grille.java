package view;
import control.EcouteurButon;

import javax.swing.*;

import java.awt.*;

public class Grille extends JPanel {
    public JButton[][] getLesButtons() {
        return lesButtons;
    }

    private JButton[][] lesButtons;
    private Fenetre saFenetre;



    public EcouteurButon getMonEcouteur() {
        return monEcouteur;
    }

    public void setMonEcouteur(EcouteurButon monEcouteur) {
        this.monEcouteur = monEcouteur;
    }

    public void setLesButtons(JButton[][] lesButtons) {
        this.lesButtons = lesButtons;
    }

    EcouteurButon monEcouteur = new EcouteurButon(this);


    public Grille(Fenetre f) {
        saFenetre = f; // on va la stoker ici on memorise l'adresse memoire de la fenetre du jeu
        this.lesButtons = new JButton [3][3];
        this.setLayout(new GridLayout(3, 3));
         monEcouteur = new EcouteurButon(this);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.lesButtons[i][j] = new JButton();
                this.add(this.lesButtons[i][j]);
                this.lesButtons[i][j].addActionListener(monEcouteur);

            }

        }
    }
    public Fenetre getSaFenetre() {
        return saFenetre;
    }


    public AbstractButton[][] getLesBoutons() {
        return lesButtons;
    }
}
