package view;

import java.awt.*;
import java.awt.event.*;
import control.Actions;



import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener {
    // Attributs
    private int largeur;
    private int hauteur;
    private Grille laGrille; // notre panneau de 3x3 boutons
    private Score leScoreRouge;
    private Score leScoreJaune;
    private JButton reset;

    private char XO = 'X';
    private boolean jeuTermine = false;

    private JPanel indicTour;
    private JLabel texte;



    public void majIndicateurTour(char prochainJoueur) {
        if (prochainJoueur == 'X') {
            indicTour.setBackground(Color.red);
            texte.setText("Joueur rouge, votre tour");
        } else {
            indicTour.setBackground(Color.yellow);
            texte.setText("Joueur jaune, votre tour");
        }
    }


    // Constructeur
    public Fenetre(int W, int H) {
        laGrille = new Grille(this);
        this.largeur = W;
        this.hauteur = H;

        reset = new JButton("Reset");
        reset.addActionListener(this);


        BorderLayout layout = new BorderLayout(); // 5 ZONES la fenetre

        setLayout(layout); // le layout -> dans la fenetre

        indicTour = new JPanel();
        indicTour.setBackground(Color.red); // Rouge car on commance par oruge
        this.add(indicTour, BorderLayout.NORTH);


        texte = new JLabel("Joueur rouge, votre tour");
        indicTour.add(texte);


        this.leScoreRouge = new Score(1); // rouge
        this.leScoreJaune = new Score(2); // jaune


        // nord
        this.add(reset, BorderLayout.SOUTH);
        this.add(leScoreJaune, layout.EAST);
        this.add(leScoreRouge, layout.WEST);
        //this.add(button2, BorderLayout.SOUTH);
        //this.add(button3, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        laGrille = new Grille(this); // on crée une grille
        this.add(laGrille, layout.CENTER); // on la place au centre

        //la taille
        this.setSize(largeur, hauteur);

        // le titre
        this.setTitle("Morpion");

        Toolkit toolkit = this.getToolkit();
        Dimension laDimension = toolkit.getScreenSize();
        setLocation((int) ((laDimension.getWidth() - largeur) / 2), (int) ((laDimension.getHeight() - hauteur) / 2));


        //fermeture de la fenetre
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.addWindowListener(new WindowListener() { // object interne annonyme car obj instancié n est pas nommé donc memorisé par aucune variable
            // ecouteur interne annonyme car elle n'est pas a l'internierer de la classe concerné

            @Override
            public void windowOpened(WindowEvent e) {   }

            @Override
            public void windowClosing(WindowEvent e) {
                Actions.action2();
            }

            @Override
            public void windowClosed(WindowEvent e) {   }

            @Override
            public void windowIconified(WindowEvent e) {   }

            @Override
            public void windowDeiconified(WindowEvent e) {   }

            @Override
            public void windowActivated(WindowEvent e) {   }

            @Override
            public void windowDeactivated(WindowEvent e) {   }
        });

    }



    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public Grille getLaGrille() {
        return laGrille;
    }

    public void setLaGrille(Grille laGrille) {
        this.laGrille = laGrille;
    }

    public Score getLeScoreRouge() {
        return leScoreRouge;
    }

    public void setLeScoreRouge(Score leScoreRouge) {
        this.leScoreRouge = leScoreRouge;
    }

    public Score getLeScoreJaune() {
        return leScoreJaune;
    }

    public void setLeScoreJaune(Score leScoreJaune) {
        this.leScoreJaune = leScoreJaune;
    }

    public JButton getReset() {
        return reset;
    }

    public void setReset(JButton reset) {
        this.reset = reset;
    }

    public void setJeuTermine(boolean jeuTermine) {
        this.jeuTermine = jeuTermine;
    }

    public void setXO(char XO) {
        this.XO = XO;
    }

    public char getXO() {
        return XO;
    }

    public boolean isJeuTermine() {
        return jeuTermine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Actions.action1(laGrille);
        }

    public Score getScoreRouge() {
        return leScoreRouge;
    }

    public Score getScoreJaune() {
        return leScoreJaune;
    }
    public Grille  getGrille() {
        return laGrille;
    }


}





