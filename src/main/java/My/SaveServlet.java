/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package My;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class SaveServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             //b1. lấy thông tin
            String uname=request.getParameter("uname");
            String pass=request.getParameter("password");
            String email=request.getParameter("Email");
            String country=request.getParameter("Country");
            //b2.Xử lý yêu cầu (Thêm user vào CSDL)
            Connection con;
            PreparedStatement ps;
              try{
            //nạp điều kiện
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            //thiết lập kết nối
            con=DriverManager.getConnection("jdbc:sqlserver://PC314;databaseName=demodb","sa","sa");
            ps=con.prepareStatement("insert into users(name,password,email,Country) values(?,?,?,?)");
            //truyền giá trị tham số cho câu lệnh truy vấn sql
            ps.setString(1, uname);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, country);
            //thi hanh truy vấn dữ liệu
            int kq=ps.executeUpdate();
            //5 xử lý điều kiện
            if(kq>0){
                out.println("<h2>Lưu Thành công</h2>");
            }else
            {
                  out.println("<h2>Thất bại</h2>");
            }
            //dống kết nối csdl
            con.close();
            }catch(Exception e){
                System.out.println("loi:"+e.toString());
                out.println("<h2>Thất bại</h2>");
            }
            request.getRequestDispatcher("index.html").include(request, response);
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
