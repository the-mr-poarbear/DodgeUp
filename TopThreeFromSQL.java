import com.mysql.cj.x.protobuf.MysqlxCrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TopThreeFromSQL {

    static int[] topThreeScore = new int[3];
    static Connection connection;

    public static  void TopThree(){

        try{
            String url = "jdbc:mysql://localhost:3306/dogeup";

            String username = "root";
            String password = "13831383";
            connection = DriverManager.getConnection(url, username , password);

            String sql = "SELECT * FROM topthree ORDER BY score DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            for (int i = 0; i < 3 ; i++) {
                resultSet.next();
                topThreeScore[i] = resultSet.getInt(1);
            }

        }
        catch (Exception e){

        }

    }

    public static void Update(){
        try{
            String sql = "TRUNCATE TABLE topthree";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            for (int i = 0; i < 3 ; i++) {
                String sql2 = "INSERT INTO topthree VALUE(?)";
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.setInt(1 , topThreeScore[i]);
                preparedStatement2.executeUpdate();
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

  // connection = DriverManager.getConnection(url, username , password);
}
