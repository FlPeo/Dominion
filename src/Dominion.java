public class Dominion
{
    public static void main (String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                Accueil accueil = new Accueil();
                Model_Accueil modelAccueil = new Model_Accueil();
                Control_Accueil control = new Control_Accueil(accueil, modelAccueil);
            }
        });
    }
}