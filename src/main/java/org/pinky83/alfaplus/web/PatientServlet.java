package org.pinky83.alfaplus.web;

import org.pinky83.alfaplus.LoggedUser;
import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.model.User;
import org.pinky83.alfaplus.service.UserService;
import org.pinky83.alfaplus.web.patient.PatientController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public class PatientServlet extends HttpServlet{

    private PatientController controller;

    private UserService userService;

    private LoggedUser loggedUser = new LoggedUser();//user for tests

    private ConfigurableApplicationContext appCtx;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controller = appCtx.getBean(PatientController.class);
        userService = appCtx.getBean(UserService.class);
    }

    @Override
    public void destroy() {
        appCtx.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String actioon = req.getParameter("action");

        if (actioon.equalsIgnoreCase("list_patient")){
            req.setAttribute("patientList", controller.getAll(loggedUser.getId()));
            req.getRequestDispatcher("patientList.jsp").forward(req, resp);
        }
        else if(actioon.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(req.getParameter("id"));
            controller.delete(id, loggedUser.getId());

            req.setAttribute("patientList", controller.getAll(loggedUser.getId()));
            req.getRequestDispatcher("patientList.jsp").forward(req, resp);
        }
        else resp.setStatus(400);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        User testUser = userService.getByEmail("kiso4ka2345");//fake user for tests
        testUser.setId(loggedUser.getId());

        String name = req.getParameter("name");
        String comment = req.getParameter("comment");
        if(!(name.equals("")&&comment.equals(""))) {
            Patient newPatient = new Patient(name, LocalDate.now(), LocalTime.now(), 1, comment);
            controller.create(newPatient, testUser);
        }

        req.setAttribute("patientList", controller.getAll(loggedUser.getId()));
        req.getRequestDispatcher("patientList.jsp").forward(req, resp);
    }
}
