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
        Controller controller = new Controller();
        controller.readFiles();
//        ArrayList<String> textData = controller.getDictionaryData();
//        for(int i = 0; i < textData.size(); i++) {
//            System.out.println(textData.get(i));
//        }
        controller.readPasswdData();
    }
    
}
