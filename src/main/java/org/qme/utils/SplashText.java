package org.qme.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class SplashText {

    public static String grabSplash(File file) throws FileNotFoundException {
        String result = null;
        Random rand = new Random();
        int i = 0;
        for(Scanner sc = new Scanner(file); sc.hasNext(); )
        {
            ++i;
            String line = sc.nextLine();
            if(rand.nextInt(i) == 0) {
                result = line;
            }
        }

        return result;
    }

}
