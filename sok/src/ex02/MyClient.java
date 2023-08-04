package ex02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 클라이언트는 read버퍼를 만들고
 * 서버는 write버퍼를 추가로 만든다
 */
public class MyClient {

    private Socket socket;
    private BufferedWriter writer;

    public MyClient() throws Exception {
        // 1. 서버 연결
        socket = new Socket("localhost", 10000);
        Socket socket = new Socket("localhost", 10000);

        // 2. 메시지 전송
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
        writer.write("Hello World");
        writer.write("\n"); // 버퍼에 끝을 알려주는 프로토콜
        writer.flush(); // 버퍼에 담긴 메시지를 전송
        //read버퍼로 읽어서 모니터 출력
        new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        reader.read();

        reader.read();


    }
    public static void main(String[] args) {
        try {
            new MyClient();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}