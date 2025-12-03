package com.poly.Listener;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.io.*;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener {
    // Sử dụng file để lưu số đếm (application scope)
    private static final String COUNT_FILE_NAME = "/visitors.txt"; 

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext application = event.getServletContext();
        String filePath = application.getRealPath(COUNT_FILE_NAME);
        int visitors = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line != null) {
                visitors = Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            // Khởi tạo bằng 0 nếu file chưa có hoặc lỗi
        }
        
        application.setAttribute("visitors", visitors); 
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // Tăng số đếm lên 1 mỗi khi có session mới
        ServletContext application = event.getSession().getServletContext();
        synchronized (application) { // Đồng bộ hóa khi thao tác với application scope
            int visitors = (int) application.getAttribute("visitors");
            application.setAttribute("visitors", visitors + 1);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext application = event.getServletContext();
        String filePath = application.getRealPath(COUNT_FILE_NAME);
        int visitors = (int) application.getAttribute("visitors");

        // Lưu số đếm trở lại nguồn lưu trữ
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(String.valueOf(visitors));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
