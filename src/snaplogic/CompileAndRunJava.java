package snaplogic;

import java.io.*;

public class CompileAndRunJava {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter java file name to compile and run");
        String fileName=bufferedReader.readLine();
        // Compile the first Java class
        int exitCode = compileJavaClass(fileName);
        if(exitCode==0) {
            runJavaClass(fileName.substring(0, fileName.indexOf(".java")));
        }
    }

    public static int compileJavaClass(String className) {
        try {
            Process process = Runtime.getRuntime().exec("javac " + className);
            BufferedReader reader;
            int exitCode=process.waitFor();
            if(exitCode==0) {
                System.out.printf(className+" compiled successfully");
                return 0;
            }
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            System.out.println("compile error of " + className + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            return exitCode;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static void runJavaClass(String className) {
        try {
            Process process = Runtime.getRuntime().exec("java " + className);
            BufferedReader reader;
            if(process.waitFor()==0) {
                 reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            }
            else{
                reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            }
            String line;
            System.out.println("Output of " + className + ":");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println(className + " exited with code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
