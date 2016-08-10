/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordcracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.User;

public class Controller {
    private final int LIMIT = 1000;
    private final String SEMI_COLON = ":";
    
    private ArrayList<String> passwdData;
    private ArrayList<String> shadowData;
    private ArrayList<String> dictionaryData;
    
    private ArrayList<User> users;
    
    private String result;
    
    public Controller() {
        users = new ArrayList<User>();
    }
    
    /* open and read files then save data */
    public void readFiles() throws IOException {
        String passwd_path = "passwd.txt";
        ReadFile readPass = new ReadFile(passwd_path);
        passwdData = readPass.OpenFile();
        
        String shadow_path = "shadow.txt";
        ReadFile readShad = new ReadFile(shadow_path);
        shadowData = readShad.OpenFile();
        
        String dictionary_path = "dictionary.txt";
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
            if(userId > LIMIT) {
                user = new User();
                user.setUsername(lineData[0]);
                user.setPassword(lineData[1]);
                user.setFullname(lineData[4]);
//                System.out.println(user);
                users.add(user);
            }
        }
    }
    
    public void readShadowData() {
        String currentLine, username, password;
        String lineData[];
        User user;
        int currUser;
        boolean found;
        for(int i = 0; i < shadowData.size(); i++) {
            currentLine = shadowData.get(i);
            lineData = currentLine.split(SEMI_COLON);
            username = lineData[0];
            password = lineData[1];
            found = false;
            currUser = 0;
            while(currUser < users.size() && found == false) {
                user = users.get(currUser);
                if(user.getUsername().contentEquals(username)) {
                    user.setPassword(password);
//                    System.out.println(user.getPassword());
                }
                currUser++;
            }
        }
    }
    
    public void crackPassword() {
        
       String currentLine;
       StringBuilder sb = new StringBuilder();
       boolean matched = false;
       int currUser =0;
       User user;
       
       while(currUser < users.size()){
    	   user = users.get(currUser);
           if(user.getPassword().equals("*") || user.getPassword().equals("!") || user.getPassword().equals("x")){
        	   matched = true;
           } else{
                for(int i =0; i < dictionaryData.size(); i++) {
                    currentLine = dictionaryData.get(i);
                    if(Sha512Crypt.verifyPassword(currentLine,user.getPassword()) == true){
                        user.setPassword(currentLine);
                        sb.append("Fullname: " + users.get(currUser).getFullname() + "\t\t\tUsername: " + users.get(currUser).getUsername() + "\t\t\tPassword: " + dictionaryData.get(i) + "\n");
                        matched = true;
                        break;
                    }
                }
                
           }
           if(matched == false) {
         	   sb.append("Fullname: " + users.get(currUser).getFullname() + "\t\t\tUsername: " + users.get(currUser).getUsername() + "\t\t\tPassword not found!\n");
            }
           
           currUser++;
   		}
       
       result = sb.toString();
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
    
    public String getResult() {
    	return this.result;
    }
    
    public void createFile(String text) {
    	JFrame parentFrame = new JFrame();
    	 
    	JFileChooser fileChooser = new JFileChooser(new File("C:\\"));
    	fileChooser.setDialogTitle("Specify a file to save");   
    	fileChooser.setFileFilter(new FileTypeFilter(".txt", "Text File"));
    	int userSelection = fileChooser.showSaveDialog(parentFrame);
    	 
    	if (userSelection == JFileChooser.APPROVE_OPTION) {
    	    File fileToSave = fileChooser.getSelectedFile();
    	    try {
    	    	FileWriter fw = new FileWriter(fileToSave.getPath());
    	    	fw.write(text);
    	    	fw.flush();
    	    	fw.close();
    	    } catch(Exception e2) {
    	    	JOptionPane.showMessageDialog(null, e2.getMessage());
    	    }
    	}   	
    }
    
}
