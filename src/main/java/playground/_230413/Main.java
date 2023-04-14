package playground._230413;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        String text = "test hello world";
        String path = "test1";
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
        try (OutputStream outputStream = new FileOutputStream(path);
             BufferedOutputStream bos = new BufferedOutputStream(outputStream, 8192);) {
            bos.write(bytes,0 , bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try ( InputStream inputStream = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(inputStream, 8192);
        InputStreamReader inputStreamReader = new InputStreamReader(bis, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader( inputStreamReader, 8192)){
            char[] buffer = new char[4];
            while(true){
                int len = bufferedReader.read(buffer);
                if(len == -1 ){
                    break;
                }
                System.out.printf(new String(buffer,0,len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
