package pandaco.adoptuninfocom;

/**
 * Created by lerouxca on 22/03/2016.
 */
public class Session {

    private static int idPersonne = 0;

    public static void setIdPersonne(int param)
    {
        Session.idPersonne = param;
    }

    public static int getIdPersonne()
    {
        return Session.idPersonne;
    }
}