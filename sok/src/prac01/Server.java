package prac01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    ServerSocket serverSocket;
    Socket socket;

    BufferedReader reader;
    BufferedWriter writer;
    Scanner keyboard;



    public Server()throws Exception{
        serverSocket = new ServerSocket(10000);
        socket= serverSocket.accept();//리스너 대기중
        System.out.println("클라이언트가 연결되었습니다.");
        System.out.println(socket.getPort());
        System.out.println(socket.getInetAddress());

        connectLine();
        write();
        read();



    }
    //버퍼 만들어야지

    private  void  write(){
        new Thread(()->{
            try{
                while(true){
                    String msg = keyboard.nextLine();
                    writer.write(msg);
                    writer.write("\n");
                    writer.flush();
                }

            }catch (Exception e){
                System.out.println("괜찮아!");
            }

        }).start();

    }

    public static void main(String[] args) {
        try{
            new Server();

        }catch (Exception e){
            e.printStackTrace();
        }

    }














    private void connectLine(){
        try {
            reader= new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        keyboard= new Scanner(System.in,"utf-8");

    }

    //1.읽기 스레드
    private  void  read(){
        new Thread(()->{
            try{
                while (true){
                    String msg = reader.readLine();
                    String username=msg.split(":")[0];//":"을 기준으로 나눈다 if) "ssar:"[0]->ssar
                    String body = msg.split(":")[1];
                    System.out.println(username+":"+body);

                }

                }catch(Exception e){
                e.printStackTrace();
            }
        }).start();

    }
    //2.쓰기 스레드





    }



