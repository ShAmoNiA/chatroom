import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Kiarash23 in 6/2/2019
 */
public class PersonDB {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PersonDB() throws Exception {
        Class.forName("org.postgresql.Driver");

        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=proj", "postgres", "123456789");
    }

    public void addPerson(Person person) throws Exception{
        preparedStatement = connection.prepareStatement("insert into person values (default ,?,?,?,?,?)");
        preparedStatement.setString(1, person.getUsername());
        preparedStatement.setString(2,person.getPassword());
        preparedStatement.setString(3,person.getFirstname());
        preparedStatement.setString(4,person.getLastname());
        preparedStatement.setString(5,person.getEmail());
        preparedStatement.executeUpdate();
    }

    public void getPersons() throws Exception{
        preparedStatement = connection.prepareStatement("select * from person");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("username"));
        }
    }

    public String getPerson(String username) throws Exception{
        preparedStatement = connection.prepareStatement("select * from person where username = ?");
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("username");
    }

    public String getPass(String pass) throws Exception{
        preparedStatement = connection.prepareStatement("select * from person where pass = ?");
        preparedStatement.setString(1,pass);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getString("pass");
    }

    public void changePass(Person person, String newPass) throws Exception{
        preparedStatement = connection.prepareStatement("update person set pass = ? where username = ?");
        preparedStatement.setString(1, newPass);
        preparedStatement.setString(2,person.getUsername());
        preparedStatement.executeUpdate();
    }

    public void deletePerson(String username) throws Exception{
        preparedStatement = connection.prepareStatement("delete from person where username = ?");
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
    }

    public void close() throws Exception{
        preparedStatement.close();
        connection.close();
    }

}
