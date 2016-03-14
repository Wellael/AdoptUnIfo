package pandaco.adoptuninfocom;

import android.os.Environment;

import java.io.*;

/**
 * Created by dehoursl on 14/03/2016.
 */
public class Reader {

    private final String path = Environment.getRootDirectory().toString() + "/adoptuninfo/";
    private final String file_name = "log.txt";
    private static Reader instance = null;

    private Reader(){}

    public static Reader getInstance()
    {
        if(instance == null)
            instance = new Reader();
        return instance;
    }

    public boolean read()
    {

        return false;
    }

    public boolean write(String str) throws IOException {
        File file = new File(path + file_name);
        Boolean state;
        if(!file.exists())
            file.createNewFile();
        FileWriter abc = new FileWriter(file);
        try
        {
            abc.write(str);
            state = true;
        }
        catch (IOException ie)
        {
            state = false;
        }
        abc.close();

        return state;
    }
}
