package prac01;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() throws IOException {
        // 1. 서버 연결
        Socket socket = new Socket("192.168.200.110", 10000);

        // 2. 버퍼 생성
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), "utf-8")
        );
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream(), "utf-8")
        );
        Scanner keyboard = new Scanner(System.in, "utf-8");

        // 쓰기 스레드
        new Thread(() -> {
            try {
                while(true){
                    String msg = keyboard.nextLine();
                    writer.write("yhl0326:"+msg);
                    writer.write("\n");
                    writer.flush();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

        // 읽기 스레드
        new Thread(() -> {
            try {
                while(true){
                    String msg = reader.readLine(); // 버퍼 비우기
                    System.out.println("클라이언트로 부터 받은 메세지 : "+msg);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

    }

    public static void main(String[] args) {
        try {
            new Client();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}






