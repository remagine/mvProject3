package playground._230418;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main2 {


    public static void main(String[] args) {
        // BufferedInputStream, InputStreamReader 용례추가
        // data 참조변수를 출력하고자 한다.
        // 이는 논리문자열을 메모리로 부터 스트림밍 처리로 출력하겠다는 의미이다.
        // 스트리밍 처리를 위해 문자열을 물리 데이터인 바이트 배열로 변환하려한다.
        String data = "hello world";
        // 논리 <> 물리 데이터 간 변환에는 변환 규칙이 필요하며 문자열 변환 규칙은 인코딩/디코딩라 부른다
        // 가장 범용적인 Utf-8로 변환해보기로 한다.
        // 아스키 코드를 기반으로 영어 1바이트 한글 3바이트 최대 4바이트 까지 가변 사이즈를 갖는다.
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        // 바이트 데이타를 다루기에 알맞은 구현체를 사용한다
        // 하지만 구현체에 종속되지 않고
        // inputStream의 동작에 집중하기에 참조변수는 조상인 inputStream으로 선언한다
        // io 자원 사용이 끝나면 해제를 해야 하는데, 이때 try-resource 를 사용하는 것이 편리하다
        try(InputStream inputStream = new ByteArrayInputStream(bytes);
            // 메모리로 근원지로 부터 스트리밍 처리할 때 중요한 것은 버퍼처리이다
            // 버퍼는 스트리밍 데이터를 근원지로 부터 정해진 사이즈 만큼 씩 가져오는 단위이다
            // 버퍼처리를 하지 않으면 1byte 단위로 스트림 데이터가 흐르게 되는데
            // 이는 근원지와 스트림 io간 연결비용이 커지게 된다
            // 버퍼 사이즈 만큼 스트림이 근원지로 부터 데이터를 가져오는 반복횟수가 줄어든다
            BufferedInputStream bis = new BufferedInputStream(inputStream, 8192);
            // 8192 사이즈 버퍼는 장미칼과 같이 사용하기 좋은 사이즈이다.
            // 하지만 근원지의 종류가 파일이라면 턱없이 작은 사이즈이다. 스트림의 근원의 종류에 따라 달라져야 할 것이다.
            // 버퍼 처리된 스트림이 생성되었다.
            // 출력의 방식은 출력의 도구에 의해 결정된다
            // 우리는 자바 콘솔 출력을 위해 System.out을 사용할 것이다.
            // 콘솔 출력은 논리 데이터는 문자열을 매개변수로 받는다.
            // 그러므로 이진코드 스트리밍 처리를 논리데이터 스트리밍 처리해 줄 도구가 필요하다
            InputStreamReader inputStreamReader = new InputStreamReader(bis, StandardCharsets.UTF_8);
            // 바이트 데이터 스트림을 받아 어떤 디코딩으로 변환할지 정하면
            // 바이트 데이터 스트림을 문자열 스트림으로 변환한다.
            ){
            // 문자열 스트림을 다루므로 문자열 버퍼를 준비한다.
            char[] charBuffer = new char[1024];
            // 문자열 스트리밍 처리를 위한 반복문 선언하고
            // 효율적인 문자열 처리를 위해 StringBuilder를 선언한다
            StringBuilder stringBuilder = new StringBuilder();
            while(true){
                int len = inputStreamReader.read(charBuffer);
                if(len == -1){
                    break;
                }
                stringBuilder.append(charBuffer,0,len);
            }

            System.out.println(stringBuilder);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
