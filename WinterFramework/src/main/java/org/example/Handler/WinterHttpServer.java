package org.example.Handler;

import com.sun.net.httpserver.HttpServer;
import org.example.Annotation.BOComponent;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WinterHttpServer {

    private HttpServer server;

    public void start(int port, String basePackage) throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        try {
            // Tự động quét controller trong package được chỉ định
            server.createContext("/", new BORequestHandler(basePackage));
        } catch (Exception e) {
            e.printStackTrace();
        }
        server.setExecutor(null);
        server.start();
    }

    public void stop() {
        server.stop(0);
    }
}