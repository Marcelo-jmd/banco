package servicio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 * Servicio que consulta la API de RENIEC para obtener datos de un DNI
 */
public class ReniecServicio {

    // ⚠️ Lo ideal es no poner el token en el código, usar variables de entorno
    private static final String TOKEN = "sk_10670.g5Gn1vFmw6ggqO4UL5OWdIBmiDPCAZ";
    private static final String API_URL = "https://api.apis.net.pe/v1/dni?numero=";

    /**
     * Consulta nombres y apellidos completos usando el DNI
     */
    public String obtenerNombrePorDNI(String dni) {
        try {
            // Construimos la URL con el número de DNI
            URL url = new URL(API_URL + dni);

            // Abrimos conexión HTTP
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Añadimos token al header
            con.setRequestProperty("Authorization", "Bearer " + TOKEN);

            // Leemos la respuesta de la API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder respuesta = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                respuesta.append(inputLine);
            }
            in.close();

            // Convertimos a JSON
            JSONObject json = new JSONObject(respuesta.toString());

            // Concatenamos nombre completo
            String nombreCompleto = json.getString("nombres") + " "
                    + json.getString("apellidoPaterno") + " "
                    + json.getString("apellidoMaterno");

            return nombreCompleto;

        } catch (Exception e) {
            e.printStackTrace();
            return "No encontrado";
        }
    }
}
