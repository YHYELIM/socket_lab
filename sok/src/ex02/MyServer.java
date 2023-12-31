package ex02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    private ServerSocket serverSocket;
    private BufferedReader request;
    private BufferedWriter response;
//키보드에 버퍼 연결->키보드로 메세지를 서버쪽으로 전송
    public MyServer() throws Exception{
        // 1. 서버 대기중
        serverSocket = new ServerSocket(10000);
        ServerSocket serverSocket = new ServerSocket(10000);
        Socket socket = serverSocket.accept();//리스너
        System.out.println("클라이언트가 연결되었습니다");

        //키보드 입력

        // 2. 서버 요청 받음
        request = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        BufferedReader request = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        String msg = request.readLine(); // 버퍼에 \n까지 읽음
        System.out.println("클라이언트에게서 요청이 왔습니다 : "+msg);


        // 3. 서버 응답
        response = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
        BufferedWriter response = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
        response.write("<html><h1>Hello</h1></html>");
        response.write("\n");
        response.flush();
        System.out.println("클라이언트에게 응답합니다");
    }
    public static void main(String[] args) {
        try {
            new MyServer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}