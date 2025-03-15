package serverlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.reservasfuncion;


@WebServlet(name = "ListaReservaServlet", urlPatterns = {"/ListaReservaServlet"})
public class ListaReservaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //obtener la lista de reservas
        HttpSession session = request.getSession();
        List<reservasfuncion> listareserva = (List<reservasfuncion>) session.getAttribute("listareserva");
    
        //si no existe una reserva
        if (listareserva == null){
            listareserva = new ArrayList<>();
            session.setAttribute("listareserva", listareserva);
        }
        
        //resirigir a la jsp de la lista de reservas
        request.setAttribute("listareserva", listareserva);
        request.getRequestDispatcher("Reservas.jsp").forward(request, response);
    
    }
    

    
    

}
