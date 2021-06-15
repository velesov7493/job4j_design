package ru.job4j.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean finish = false;
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))
                ) {
                    for (
                            String str = in.readLine();
                            str != null && !str.isEmpty();
                            str = in.readLine()
                    ) {
                        System.out.println(str);
                        finish |= str.contains("msg=Bye");
                    }
                    out.write("HTTP/1.1 200 OK\r\n".getBytes());
                    if (finish) {
                        server.close();
                    }
                }
            }
        }
    }
}