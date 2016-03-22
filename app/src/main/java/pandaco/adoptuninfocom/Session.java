package pandaco.adoptuninfocom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lerouxca on 22/03/2016.
 */
public class Session
{

    private static int idConnectedPerson = 0; //id de la personne connectee
    private static int idPersonne = 0; //personne a trouver pour l'algo de recherche
    private static List<Integer> kikilavu = new ArrayList<Integer>();
    private static List<String> infos = new ArrayList<String>();

    public static void setIdPersonne(int param){ Session.idPersonne = param; }
    public static int getIdPersonne(){ return Session.idPersonne; }

    public static void setIdConnectedPerson(int param){ Session.idConnectedPerson = param; }
    public static int getIdConnectedPerson(){ return Session.idConnectedPerson; }

    public static void setInfos(List<String> lst){ Session.infos = lst; }
    public static List<String> getInfos(){ return Session.infos; }

    public static List<Integer> getKikilavu() throws IOException {
        Reader r = Reader.getInstance();
        return r.read("dejavu");
    }
}