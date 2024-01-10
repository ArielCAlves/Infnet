import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dto.UsuarioDTOInput;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceTest {

    @Test
    public void testListagemUsuarios() {
        try {
            URL url = new URL("http://localhost:4567/usuarios");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            assertEquals(200, responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            reader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsercaoUsuarioExterno() {
        try {
            URL apiURL = new URL("https://randomuser.me/api/");
            HttpURLConnection apiConnection = (HttpURLConnection) apiURL.openConnection();
            apiConnection.setRequestMethod("GET");

            int apiResponseCode = apiConnection.getResponseCode();
            assertEquals(200, apiResponseCode);

            BufferedReader apiReader = new BufferedReader(new InputStreamReader(apiConnection.getInputStream()));
            StringBuilder apiResponse = new StringBuilder();
            String line;
            while ((line = apiReader.readLine()) != null) {
                apiResponse.append(line);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(apiResponse.toString());
            JsonNode userNode = rootNode.path("results").get(0);

            UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
            usuarioDTOInput.setNome(userNode.path("name").path("first").asText());
            usuarioDTOInput.setSenha("SenhaAleatoria");

            apiReader.close();
            apiConnection.disconnect();

            URL localURL = new URL("http://localhost:4567/usuarios");
            HttpURLConnection localConnection = (HttpURLConnection) localURL.openConnection();
            localConnection.setRequestMethod("POST");
            localConnection.setDoOutput(true);

            try (OutputStream os = localConnection.getOutputStream()) {
                byte[] input = objectMapper.writeValueAsBytes(usuarioDTOInput);
                os.write(input);
            }

            int localResponseCode = localConnection.getResponseCode();
            assertEquals(201, localResponseCode);

            localConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
