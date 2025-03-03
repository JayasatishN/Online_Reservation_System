package First;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String trainNumber = request.getParameter("trainNumber");
        String classType = request.getParameter("classType");
        String dateOfJourney = request.getParameter("dateOfJourney");
        String fromLocation = request.getParameter("fromLocation");
        String toLocation = request.getParameter("toLocation");
        String pnr = request.getParameter("pnr"); // Ensure a unique PNR is generated

        // Database connection details
        final String url = "jdbc:mysql://localhost:3306/reservation_system";
        final String user = "root";
        final String pass = "Satish@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            // SQL Query to insert reservation details
            String sql = "INSERT INTO reservations (userId, trainNumber, classType, dateOfJourney, fromLocation, toLocation, pnr) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(userId));
            stmt.setString(2, trainNumber);
            stmt.setString(3, classType);
            stmt.setString(4, dateOfJourney);
            stmt.setString(5, fromLocation);
            stmt.setString(6, toLocation);
            stmt.setString(7, pnr);

            int rowsInserted = stmt.executeUpdate();
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            if (rowsInserted > 0) {
                response.getWriter().println("Reservation Successful");
            } else {
                response.getWriter().println("Reservation Failed");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
