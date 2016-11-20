package dominion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by florian on 13/11/2016.
 */
public class Control_Partie implements ActionListener{

    private View_Partie viewPartie;
    private Model_Partie modelPartie;

    /**
     * Constructeur du controleur lié à la partie
     * @param vue (vue de la partie)
     * @param modele (données nécessaires au fonctionnement de la partie)
     */
    Control_Partie(View_Partie vue, Model_Partie modele)
    {
        this.viewPartie = vue;
        this.modelPartie = modele;
        //this.viewAccueil.setButtonControl(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //to do
    }
}
