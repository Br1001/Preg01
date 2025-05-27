package servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CambiarContra")
public class CambiarClaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        EstudianteWeb user = (EstudianteWeb) session.getAttribute("usuario");

        String actual = request.getParameter("actual");
        String nueva = request.getParameter("nueva");
        String confirma = request.getParameter("confirma");

        if (!user.getPassEstd().equals(actual)) {
            response.sendRedirect("cambiarClave.html?error=clave_incorrecta");
            return;
        }
        if (!nueva.equals(confirma)) {
            response.sendRedirect("cambiarClave.html?error=no_coincide");
            return;
        }

        user.setPassEstd(nueva);
        new EstudianteWebJpaController().edit(user);
        response.sendRedirect("principal.html?clave_actualizada=1");
    }
}
