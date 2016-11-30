package org.pinky83.alfaplus.web;

import org.pinky83.alfaplus.service.PatientService;
import org.pinky83.alfaplus.service.PatientServiceMock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 30.11.2016./
 */
public class PatientServlet extends HttpServlet{
    private PatientService service = new PatientServiceMock();

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
}
