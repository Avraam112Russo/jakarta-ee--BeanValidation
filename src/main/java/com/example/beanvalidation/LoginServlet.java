package com.example.beanvalidation;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Inject
    private MyUser myUser;
    @Inject
    private Validator validator;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       myUser.setAge(Integer.parseInt(req.getParameter("age")));
       myUser.setName(req.getParameter("name"));
       myUser.setEmail(req.getParameter("email"));
       Set<ConstraintViolation<MyUser>> constraintViolations = validator.validate(myUser);
       if (constraintViolations.size() > 0){
           List<String> listOfViolations = new ArrayList<>();
           constraintViolations.stream()
                   .forEach(violations -> {
                       System.out.println(violations.getInvalidValue());
                       System.out.println(violations.getMessage());
                       listOfViolations.add(violations.getMessage());
                   });
//           resp.setContentType("text/html");
           resp.getWriter().write("Something went wrong: ");
           listOfViolations.stream().forEach(str -> {
               try {
                   resp.getWriter().write("\n" + str); ;
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           });
       }else {
           resp.getWriter().write(myUser.toString());
           resp.getWriter().write("\nSuccess!");
       }
    }
}
