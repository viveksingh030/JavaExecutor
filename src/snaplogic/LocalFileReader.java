package snaplogic;

import java.io.*;

public class LocalFileReader {
    public static void main(String[] args) throws IOException {
        System.out.println(readFile());
    }

    private static String readFile() throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new FileReader("../file.txt"));
        StringBuilder str=new StringBuilder();
        String line;
        while((line=bufferedReader.readLine())!=null){
            str.append(line).append("\n");
        }
        return  str.toString();
    }
}
