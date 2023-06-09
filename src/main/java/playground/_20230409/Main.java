/*
package playground._20230409;

import java.io.InputStream;

public class Main {
    public static void main(String[] agrs) {
        // 주석으로 컴퓨터 과학을 최대한 설명
        // 템플릿 코딩에 특화되어 있다
        // 어떤 코드 뭉치를 가져와서
        // 기본 인풋스트림

        // 메모리에 저장된 유니코드 스트링 데이터, 우리는 이 데이터를 출력하기로 하였다.
        String text = "hello world";
        // 스트림은 이진코드를 다루는 프로그램이다. 때문에 스트링을 바이트 배열로 바꿔야 하낟
        // 논리적인 스트링을 물리적 데이터인 이진코드로 바꿀 때는 어떻게 바꿀 것인가를 컴퓨터에게 알려줘야 하낟
        // 이것을 인코딩이라 한다
        byte[] bytes = text.getBytes("utf-8");
        // 바이트 배열을 컴퓨터가 처리할 수 잇는 스트림으로 바꿔줘야 한다
        // 바이트는 메모리에 있고, 바이트 처리 구현체인 bytearraystream을 써야 한다
        // byte 배열의 처음부터 끝까지 사용하기로 하낟
        // 또한 특정 구현체에 종속되지 않고 , 범용적인 InputStream을 사용해서 구현할 것이다
        // 왜나하면 중요한 것은 어떤 근원지에서 온 것인가가 아니라, 근원지에서 보내진 바이트 스트림 처리가 중요하기 때문이다
        InputStream is = new ByteArrayInputStrteam(bytes, 0, bytes.length);
        // 이제 스트리밍 처리를 하게 될 텐데, 스트리밍 처리는 연속적인 스트림을 분할 연속 처리이다.
        // 그러므로 어떤 단위로 처리할 것인지 분할단위를 정의하고
        // 연속적인 처리이므로 언제 끝날지를 정의해야 한다.
        // 단위 = 버퍼, 끝 = eof
        byte[] buffer = new byte[1024];
        while(true){
            // inputStream은 read() 메소드를 사용해 스트리밍 처리를 한다.
            // read메소드는 주어진 버퍼를 얼마나 읽어들였는지를 int값으로 리턴한다

             // 스트림 연속적인 바이트 데이터가 버퍼에 담겼고, 담긴 만큼 리턴
            int len = is.read(buffer);
            if( len == -1){
                break;
            }

            // 우리가 처리할 것은 문자열로 출력하는 것이다
            // 문자열 출력은 논리적인 문자열을 출력하는 것이고, 우리는 print()를 도구로 활용할 것이다.
            // 그러므로 논리적인 데이터가 필요하고, 그러니 물리적인 데이터를 논리적인 데이터로 바꿔야한다.
            // 논리적인 데이터 변환에는 변환 방식인 인코딩/디코딩 형식을 컴퓨터에게 알려줘야 한다.
            String s = new String(buffer, 0, len, "utf-8");
            print(s);

        }





    }
}
*/
