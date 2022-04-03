package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author tortl
 */
public class FileManager {

    public static final String CP1251 = "cp1251";
    public static final String UTF_8 = "UTF-8";

    public static String read(File file, String encoding) {
        String str = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), encoding
        ))) {
            StringBuilder sb = new StringBuilder();
            while ((str = br.readLine()) != null) {
                sb.append(str).append("\n");
            }
            str = sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return str;
    }

    public static void write(String str, File file, String encoding) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), encoding
        ))) {
            bw.write(str);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
     private void checkEncoding(File myFile){
//         FileInputStream fIn  = new FileInputStream(myFile);
//         byte[] buf = new byte[4096];
//         UniversalDetector detector = new UniversalDetector(null);
//         int nread;
//         while ((nread = fIn.read(buf)) > 0 && !detector.isDone()) {
//             detector.handleData(buf, 0, nread);
//         }
//         detector.dataEnd();
//         String encoding = detector.getDetectedCharset();
//         String chartsetName = null;
//         if (encoding.equalsIgnoreCase("WINDOWS-1252")){
//             chartsetName = "ISO-8859-1";
//         }
//         if (encoding.equalsIgnoreCase("UTF-8")){
//             chartsetName = "UTF-8";
//         }
//
//         BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn, chartsetName));
     }
}
