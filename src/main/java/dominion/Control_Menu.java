package dominion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Flo on 31/12/2016.
 */
public class Control_Menu implements ActionListener{
    private Partie partie;
    private Control_Partie_Mouse control;
    private View_Plateau plateau;

    public Control_Menu(Partie p, Control_Partie_Mouse cm, View_Plateau pl){
        partie = p;
        control = cm;
        plateau = pl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem)e.getSource();

        switch (item.getActionCommand()){
            case "save":
                try {
                    partie.sauvegarder();
                } catch (SauvegardeException e1) {
                    afficherErreurSauvegarde(e1);
                }
                break;
            case "load":
                try {
                    partie = Partie.getSauvegardePartie();
                    control.setPartie(partie);
                    plateau.setPartie(partie);
                    plateau.majVue();
                } catch (SauvegardeException e1) {
                    afficherErreurGetSauvegarde(e1);
                }
                break;
            case "quit":
                if(plateau.showOptionDialog("Quitter", "Êtes-vous sûr de vouloir quitter ? La partie ne sera pas sauvegardée.")){
                    System.exit(0);
                }
                break;
            case "retourAccueil":
                if(plateau.showOptionDialog("Retour à l'accueil", "Êtes-vous sûr de vouloir quitter et retourner à l'accueil ? La partie ne sera pas sauvegardée.")){
                    //enlève la fenetre du plateau
                    Window w = SwingUtilities.getWindowAncestor(plateau);
                    w.setVisible(false);

                    //met en place le menu
                    View_Accueil accueil = new View_Accueil();
                    new Control_Accueil(accueil);
                    accueil.display();
                }
                break;
        }
    }

    private void afficherErreurGetSauvegarde(SauvegardeException e) {
        switch(e.getType()){
            case FICHIER_INEXISTANT:
                plateau.jOptionMessage("Erreur lors de la sauvegarde", "Le fichier de sauvegarde est inexistant");
                break;
            case LECTURE_IMPOSSIBLE:
                plateau.jOptionMessage("Erreur lors de la sauvegarde", "Impossible de récupérer les données de sauvegarde");
                break;
        }
    }

    private void afficherErreurSauvegarde(SauvegardeException e) {
        switch(e.getType()){
            case FICHIER_INEXISTANT:
                plateau.jOptionMessage("Erreur lors de la sauvegarde", "Le fichier de sauvegarde est inexistant");
                break;
            case ECRITURE_IMPOSSIBLE:
                plateau.jOptionMessage("Erreur lors de la sauvegarde", "Impossible de sauvegarder la partie à cause d'une erreur système");
                break;
        }
    }
}
