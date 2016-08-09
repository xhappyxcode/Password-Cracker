/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordcracker;

import java.io.IOException;
import java.util.ArrayList;
import model.User;

/**
 *
 * @author Joy
 */
public class Controller {
    private final int LIMIT = 1000;
    private final String SEMI_COLON = ":";
    
    private ArrayList<String> passwdData;
    private ArrayList<String> shadowData;
    private ArrayList<String> dictionaryData;
    
    private ArrayList<User> users;
    
    /* open and read files then save data */
    public void readFiles() throws IOException {
        String passwd_path = "src\\textFiles\\passwd.txt";
        ReadFile readPass = new ReadFile(passwd_path);
        passwdData = readPass.OpenFile();
        
        String shadow_path = "src\\textFiles\\shadow.txt";
        ReadFile readShad = new ReadFile(shadow_path);
        shadowData = readShad.OpenFile();
        
        String dictionary_path = "src\\textFiles\\dictionary.txt";
        ReadFile readDict = new ReadFile(dictionary_path);
        dictionaryData = readDict.OpenFile();
    }
    
    public void readPasswdData() {
        String currentLine;
        String lineData[];
        User user;
        for(int i =0; i < passwdData.size(); i++) {
            currentLine = passwdData.get(i);
            lineData = currentLine.split(SEMI_COLON);
            int userId = Integer.parseInt(lineData[2]);
            if(userId < LIMIT) {
                user = new User();
                user.setUsername(lineData[0]);
                user.setPassword(lineData[1]);
                user.setFullname(lineData[4]);
                System.out.println(user);
                users.add(user);
            }
        }
    }
    
    public void readShadowData() {
        String currentLine = shadowData.get(0);
        String lineData[] = currentLine.split(SEMI_COLON);
        
    }

    /**
     * @return the passwdData
     */
    public ArrayList<String> getPasswdData() {
        return passwdData;
    }

    /**
     * @return the shadowData
     */
    public ArrayList<String> getShadowData() {
        return shadowData;
    }

    /**
     * @return the dictionaryData
     */
    public ArrayList<String> getDictionaryData() {
        return dictionaryData;
    }
    
    
}
