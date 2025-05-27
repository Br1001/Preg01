package servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dni = request.getParameter("ndni");
        String clave = request.getParameter("clave");

        EstudianteWeb est = new EstudianteWebJpaController().buscarPorDni(dni);

        if (est != null && est.getPassEstd().equals(clave)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", est);
            response.sendRedirect("principal.html");
        } else {
            response.sendRedirect("login.html?error=1");
        }
    }
}
