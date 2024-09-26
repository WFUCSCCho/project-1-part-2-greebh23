/**********************************************************************
 * @file Parser.java
 * @brief This program implements the Parser class. It reads an output
 * file and creates an output file.
 * @author Blythe Greene
 * @date: September 25, 2024
 ***********************************************************************/
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

///cre own inut file

public class Parser {

    //Create a BST tree of Integer type
    //BST of type RemoteWork
    private BST<RemoteWork> mybst = new BST<>();

    public Parser(String filename, String csvname) throws FileNotFoundException {
        process(new File(filename), new File(csvname));
    }

    // Implement the process method
    // Get rid of extra spaces for the input commands.
    // Calls operate_BST for the right commands to update the file
    public void process(File input, File csvinput) throws FileNotFoundException {

        //Divide every line into a sting array.
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;
        FileInputStream csvFileNameStream = null;
        Scanner inputHoursScanner = null;

        try {
            //Open the input file
            inputFileNameStream = new FileInputStream(input);
            inputFileNameScanner = new Scanner(inputFileNameStream);
            //Open CSV file
            csvFileNameStream = new FileInputStream(csvinput);
            inputHoursScanner = new Scanner(csvFileNameStream);


            //Ignores first line.
            inputFileNameScanner.nextLine();
            inputHoursScanner.nextLine();

            //ArrayList<RemoteWork> workHoursList = new ArrayList<RemoteWork>;
            //loop through each line of CSV file
            while(inputHoursScanner.hasNext()){
                String data = "insert," + inputHoursScanner.nextLine();//line = ln
                String[] parts = data.split(","); // split the string into multiple parts
                //System.out.println(parts);
                RemoteWork r;
                //Insert data into BST
                operate_BST(parts);

            }
            //Is there a blank line?
            while (inputFileNameScanner.hasNext()) {
                String ln = inputFileNameScanner.nextLine();//line = ln
                ln = ln.strip();

                int ind = ln.indexOf(" ");//index

                if(ln.equals("print")) {
                    ind = 0;
                }
                else if(ind < 0 && !ln.trim().isEmpty()) {
                    operate_BST(new String[] {ln});
                    continue;
                }
                else if(ind <= -1){
                    continue;
                }

                int i;
                for(i = ind; i < ln.length() ; i++) {
                    if(ln.charAt(i)== ' '  && !Character.isWhitespace(ln.charAt(i + 1))) {
                        continue;
                    }
                    else if(ln.substring(i, i + 1).equals(" "))
                    {
                        ln = ln.substring(0, i) + ln.substring(i + 1);
                        i--;//Decrement
                    }
                }
                String[] command = ln.split(" ");//Array of strings
                //System.out.println(Arrays.toString(command));
                //call the operate_BST method
                if(command.length!= 6){
                    operate_BST(new String[] {ln});
                }
                else if(command.length > 0) {
                    operate_BST(command);
                }
            }

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        //Make sure the input file is closed
        finally {
            if (inputFileNameStream != null) {
                try {
                    inputFileNameStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    // Implement the operate_BST method
    // Find out the next command and employ the BST
    public void operate_BST(String[] command) {
        RemoteWork r = new RemoteWork();
        //System.out.println(Arrays.toString(command));
        if(command.length == 6){
            //System.out.println(command[1]);
            r = new RemoteWork(Integer.parseInt(command[1]), command[2],
                    Integer.parseInt(command[3]), Integer.parseInt(command[4]),
                    Integer.parseInt(command[5]));
        }
        /*
        else if(command.length == 2){
            //System.out.println(command[1]);
            r.setHours_Worked_Per_Week(Integer.parseInt(command[1]));
        }
        */

        switch (command[0]) {
            //Every case below is their own command.
            //call writeToFile
            //mybst is of Integer type
            case "search" -> {
                RemoteWork r1 = mybst.search(r);
                if(r1 != null) {
                    writeToFile("found " + r.toString(), "./result.txt");
                }
                else {
                    writeToFile("search failed", "./result.txt");
                }
            }

            case "remove" -> {
                //mybst.iterator();
                RemoteWork r2 = mybst.remove(r);
                if(r2 != null) {
                    writeToFile("removed " + r.toString(), "./result.txt");
                }
                else {
                    writeToFile("remove failed", "./result.txt");
                }
            }
            case "insert" -> {
                mybst.insert(r);
                //mybst.iterator();
                writeToFile("insert " + r.toString(), "./result.txt");
            }


            case "print" -> {
                String res = mybst.iterator();
                writeToFile(res, "./result.txt");
            }

            // The default case for Invalid Command
            default -> writeToFile("Invalid Command", "./result.txt");
        }
    }


    // Implement the writeToFile method
    // Generate the result file from the output of the command
    public void writeToFile(String content, String filePath) {
        //write BST result to the result file
        // You have to generate the file if it does not exist already
        FileWriter f = null;

        // Try block opens the file and writes to it.
        try {
            f = new FileWriter(filePath, true);
            f.write(content + "\n");
            f.close();
        }
        // The catch block is for exceptions.
        catch(Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}

