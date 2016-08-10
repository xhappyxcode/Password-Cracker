/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordcracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFile {
    private String path;
    
    public ReadFile(String file_path) {
        this.path = file_path;
    }
    
    public ArrayList<String> OpenFile() throws IOException {
        ArrayList<String> textData = new ArrayList<String>();
        try {
            FileReader file = new FileReader(path);
            BufferedReader textReader = new BufferedReader(file);

            String currentLine = null;
            while ((currentLine = textReader.readLine()) != null) {
                textData.add(currentLine);
            }

            textReader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File not found!");
        }
        return textData;
    }
}
