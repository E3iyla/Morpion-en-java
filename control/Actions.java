package control;

import view.Grille;

public class Actions {

    public static void action1(Grille laGrille)
    {
        resetLeJeu(laGrille);

    }
    private static void resetLeJeu(Grille laGrille)
    {
        System.out.println(" RESET ");
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++) {
                laGrille.getLesBoutons()[i][j].setText("");//il faut écrire le getter
                laGrille.getLesBoutons()[i][j].setSelected(false);
                laGrille.getLesBoutons()[i][j].setBackground(null);
            }
        laGrille.getMonEcouteur().setJeuTermine(false);//il faut écrire le setter
        laGrille.getMonEcouteur().setXO('X');//il faut écrire le setter

    }
    public static void action2()
    {
        quitterLeJeu();
    }

    private  static void quitterLeJeu()
    {
        System.out.println("bye bye ");
        System.exit(0);

    }
}