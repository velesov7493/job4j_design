package ru.job4j.sockets;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EchoServer {

    private final static Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        Pattern ptMessage = Pattern.compile("\\?msg=\\w+");
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean finish = false;
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))
                ) {
                    String reply = "HTTP/1.1 200 OK\r\n\r\n";
                    for (
                            String str = in.readLine();
                            str != null && !str.isEmpty();
                            str = in.readLine()
                    ) {
                        System.out.println(str);
                        Matcher m = ptMessage.matcher(str);
                        if (m.find()) {
                            String msg = m.group().substring(5);
                            switch (msg) {
                                case "Exit": finish = true; break;
                                case "Hello": reply += "Hello, dear friend.\r\n";  break;
                                default: reply += "What?\r\n"; break;
                            }
                        }
                    }
                    out.write(reply.getBytes());
                    if (finish) {
                        server.close();
                    }
                }
            }
        } catch (IOException ex) {
            LOG.error("Ошибка ввода-вывода сокета: ", ex);
        }
    }
}