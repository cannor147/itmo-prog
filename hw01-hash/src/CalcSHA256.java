import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CalcSHA256 {

    public static final String algorithm = "SHA-256";

    public static void main(String args[]) {
        if (args.length == 1) {
            readFilesNames(args[0]);
        }
    }

    private static String calculating(String filesName) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filesName));
            byte[] hash = MessageDigest.getInstance(algorithm).digest(bytes);
            String hex = DatatypeConverter.printHexBinary(hash);
            return hex;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void readFilesNames(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferReading = new BufferedReader(fileReader);
            String filesName;
            while ((filesName = bufferReading.readLine()) != null) {
                System.out.println(calculating(filesName));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}