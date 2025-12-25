package control;

import view.Grille;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PlayWave;
import view.Score;




public class EcouteurButon     implements ActionListener {
    Grille saGrille;
    private char XO = 'X';
    boolean jeuTermine = false;
    private int valeurDuScoreActuelle;
    private String getSaFenetre;
    private PlayWave playWave;


// c quoi difference entre implement et extend ????????????????????????????????????????????????????????????

    private int detecterVictoire(){
        JButton[][] T = this.saGrille.getLesButtons();

        boolean victoire = false;
        char quiGagne='?';

        System.out.print("-> verification des lignes");
        for(int ligne=0;(ligne<3) && (!victoire) && !(T[ligne][0].getText().equals("")) ;ligne++) //verif des 3 lignes
        if (T[ligne][0].getText().equals(T[ligne][1].getText())  && T[ligne][0].getText().equals(T[ligne][2].getText()))
        {victoire=true;quiGagne=T[ligne][0].getText().charAt(0);
            T[ligne][0].setBackground(Color.GREEN);
            T[ligne][1].setBackground(Color.GREEN);
            T[ligne][2].setBackground(Color.GREEN);
        }
        System.out.println(">>COLONNES");
        for(int col=0;(col<3) && (!victoire) && !(T[0][col].getText().equals(""));col++)  // verif des 3 colonnes
            if (T[0][col].getText().equals(T[1][col].getText())  && T[1][col].getText().equals(T[2][col].getText()))   {victoire=true;quiGagne=T[0][col].getText().charAt(0);
                T[0][col].setBackground(Color.GREEN);
                T[1][col].setBackground(Color.GREEN);
                T[2][col].setBackground(Color.GREEN);
            };
        // verif des 2 diagonales
        System.out.println(">>diag1");
        if (T[0][0].getText().equals(T[1][1].getText())  && T[1][1].getText().equals(T[2][2].getText()) && (!T[1][1].getText().equals("")))   {victoire=true;quiGagne=T[1][1].getText().charAt(0);T[0][0].setBackground(Color.GREEN);
            T[1][1].setBackground(Color.GREEN);
            T[2][2].setBackground(Color.GREEN);
        };
        System.out.println(">>diag2");
        if (T[0][2].getText().equals(T[1][1].getText())  && T[1][1].getText().equals(T[2][0].getText()) && (!T[1][1].getText().equals("")))   {victoire=true;quiGagne=T[1][1].getText().charAt(0);
            T[0][2].setBackground(Color.GREEN);
            T[1][1].setBackground(Color.GREEN);
            T[2][0].setBackground(Color.GREEN);};

        System.out.println(">>fini...");

        if (victoire)
        { switch(quiGagne)   {
            case 'X' :return 1;
            case 'O' :return 2;
        }
        }
        return -1;
    }


    public EcouteurButon(Grille g)
    {
        this.saGrille=g;


    }
    public void setXO(char XO) {
        this.XO = XO;
    }

    public char getXO() {
        return XO;
    }

    public void setJeuTermine(boolean jeuTermine) {
        this.jeuTermine = jeuTermine;
    }

    public boolean isJeuTermine() {
        return jeuTermine;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("clique sur button");
        System.out.println(" clic sur bouton");
        JButton leBoutonClique = (JButton) e.getSource(); // on recup la source de l'evenement qui est un Object
        // puis on transtype cet Object en JButton (polymorphisme)
        leBoutonClique.setFont(Score.maFaconDEcrire); // maFaconDecrire est public et static dans la classe Score

        if(!jeuTermine){
            System.out.println("Clic sur boutton");
            playWave = new PlayWave("smb_kick.wav");
            playWave.start();
            JButton leBoutonClique2 = (JButton) e.getSource();
        }

        if (!leBoutonClique.isSelected()) { // si ce bouton est cliquable
            if (XO == 'X') {

                leBoutonClique.setForeground(Color.red);
                leBoutonClique.setText("X");
                XO = 'O';
                // " Joueur rouge, votre tour "
            } else {
                leBoutonClique.setForeground(Color.yellow);
                leBoutonClique.setText("O");
                XO = 'X';
                // " Joueur jaune, votre tour "

            }
            leBoutonClique.setSelected(true); // ce bouton devient non cliquable
            saGrille.getSaFenetre().majIndicateurTour(XO);

        }

        int reponse = detecterVictoire();
        switch (reponse) {
            case 1: // Victoire des Rouges
                playWave = new PlayWave("power-up.wav");
                playWave.start();
                JOptionPane.showMessageDialog(null, "Victoire des ROUGES");

                // Mise à jour du score
                valeurDuScoreActuelle = this.saGrille.getSaFenetre().getScoreRouge().getValeur();
                this.saGrille.getSaFenetre().getScoreRouge().getLeScore().setText("" + (valeurDuScoreActuelle + 1));
                this.saGrille.getSaFenetre().getScoreRouge().setValeur(valeurDuScoreActuelle + 1);

                // RESET AUTOMATIQUE ICI
                Actions.action1(this.saGrille); // <--- AJOUTE CETTE LIGNE

                // Tu n'as plus besoin de "jeuTermine = true" car le jeu redémarre
                break;

            case 2: // Victoire des Jaunes
                playWave = new PlayWave("smb_kick.wav");
                playWave.start();
                JOptionPane.showMessageDialog(null, "Victoire des JAUNES");

                // Mise à jour du score
                valeurDuScoreActuelle = this.saGrille.getSaFenetre().getScoreJaune().getValeur();
                this.saGrille.getSaFenetre().getScoreJaune().getLeScore().setText("" + (valeurDuScoreActuelle + 1));
                this.saGrille.getSaFenetre().getScoreJaune().setValeur(valeurDuScoreActuelle + 1);


                Actions.action1(this.saGrille);

                break;
        }
    }
}
