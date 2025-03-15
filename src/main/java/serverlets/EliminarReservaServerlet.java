package serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.reservasfuncion;

@WebServlet(name = "EliminarReservaServerlet", urlPatterns = {"/EliminarReservaServerlet"})
public class EliminarReservaServerlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //obtener la id a borrar
        int id = Integer.parseInt(request.getParameter("id"));
        
        
        HttpSession session = request.getSession();
        List<reservasfuncion> listareserva = (List<reservasfuncion>) session.getAttribute("listareserva");
        
        if (listareserva != null){
            for(reservasfuncion reservas : listareserva){
                if (reservas.getId() == id){
                    listareserva.remove(reservas);
                    break;
                }
            }
        }
        
        response.sendRedirect("ListaReserva");
    }
    
    
    
    
    
}


