package bot.anonymous.commands;

import bot.anonymous.Commands;
import bot.anonymous.data.Fortunes;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.DefaultBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class FortuneCommand extends DefaultBotCommand {

    public FortuneCommand() {
        super(Commands.fortuneCommand, "Get your fortune :D");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, Integer integer, String[] strings) {
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId().toString());
        message.setText("Your fortune: " + Fortunes.getRandomFortune());
        message.setReplyToMessageId(integer);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
