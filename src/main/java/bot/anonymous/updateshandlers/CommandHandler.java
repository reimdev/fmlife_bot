package bot.anonymous.updateshandlers;

import bot.anonymous.BotConfig;
import bot.anonymous.commands.*;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandHandler extends TelegramLongPollingCommandBot {

    public CommandHandler() {

        register(new RollCommand());
        register(new FortuneCommand());
        register(new QuoteCommand());
        register(new QotdCommand());
        register(new EthCommand());
        register(new SourceCommand());
        register(new WeatherCommand());

    }

    @Override
    public String getBotUsername() {
        return BotConfig.USERNAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    @Override
    public String getBotToken() {
        return BotConfig.getKey();
    }
}
