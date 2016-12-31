package dominion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by mlucile on 16/12/16.
 */
public class Control_Partie_Mouse extends MouseAdapter
{
    View_Plateau vuePlateau;
    Partie partie;
    View_Accueil vueAccueil;

    Control_Partie_Mouse(Partie partie, View_Plateau vuePlateau, View_Accueil vueAccueil)
    {
        this.vueAccueil = vueAccueil;
        this.partie = partie;
        this.vuePlateau = vuePlateau;
        vuePlateau.setListeners(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        // Si c'est un tour action
        if(partie.getEtapesTour()  == EtapesTour.ACTION)
        {
            // Si clique sur le bouton finTour
            if(e.getX()>vuePlateau.getCoordBouton()[0]
                    && e.getY()>vuePlateau.getCoordBouton()[1]
                    && e.getX()<vuePlateau.getCoordBouton()[2]
                    && e.getY()<vuePlateau.getCoordBouton()[3])
            {
                partie.finTourAction();
                vuePlateau.majVue();
            }
            else
            {
                // Main
                for(int i=0; i<vuePlateau.getCoordCartesMain().size(); i++)
                {
                    if (e.getX() > vuePlateau.getCoordCartesMain().get(i).get(0)
                            && e.getY() > vuePlateau.getCoordCartesMain().get(i).get(1)
                            && e.getX() < vuePlateau.getCoordCartesMain().get(i).get(2)
                            && e.getY() < vuePlateau.getCoordCartesMain().get(i).get(3))
                    {
                        Cards c = partie.getJoueurCourrant().getCarteMain(i);
                        if(c.isCarteAction())
                        {
                            ActionCards carteAction = (ActionCards)c;
                            if(carteAction.isCarteADefausserApresAction())
                            {
                                partie.getJoueurCourrant().carteVaDeMainADefausse(i);
                            }

                            carteAction.rempliActionsTour(partie);
                            partie.actions();
                            vuePlateau.majVue();
                        }
                    }
                }
            }
        }

        // Si c'est un tour achat
        else if(partie.getEtapesTour()  == EtapesTour.ACHAT)
        {
            // Si clique sur le bouton passerTour
            if(e.getX()>vuePlateau.getCoordBouton()[0]
                    && e.getY()>vuePlateau.getCoordBouton()[1]
                    && e.getX()<vuePlateau.getCoordBouton()[2]
                    && e.getY()<vuePlateau.getCoordBouton()[3])
            {
                boolean joueurAChange = partie.finTourAchat();
                //Notifie le joueur suivant que c'est son tour
                vuePlateau.jOptionMessage("Votre tour",
                        "Joueur " + partie.getJoueurCourrant().getNomJoueur() + ". C'est votre tour");    //A deplacer dans le if ? (si plusieurs tours d'achat)

                vuePlateau.majVue();
                if(joueurAChange) vuePlateau.changeJoueur();
                return;
            }

            // Si clique sur une carte de la main
            for(int i=0; i<vuePlateau.getCoordCartesMain().size(); i++)
            {
                if (e.getX() > vuePlateau.getCoordCartesMain().get(i).get(0)
                        && e.getY() > vuePlateau.getCoordCartesMain().get(i).get(1)
                        && e.getX() < vuePlateau.getCoordCartesMain().get(i).get(2)
                        && e.getY() < vuePlateau.getCoordCartesMain().get(i).get(3))
                {
                   return;
                }
            }

            // Si clique sur carte victoire
            boolean possible = false;
            for(int i=0; i<vuePlateau.getCoordCartesVictoires().length; i++)
            {
                if(e.getX()>vuePlateau.getCoordCartesVictoires()[i][0]
                        && e.getY()>vuePlateau.getCoordCartesVictoires()[i][1]
                        && e.getX()<vuePlateau.getCoordCartesVictoires()[i][2]
                        && e.getY()<vuePlateau.getCoordCartesVictoires()[i][3])
                {
                    //notification : alerte achat carte malédiction
                    boolean accepteAchat = true;
                    if (i == 4) {
                        accepteAchat = vuePlateau.showOptionDialog("Malédiction", "Voulez-vous vraiment acheter une carte malédiction ?");

                        if (accepteAchat)
                            possible = partie.tryAchatCarteVictoire(i);
                    }
                }
            }

            for(int i=0; i<vuePlateau.getCoordCartesActions().length; i++)
            {
                if (e.getX() > vuePlateau.getCoordCartesActions()[i][0]
                        && e.getY() > vuePlateau.getCoordCartesActions()[i][1]
                        && e.getX() < vuePlateau.getCoordCartesActions()[i][2]
                        && e.getY() < vuePlateau.getCoordCartesActions()[i][3])
                {
                    possible = partie.tryAchatCarteAction(i);
                }
            }

            for(int i=0; i<vuePlateau.getCoordCartesTresors().length; i++)
            {
                if(e.getX()>vuePlateau.getCoordCartesTresors()[i][0]
                        && e.getY()>vuePlateau.getCoordCartesTresors()[i][1]
                        && e.getX()<vuePlateau.getCoordCartesTresors()[i][2]
                        && e.getY()<vuePlateau.getCoordCartesTresors()[i][3])
                {
                    possible = partie.tryAchatCarteTresor(i);
                }
            }

            if(possible)
            {
                if(partie.finTourAchat()){
                    vuePlateau.jOptionMessage("Votre tour",
                            "Joueur " + partie.getJoueurCourrant().getNomJoueur() + ". C'est votre tour");
                }

                vuePlateau.majVue();
            }

            if(partie.finDePartie())
            {
                vueAccueil.afficherMenu();
                Model_Accueil modelAccueil = new Model_Accueil(); //ModelAccueil est-il vraiment utile ?
                new Control_Accueil(vueAccueil, modelAccueil);
            }
        }

        else if(partie.getEtapesTour()  == EtapesTour.CHOIX_JUSQUA_4_CARTES_DE_MAIN){

        }
        else if(partie.getEtapesTour()  == EtapesTour.DEFAUSSER_AUTANT_CARTES_MAIN_QUON_VEUT){

        }
        else if(partie.getEtapesTour()  == EtapesTour.TOUS_ADVERSAIRES_DEFAUSSENT_POUR_PLUS_QUE_3_CARTES_EN_MAIN){

        }
        else if(partie.getEtapesTour()  == EtapesTour.TOUS_ADVERSAIRES_CHOIX_CARTE_VICTOIRE_DANS_MAIN){

        }
        else if(partie.getEtapesTour()  == EtapesTour.TOUS_ADVERSAIRES_DEVOILENT_2_PREMIERES_CARTES_DECK){

        }
        else if(partie.getEtapesTour()  == EtapesTour.TOUS_ADVERSAIRES_DEVOILENT_1ERE_CARTE_DECK){

        }



/*
        // Victoire
        for(int i=0; i<vuePlateau.getCoordCartesVictoires().length; i++)
        {
            if(e.getX()>vuePlateau.getCoordCartesVictoires()[i][0]
                    && e.getY()>vuePlateau.getCoordCartesVictoires()[i][1]
                    && e.getX()<vuePlateau.getCoordCartesVictoires()[i][2]
                    && e.getY()<vuePlateau.getCoordCartesVictoires()[i][3])
            {
                System.out.println("Ok ici victoire " + i);
            }
        }

        // Trésor
        for(int i=0; i<vuePlateau.getCoordCartesTresors().length; i++)
        {
            if(e.getX()>vuePlateau.getCoordCartesTresors()[i][0]
                    && e.getY()>vuePlateau.getCoordCartesTresors()[i][1]
                    && e.getX()<vuePlateau.getCoordCartesTresors()[i][2]
                    && e.getY()<vuePlateau.getCoordCartesTresors()[i][3])
            {
                System.out.println("Ok ici trésors " + i);
            }
        }

        // Action
        for(int i=0; i<vuePlateau.getCoordCartesActions().length; i++)
        {
            if (e.getX() > vuePlateau.getCoordCartesActions()[i][0]
                    && e.getY() > vuePlateau.getCoordCartesActions()[i][1]
                    && e.getX() < vuePlateau.getCoordCartesActions()[i][2]
                    && e.getY() < vuePlateau.getCoordCartesActions()[i][3])
            {
                System.out.println("Ok ici actions  " + i);
            }
        }

        // Main
        for(int i=0; i<vuePlateau.getCoordCartesMain().size(); i++)
        {
            if (e.getX() > vuePlateau.getCoordCartesMain().get(i).get(0)
                    && e.getY() > vuePlateau.getCoordCartesMain().get(i).get(1)
                    && e.getX() < vuePlateau.getCoordCartesMain().get(i).get(2)
                    && e.getY() < vuePlateau.getCoordCartesMain().get(i).get(3))
            {
                System.out.println("Ok ici main  " + i);
            }
        }
*/
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
    }
}
