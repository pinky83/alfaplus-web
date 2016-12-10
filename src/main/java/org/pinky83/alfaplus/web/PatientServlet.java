package org.pinky83.alfaplus.web;

import org.pinky83.alfaplus.model.Patient;
import org.pinky83.alfaplus.service.PatientService;
import org.pinky83.alfaplus.service.PatientServiceImpl;

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
    private PatientService service = new PatientServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String actioon = req.getParameter("action");

        if (actioon.equalsIgnoreCase("list_patient")){
            req.setAttribute("patientList", service.getAll());
            req.getRequestDispatcher("patientList.jsp").forward(req, resp);
        }
        else if(actioon.equalsIgnoreCase("delete")){
            Integer id = Integer.parseInt(req.getParameter("id"));
            service.delete(service.getById(id));

            req.setAttribute("patientList", service.getAll());
            req.getRequestDispatcher("patientList.jsp").forward(req, resp);
        }
        else resp.setStatus(400);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String comment = req.getParameter("comment");
        if(!(name.equals("")&&comment.equals(""))) {
            Patient newPatient = new Patient(name, LocalDate.now(), LocalTime.now(), true, comment);
            service.create(newPatient);
        }

        req.setAttribute("patientList", service.getAll());
        req.getRequestDispatcher("patientList.jsp").forward(req, resp);
    }
}
