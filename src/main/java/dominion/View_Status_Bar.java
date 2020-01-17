package dominion;
import java.awt.*;

/**
 * Created by mlucile on 15/12/16.
 */
public class View_Status_Bar
{
    private String statutText;
    private View_Plateau vuePlateau;
    private Partie partie;

    /**
     * VueBarreStatut
     * affiche le nom des joueurs et la case survolée par la souris
     *
     * @param partie (model)
     * @param vuePlateau (jpanel qui affiche la barre de statut)
     */
    View_Status_Bar(Partie partie, View_Plateau vuePlateau)
    {
        this.partie = partie;
        this.vuePlateau = vuePlateau;
        this.statutText = "joueurs : " + partie.getJoueurs()[0].getNomJoueur() + "   VS   " + partie.getJoueurs()[1].getNomJoueur();
    }

    /**
     * PaintMe
     * Paint l'objet graphique
     *
     * @param g (boite à outil servant à peindre des éléments)
     * @param xBase (axe x de l'objet)
     * @param yBase (axe y de l'objet)
     */
    void paintMe(Graphics g, int xBase, int yBase)
    {
        g.setColor(Color.black);
        g.fillRect(xBase,yBase-50, vuePlateau.getWidth(), 40);
        g.setColor(Color.white);

        g.drawString(statutText, xBase + 40, yBase-32);
        g.drawString("C'est au tour de : "+ partie.getJoueurCourrant().getNomJoueur(), xBase + 300, yBase-32);

        String chaineEtapeTour = partie.getEtapesTour().getIndication();
        if(partie.getEtapesTour().equals(EtapesTour.ACHAT)){
            String pluriel = (partie.getJoueurCourrant().getNbTourAchat() == 1)?"":"s";
            chaineEtapeTour += (" (" + partie.getJoueurCourrant().getNbTourAchat()+ " restant" + pluriel +")"+
            "         "+ partie.getJoueurCourrant().getCoins()+ " coins disponibles");
        }
        else if(partie.getEtapesTour().equals(EtapesTour.ACTION)){
            String pluriel = (partie.getJoueurCourrant().getNbTourAction() == 1)?"":"s";
            chaineEtapeTour += (" (" + partie.getJoueurCourrant().getNbTourAction()+ " restant" + pluriel +")");
        }

        g.drawString(chaineEtapeTour, xBase + 600, yBase-32);

    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}
