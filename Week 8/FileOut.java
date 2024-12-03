import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;

public class FileOut{
    public static void main(String[] args){
        Path file = Paths.get("/Users/arundp/Desktop/OOP Lab/Week 8/greeting.txt");
        String s = "GoodBye World";
        byte[] data = s.getBytes();

        

        try{
            OutputStream output = new Buffered(Files.newOutputStream();
            output.write(data);
            output.flush();
            output.close();
        }catch(Exception e){
            System.out.println("Couldn't write string to the file");
        }
        
    }
}