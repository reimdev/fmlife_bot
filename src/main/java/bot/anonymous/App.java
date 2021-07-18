package bot.anonymous;

import java.util.*;
import java.io.*;

import bot.anonymous.updateshandlers.CommandHandler;
import org.telegram.telegrambots.meta.*;
import org.telegram.telegrambots.meta.exceptions.*;
import org.telegram.telegrambots.updatesreceivers.*;

public class App {

    public static void main( String[] args ) {
		try {
			TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
			api.registerBot(new CommandHandler());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
    }

}
