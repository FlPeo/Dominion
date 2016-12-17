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

        System.out.println(modelPartie.getEtapesTour());
        System.out.println(i);

        if(modelPartie.getEtapesTour()  == EtapesTour.ACTION){
            if(i==17){
                modelPartie.finTourAction();
            }
            else if(isCarteMain(i)){
                i -= 20;
                Cards c = modelPartie.getJoueurCourrant().getCarteMain(i);
                if(c.isCarteAction()){
                    ActionCards carteAction = (ActionCards)c;
                    if(carteAction.isCarteADefausserApresAction()){
                        modelPartie.getJoueurCourrant().carteVaDeMainADefausse(i);
                    }

                    carteAction.rempliActionsTour(modelPartie);
                    modelPartie.actions();
                    viewPartie.majVue();
                }
            }
        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.ACHAT){

            if(i==17){
                boolean changeJoueur = modelPartie.finTourAchat();
                //Notifie le joueur suivant que c'est son tour
                viewPartie.jOptionMessage("Votre tour", "Joueur " + modelPartie.getJoueurAdverse().getNomJoueur() + ". C'est votre tour");

                viewPartie.majVue();
                if(changeJoueur) viewPartie.changeJoueur();
                return;
            }

            if(isBoutonChoix(i) || isCarteMain(i)){
                return;
            }

            boolean possible = false;
            if(isCarteVictoireTas(i)){
                //notification : alerte achat carte malédiction
                boolean accepteAchat = true;

                if(i==3)
                    accepteAchat = viewPartie.showOptionDialog("Malédiction", "Voulez-vous vraiment acheter une carte malédiction ?");


                if (accepteAchat)
                    possible = modelPartie.tryAchatCarteVictoire(i);

            }
            else if(isCarteActionTas(i)){
                i-=4;
                possible = modelPartie.tryAchatCarteAction(i);
            }
            else if(isCarteTresorTas(i)){
                i-=14;
                possible = modelPartie.tryAchatCarteTresor(i);
            }

            if(possible){
                boolean changeJoueur = modelPartie.finTourAchat();
                viewPartie.majVue();
                if(changeJoueur){
                    viewPartie.changeJoueur();
                }
            }

            if(modelPartie.finDePartie()){
                viewPartie.dispose();
                Model_Accueil modelAccueil = new Model_Accueil(); //ModelAccueil est-il vraiment utile ?
                View_Accueil accueil = new View_Accueil();
                new Control_Accueil(accueil, modelAccueil);
            }
        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.CHOIX_1_CARTE_DE_MAIN){

        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.CHOIX_1_CARTE_TRESOR_DE_MAIN){

        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.CHOIX_1_CARTE_ACTION_DE_MAIN){
            System.out.println("ptr");
            if(isCarteMain(i)){
                System.out.println("ptr2");
                i-=17+NB_BOUTONS_CHOIX;

                Cards c = modelPartie.getJoueurCourrant().getCarteMain(i);
                if(c.isCarteAction()){
                    modelPartie.addCarteChoisieParJoueur(c);
                    modelPartie.setEtapesTour(EtapesTour.ACTION);

                    modelPartie.actions();
                    viewPartie.majVue();
                }
            }
            else if(i==17){
                modelPartie.removeFirstAction();
                modelPartie.setEtapesTour(EtapesTour.ACTION);

                modelPartie.actions();
                viewPartie.majVue();
            }
        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.CHOIX_JUSQUA_4_CARTES_DE_MAIN){

        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.DEFAUSSER_AUTANT_CARTES_MAIN_QUON_VEUT){

        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.TOUS_ADVERSAIRES_DEFAUSSENT_POUR_PLUS_QUE_3_CARTES_EN_MAIN){

        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.TOUS_ADVERSAIRES_CHOIX_CARTE_VICTOIRE_DANS_MAIN){

        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.TOUS_ADVERSAIRES_DEVOILENT_2_PREMIERES_CARTES_DECK){

        }
        else if(modelPartie.getEtapesTour()  == EtapesTour.TOUS_ADVERSAIRES_DEVOILENT_1ERE_CARTE_DECK){

        }
    }




    public boolean isCarteVictoireTas(int i){
        return i<=3;
    }

    public boolean isCarteActionTas(int i){
        return i>=4 && i<=13;
    }

    public boolean isCarteTresorTas(int i){
        return i>=14 && i<=16;
    }

    public boolean isBoutonChoix(int i){
        return i>=17 && i<=16+NB_BOUTONS_CHOIX;
    }

    public boolean isCarteMain(int i){
        return i>=17+NB_BOUTONS_CHOIX;
    }

}
