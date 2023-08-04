package ex05;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 클라이언트는 read 버퍼를 추가로 만들고
 * 서버는 write 버퍼를 추가로 만든다
 */
public class MyClient {

    public MyClient() throws IOException {
        // 1. 서버 연결
        Socket socket = new Socket("127.0.0.1", 10000);

        // 2. 버퍼 생성 (송수신 선 만듬)
        Scanner keyboard = new Scanner(System.in);

        PrintWriter request = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);//자동 플러쉬
        BufferedReader response = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "utf-8")
                //키보드로 부터 읽는다
        );

        while(true){
            // 3. 키보드로 부터 입력 대기
            String requestBody = keyboard.nextLine();

            // 4. 서버로 요청
            request.println(requestBody);

            // 5. 서버로부터 응답
            String responseBody = response.readLine();
            System.out.println("서버로부터 받은 메시지 : "+responseBody);
        }
//소켓을 다시 생성해서 연결할 필요없고 계속 쓰기만 하면 되니까 여기에 while문을 돌려서 demon을 만든다
    }

    public static void main(String[] args) {
        try {
            new MyClient();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
//반이중
//보냈으면 소켓의 연결이 끊김
//끊어야지 안그러면 피곤함 전화 기록을 1년 넘게 보관하려면 피곤하다
//html 보내면 받는 입장에서 파싱을한다
//웹은 스테이트리스 (소켓 연결 끊음) 상태저장을 안함


