package dominion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by sakalypse on 05/11/16.
 */
public class View_Accueil extends JFrame
{
    private int xSize, ySize;
    private View_Plateau vuePlateau;

    private JLabel titre;
    private JLabel background;

    private DominionButton lancerPartie;
    private DominionButton credit;
    private DominionButton quitterJeu;

    /**
     * Constructeur de la vue
     */
    View_Accueil()
    {
        super();

        initAttribut();
        creerWidgetAccueil();

        setUndecorated(true);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        Toolkit tk = Toolkit.getDefaultToolkit();
        xSize = (int) tk.getScreenSize().getWidth();
        ySize = (int) tk.getScreenSize().getHeight();
        setSize(xSize, ySize);

        setTitle("Dominion");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Initialise les attributs de la classe
     */
    private void initAttribut()
    {
        // Initialisation des variables
        titre = new JLabel(new ImageIcon(("Images/decors/logo.jpg")));

        lancerPartie = new DominionButton("Lancer la partie");
        credit = new DominionButton("Credits");
        quitterJeu = new DominionButton("Quitter");

        GraphicsEnvironment fontLabel = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try
        {
            fontLabel.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("font/Cardinal.ttf")));
        }
        catch (FontFormatException | IOException fe)
        {
            fe.printStackTrace();
        }
    }

    /**
     * creerWidgetAccueil
     * Place les éléments de la vue pour le menu principal
     *
     */
    private void creerWidgetAccueil()
    {
        JPanel menus = new JPanel(new GridLayout(6,1,0,25));
        menus.setOpaque(false);
        menus.add(Box.createVerticalGlue());
        menus.add(Box.createVerticalGlue());
        menus.add(Box.createVerticalGlue());
        menus.add(lancerPartie);
        menus.add(credit);
        menus.add(quitterJeu);

        JPanel menu = new JPanel(new BorderLayout());
        menu.setOpaque(false);
        menu.add(titre, BorderLayout.NORTH);
        menu.add(menus, BorderLayout.SOUTH);

        JPanel organisation = new JPanel(new GridLayout(2, 3));
        organisation.setOpaque(false);
        organisation.add(menu);
        organisation.add(Box.createVerticalGlue());
        organisation.add(Box.createVerticalGlue());
        organisation.add(Box.createVerticalGlue());


        // Mise en place du fond d'écran
        background = new JLabel(new ImageIcon("Images/decors/background1.jpg"));
        background.setSize(xSize, ySize);
        background.setLayout(new FlowLayout());
        background.add(organisation, BorderLayout.CENTER);

        setContentPane(background);
    }

    void creerWidgetPartie(Partie modelPartie)
    {
        vuePlateau = new View_Plateau(this, modelPartie);
        setContentPane(vuePlateau);
        setVisible(true);
    }

    void creerMenuPartie(Partie modelPartie, Control_Partie_Mouse controlMouse){
        JMenuBar barreMenu = new JMenuBar();
        JMenu file = new JMenu("Partie");
        Control_Menu controlMenu = new Control_Menu(modelPartie, controlMouse, vuePlateau, vuePlateau.getBarreStatut());

        JMenuItem retourAccueil = new JMenuItem("Retour à l'accueil");
        retourAccueil.setActionCommand("retourAccueil");
        retourAccueil.addActionListener(controlMenu);

        JMenuItem save = new JMenuItem("Sauvegarder");
        save.setActionCommand("save");
        save.addActionListener(controlMenu);

        JMenuItem load = new JMenuItem("Reprendre");
        load.setActionCommand("load");
        load.addActionListener(controlMenu);

        JMenuItem quit = new JMenuItem("Quitter");
        quit.setActionCommand("quit");
        quit.addActionListener(controlMenu);

        file.add(retourAccueil);
        file.add(save);
        file.add(load);
        file.add(quit);

        barreMenu.add(file);
        setJMenuBar(barreMenu);
    }

    void removeMenuPartie(){
        setJMenuBar(null);
    }

    /**
     * display
     * Permet au controlGroup d'afficher la vue lorsque tous les controlleurs sont en place
     */
    void display() {
        setVisible(true);
    }

    /**
     * setButtonControl
     * Place des écouteurs sur tous les boutons principaux du menu et des formulaires
     * @param listener (écouteur)
     */
    void setButtonControl(ActionListener listener)
    {
        lancerPartie.addActionListener(listener);
        credit.addActionListener(listener);
        quitterJeu.addActionListener(listener);
    }
    /**
     * messagePop
     * Permet d'afficher une fenetre pop-up avec un champs de saisi pour récupérer une information
     * @param message (texte à afficher)
     * @return fenetre qui s'affiche
     */
    String messagePop(String message)
    {
        return JOptionPane.showInputDialog(this, message, "dominion.Dominion", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Affiche une fenêtre popup
     * @param titreFenetre (titre de la fenêtre)
     * @param message (chaine de caractères qui sera affichée dans le corps de la fenêtre
     */
    void jOptionMessage(String titreFenetre, String message)
    {
        JOptionPane.showMessageDialog(this, message, titreFenetre, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * afficherMenu
     * Permet d'afficher les boutons du menu
     *
     */
    void afficherMenu()
    {
        creerWidgetAccueil();
        setVisible(true);
    }

    /**
     * initMenuPartie
     * Instancie les attributs de la bar de menu
     *
     */
    void initMenuPartie()
    {
        JMenuBar barMenu = new JMenuBar();

        JMenu optionPartie = new JMenu("Fichier");
        JMenu parametres = new JMenu("Options");

        barMenu.add(optionPartie);
        barMenu.add(parametres);

        setJMenuBar(barMenu);
    }

    public JButton getLancerPartie() {
        return lancerPartie;
    }

    public JButton getCredit() {
        return credit;
    }

    public JButton getQuitterJeu() {
        return quitterJeu;
    }

    public View_Plateau getVuePlateau() {
        return vuePlateau;
    }
}
