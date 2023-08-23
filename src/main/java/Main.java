import dao.UserDao;
import dao.UserDaoImpl;
import model.User;
import util.DatabaseUtil;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        //create
        User u1 = new User(1,"Adilet","adilet@gmail.com","qqqwww");
        userDao.addUser(u1);

        //read by id
        System.out.println(userDao.getUser(u1.getId()));

        //read all users
        List<User> users = userDao.getAllUsers();
        for(User u : users){
            System.out.println(u);
        }

        //update
        u1.setEmail("askar@gmail.com");
        userDao.updateUser(u1);
        System.out.println(userDao.getUser(u1.getId()));

        //delete
        userDao.deleteUser(u1.getId());
    }
}
