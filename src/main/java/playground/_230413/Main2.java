package playground._230413;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main2 {
    public static void main(String[] args) {
        String data = "123 안녕 세상";
        String path = "here";
        String path2 = "there";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        try (OutputStream outputStream = new FileOutputStream(path);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        ) {
            bufferedOutputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream inputStream = new FileInputStream(path);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        OutputStream outputStream = new FileOutputStream(path2);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);){
            byte[] buffer = new byte[8192];
            while(true){
                int len = bufferedInputStream.read(buffer);
                if(len == -1){
                    break;
                }
                bufferedOutputStream.write(buffer,0,len);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
