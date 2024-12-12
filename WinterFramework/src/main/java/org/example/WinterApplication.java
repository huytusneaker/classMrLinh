package org.example;

import org.example.Annotation.BOApplication;
import org.example.Annotation.BOController;
import org.example.CommonUtils.PropertiesUtil;
import org.example.Handler.WinterContainer;
import org.example.Handler.WinterHttpServer;

import java.nio.file.Paths;

public class WinterApplication {
    public static void run(Class<?> primarySource, String... args) throws Exception {
        System.out.println("Starting Winter Application...");

        // Kiểm tra annotation @BOApplication
        if (!primarySource.isAnnotationPresent(BOApplication.class)) {
            throw new RuntimeException("Missing @BOApplication annotation on " + primarySource.getName());
        }

        // Tải file properties từ ứng dụng
        String basePath = Paths.get("").toAbsolutePath().toString();
        String propertiesFilePath = basePath + "/src/main/resources/application.properties";
        PropertiesUtil.loadProperties(propertiesFilePath);

        // Lấy instance của container
        WinterContainer container = WinterContainer.getInstance();

        // Quét package dựa trên class chính (primarySource)
        String basePackage = primarySource.getPackageName();
        container.scan(basePackage);

        // Khởi động HTTP server (nếu cần)
        startHttpServerIfNecessary(container, basePackage);

        System.out.println("Winter Application started successfully.");
    }

    private static void startHttpServerIfNecessary(WinterContainer container, String basePackage) {
        try {
            boolean hasController = container.getBeansWithAnnotation(BOController.class).size() > 0;

            if (hasController) {
                int port = PropertiesUtil.getIntProperty("server.port", 8080);

                WinterHttpServer server = new WinterHttpServer();
                server.start(port, basePackage); // Sử dụng port từ file cấu hình
                System.out.println("HTTP Server started on port " + port);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start HTTP Server", e);
        }
    }
}
