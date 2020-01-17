package dominion;

public class Dominion
{
    public static void main (String[] args)
    {
        if(args.length != 0){
            System.out.println("USAGE: java Dominion");
            System.exit(1);
        }

        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                View_Accueil accueil = new View_Accueil();
                new Control_Accueil(accueil);
                accueil.display();
            }
        });
    }
}