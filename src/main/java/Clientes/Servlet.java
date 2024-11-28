package Clientes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/calcularPrimos")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        int inicio = Integer.parseInt(request.getParameter("inicio"));
        int fin = Integer.parseInt(request.getParameter("fin"));

        // Calcular los números primos
        ArrayList<Integer> primos = calcularPrimos(inicio, fin);

        // respuesta como texto HTML
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //HTML de la respuesta
        out.println("<html><head><title>Números Primos prueba</title>" +
                "<style>\n" +
                "        /* Estilos globales */\n" +
                "        * {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            font-family: 'Roboto', sans-serif;\n" +
                "            background: linear-gradient(135deg, #512c3b, #7392b8);\n" +
                "            background-size: cover;\n" +
                "            background-position: center;\n" +
                "            height: 100vh;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            flex-direction: column;\n" +
                "            color: white;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "\n" +
                "        /* Título con neón */\n" +
                "        h1 {\n" +
                "            font-size: 4rem;\n" +
                "            font-weight: bold;\n" +
                "            color: white;\n" +
                "            text-shadow: 0 0 15px rgba(255, 255, 255, 0.7), 0 0 30px rgba(186, 122, 155, 0.8);\n" +
                "            margin-bottom: 50px;\n" +
                "            text-align: center;\n" +
                "            animation: glow 1.5s ease-in-out infinite alternate;\n" +
                "        }\n" +
                "        </style></head><body>");
        out.println("<h1>Resultados:</h1>");

        // Mostrar los números primos
        out.println("<p>Números primos entre " + inicio + " y " + fin + ":</p>");

        if (primos.isEmpty()) {
            out.println("<p>No hay números primos en este rango.</p>");
        } else {
            out.println("<ul>");
            for (int primo : primos) {
                out.println("<li>" + primo + "</li>");
            }
            out.println("</ul>");
        }

        //cantidad de números primos
        out.println("<p>Total de números primos: " + primos.size() + "</p>");
        out.println("<a href='index.html'>Volver</a>");
        out.println("</body></html>");
    }

    //verificar si un número es primo
    private boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0)
                return false;
        }
        return true;
    }

    //calcular los números primos dentro de un rango
    private ArrayList<Integer> calcularPrimos(int inicio, int fin) {
        ArrayList<Integer> primos = new ArrayList<>();
        for (int i = inicio; i <= fin; i++) {
            if (esPrimo(i)) {
                primos.add(i);
            }
        }
        return primos;
    }
}
