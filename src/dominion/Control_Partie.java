package dominion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static dominion.View_Partie.NB_BOUTONS_CHOIX;

/**
 * Created by florian on 13/11/2016.
 */
public class Control_Partie implements ActionListener{

    private View_Partie viewPartie;
    private Partie modelPartie;

    /**
     * Constructeur du controleur lié à la partie
     * @param vue (vue de la partie)
     * @param modele (données nécessaires au fonctionnement de la partie)
     */
    Control_Partie(View_Partie vue, Partie modele)
    {
        this.viewPartie = vue;
        this.modelPartie = modele;
        viewPartie.setActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JButton bouton = (JButton)e.getSource();
        int i = Integer.parseInt(bouton.getActionCommand());

        if(modelPartie.getEtapesTour()  == EtapesTour.ACTION){
            if(i==17){
                modelPartie.finTourAction();
            }
            else if(isCarteMain(i)){
                i -= 20;
                Cards c = modelPartie.getJoueurCourrant().getCarteMain(i);
                if(c.isCarteAction()){
                    ((ActionCards)c).action();
                    if(((ActionCards)c).isCarteADefausserApresAction()){
                        modelPartie.getJoueurCourrant().removeCarteMain(i);
                    }
                    modelPartie.finTourAction();
                    viewPartie.majVue();
                }
            }
        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.ACHAT){
            if(i==17){
                boolean changeJoueur = modelPartie.finTourAchat();
                viewPartie.majVue();
                if(changeJoueur) viewPartie.changeJoueur();
                return;
            }

            if(isBoutonChoix(i) || isCarteMain(i)){
                return;
            }

            boolean possible = false;
            if(isCarteVictoire(i)){
                possible = modelPartie.tryAchatCarteVictoire(i);

            }
            else if(isCarteAction(i)){
                i-=4;
                possible = modelPartie.tryAchatCarteAction(i);
            }
            else if(isCarteTresor(i)){
                i-=14;
                possible = modelPartie.tryAchatCarteTresor(i);
            }

            if(possible){
                boolean changeJoueur = modelPartie.finTourAchat();
                viewPartie.majVue();
                if(changeJoueur) viewPartie.changeJoueur();
            }

            if(modelPartie.finDePartie()){
                viewPartie.dispose();
                Model_Accueil modelAccueil = new Model_Accueil(); //ModelAccueil est-il vraiment utile ?
                View_Accueil accueil = new View_Accueil();
                new Control_Accueil(accueil, modelAccueil);
            }

        }
    }




    public boolean isCarteVictoire(int i){
        return i<=3;
    }

    public boolean isCarteAction(int i){
        return i>=4 && i<=13;
    }

    public boolean isCarteTresor(int i){
        return i>=14 && i<=16;
    }

    public boolean isBoutonChoix(int i){
        return i>=17 && i<=16+NB_BOUTONS_CHOIX;
    }

    public boolean isCarteMain(int i){
        return i>=17+NB_BOUTONS_CHOIX;
    }

}
