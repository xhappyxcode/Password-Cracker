/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordcracker;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Joy
 */
public class PasswordCracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Joy\\Downloads\\DLSU\\ETHIHAC\\Linux passwd_shadow File Password Cracker\\PasswordCracker\\src\\textFiles\\passwd.txt";
        ReadFile readFile = new ReadFile(path);
        ArrayList<String> textData = readFile.OpenFile();
        for(int i = 0; i < textData.size(); i++) {
            System.out.println(textData.get(i));
        }
    }
    
}
