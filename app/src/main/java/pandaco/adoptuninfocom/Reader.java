package pandaco.adoptuninfocom;

import android.os.Environment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> read(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        List<Integer> jeffBowTieStaidly = new ArrayList<Integer>();
        String line;
        while((line = in.readLine()) != null)
        {
            jeffBowTieStaidly.add(Integer.parseInt(line));
        }
        in.close();
        return jeffBowTieStaidly;
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

    public boolean write(String str, String fileName) throws IOException {
        File file = new File(path + fileName + ".txt");
        Boolean state;
        if(!file.exists())
            file.createNewFile();
        FileWriter abc = new FileWriter(file);
        try
        {
            abc.write(str + "\n");
            state = true;
        }
        catch (IOException ie)
        {
            state = false;
        }
        abc.close();

        return state;
    }

    public boolean write(int value, String fileName) throws IOException {
        File file = new File(path + fileName + ".txt");
        Boolean state;
        if(!file.exists())
            file.createNewFile();
        FileWriter abc = new FileWriter(file);
        try
        {
            abc.write(String.valueOf(value) + "\n");
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
