package ir.arcinc.dao;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;
import ir.arcinc.Connection;
import ir.arcinc.model.User;

import java.util.UUID;

/**
 * Created by tahae on 6/19/2016.
 */
public class UserDao extends GenericDao {
    public UserDao(Connection connection) {
        super(connection);

        connection.execute(dropUserTableQuery);
        connection.execute(createUserTableQuery);
    }

    private final static String createUserTableQuery =
            "CREATE TABLE IF NOT EXISTS user(" +
                "userid uuid," +
                "name text," +
                "email text," +
                "phone text," +
                "username text," +
                "password text," +
                "PRIMARY KEY (username)" +
            ")";

    private final static String dropUserTableQuery =
            "DROP TABLE IF EXISTS user";

    private final static String userInsertQuery =
            "INSERT INTO user(userid,name,email,phone,username,password) VALUES (?,?,?,?,?,?)";

    private final static String findUserByUsernameQuery =
            "SELECT userid,name,email,phone,username,password FROM user WHERE username=?";

    public UUID createUser(User u){
        if (findByUserName(u.getUsername()) != null)
            return null;
        UUID id = UUIDs.timeBased();
        u.setUserId(id);
        connection.execute(userInsertQuery,u.getUserId(),u.getName(),u.getEmail(),u.getPhone(),u.getUsername(),u.getPassword());
        return id;
    }

    public User findByUserName(String username){
        Row user = connection.execute(findUserByUsernameQuery,username).one();
        if (user == null)
            return null;
        return new User(
                user.getUUID("userid"),
                user.getString("name"),
                user.getString("phone"),
                user.getString("username"),
                user.getString("password"),
                user.getString("email")
                );
    }

    public User findByUserNameAndPassword(String username, String password){
        User u = findByUserName(username);
        if (u == null)
            return null;
        if (!u.getPassword().equals(password))
            return null;
        return u;
    }
}
