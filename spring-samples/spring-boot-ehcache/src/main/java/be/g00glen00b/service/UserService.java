package be.g00glen00b.service;

import be.g00glen00b.dto.TaskDTO;
import be.g00glen00b.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ArrayList<User> users = new ArrayList<>();


    @Cacheable(value = "users")
    public ArrayList<User> showAll() {
        return users;
    }

    @CacheEvict(value= "users", key="#id")
    public User clearbyID(int id) {
        for (User u: users){
            if(u.getId() == id){
                users.remove(u);
                logger.info("User deleted");
                return u;
            }
        }
        return null;
    }

    @CachePut (value= "users", key="#id")
    public User addUser(int id, String f_name, String l_name){
        User newuser = new User(id,f_name, l_name);
        users.add(newuser);
        logger.info("userAdd");
        return newuser;
    }

    @Cacheable(value= "users", key="#id")
    public User getUser (int id){
        for (User u: users){
            if(u.getId() == id){
                try {
                    Thread.sleep(10000);
                    logger.info("is this being called?");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return u;
            }
        }
        return null;
    }

}
