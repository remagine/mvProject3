package playground._230412;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        String text = "현재시각 : " + LocalDateTime.now();
        String path = "datetime.txt";
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);

        try (OutputStream outputStream = new FileOutputStream(path);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);) {
            bufferedOutputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream inputStream = new FileInputStream(path);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
        ) {
            StringBuilder sb = new StringBuilder();
            char[] charBuffer = new char[4096];
            while (true) {
                int len = bufferedReader.read(charBuffer);
                if (len == -1) {
                    break;
                }
                sb.append(new String(charBuffer,0,len));
            }

            System.out.println(sb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
