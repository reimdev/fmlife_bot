package bot.anonymous;

import java.io.IOException;
import java.util.Properties;

public class BotConfig {
    public static final String USERNAME = "fmlife_bot";

    public static String getKey() {
        return System.getenv("FML_API_KEY");
    }
}
