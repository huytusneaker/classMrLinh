package org.example.Handler;
//
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//import org.example.Annotation.BOAutowired;
//import org.example.Annotation.BOComponent;
//import org.example.Annotation.BOController;
//import org.reflections.Reflections;
//import org.reflections.util.ClasspathHelper;
//import org.reflections.util.ConfigurationBuilder;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.Set;
//
//public class BORequestHandler implements HttpHandler {
//    private BOControllerProcessor controllerProcessor= new BOControllerProcessor();
//
//    public BORequestHandler(String basePackage) throws Exception {
//        // Lấy tất cả các URLs từ classpath
//        Collection<URL> urls = ClasspathHelper.forClassLoader(
//                ClasspathHelper.contextClassLoader(),
//                ClassLoader.getSystemClassLoader()
//        );
//
//        // Cấu hình Reflections với URLs
//        Reflections reflections = new Reflections(
//                new ConfigurationBuilder()
//                        .forPackages(basePackage)
//                        .addUrls(urls)
//        );
//
//        // Quét và xử lý các lớp
//        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(BOController.class);
//        System.out.println("Found controllers: " + controllers);
//
//        // Đăng ký các controller vào ControllerProcessor
//        Iterator<Class<?>> iterator = controllers.iterator();
//        while (iterator.hasNext()) {
//            Class<?> item = iterator.next();
//            controllerProcessor.processControllers(item);
//        }
//
//    }
//
//    @Override
//    public void handle(HttpExchange exchange) throws IOException {
//        String path = exchange.getRequestURI().getPath();
//        Method handlerMethod = controllerProcessor.getHandlerForPath(path);
//
//        if (handlerMethod != null) {
//            try {
//                Object controllerInstance = handlerMethod.getDeclaringClass().getDeclaredConstructor().newInstance();
//                String response = (String) handlerMethod.invoke(controllerInstance);
//                exchange.sendResponseHeaders(200, response.getBytes().length);
//                OutputStream os = exchange.getResponseBody();
//                os.write(response.getBytes());
//                os.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//                exchange.sendResponseHeaders(500, -1);
//            }
//        } else {
//            String response = "Not Found";
//            exchange.sendResponseHeaders(404, response.getBytes().length);
//            OutputStream os = exchange.getResponseBody();
//            os.write(response.getBytes());
//            os.close();
//        }
//    }
//}


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

public class BORequestHandler implements HttpHandler {
    private WinterContainer container;

    public BORequestHandler(String basePackage) throws Exception {
        container = WinterContainer.getInstance();
        container.scan(basePackage);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        Method handlerMethod = container.getRouteHandler(path);

        if (handlerMethod != null) {
            try {
                // Lấy instance của controller từ container
                Object controllerInstance = container.get(handlerMethod.getDeclaringClass());
                String response = (String) handlerMethod.invoke(controllerInstance);
                System.out.println("Handle Method được gọi nàyyyyyyyyyyyyyyyyy");
                // Trả về response
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(500, -1);
            }
        } else {
            String response = "404 Not Found";
            exchange.sendResponseHeaders(404, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
