import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mlucile on 05/11/16.
 */
public class Control_Accueil implements ActionListener
{
    private Accueil accueil;
    private Model_Accueil modelAccueil;

    /**
     * Constructeur du controleur lié au menu de l'accueil
     * @param accueil (vue de l'accueil)
     * @param modelAccueil (données nécessaires au fonctionnement de l'accueil)
     */
    Control_Accueil(Accueil accueil, Model_Accueil modelAccueil)
    {
        this.accueil = accueil;
        this.modelAccueil = modelAccueil;
        this.accueil.setButtonControl(this);
    }

    /**
     * Détermine les actions à faire en fonction du bouton choisi
     * @param e (evenement produit par l'utilisateur)
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(accueil.getLancerPartie()))
        {
            modelAccueil.demarrerPartie();
        }
        else if(e.getSource().equals(accueil.getCredit()))
        {
            accueil.jOptionMessage("Crédits",
                    "Michael BOUTBOUL\n"
                    + "Marie-Lucile CANIARD\n"
                    + "Sylvain GUYOT\n"
                    + "Kevin LIMACHER\n"
                    + "Adonis N'DOLO\n"
                    + "Florian PARTY");
        }
        else if(e.getSource().equals(accueil.getQuitterJeu()))
        {
            System.exit(0);
        }
    }
}
