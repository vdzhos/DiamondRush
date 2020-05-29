package source;

import java.io.*;

public class ProgressStorage {

    private static File file = new File("Progress.txt");

    /*public ProgressStorage(){
        System.out.println("File created");
        try{
            writeContentToFile("Progress in 'Diamond Rush'\n1--\n2--\n3--\n4--\n5--\n");
            updateContent(2, true, true);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }*/

    public static void updateContent(int level, boolean isCompleted, boolean artifactIsCollected){
        String[] content  = getContent();
        String str = "" + level;
        if (isCompleted) str = str + "+";
        else str = str + "-";
        if (artifactIsCollected) str = str + "+";
        else str = str + "-";
        content[level] = str;
        String strContent = "";
        for (int i = 0; i < 6; i++){
            strContent = strContent + content[i] + "\n";
        }
        try{
            writeContentToFile(strContent);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void writeContentToFile(String strContent) throws IOException{
        BufferedWriter wr = new BufferedWriter(new FileWriter(file));
        wr.write(strContent);
        wr.close();
    }

    public static String[] getContent(){
        String[] content = new String[6];
        int i = 0;
        try {
        BufferedReader rd = new BufferedReader(new FileReader(file.getName()));
            while (true){
                String s = rd.readLine();
                if ((s == null)||(i == 6)) break;
                //content += s + "\n";
                content[i] = s;
                i++;
            }
            rd.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static boolean getLevelIsCompleted(int level){
        String[] content = getContent();
        return content[level].charAt(1) == '+';
    }

    public static boolean getArtifactIsCollected(int level){
        String[] content = getContent();
        return content[level].charAt(2) == '+';
    }
}
