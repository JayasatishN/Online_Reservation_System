package First;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CancellationServlet")
public class CancellationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pnr = request.getParameter("pnr");

        final String url = "jdbc:mysql://localhost:3306/reservation_system";
        final String user = "root";
        final String pass = "Satish@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            // Fetch reservation details for the given PNR
            String fetchSql = "SELECT * FROM reservations WHERE pnr = ?";
            PreparedStatement fetchStmt = conn.prepareStatement(fetchSql);
            fetchStmt.setString(1, pnr);
            ResultSet resultSet = fetchStmt.executeQuery();

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            if (resultSet.next()) {
                // Insert into cancellations table before deleting
                String cancelSql = "INSERT INTO cancellations (reservation_id, cancellation_date) SELECT id, CURDATE() FROM reservations WHERE pnr = ?";
                PreparedStatement cancelStmt = conn.prepareStatement(cancelSql);
                cancelStmt.setString(1, pnr);
                cancelStmt.executeUpdate();

                // Now delete the reservation
                String deleteSql = "DELETE FROM reservations WHERE pnr = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                deleteStmt.setString(1, pnr);
                int rowsDeleted = deleteStmt.executeUpdate();

                if (rowsDeleted > 0) {
                    response.getWriter().println("Cancellation Successful");
                } else {
                    response.getWriter().println("Cancellation Failed");
                }

                cancelStmt.close();
                deleteStmt.close();
            } else {
                response.getWriter().println("PNR Not Found");
            }

            fetchStmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
