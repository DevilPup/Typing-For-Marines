package marinetyping;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Joe Pegram
 */
public class Player {
    private double oneWPM = 0;
    private String onePlayer = " ";
    private String oneRank = "";
    private int oneLevel = 0;
    
    Player(){
    }
    
    Player(String name, double words,int level) {
        setName(name);
        setWPM(words);
//        setRank(rank);
        setLevel(level);
    }
    
    public void setWPM(double words){
        this.oneWPM = words;
    }
    
    public double getWPM(){
        return this.oneWPM;
    }
    
    public void setName(String playerOne){
        this.onePlayer = playerOne;
    }
    
    public String getName(){
        return this.onePlayer;
    }
    
//    public void setRank(String oneRank){
//        this.oneRank = oneRank;
//    }
//    
//    public String getRank(){
//        return this.oneRank;
//    }
    
    public void setLevel(int level){
        this.oneLevel = level;
    }
    
    public int getLevel(){
        return oneLevel;
    }
    
    /**
     * Write the object to a PrintWriter
     * @param	out	PrintWriter to write the object
     */
    public void save(PrintWriter out) throws IOException{
        
        try {
            //load the playerOne list (if it exists)Scanner in = new Scanner(file);
            File file = new File("player.txt");
            Scanner in = new Scanner(file);
            if(file.length() == 0) {
                    //load the file (if it exists)
                    out.println(getName() + " " + getWPM() + " " + getLevel());
                    
            } else{
                out.println();
                out.println(getName() + " " + getWPM() + " " + getLevel());
            }
            in.close();
        } catch(IOException ex) {
                System.out.println("Error occured while reading file.");
        }
	    
    }


    /**
     * Load an object from a scanner
     * @param	in	Scanner from which we read the object
     */
    public void load(Scanner in) throws IOException{
	    setName(in.next());
	    setWPM(in.nextFloat());
            setLevel(in.nextInt());
    }
    
    @Override
    public String toString(){
        return String.format("%s %.1f %d", onePlayer, oneWPM, oneLevel);
    }
}
