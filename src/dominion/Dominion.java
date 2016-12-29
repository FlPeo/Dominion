package dominion;

public class Dominion
{
    public static void main (String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Model_Accueil modelAccueil = new Model_Accueil(); //ModelAccueil est-il vraiment utile ?
                View_Accueil accueil = new View_Accueil();
                new Control_Accueil(accueil, modelAccueil);
                accueil.display();
            }
        });
    }
}