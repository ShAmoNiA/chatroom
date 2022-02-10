import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HistoryChat {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public HistoryChat()throws Exception{
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=his", "postgres", "123456789");
    }

    public void addHistory(PersonHistory personHistory)throws Exception{
        preparedStatement = connection.prepareStatement("insert into history values (default ,?,?,?)");
        preparedStatement.setString(1,personHistory.getChatHistory());
        preparedStatement.setString(2,personHistory.getUsername1());
        preparedStatement.setString(3,personHistory.getUsername2());
        preparedStatement.executeUpdate();
    }

    public String getHistory(String username1,String username2)throws Exception{
        preparedStatement = connection.prepareStatement("select * from history where username1 = ? and username2 = ?");//
        preparedStatement.setString(1,username1);
        preparedStatement.setString(2,username2);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        return resultSet.getString("chatroom");
    }

    public String getperson(String username1)throws Exception{
        preparedStatement = connection.prepareStatement("select * from history where username1 = ? and username2 = ?");//
        preparedStatement.setString(1,username1);
        preparedStatement.setString(2,username1);

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();

        return resultSet.getString("username");
    }

    public void changeHistory(PersonHistory personHistory, String newhistory) throws Exception{
        preparedStatement = connection.prepareStatement("update history set chatroom = ? where username1 = ? and username2 = ?");
        preparedStatement.setString(1,newhistory);
        preparedStatement.setString(2,personHistory.getUsername1());
        preparedStatement.setString(3,personHistory.getUsername2());
        preparedStatement.executeUpdate();
    }
    public void close() throws Exception{
        preparedStatement.close();
        connection.close();
    }
}
