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

        g.drawString(statutText, xBase + 50, yBase-32);
        g.drawString("C'est au tour de : "+ partie.getJoueurCourrant().getNomJoueur(), xBase + 700, yBase-32);
    }

    /**
     * setStatutText
     * met à jour le texte de la barre de statut
     * @param statutText (texte de la barre de statut)
     */
    void setStatutText(String statutText) {
        this.statutText = statutText;
    }
}
