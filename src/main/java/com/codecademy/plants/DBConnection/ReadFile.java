import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner; // Import the Scanner class to read text files

// This file removes all ID numbers from data.sql file so that the IDs can be generated.

public class ReadFile {
    public static void main(String[] args) {
        try {
            File myObj = new File("src/main/resources/data.sql");
            Scanner myReader = new Scanner(myObj);
            String newString = "";
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitStr = data.split(" ");
                for (String a : splitStr)
                    if (a.contains("(")) {
                        String newWord = "(";
                        newString += newWord;
                    } else if (a.contains(";")) {
                        newString += a + "\n";
                    } else {
                        newString += a + " ";
                    }
            }
            String newString1 = newString.substring(0, 25000);
            String newString2 = newString.substring(25000);
//            System.out.println(newString1);
//            System.out.println(newString2);
            myReader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/data.sql"), 49906);
            writer.write(newString, 0, 49906);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An Error occurred");
            e.printStackTrace();
        }
    }
}
