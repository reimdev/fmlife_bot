package bot.anonymous.data;

import java.util.Random;

public class Fortunes {

    static String[] fortunes = new String[] {
        "Reply hazy, try again",
                "Excellent Luck",
                "Good Luck",
                "Average Luck",
                "Bad Luck",
                "Good news will come to you by mail",
                "（　´_ゝ`）ﾌｰﾝ",
                " ｷﾀ━━━━━━(ﾟ∀ﾟ)━━━━━━ !!!!",
                "You will meet a dark handsome stranger",
                "Better not tell you now",
                "Outlook good",
                "Very Bad Luck",
                "Godly Luck"
    };

    public static String getRandomFortune() {
        return fortunes[(new Random()).nextInt(fortunes.length)];
    }

}
