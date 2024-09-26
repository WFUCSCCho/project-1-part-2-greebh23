/**********************************************************************
 * @file Proj1.java
 * @brief This program gets the correct number of files. This program accepts 2 files.
 * Kaggle Dataset for Remote Work: https://www.kaggle.com/datasets/mrsimple07/remote-work-productivity
 * @author Blythe Greene
 * @date: September 26, 2024
 ***********************************************************************/
import java.io.FileNotFoundException;

public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 2){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser(args[0], args[1]);
    }
}