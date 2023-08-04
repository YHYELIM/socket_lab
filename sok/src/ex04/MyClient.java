package ex04;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

// HDX - 스레드가 필요 없다.
public class MyClient {

    public MyClient() throws IOException {
        // 1. 서버 대기중
        ServerSocket serverSocket = new ServerSocket(10000);
        Socket socket =  serverSocket.accept(); // 리스너 대기중
        System.out.println("클라이언트가 연결되었습니다");

        // 2.서버 요청 받음
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "utf-8")
        );
        String msg = reader.readLine(); // 버퍼 비우기
        System.out.println("클라이언트에게서 요청이 왔습니다 : "+msg);

        // 3. 구문 분석 (파싱)
        String responseBody = "";
        if(msg.equals("text/html")){
            responseBody = "<html><h1>Hello</h1></html>";
        }else{
            responseBody = "404 not found";
        }

        // 4. 서버 응답
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), "utf-8")
        );
        writer.write(responseBody);
        writer.write("\n");
        writer.flush();
    }

    public static void main(String[] args) {
        try {
            new MyServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//반이중
//보냈으면 소켓의 연결이 끊김
//끊어야지 안그러면 피곤함 전화 기록을 1년 넘게 보관하려면 피곤하다
//html 보내면 받는 입장에서 파싱을한다

