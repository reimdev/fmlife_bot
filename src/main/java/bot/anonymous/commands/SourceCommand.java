package bot.anonymous.commands;

import bot.anonymous.BotConfig;
import bot.anonymous.Commands;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.DefaultBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SourceCommand extends DefaultBotCommand {

    public SourceCommand() {
        super(Commands.sourceCommand, "Get the source code of this bot");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, Integer messageId, String[] strings) {
        SendMessage message = new SendMessage();

        String messageText = BotConfig.USERNAME + " v1.0 ~ " + BotConfig.REMOTE;

        message.setText(messageText);

        message.setChatId(chat.getId().toString());
        message.setReplyToMessageId(messageId);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}