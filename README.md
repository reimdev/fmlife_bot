# fmlife_bot: Telegram Bot written in Java

## Creating a new command
### Add command name to Commands class
```
public class Commands {
    // snipped out
    public static final String demoCommand = commandInitChar + "demo";
}
```
 
### Add command class that extends DefaultBotCommand 
```
public class DemoCommand extends DefaultBotCommand {

    public DemoCommand() {
        super(Commands.demoCommand, "Command description");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, Integer messageId, String[] strings) {
        SendMessage message = new SendMessage();
	
        message.setText("This is a demo");

        message.setChatId(chat.getId().toString());
        message.setReplyToMessageId(messageId);

        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
```
### Register command in CommandHandler class
```
public class CommandHandler extends TelegramLongPollingCommandBot {

    public CommandHandler() {
	
        register(new DemoCommand());

    }

   // snipped out
}
```