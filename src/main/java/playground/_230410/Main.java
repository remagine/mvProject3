package playground._230410;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String data = "asdf1234 ㅁㄴㅇㄹ1234";
        String path = "test.txt";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        OutputStream outputStream = new FileOutputStream(path);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        try (outputStream;
             bufferedOutputStream;) {
            bufferedOutputStream.write(bytes); // try-resource 에서 flush,close 를 처리해준다.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InputStream inputStream = new FileInputStream(path);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader, 8192);
        try (inputStream;
             bufferedInputStream;
             inputStreamReader;
             bufferedReader;
        ) {
            char[] charBuffer = new char[4];
            while (true) {
                int len = bufferedReader.read(charBuffer);
                if (len == -1) {
                    break;
                }
                System.out.print(new String(charBuffer, 0, len));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}