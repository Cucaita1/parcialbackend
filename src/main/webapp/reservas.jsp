<%-- 
    Document   : reservas
    Created on : 13/03/2025, 8:22:43 p. m.
    Author     : Juan Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="logica.reservasfuncion"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Reservas</title>
</head>
<body>
    <h1>Lista de Reservas</h1>
    <table border="1">
        <tr>
            <th>Nombre</th>
            <th>Fecha</th>
            <th>Espacio</th>
            <th>Duración (horas)</th>
        </tr>
        <%
            List<reservasfuncion> listaReservas = (List<reservasfuncion>) session.getAttribute("listaReservas");
            if (listaReservas != null) {
                for (reservasfuncion reserva : listaReservas) {
        %>
        <tr>
            <td><%= reserva.getNombre() %></td>
            <td><%= reserva.getFecha() %></td>
            <td><%= reserva.getEspacio() %></td>
            <td><%= reserva.getDuracion() %></td>
            <td>
                <!-- Botón para eliminar la reserva -->
                <form action="eliminarReserva" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= reserva.getId() %>">
                    <button type="submit">Eliminar</button>
                </form>
            </td>
        </tr>
        <%
                }
            }else{
        %>
         <tr>
            <td colspan="4">No hay reservas registradas.</td>
        </tr>
        <%
            }
        %>
    </table>
    <br>
    <a href="index.jsp">Volver al formulario</a>
</body>
</html>
