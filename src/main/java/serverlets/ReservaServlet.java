/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package serverlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.reservasfuncion;


    @WebServlet("/reserva")
public class ReservaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<reservasfuncion> reservas = new ArrayList<>(); // Lista para almacenar reservas

    
        //doPost para solamente guardar los datos de la lista
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // obtener parametros del formulario
        String nombre = request.getParameter("nombre");
        String fechaStr = request.getParameter("fecha");
        String espacio = request.getParameter("espacio");
        int duracion = Integer.parseInt(request.getParameter("duracion"));


        // Convertir la fecha de String a Date
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
        } catch (ParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha inválido");
            return;
        }

        // Crear una nueva reserva  
        reservasfuncion reserva = new reservasfuncion(nombre, fecha, espacio, duracion);
        
        //obtener la lista de reservas
        HttpSession session = request.getSession();
        List<reservasfuncion> listareserva = (List<reservasfuncion>) session.getAttribute("listareserva");
        
        
             if (listareserva == null){
            listareserva = new ArrayList<>();
            session.setAttribute("listareserva", listareserva);
            }
                    
        listareserva.add(reserva);
        
        
    }
}
    
    

   
