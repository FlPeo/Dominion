package dominion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by florian on 13/11/2016.
 */
public class View_Partie  extends JFrame {

    private int xSize, ySize;
    private Model_Partie modelePartie;

    private JLabel labelJoueur1;
    private JLabel labelJoueur2;

    private JButton[] boutonsVictoire;
    private JButton[] boutonsAction;
    private ArrayList<JButton> boutonsCartesJoueur;

    private JButton[] boutonsTresor;
    private JButton[] boutonsChoix;


    /**
     * Constructeur de la vue
     */
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


        setTitle("dominion.Dominion");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        display();
    }

    /**
     * Initialise les attributs de la classe
     */
    private void initAttribut()
    {
        int i;
        labelJoueur1 = new JLabel("J1");
        labelJoueur2 = new JLabel("J2");
        labelJoueur1.setHorizontalAlignment(JLabel.CENTER);
        labelJoueur2.setHorizontalAlignment(JLabel.CENTER);

        boutonsVictoire = new JButton[3];
        for(i=0 ; i<3 ;i++){
            boutonsVictoire[i] = new JButton("Vict" + i);
        }

        boutonsAction = new JButton[10];
        for(i=0 ; i<10 ;i++){
            boutonsAction[i] = new JButton("Act" + i);
        }


        boutonsCartesJoueur = new ArrayList<JButton>();
        for(i=0 ; i<5 ;i++){
            boutonsCartesJoueur.add(new JButton("Car" + i));
        }

        boutonsTresor = new JButton[4];
        for(i=0 ; i<4 ;i++){
            boutonsTresor[i] = new JButton("Tres" + i);
        }



        boutonsChoix = new JButton[3];
        for(i=0 ; i<3 ;i++){
            boutonsChoix[i] = new JButton("Ch" + i);
        }
    }

    /**
     * creerWidgetPartie
     * Place les éléments de la vue pour la partie
     *
     */
    private void creerWidgetPartie()
    {
        JPanel global = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        JPanel panelGauche = createPanelGauche();
        JPanel panelCentral = createPanelCentral();
        JPanel panelDroite = createPanelDroit();


        global.add(panelGauche);
        global.add(panelCentral);
        global.add(panelDroite);

        setContentPane(global);
    }


    /**
     * createPanelDroit
     * Permet de créer le Panel de droite (bouton de cartes Trésor et boutons de choix)
     */
    private JPanel createPanelDroit() {
        int i;

        JPanel panelDroite = new JPanel(new GridLayout(2,1));
        panelDroite.setPreferredSize(new Dimension(xSize/8, ySize));



        JPanel panelCartesTresor = new JPanel();
        BoxLayout layoutTresor = new BoxLayout(panelCartesTresor, BoxLayout.Y_AXIS);
        panelCartesTresor.setLayout(layoutTresor);
        panelCartesTresor.setBackground(Color.GREEN);

        for(i=0 ; i<4; i++){
            JPanel p = new JPanel(new BorderLayout());
            p.add(boutonsTresor[i], BorderLayout.CENTER);
            p.add(Box.createHorizontalStrut(15), BorderLayout.EAST);
            p.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
            panelCartesTresor.add(p);
        }




        JPanel panelChoix = new JPanel();
        BoxLayout layoutChoix = new BoxLayout(panelChoix, BoxLayout.Y_AXIS);
        panelChoix.setLayout(layoutChoix);
        panelChoix.setBackground(Color.BLUE);

        for(i=0 ; i<3; i++){
            JPanel p = new JPanel(new BorderLayout());
            p.add(boutonsChoix[i], BorderLayout.CENTER);
            p.add(Box.createHorizontalStrut(15), BorderLayout.EAST);
            p.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
            panelChoix.add(p);
        }


        panelDroite.add(panelCartesTresor);
        panelDroite.add(panelChoix);

        return panelDroite;
    }


    /**
     * createPanelCentral
     * Permet de créer le Panel central (cartes du joueur, cartes action disponibles à l'achat, cartes de dominion.Victoire)
     */
    private JPanel createPanelCentral() {
        int i;

        JPanel panelCentral = new JPanel(new GridLayout(2,1));
        panelCentral.setPreferredSize(new Dimension(6*(xSize/8), ySize));
        JPanel panelCentralHaut = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));



        JPanel panelCartesVictoire = new JPanel();
        BoxLayout layoutVictoire = new BoxLayout(panelCartesVictoire, BoxLayout.Y_AXIS);
        panelCartesVictoire.setLayout(layoutVictoire);
        panelCartesVictoire.setBackground(Color.WHITE);
        panelCartesVictoire.setPreferredSize(new Dimension(xSize/8, ySize/2));


        JPanel panelCartesAction = new JPanel(new BorderLayout());
        panelCartesAction.setPreferredSize(new Dimension(5*(xSize/8), ySize/2));
        panelCartesAction.setBackground(Color.BLUE);
        JPanel panelCentreCartesAction = new JPanel(new GridLayout(2,5));


        for(i=0 ; i<3; i++){
            JPanel p = new JPanel(new BorderLayout());
            p.add(boutonsVictoire[i], BorderLayout.CENTER);
            p.add(Box.createHorizontalStrut(15), BorderLayout.EAST);
            p.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
            panelCartesVictoire.add(p);
        }

        for(i=0 ; i<10 ; i++){
            panelCentreCartesAction.add(boutonsAction[i], i);
        }
        panelCartesAction.add(Box.createHorizontalStrut(15), BorderLayout.EAST);
        panelCartesAction.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
        panelCartesAction.add(panelCentreCartesAction, BorderLayout.CENTER);




        JPanel panelCartesJoueur = new JPanel(new BorderLayout());
        JPanel conteneurCartes = new JPanel(new GridLayout(1,5));
        conteneurCartes.setSize(new Dimension(-1, ySize/2 - 30));
        panelCartesJoueur.setBackground(Color.PINK);

        for(i=0 ; i<5 ; i++){
            conteneurCartes.add(boutonsCartesJoueur.get(i));
        }
        panelCartesJoueur.add(Box.createHorizontalStrut(15), BorderLayout.EAST);
        panelCartesJoueur.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
        panelCartesJoueur.add(Box.createVerticalStrut(15), BorderLayout.NORTH);
        panelCartesJoueur.add(conteneurCartes, BorderLayout.CENTER);


        panelCentralHaut.add(panelCartesVictoire);
        panelCentralHaut.add(panelCartesAction);
        panelCentral.add(panelCentralHaut, 0);
        panelCentral.add(panelCartesJoueur, 1);


        return panelCentral;
    }



    /**
     * createPanelGauche
     * Permet de créer le Panel de gauche (nom des joueurs et défausse)
     */
    private JPanel createPanelGauche() {
        JPanel panelGauche = new JPanel(new GridLayout(2,1));
        panelGauche.setPreferredSize(new Dimension(xSize/8, ySize));



        JPanel panelJoueurs = new JPanel();
        BoxLayout layoutJoueurs = new BoxLayout(panelJoueurs, BoxLayout.Y_AXIS);
        panelJoueurs.setLayout(layoutJoueurs);
        panelJoueurs.setBackground(Color.GRAY);

        JPanel panelDefausse = new JPanel();
        panelDefausse.setBackground(Color.RED);




        JPanel p1 = new JPanel(new BorderLayout()), p2 = new JPanel(new BorderLayout());
        p1.add(labelJoueur1, BorderLayout.CENTER);
        p2.add(labelJoueur2, BorderLayout.CENTER);



        panelJoueurs.add(p1);
        panelJoueurs.add(p2);
        panelGauche.add(panelJoueurs, 0);
        panelGauche.add(panelDefausse, 1);

        return panelGauche;
    }

    /**
     * display
     * Permet d'afficher la fenetre
     */
    void display() {
        setVisible(true);
    }






}
