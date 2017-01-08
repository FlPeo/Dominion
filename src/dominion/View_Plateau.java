package dominion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * Created by mlucile on 15/12/16.
 */

class View_Plateau extends JPanel
{
    private View_Accueil vueAccueil;
    private Partie modelPartie;
    private ImageIcon background;
    private View_Status_Bar barreStatut;

    private ImageIcon[] cartesActions;
    private ImageIcon[] cartesVictoires;
    private ImageIcon[] cartesTresors;
    private List<ImageIcon> cartesMain;

    private int[][] coordCartesVictoires;
    private int[][] coordCartesTresors;
    private int[][] coordCartesActions;
    private int[] coordBouton;
    private List<List<Integer>> coordCartesMain;

    View_Plateau(View_Accueil vue, Partie modelPartie)
    {
        vueAccueil = vue;
        this.modelPartie = modelPartie;
        background = new ImageIcon("Images/decors/backgroundParty.jpg");
        barreStatut = new View_Status_Bar(modelPartie, this);

        // Instanciation des cartes action
        int i;
        cartesActions = new ImageIcon[10];
        for(i=0 ; i<10 ;i++)
            cartesActions[i] = new ImageIcon("Images/Action/Action"+modelPartie.getIdAction(i)+".jpg");

        // Instanciation des cartes victoires
        cartesVictoires = new ImageIcon[4];
        for(i=0 ; i<4 ;i++)
            cartesVictoires[i] = new ImageIcon("Images/Victoire/Victoire"+i+".jpg");

        // Instanciation des cartes trésor
        cartesTresors = new ImageIcon[3];
        for(i=0 ; i<3 ;i++)
            cartesTresors[i] = new ImageIcon("Images/Tresor/Tresor"+i+".jpg");

        // Instanciation des cartes de la main
        cartesMain = new ArrayList<>();
        for(i=0 ; i<5 ;i++)
            cartesMain.add(new ImageIcon(modelPartie.getJoueurCourrant().getCheminImageCarte(i)));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(vueAccueil.getWidth(),vueAccueil.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(background.getImage(), 0, 0, vueAccueil.getWidth(), vueAccueil.getHeight(), null);

        // Cartes victoires
        for(int i=0; i<4; i++)
        {
            g.drawImage(cartesVictoires[i].getImage(),
                    vueAccueil.getWidth()-(95*vueAccueil.getWidth()/100),
                    vueAccueil.getHeight()-(98*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100),
                    12*vueAccueil.getWidth()/100, 16*vueAccueil.getHeight()/100,
                    null);

            //dessine le background du nombre
            g.setColor(new Color (0,0,255, 150));
            g.fillRect(vueAccueil.getWidth()-(95*vueAccueil.getWidth()/100),
                    vueAccueil.getHeight()-(98*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100),
                    vueAccueil.getWidth()-(99*vueAccueil.getWidth()/100),
                    vueAccueil.getHeight()-(97*vueAccueil.getHeight()/100));
            g.setColor(new Color(0,0,0));
            //écrit le nombre de carte restant
            g.drawString(String.valueOf(modelPartie.getNbRestantCartesVictoire(i)),
                    vueAccueil.getWidth()-(95*vueAccueil.getWidth()/100),
                    vueAccueil.getHeight()-(96*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100));
        }


        // Cartes trésor
        for(int i=0; i<3; i++)
        {
            g.drawImage(cartesTresors[i].getImage(),
                    vueAccueil.getWidth()-(17*vueAccueil.getWidth()/100),
                    vueAccueil.getHeight()-(98*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100),
                    12*vueAccueil.getWidth()/100, 16*vueAccueil.getHeight()/100,
                    null);


            //dessine le background du nombre
            g.setColor(new Color (0,0,255, 150));
            g.fillRect(vueAccueil.getWidth()-(17*vueAccueil.getWidth()/100),
                    vueAccueil.getHeight()-(98*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100),
                    vueAccueil.getWidth()-(99*vueAccueil.getWidth()/100),
                    vueAccueil.getHeight()-(97*vueAccueil.getHeight()/100));
            g.setColor(new Color(0,0,0));
            //écrit le nombre de carte restant
            g.drawString(String.valueOf(modelPartie.getNbRestantCartesTresor(i)),
                    vueAccueil.getWidth()-(17*vueAccueil.getWidth()/100),
                    vueAccueil.getHeight()-(96*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100));
        }

        // Cartes action
        for(int i=0; i<10; i++)
        {
            if(i<5)
            {
                g.drawImage(cartesActions[i].getImage(),
                        vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + i * (12 * vueAccueil.getWidth() / 100),
                        vueAccueil.getHeight() - (98 * vueAccueil.getHeight() / 100),
                        11 * vueAccueil.getWidth() / 100, 23 * vueAccueil.getHeight() / 100,
                        null);

                //dessine le background du nombre
                g.setColor(new Color (0,0,255, 150));
                g.fillRect(vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + i * (12 * vueAccueil.getWidth() / 100),
                        vueAccueil.getHeight() - (98 * vueAccueil.getHeight() / 100),
                        vueAccueil.getWidth()-(99*vueAccueil.getWidth()/100),
                        vueAccueil.getHeight()-(97*vueAccueil.getHeight()/100));
                g.setColor(new Color(0,0,0));
                //écrit le nombre de carte restant
                g.drawString(String.valueOf(modelPartie.getNbRestantCartesAction(i)),
                        vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + i * (12 * vueAccueil.getWidth() / 100),
                        vueAccueil.getHeight() - (96 * vueAccueil.getHeight() / 100));
            }
            else
            {
                g.drawImage(cartesActions[i].getImage(),
                        vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + (i-5) * (12 * vueAccueil.getWidth() / 100),
                        vueAccueil.getHeight() - (98 * vueAccueil.getHeight() / 100) + 24 * vueAccueil.getHeight() / 100,
                        11 * vueAccueil.getWidth() / 100, 23 * vueAccueil.getHeight() / 100,
                        null);

                //dessine le background du nombre
                g.setColor(new Color (0,0,255, 150));
                g.fillRect(vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + (i-5) * (12 * vueAccueil.getWidth() / 100),
                        vueAccueil.getHeight() - (98 * vueAccueil.getHeight() / 100) + 24 * vueAccueil.getHeight() / 100,
                        vueAccueil.getWidth()-(99*vueAccueil.getWidth()/100),
                        vueAccueil.getHeight()-(97*vueAccueil.getHeight()/100));
                g.setColor(new Color(0,0,0));
                //écrit le nombre de carte restant
                g.drawString(String.valueOf(modelPartie.getNbRestantCartesAction(i)),
                        vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + (i-5) * (12 * vueAccueil.getWidth() / 100),
                        vueAccueil.getHeight() - (96 * vueAccueil.getHeight() / 100) + 24 * vueAccueil.getHeight() / 100);
            }
        }

        // Main
        for(int i=0; i<cartesMain.size(); i++)
        {
            g.drawImage(cartesMain.get(i).getImage(),
                    vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + i * (61/cartesMain.size() * vueAccueil.getWidth() / 100),
                    vueAccueil.getHeight() - (45 * vueAccueil.getHeight() / 100),
                    55/cartesMain.size() * vueAccueil.getWidth() / 100, 95/cartesMain.size() * vueAccueil.getWidth() / 100,
                    null);

        }

        // Bouton pour passer un tour action ou coins
        g.drawImage(new ImageIcon("Images/decors/finTour.png").getImage(),
                vueAccueil.getWidth()-(17*vueAccueil.getWidth()/100),
                vueAccueil.getHeight()-(40*vueAccueil.getHeight()/100),
                12*vueAccueil.getWidth()/100, 16*vueAccueil.getHeight()/100,
                null);

        // Barre d'état en bas
        barreStatut.paintMe(g, 0, getHeight());

        setTabCoords();
    }

    public void setTabCoords()
    {
        int i;
        // Mise en place des tableaux de coordonnées pour les listener :
        //      0 --> coordX haut gauche
        //      1 --> coordY haut gauche
        //      2 --> coordX bas droit
        //      3 --> coordY bas droit
        coordCartesActions = new int[10][4];
        coordCartesTresors = new int[3][4];
        coordCartesVictoires = new int[4][4]; // Comprend aussi carte malédiction
        coordBouton = new int[4];
        coordCartesMain = new ArrayList<>();

        // Coordonnées des cartes victoires
        // La carte 'Malédiction' se place en dessous des cartes victoires donc on la met avec dans le meme tableau
        for(i=0; i<4; i++)
        {
            coordCartesVictoires[i][0] = vueAccueil.getWidth()-(95*vueAccueil.getWidth()/100);
            coordCartesVictoires[i][1] = vueAccueil.getHeight()-(98*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100);
            coordCartesVictoires[i][2] = coordCartesVictoires[i][0] + 12*vueAccueil.getWidth()/100;
            coordCartesVictoires[i][3] = coordCartesVictoires[i][1] + 16*vueAccueil.getHeight()/100;
        }

        // Coordonnées des cartes trésors
        for(i=0; i<3; i++)
        {
            coordCartesTresors[i][0] = vueAccueil.getWidth()-(17*vueAccueil.getWidth()/100);
            coordCartesTresors[i][1] = vueAccueil.getHeight()-(98*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100);
            coordCartesTresors[i][2] = coordCartesTresors[i][0] + 12*vueAccueil.getWidth()/100;
            coordCartesTresors[i][3] = coordCartesTresors[i][1] + 16*vueAccueil.getHeight()/100;
        }

        // Coordonnées cartes actions
        for(i=0; i<10; i++)
        {
            if(i<5)
            {
                coordCartesActions[i][0] = vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + i * (12 * vueAccueil.getWidth() / 100);
                coordCartesActions[i][1] = vueAccueil.getHeight() - (98 * vueAccueil.getHeight() / 100);
                coordCartesActions[i][2] = coordCartesActions[i][0] + 11 * vueAccueil.getWidth() / 100;
                coordCartesActions[i][3] = coordCartesActions[i][1] + 23 * vueAccueil.getHeight() / 100;
            }
            else
            {
                coordCartesActions[i][0] = vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + (i-5) * (12 * vueAccueil.getWidth() / 100);
                coordCartesActions[i][1] = vueAccueil.getHeight() - (98 * vueAccueil.getHeight() / 100) + 24 * vueAccueil.getHeight() / 100;
                coordCartesActions[i][2] = coordCartesActions[i][0] + 11 * vueAccueil.getWidth() / 100;
                coordCartesActions[i][3] = coordCartesActions[i][1] + 23 * vueAccueil.getHeight() / 100;
            }
        }

        // Coordonnées du bouton
        coordBouton[0] = vueAccueil.getWidth()-(17*vueAccueil.getWidth()/100);
        coordBouton[1] = vueAccueil.getHeight()-(40*vueAccueil.getHeight()/100);
        coordBouton[2] = coordBouton[0] + 12*vueAccueil.getWidth()/100;
        coordBouton[3] = coordBouton[1] + 16*vueAccueil.getHeight()/100;

        // Coordonnées cartes main
        coordCartesMain.clear();
        List<Integer> coords;
        for(i=0; i<cartesMain.size(); i++)
        {
            coords = new ArrayList<>();
            coords.add(vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + i * (61/cartesMain.size() * vueAccueil.getWidth() / 100));
            coords.add(vueAccueil.getHeight() - (45 * vueAccueil.getHeight() / 100));
            coords.add(coords.get(0) + 55/cartesMain.size() * vueAccueil.getWidth() / 100);
            coords.add(coords.get(1) + 95/cartesMain.size() * vueAccueil.getWidth() / 100);
            coordCartesMain.add(coords);
        }

        for(i=0; i<coordCartesMain.size(); i++)
        {
            System.out.print(coordCartesMain.get(i).get(0) + "  ");
            System.out.print(coordCartesMain.get(i).get(1) + "  ");
            System.out.print(coordCartesMain.get(i).get(2) + "  ");
            System.out.println(coordCartesMain.get(i).get(3) + "  ");
        }
    }

    public void majVue()
    {
        //a mettre dans repaint ?
        Graphics g = this.getGraphics();
        super.paintComponent(g);

        int i;
        for(i = 0 ; i<3 ; i++)
        {
            if(modelPartie.getNbRestantCartesTresor(i) == 0)
            {
                g.setColor(new Color(255,255,255,0));
                g.fillRect(vueAccueil.getWidth()-(17*vueAccueil.getWidth()/100),
                        vueAccueil.getHeight()-(98*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100),
                        12*vueAccueil.getWidth()/100, 16*vueAccueil.getHeight()/100);
                g.drawLine(coordCartesTresors[i][0], coordCartesTresors[i][1],
                        coordCartesTresors[i][2], coordCartesTresors[i][3]);
                g.drawLine(coordCartesTresors[i][0], coordCartesTresors[i][1] + 16*vueAccueil.getHeight()/100,
                        coordCartesTresors[i][2] + 12*vueAccueil.getWidth()/100, coordCartesTresors[i][3]);
            }
        }

        for(i = 0 ; i<4 ; i++)
        {
            if(modelPartie.getNbRestantCartesVictoire(i) == 0)
            {
                g.setColor(new Color(255,255,255,0));
                g.fillRect(vueAccueil.getWidth()-(95*vueAccueil.getWidth()/100),
                        vueAccueil.getHeight()-(98*vueAccueil.getHeight()/100) + i*(17*vueAccueil.getHeight()/100),
                        12*vueAccueil.getWidth()/100, 16*vueAccueil.getHeight()/100);
                g.drawLine(coordCartesVictoires[i][0], coordCartesVictoires[i][1],
                        coordCartesVictoires[i][2], coordCartesVictoires[i][3]);
                g.drawLine(coordCartesVictoires[i][0], coordCartesVictoires[i][1] + 16*vueAccueil.getHeight()/100,
                        coordCartesVictoires[i][2] + 12*vueAccueil.getWidth()/100, coordCartesVictoires[i][3]);
            }
        }

        for(i = 0 ; i<10 ; i++)
        {
            if(modelPartie.getNbRestantCartesAction(i) == 0)
            {
                g.setColor(new Color(255,255,255,0));
                if(i<5)
                {
                    g.fillRect(vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + i * (12 * vueAccueil.getWidth() / 100),
                            vueAccueil.getHeight() - (98 * vueAccueil.getHeight() / 100),
                            11 * vueAccueil.getWidth() / 100, 23 * vueAccueil.getHeight() / 100);
                }
                else
                {
                    g.fillRect(vueAccueil.getWidth() - (80 * vueAccueil.getWidth() / 100) + (i-5) * (12 * vueAccueil.getWidth() / 100),
                            vueAccueil.getHeight() - (98 * vueAccueil.getHeight() / 100) + 24 * vueAccueil.getHeight() / 100,
                            11 * vueAccueil.getWidth() / 100, 23 * vueAccueil.getHeight() / 100);
                }
                g.drawLine(coordCartesActions[i][0], coordCartesActions[i][1],
                        coordCartesActions[i][2], coordCartesActions[i][3]);
                g.drawLine(coordCartesActions[i][0], coordCartesActions[i][1] + 16*vueAccueil.getHeight()/100,
                        coordCartesActions[i][2] + 12*vueAccueil.getWidth()/100, coordCartesActions[i][3]);
            }
        }
        //fin de a mettre dans repaint ?

        cartesMain.clear();
        for(i=0 ; i<modelPartie.getJoueurCourrant().getSizeMain() ;i++)
            cartesMain.add(new ImageIcon(modelPartie.getJoueurCourrant().getCheminImageCarte(i)));

        repaint();
    }

    public void changeJoueur()
    {
        validate();
    }

    /*
     * Permet de notifier un message à l'utilisateur
    */
    void jOptionMessage(String titreFenetre, String message)
    {
        JOptionPane.showMessageDialog(this, message, titreFenetre, JOptionPane.INFORMATION_MESSAGE);
    }

    /*
    * Permet de demander l'avi à l'utilisateur
    * return True si oui
    * False si non
     */
    public boolean showOptionDialog(String titre, String message){
        int choix = JOptionPane.showOptionDialog(null,
                message,
                titre,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null);
        if (choix == JOptionPane.YES_OPTION)
            return true;
        else if (choix == JOptionPane.NO_OPTION)
            return false;
        else
            return false;
    }

    void setListeners(MouseListener listenrs)
    {
        this.addMouseListener(listenrs);
    }

    public int[][] getCoordCartesVictoires() {
        return coordCartesVictoires;
    }

    public int[][] getCoordCartesTresors() {
        return coordCartesTresors;
    }

    public int[][] getCoordCartesActions() {
        return coordCartesActions;
    }

    public List<List<Integer>> getCoordCartesMain() {
        return coordCartesMain;
    }

    public int[] getCoordBouton() {
        return coordBouton;
    }

    public void setPartie(Partie partie) {
        this.modelPartie = partie;
        barreStatut.setPartie(partie);
    }

    public View_Status_Bar getBarreStatut() {
        return barreStatut;
    }
}
