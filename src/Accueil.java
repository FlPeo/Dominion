import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by sakalypse on 05/11/16.
 */
public class Accueil  extends JFrame {
    private int xSize, ySize;

    private JLabel titre;
    private JLabel background;

    private JButton lancerPartie;
    private JButton credit;
    private JButton quitterJeu;

    /**
     * Constructeur de la vue
     */
    Accueil()
    {
        initAttribut();
        creerWidgetAccueil();

        setUndecorated(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        xSize = (int) tk.getScreenSize().getWidth();
        ySize = (int) tk.getScreenSize().getHeight();
        setSize(xSize, ySize);

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
        titre = new JLabel("Dominion");
        titre.setForeground(new Color(0, 0, 100));
        titre.setFont(new Font("Arial", Font.BOLD, 30));

        lancerPartie = new JButton("Lancer la partie");
        credit = new JButton("Credits");
        quitterJeu = new JButton("Quitter");

        lancerPartie.setBackground(Color.white);
        credit.setBackground(Color.white);
        quitterJeu.setBackground(Color.white);
    }

    /**
     * creerWidgetAccueil
     * Place les éléments de la vue pour le menu principal
     *
     */
    private void creerWidgetAccueil()
    {
        JPanel centre = new JPanel(new GridLayout(11, 1, 0, 10));
        centre.setOpaque(false);
        centre.add(Box.createVerticalGlue());
        centre.add(lancerPartie);
        centre.add(credit);
        centre.add(quitterJeu);

        JPanel organisation = new JPanel(new BorderLayout());
        organisation.setOpaque(false);
        organisation.add(titre, BorderLayout.NORTH);

        organisation.add(centre, BorderLayout.SOUTH);

        // Mise en place du fond d'écran
        setLayout(new BorderLayout());
        background = new JLabel(); //TODO : a ajouter
        background.setSize(xSize, ySize);
        background.setLayout(new FlowLayout());
        background.add(organisation, BorderLayout.CENTER);

        setContentPane(background);
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
        return JOptionPane.showInputDialog(this, message, "Dominion", JOptionPane.QUESTION_MESSAGE);
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
}
