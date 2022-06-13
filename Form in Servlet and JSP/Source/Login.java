import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {

    public void init() {
    }

    public Connection connect(PrintWriter out) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db1";
            String user = "root";
            String password = "admin";
            connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (Exception e) {
            out.println("<br>" + e);
        }

        return connection;
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            response.setContentType("text/html");
            Connection conn = connect(out);
            Statement statement = conn.createStatement();
            String firstname = request.getParameter("firstname");
            String password = request.getParameter("password");
            PreparedStatement ps = conn
                    .prepareStatement("Select firstname, password from loginform where firstname=? and password=?");
            ps.setString(1, firstname);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == true)
            out.println("You have successfully logged in!"); 
            else
                out.println("Invalid login credentials");
        } catch (Exception e) {
            out.println("<br>" + e);
        }

    }

    public void destroy() {
    }

}