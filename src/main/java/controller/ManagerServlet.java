package controller;

import dao.ClassesDAO;
import dao.StudentDAO;
import model.Classes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ManagerServlet", urlPatterns = "/school")
public class ManagerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;
    private ClassesDAO classesDAO;
    public void init(){
        studentDAO = new StudentDAO();
        classesDAO = new ClassesDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "class":
                    listClass(request, response);
                    break;
                case "createClass":
                    showFormAddClass(request, response);
                    break;
                case "editClass":
                    showFormEditClass(request, response);
                    break;
                case "deleteClass":
                    deleteClass(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listClass(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        List<Classes> classesList = classesDAO.selectAllClasses();
        request.setAttribute("classesList", classesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormAddClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormEditClass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Classes existingClass = classesDAO.selectClasses(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/edit.jsp");
        request.setAttribute("class", existingClass);
        dispatcher.forward(request, response);

    }

    private void deleteClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

            classesDAO.deleteClasses(id);


        List<Classes> listClass = classesDAO.selectAllClasses();
        request.setAttribute("listClass", listClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/list.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action = "";
        }
        try {
            switch (action){
                case "createClass":
                    insertClass(request,response);
                    break;
                case "editClass":
                    updateClass(request,response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Classes newClass = new Classes(name, description);
        classesDAO.insertClasses(newClass);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/create.jsp");
        dispatcher.forward(request, response);
    }

    private void updateClass(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Classes book = new Classes(id, name, description);
        classesDAO.updateClasses(book);
        RequestDispatcher dispatcher = request.getRequestDispatcher("classes/edit.jsp");
        dispatcher.forward(request, response);
    }



}
