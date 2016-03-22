package pandaco.adoptuninfocom;

/**
 * Created by lerouxca on 22/03/2016.
 */
public class Session
{

    private static int idConnectedPerson = 0; //id de la personne connectee
    private static int idPersonne = 0; //personne a trouver pour l'algo de recherche

    public static void setIdPersonne(int param)
    {
        Session.idPersonne = param;
    }
    public static int getIdPersonne()
    {
        return Session.idPersonne;
    }

    public static void setIdConnectedPerson(int param)
    {
        Session.idConnectedPerson = param;
    }

    public static int getIdConnectedPerson()
    {
        return Session.idConnectedPerson;
    }

}