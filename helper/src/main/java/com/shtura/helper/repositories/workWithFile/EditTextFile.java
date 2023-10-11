package com.shtura.helper.repositories.workWithFile;

import java.io.File;
import java.io.FileOutputStream;

public class EditTextFile {

    public EditTextFile() {
        
    }
    
    public static void outputToFile(String str) throws Exception {
        
        File file = new File("src/main/resources/ansible/file/shtura/messageToWine/messageToWine.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        
        String greetings = System.lineSeparator() + str + System.lineSeparator() + System.lineSeparator() + "-----------------------------------------------------------------------"
                + System.lineSeparator() + "    Для закрытия этого окна кликните по нему";
        
        fileOutputStream.write(greetings.getBytes());

        fileOutputStream.close();
    }
}
