/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordcracker;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joy
 */
public class ReadFile {
//    private String path;

    private BufferedReader br;
    private File txtFile;
    private FileInputStream fis;
    private String line, text;
    private StringBuilder isb;

    public ReadFile(String filePath) {
        try {
            txtFile = new File(filePath);
            fis = new FileInputStream(txtFile);
            isb = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            line = br.readLine();
            while (line != null) {
                isb.append(line);
                isb.append('\n');
                line = br.readLine();
            }
            text = isb.toString();
            
            //for checking purposes only
            System.out.println(text);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("File not found!");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public String getFileContents() {
        return this.text;
    }

    /*public ArrayList<String> OpenFile() throws IOException {
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
    }*/
}
