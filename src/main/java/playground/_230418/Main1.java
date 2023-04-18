package playground._230418;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main1 {
    public static void main(String[] args) throws IOException {
        // String은 문자배열로 논리 데이터 이다. 문자배열을 자바에서는 String이라는 클래스로 다룬다.
        // 클래스는 속성 멤버변수와 기능 메서드로 이루어져있다. String은 문자배열값과 문자를 다루는 기능의 객체이다.
        String data = "hello world";
        // 메모리에 있는 문자열 출력을 하기 위해서
        // 바이트배열을 받을 인풋스트림이 필요하다. 
        // 왜나하면 컴퓨터는 스트림을 통해 입출력을 받기 때문이다.
        // 그리고 스트림은 바이트단위로 움직인다.
        // 문자열을 바이트 배열로 바꾸는 작업은 논리 데이터를 물리 데이터로 바꾸는 작업이다
        // 이 변환작업에는 변환규칙이 필요한데, 문자열 변환 규칙을 인코딩/디코딩이라 한다.
        // utf-8로 바이트 배열을 만들었따
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        // 바이트 배열을 인풋스트림을 통해 출력 스트리밍 처리를 할 것이다
        // 자바에서 인풋스트림은 여러 구현체가 있는데 이중 바이트어레이스트림은 메모리에 있는 바이트 배열을 다룰 때 사용한다
        // ByteArrayStream 형으로 참조변수를 선언하지 않은 것은
        // 구체적인 구현에 의존하지 않고, inputStream 본연의 동작에 집중하겠다는 의미이다.
        InputStream inputStream = new ByteArrayInputStream(bytes);
        // 스트리밍 처리 전, 효율적인 처리를 위한 버퍼처리가 필요하다
        // 여기서 버퍼처리는 read()메서드에서 사용될 버퍼를 의미한다.
        // read 메서드에서 버퍼는 끝까지 채워지고 채워진 버퍼 자릿수를 리턴하고
        // 아직 스트림에 데이터가 남아있으면 기존 버퍼를 덮어쓴다
        // 스트림에 남은 데이터가 없으면 eof = -1 을 리턴한다
        // 스트리밍 처리는 연속된 데이터를 분할처리하는 것이므로 반복과 반복탈출조건을 명시해야한다
        byte[] buffer = new byte[1024];
        while (true) {
            int len = inputStream.read(buffer);
            if (len == -1) {
                break;
            }
            System.out.print(new String(buffer, 0, len, StandardCharsets.UTF_8));
        }

    }
}
