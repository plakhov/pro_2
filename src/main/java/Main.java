import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Random;

public class Main {

    public static void main(String[] args)  {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro", "root", "root")) {
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement("insert into students (Name, GroupName, Score) values (?,?,?)");
            for (int i = 0; i < 5; i++) {
                statement.setString(1, "Иванов+"+i);
                statement.setString(2, "Группа");
                statement.setInt(3, i*10);
                statement.executeUpdate();
            }
            conn.commit();
            Statement statement1 = conn.createStatement();
            ResultSet resultSet = statement1.executeQuery("select * from students");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
