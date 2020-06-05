package source;

import java.io.*;

/**
 * @author Iryna Matviienko
 */
public class ProgressStorage {

    private static File file = new File("Progress.txt");

    /**
     * Write updated data about one level to file (other levels without changes)
     * @param level
     * @param isCompleted
     * @param artifactIsCollected
     */
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

    /**
     * Reset content, start ew game
     */
    public static void resetContent(){
        String[] content  = getContent();
        String strContent = "" + content[0] + "\n";
        for (int i = 0; i <=5; i++){
            if (i != 0){
                content[i] = i + "--\n";
                strContent = strContent + content[i];
            }
        }
        try{
            writeContentToFile(strContent);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Write new content to file
     * @param strContent
     * @throws IOException
     */
    private static void writeContentToFile(String strContent) throws IOException{
        BufferedWriter wr = new BufferedWriter(new FileWriter(file));
        wr.write(strContent);
        wr.close();
    }

    /**
     * Get content from file
     * @return
     */
    public static String[] getContent(){
        String[] content = new String[6];
        int i = 0;
        try {
        BufferedReader rd = new BufferedReader(new FileReader(file.getName()));
            while (true){
                String s = rd.readLine();
                if ((s == null)||(i == 6)) break;
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

    /**
     * Get whether level is completed
     * @param level
     * @return
     */
    public static boolean getLevelIsCompleted(int level){
        String[] content = getContent();
        return content[level].charAt(1) == '+';
    }

    /**
     * Get whether artifact is collected
     * @param level
     * @return
     */
    public static boolean getArtifactIsCollected(int level){
        String[] content = getContent();
        return content[level].charAt(2) == '+';
    }
}
