import javax.swing.*;
import java.awt.*;

/**
 * Created by florian on 13/11/2016.
 */
public class View_Partie  extends JFrame {

    private int xSize, ySize;
    private Model_Partie modelePartie;

    View_Partie(Model_Partie partie)
    {
        super();
        modelePartie = partie;

        Toolkit tk = Toolkit.getDefaultToolkit();
        xSize = (int) tk.getScreenSize().getWidth();
        ySize = (int) tk.getScreenSize().getHeight();
        setSize(xSize, ySize);

        initAttribut();
        creerWidgetPartie();

        setUndecorated(true);


        setTitle("Dominion");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        display();
    }

    /**
     * Initialise les attributs de la classe
     */
    private void initAttribut()
    {
        // Initialisation des variables
        /*titre = new JLabel("Dominion");
        titre.setForeground(new Color(0, 0, 100));
        titre.setFont(new Font("Arial", Font.BOLD, 30));

        lancerPartie = new JButton("Lancer la partie");
        credit = new JButton("Credits");
        quitterJeu = new JButton("Quitter");

        lancerPartie.setBackground(Color.white);
        credit.setBackground(Color.white);
        quitterJeu.setBackground(Color.white);*/
    }

    /**
     * creerWidgetPartie
     * Place les éléments de la vue pour le menu principal
     *
     */
    private void creerWidgetPartie()
    {
        JPanel global = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));




        JPanel panelGauche = new JPanel(new GridLayout(2,1));
        panelGauche.setPreferredSize(new Dimension(xSize/8, ySize));

        JPanel panelJoueurs = new JPanel();
        panelJoueurs.setBackground(Color.GRAY);
        JPanel panelDefausse = new JPanel();
        panelDefausse.setBackground(Color.RED);

        panelGauche.add(panelJoueurs, 0);
        panelGauche.add(panelDefausse, 1);




        JPanel panelCentral = new JPanel(new GridLayout(2,1));
        panelCentral.setPreferredSize(new Dimension(6*(xSize/8), ySize));

        JPanel panelCentralHaut = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));


        JPanel panelCartesVictoire = new JPanel();
        panelCartesVictoire.setBackground(Color.WHITE);
        panelCartesVictoire.setPreferredSize(new Dimension(xSize/8, ySize/2));
        JPanel panelCartesAction = new JPanel();
        panelCartesAction.setBackground(Color.ORANGE);
        panelCartesAction.setPreferredSize(new Dimension(6*(xSize/8), ySize/2));

        JPanel panelCartesJoueur = new JPanel();
        panelCartesJoueur.setBackground(Color.PINK);

        panelCentralHaut.add(panelCartesVictoire);
        panelCentralHaut.add(panelCartesAction);
        panelCentral.add(panelCentralHaut, 0);
        panelCentral.add(panelCartesJoueur, 1);






        JPanel panelDroite = new JPanel(new GridLayout(2,1));
        panelDroite.setPreferredSize(new Dimension(xSize/8, ySize));

        JPanel panelCartesTresor = new JPanel();
        panelCartesTresor.setBackground(Color.GREEN);
        JPanel panelChoix = new JPanel();
        panelChoix.setBackground(Color.BLUE);

        panelDroite.add(panelCartesTresor);
        panelDroite.add(panelChoix);




        global.add(panelGauche);
        global.add(panelCentral);
        global.add(panelDroite);

        setContentPane(global);


    }

    /**
     * display
     * Permet au controlGroup d'afficher la vue lorsque tous les controlleurs sont en place
     */
    void display() {
        setVisible(true);
    }






}
