package com.enderun.raven.web.controller;

import com.enderun.raven.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by enes on 18.07.2016.
 */

@Controller
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {

        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "hello";

    }

    /*

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        model.addObject("msg", name);

        return model;

    }

*/

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("SpringWeb")User user, ModelMap model){
        try {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("password", user.getPassword());
            model.addAttribute("email", user.getEmail());
            // Loading drivers for MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chronos","root","enes1234");

            PreparedStatement ps = con.prepareStatement("INSERT INTO Users(USERNAME,PASSWORD,EMAIL) VALUES(?,?,?)");

            String username = user.getUsername();
            String password = user.getPassword();
            String email = user.getEmail();

            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,email);

            int i=ps.executeUpdate();

            if (i>0){
                return "hello";
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "Not Registered!";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public List<User> listUsers(){

        try {

            List<User> userList = new ArrayList<User>();

            // Loading drivers for MySQL
            Class.forName("com.mysql.jdbc.Driver");

            // Creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Chronos","root","enes1234");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Chronos.Users");

            // Execute select SQL statement
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);

                User u = new User(id,username,password,email);
                userList.add(u);

            }

            return userList;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
