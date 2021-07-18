package bot.anonymous;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiClient {

    public String request(String urlstr) {

        HttpURLConnection huc = null;
        try {
            URL url = new URL(urlstr);
            huc = (HttpURLConnection) url.openConnection();

            return streamToString(huc.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (huc != null) {
                huc.disconnect();
            }
        }
    }

    public String streamToString(InputStream in) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }

        return result.toString("UTF-8");
    }


}
