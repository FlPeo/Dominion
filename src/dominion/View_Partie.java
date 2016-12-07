package dominion;


import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by florian on 13/11/2016.
 */
public class View_Partie  extends JFrame {
    public static final int NB_BOUTONS_CHOIX = 3;


    private int xSize, ySize;
    private Partie modelePartie;

    private JLabel[] labelsJoueurs;
    private JLabel background;

    private JButton[] boutonsVictoire;
    private JButton[] boutonsAction;
    private JButton[] boutonsTresor;

    private ArrayList<JButton> boutonsCartesJoueur;
    private JButton[] boutonsChoix;
    private JPanel panelCartesMain;


    private Color couleurJoueurCourrant;
    private Color couleurJoueurDefaut;


    /**
     * Constructeur de la vue
     */
    View_Partie(Partie partie)
    {
        super();
        modelePartie = partie;
        couleurJoueurCourrant = Color.red;
        couleurJoueurDefaut = Color.black;

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
        setImagesBoutons();
    }


    /**
     * Initialise les attributs de la classe
     */
    private void initAttribut()
    {
        int i;
        labelsJoueurs = new JLabel[2];
        labelsJoueurs[0] = new JLabel(modelePartie.getNomJoueur(0));
        labelsJoueurs[0].setForeground(couleurJoueurCourrant);
        labelsJoueurs[1] = new JLabel(modelePartie.getNomJoueur(1));

        labelsJoueurs[0].setHorizontalAlignment(JLabel.CENTER);
        labelsJoueurs[1].setHorizontalAlignment(JLabel.CENTER);

        boutonsVictoire = new JButton[4];

        int j=0;
        for(i=0 ; i<4 ;i++){
            boutonsVictoire[i] = new JButton();
            boutonsVictoire[i].setActionCommand(""+j);
            j++;
        }

        boutonsAction = new JButton[10];
        for(i=0 ; i<10 ;i++){
            boutonsAction[i] = new JButton();
            boutonsAction[i].setActionCommand(""+j);
            j++;
        }

        boutonsTresor = new JButton[3];
        for(i=0 ; i<3 ;i++){
            boutonsTresor[i] = new JButton();
            boutonsTresor[i].setActionCommand(""+j);
            j++;
        }



        boutonsChoix = new JButton[3];
        boutonsChoix[0] = new JButton("Passer cette phase de jeu");
        boutonsChoix[0].setActionCommand(""+j);
        j++;
        for(i=1 ; i<3 ;i++){    //enlever un bouton de choix = changer des methodes dans Controleur de Partie
            boutonsChoix[i] = new JButton();
            boutonsChoix[i].setActionCommand(""+j);
            j++;
        }

        boutonsCartesJoueur = new ArrayList<JButton>();
        for(i=0 ; i<5 ;i++){
            boutonsCartesJoueur.add(new JButton());
            boutonsCartesJoueur.get(i).setActionCommand(""+j);
            j++;
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
        global.setOpaque(false);
        JPanel panelGauche = createPanelGauche();
        panelGauche.setOpaque(false);
        JPanel panelCentral = createPanelCentral();
        panelCentral.setOpaque(false);
        JPanel panelDroite = createPanelDroit();
        panelDroite.setOpaque(false);


        global.add(panelGauche);
        global.add(panelCentral);
        global.add(panelDroite);

        // Mise en place du fond d'écran
        background = new JLabel(new ImageIcon("Images/decors/backgroundParty.jpg"));
        background.setSize(xSize, ySize);
        background.setLayout(new FlowLayout());
        background.add(global, BorderLayout.CENTER);

        setContentPane(background);
    }


    /**
     * createPanelDroit
     * Permet de créer le Panel de droite (bouton de cartes Trésor et boutons de choix)
     */
    private JPanel createPanelDroit() {
        int i;

        JPanel panelDroite = new JPanel(new GridLayout(2,1));
        panelDroite.setOpaque(false);
        panelDroite.setPreferredSize(new Dimension(xSize/8, ySize));

        JPanel panelCartesTresor = new JPanel();
        panelCartesTresor.setOpaque(false);
        BoxLayout layoutTresor = new BoxLayout(panelCartesTresor, BoxLayout.Y_AXIS);
        panelCartesTresor.setLayout(layoutTresor);

        for(i=0 ; i<boutonsTresor.length; i++){
            JPanel p = new JPanel(new BorderLayout());
            p.setOpaque(false);
            p.add(boutonsTresor[i], BorderLayout.CENTER);
            p.add(Box.createHorizontalStrut(15), BorderLayout.EAST);
            p.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
            panelCartesTresor.add(p);
        }

        JPanel panelChoix = new JPanel();
        panelChoix.setOpaque(false);
        BoxLayout layoutChoix = new BoxLayout(panelChoix, BoxLayout.Y_AXIS);
        panelChoix.setLayout(layoutChoix);
        panelChoix.setBackground(Color.BLUE);

        for(i=0 ; i<boutonsChoix.length; i++){
            JPanel p = new JPanel(new BorderLayout());
            p.setOpaque(false);
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
        panelCentral.setOpaque(false);
        panelCentral.setPreferredSize(new Dimension(6*(xSize/8), ySize));
        JPanel panelCentralHaut = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCentralHaut.setOpaque(false);

        JPanel panelCartesVictoire = new JPanel();
        panelCartesVictoire.setOpaque(false);
        BoxLayout layoutVictoire = new BoxLayout(panelCartesVictoire, BoxLayout.Y_AXIS);
        panelCartesVictoire.setLayout(layoutVictoire);
        panelCartesVictoire.setBackground(Color.WHITE);
        panelCartesVictoire.setPreferredSize(new Dimension(xSize/8, ySize/2));

        JPanel panelCartesAction = new JPanel(new BorderLayout());
        panelCartesAction.setOpaque(false);
        panelCartesAction.setPreferredSize(new Dimension(5*(xSize/8), ySize/2));
        panelCartesAction.setBackground(Color.BLUE);
        JPanel panelCentreCartesAction = new JPanel(new GridLayout(2,5));
        panelCentreCartesAction.setOpaque(false);

        for(i=0 ; i<boutonsVictoire.length; i++){
            JPanel p = new JPanel(new BorderLayout());
            p.setOpaque(false);
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
        panelCartesJoueur.setOpaque(false);
        panelCartesMain = new JPanel(new GridLayout(1,5));
        panelCartesMain.setSize(new Dimension(-1, ySize/2 - 30));
        panelCartesJoueur.setBackground(Color.PINK);

        for(i=0 ; i<5 ; i++){
            panelCartesMain.add(boutonsCartesJoueur.get(i));
        }
        panelCartesJoueur.add(Box.createHorizontalStrut(15), BorderLayout.EAST);
        panelCartesJoueur.add(Box.createHorizontalStrut(15), BorderLayout.WEST);
        panelCartesJoueur.add(Box.createVerticalStrut(15), BorderLayout.NORTH);
        panelCartesJoueur.add(panelCartesMain, BorderLayout.CENTER);

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
        panelGauche.setOpaque(false);
        panelGauche.setPreferredSize(new Dimension(xSize/8, ySize));

        JPanel panelJoueurs = new JPanel();
        panelJoueurs.setOpaque(false);
        BoxLayout layoutJoueurs = new BoxLayout(panelJoueurs, BoxLayout.Y_AXIS);
        panelJoueurs.setLayout(layoutJoueurs);
        panelJoueurs.setBackground(Color.GRAY);

        JPanel panelDefausse = new JPanel();
        panelDefausse.setOpaque(false);
        panelDefausse.setBackground(Color.RED);

        JPanel p1 = new JPanel(new BorderLayout()), p2 = new JPanel(new BorderLayout());
        p1.setOpaque(false);
        p2.setOpaque(false);
        p1.add(labelsJoueurs[0], BorderLayout.CENTER);
        p2.add(labelsJoueurs[1], BorderLayout.CENTER);

        panelJoueurs.add(p1);
        panelJoueurs.add(p2);
        panelGauche.add(panelJoueurs, 0);
        panelGauche.add(panelDefausse, 1);

        return panelGauche;
    }

    private void setImagesBoutons() {
        int i;
        ImageIcon img;

        for(i=0 ; i<boutonsAction.length ;i++){

            img = new ImageIcon(new ImageIcon("Images/Action/Action"+modelePartie.getIdAction(i)+".jpg").getImage()
                    .getScaledInstance(boutonsAction[i].getWidth(), boutonsAction[i].getHeight(), Image.SCALE_DEFAULT));
            boutonsAction[i].setIcon(img);
        }


        for(i=0 ; i<boutonsVictoire.length ;i++){
            img = new ImageIcon(new ImageIcon("Images/Victoire/Victoire"+i+".jpg").getImage()
                    .getScaledInstance(boutonsVictoire[i].getWidth(), boutonsVictoire[i].getHeight(), Image.SCALE_DEFAULT));
            boutonsVictoire[i].setIcon(img);
        }

        for(i=0 ; i<boutonsTresor.length ;i++){
            img = new ImageIcon(new ImageIcon("Images/Tresor/Tresor"+i+".jpg").getImage()
                    .getScaledInstance(boutonsTresor[i].getWidth(), boutonsTresor[i].getHeight(), Image.SCALE_DEFAULT));
            boutonsTresor[i].setIcon(img);
        }


        for(i=0 ; i<boutonsCartesJoueur.size() ;i++){
            img = new ImageIcon(new ImageIcon(modelePartie.getJoueurCourrant().getCheminImageCarte(i)).getImage()
                    .getScaledInstance(boutonsCartesJoueur.get(0).getWidth(), boutonsCartesJoueur.get(0).getHeight(), Image.SCALE_DEFAULT));
            boutonsCartesJoueur.get(i).setIcon(img);
        }
    }

    /**
     * display
     * Permet d'afficher la fenetre
     */
    void display() {
        setVisible(true);
    }


    void setActionListener(Control_Partie control){
        for(JButton b : boutonsVictoire){
            b.addActionListener(control);
        }

        for(JButton b : boutonsAction){
            b.addActionListener(control);
        }

        for(JButton b : boutonsCartesJoueur){
            b.addActionListener(control);
        }

        for(JButton b : boutonsChoix){
            b.addActionListener(control);
        }

        for(JButton b : boutonsTresor){
            b.addActionListener(control);
        }



    }


    public void majVue() {
        int i;
        for(i = 0 ; i<3 ; i++){
            if(modelePartie.getNbRestantCartesTresor(i) == 0){
                boutonsTresor[i].setIcon(null);
                boutonsTresor[i].setText("Pile vide");
                boutonsTresor[i].setEnabled(false);
            }
        }

        for(i = 0 ; i<4 ; i++){
            if(modelePartie.getNbRestantCartesVictoire(i) == 0){
                boutonsVictoire[i].setIcon(null);
                boutonsVictoire[i].setText("Pile vide");
                boutonsVictoire[i].setEnabled(false);
            }
        }

        for(i = 0 ; i<10 ; i++){
            if(modelePartie.getNbRestantCartesAction(i) == 0){
                boutonsAction[i].setIcon(null);
                boutonsAction[i].setText("Pile vide");
                boutonsAction[i].setEnabled(false);
            }
        }

        ActionListener listener = boutonsCartesJoueur.get(0).getActionListeners()[0];
        int height = boutonsCartesJoueur.get(0).getHeight();
        int width = boutonsCartesJoueur.get(0).getWidth();
        boutonsCartesJoueur = new ArrayList<JButton>();
        JButton carteMain;
        ImageIcon img;
        int j = 17+NB_BOUTONS_CHOIX;
        panelCartesMain.removeAll();
        for(i=0 ; i<modelePartie.getJoueurCourrant().getSizeMain() ;i++){
            carteMain = new JButton();
            carteMain.addActionListener(listener);
            img = new ImageIcon(new ImageIcon(modelePartie.getJoueurCourrant().getCheminImageCarte(i)).getImage()
                    .getScaledInstance(width, height, Image.SCALE_DEFAULT));
            carteMain.setActionCommand(""+j);
            j++;

            boutonsCartesJoueur.add(carteMain);
            boutonsCartesJoueur.get(i).setIcon(img);
            panelCartesMain.add(carteMain);
        }
        validate();
    }

    public void changeJoueur() {
        int idJoueur = modelePartie.getJoueurCourrant().getNumero();
        int idJoueurPrec = (idJoueur == 0)?modelePartie.getNbJoueurs()-1:idJoueur-1;

        labelsJoueurs[idJoueurPrec].setForeground(couleurJoueurDefaut);
        labelsJoueurs[idJoueur].setForeground(couleurJoueurCourrant);
    }
}
