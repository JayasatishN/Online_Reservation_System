package First;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // JDBC URL, username and password of MySQL server
        final String url = "jdbc:mysql://localhost:3306/reservation_system";
        final String user = "root";
        final String pass = "Satish@123";

        // JDBC variables for opening and managing connection
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully");

            // Create a connection to the database
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connected successfully");

            // Create an SQL query to insert the new user
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute the query
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Query executed, rows affected: " + rowsAffected);

            if (rowsAffected > 0) {
                response.getWriter().println("Account created successfully!");
            } else {
                response.getWriter().println("Account creation failed.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
