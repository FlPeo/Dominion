package dominion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mlucile on 05/11/16.
 */
public class Control_Accueil implements ActionListener
{
    private View_Accueil viewAccueil;

    /**
     * Constructeur du controleur lié au menu de l'viewAccueil
     * @param accueil (vue de l'viewAccueil)
     */
    Control_Accueil(View_Accueil accueil)
    {
        this.viewAccueil = accueil;
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
            viewAccueil.dispose();

            String[] joueurs = new String[2];
            joueurs[0] = "joueur1";
            joueurs[1] = "joueur2";
            Partie partie= Partie.creerPartie(joueurs);


            viewAccueil.creerWidgetPartie(partie);
            Control_Partie_Mouse controlMouse = new Control_Partie_Mouse(partie, viewAccueil.getVuePlateau(), viewAccueil);
            viewAccueil.creerMenuPartie(partie, controlMouse);
            viewAccueil.validate();
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
