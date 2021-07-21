package bot.anonymous.commands;

import bot.anonymous.ApiClient;
import bot.anonymous.BotConfig;
import bot.anonymous.Commands;
import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.DefaultBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WeatherCommand extends DefaultBotCommand {

    private ApiClient apiClient;

    public WeatherCommand() {
        super(Commands.weatherCommand, "Get weather of a city");
        this.apiClient = new ApiClient();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, Integer messageId, String[] args) {
        SendMessage message = new SendMessage();

        if (args.length == 0) {
            message.setText("Enter a city name like so: /weather cork");
        } else {
            message.setText(getWeatherByCityName(args[0]));
        }

        message.setChatId(chat.getId().toString());
        message.setReplyToMessageId(messageId);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    String getWeatherByCityName(String cityName) {
        String jsonStr = getWeatherByCityNameJson(cityName);

        if (jsonStr == null)
            return "invalid city name";

        CityWeather cityWeather = CityWeather.fromJson(jsonStr);

        return cityWeather.toString();
    }

    String getWeatherByCityNameJson(String cityName) {
        String urlStr = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s",
                cityName,
                BotConfig.WEATHER_API_KEY);

        return apiClient.request(urlStr);
    }
}

class CityWeather {
    private String cityName;
    private String country;
    private String description;
    private int humidity;
    private double temperature;

    private CityWeather() {}

    public static CityWeather fromJson(String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);

        // city and country name
        String cityName = jsonObject.getString("name");
        String country = jsonObject.getJSONObject("sys").getString("country");

        // temperature & humidity
        JSONObject weatherData = jsonObject.getJSONObject("main");
        double temperature = weatherData.getDouble("temp");
        int humidity = weatherData.getInt("humidity");

        // weather description
        String description = "";
        if (jsonObject.has("weather")) {
            JSONArray weatherArr = jsonObject.getJSONArray("weather");
            if (!weatherArr.isEmpty()) {
                description = weatherArr.getJSONObject(0).getString("description");
            }
        }

        CityWeather cityWeather = new CityWeather();
        cityWeather.cityName = cityName;
        cityWeather.country = country;
        cityWeather.description = description;
        cityWeather.temperature = temperature;
        cityWeather.humidity = humidity;

        return cityWeather;
    }

    public String toString() {
        return cityName + ", " + country + "\n"
                + temperature + "°C, " + description + "\n"
                + "humidity: " + humidity + "%";
    }

}
