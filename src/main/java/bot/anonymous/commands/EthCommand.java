package bot.anonymous.commands;

import bot.anonymous.ApiClient;
import bot.anonymous.Commands;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.DefaultBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class EthCommand extends DefaultBotCommand {

    private ApiClient apiClient;

    public EthCommand() {
        super(Commands.ethCommand, "Get price of ethereum to bitcoin");
        this.apiClient = new ApiClient();
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, Integer integer, String[] strings) {
        SendMessage message = new SendMessage();
        String price = getCoinPrice("ETHBTC");
        message.setText("ETCBTC: " + price);
        message.setChatId(chat.getId().toString());
        message.setReplyToMessageId(integer);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    String getCoinPrice(String symbol) {
        String json = getCoinPriceJson(symbol);
        if (json == null) return null;

        String[] arr = json.split(":");
        if (arr.length == 3) {
            String price = arr[2].replaceAll("\"", "")
                    .replaceAll("}", "");

            return price;
        }

        return "";
    }

    String getCoinPriceJson(String symbol) {
        String urlstr = String.format("https://api.binance.com/api/v3/ticker/price?symbol=%s", symbol);
        return apiClient.request(urlstr);
    }

}
