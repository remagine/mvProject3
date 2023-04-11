package playground._230411;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String data = "헬로 월드 hello world!";
        for (int i = 0; i < 1000; i++) {
            sb.append(data);
            sb.append(i);
        }
        String text = sb.toString();
        String path = "test_230411";

        try (InputStream inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
             OutputStream outputStream = new FileOutputStream(path);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);) {
            byte[] buffer = new byte[4];
            while(true) {
                int len = bufferedInputStream.read(buffer);
                if(len == -1){
                    break;
                }
                bufferedOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}