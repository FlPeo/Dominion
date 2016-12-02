package dominion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mlucile on 05/11/16.
 */
public class Control_Accueil implements ActionListener
{
    private View_Accueil viewAccueil;
    private Model_Accueil modelAccueil;

    /**
     * Constructeur du controleur lié au menu de l'viewAccueil
     * @param accueil (vue de l'viewAccueil)
     * @param modelAccueil (données nécessaires au fonctionnement de l'viewAccueil)
     */
    Control_Accueil(View_Accueil accueil, Model_Accueil modelAccueil)
    {
        this.viewAccueil = accueil;
        this.modelAccueil = modelAccueil;
        this.viewAccueil.setButtonControl(this);
    }

    /**
     * Détermine les actions à faire en fonction du bouton choisi
     * @param e (evenement produit par l'utilisateur)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(viewAccueil.getLancerPartie()))
        {
            modelAccueil.demarrerPartie(); //ModelAccueil est-il vraiment utile ?
            viewAccueil.dispose();

            Joueur[] joueurs = new Joueur[2];
            joueurs[0] = new Joueur("joueur1", null);   //a terme, il faudra donner le deck en parametre
            joueurs[1] = new Joueur("joueur2", null);
            Partie partie = new Partie(null, null);    //il faudra appeler la methode static pour creer la partie
            partie.setJoueurs(joueurs);
            View_Partie vuePartie = new View_Partie(partie);
            new Control_Partie(vuePartie, partie);
        }
        else if(e.getSource().equals(viewAccueil.getCredit()))
        {
            viewAccueil.jOptionMessage("Crédits",
                    "Michael BOUTBOUL\n"
                    + "Marie-Lucile CANIARD\n"
                    + "Sylvain GUYOT\n"
                    + "Kevin LIMACHER\n"
                    + "Adonis N'DOLO\n"
                    + "Florian PARTY");
        }
        else if(e.getSource().equals(viewAccueil.getQuitterJeu()))
        {
            System.exit(0);
        }
    }
}
