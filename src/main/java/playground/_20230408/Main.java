package playground._20230408;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        String data = "헬로 월드";
        // 10,000번 반복하여 문자열을 연결합니다.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("hello world 헬로 월드");
        }

        data = sb.toString();
        String path = "src/main/java/playground/_20230408/test.txt";
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        long startWriteTime = System.currentTimeMillis(); // 파일 출력 시작 시간 기록

        try (OutputStream outputStream = new FileOutputStream(path);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8192);
        ){
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long endWriteTime = System.currentTimeMillis(); // 파일 출력 종료 시간 기록

        // 저장된 파일을 가져와 출력하기
        long startReadTime = System.currentTimeMillis(); // 파일 입력 시작 시간 기록

        try (InputStream inputStream = new FileInputStream(path);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 8192);
        ){
            byte[] buffer = new byte[8192];
            StringBuilder stringBuilder = new StringBuilder();
            while(true){
                int len = bufferedInputStream.read(buffer);
                if(len == -1){
                    break;
                }
                stringBuilder.append(new String(buffer,0,len,StandardCharsets.UTF_8));
            }
            System.out.println(stringBuilder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long endReadTime = System.currentTimeMillis(); // 파일 입력 종료 시간 기록

        long writeTime = endWriteTime - startWriteTime; // 파일 출력에 걸린 시간 계산
        long readTime = endReadTime - startReadTime; // 파일 입력에 걸린 시간 계산

        System.out.println("");
        System.out.println("파일 출력에 걸린 시간: " + writeTime + "ms");
        System.out.println("파일 입력에 걸린 시간: " + readTime + "ms");
    }
}
