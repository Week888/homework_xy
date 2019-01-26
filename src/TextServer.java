
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class TextServer {   //创建socket
    public static String text = null;
    public static int width = 0;

    public static void serverRun() {

        try {
            ServerSocket serverSocket = new ServerSocket(1001);
            new Thread() {   //UI线程 处理本地任务
                @Override
                public void run() {
                    super.run();
                    while (true) {
                        try {
                            input();
                            TextHandler textHandler = new TextHandler(text, width);
                            System.out.println(textHandler.handle());
                        } catch (NumberFormatException e) {
                            System.out.println("error: width must be an integer");
                            return;
                        }
                    }
                }
            }.start();

            //开启网络服务
            System.out.println("server is ready waiting for client ....");
            while (true) {
                Socket socket = serverSocket.accept();//侦听并接受到此套接字的连接,返回一个Socket对象
                new Thread(new Soc(socket)).start();  //开启新线程处理侦听到的套接字连接
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void input() throws NumberFormatException {
        Scanner in = new Scanner(System.in);
        System.out.println("please enter text");
        text = in.nextLine();
        System.out.println("please enter width");
        width = Integer.parseInt(in.nextLine());
    }

}


