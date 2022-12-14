package telegram.bot;

import privatBank.CurrencyRateApiService;
import privatBank.PrivatCurrenceRateService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;


public class CurrencyRateBot extends TelegramLongPollingBot {
    private CurrencyRateApiService privatBank = new PrivatCurrenceRateService();

    @Override
    public String getBotUsername() {
        return Constance.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return Constance.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            //extract message our customer
            Message message = update.getMessage();
            // get ID our customer
            String charId = message.getChatId().toString();
            String response = parseMassage(message.getText());
            //Future answer
            SendMessage sendMessage = new SendMessage();
            //Set ID and set answer
            sendMessage.setChatId(charId);
            sendMessage.setText(response);
            sendMessage.setReplyMarkup(bottom());
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    //Start message for customer
    private String parseMassage(String massage) {
        CurrencyRateApiService privat = new PrivatCurrenceRateService();
        String response;
        if (massage.equals("/start")) {
            response = "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют";
        }
        else if (massage.equals("Отримати інформацію")) {
            return String.valueOf(privat.getCurrency());
        }
        else if (massage.equals("Налаштування")){
            return "I don't have settings";
        }
        else response = "I don't know what do you want";

        return response;
    }

    //Add bottom for telegram Bot
    public ReplyKeyboardMarkup bottom() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> row = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();

        row.add(keyboardRow);

        keyboardRow.add(new KeyboardButton("Отримати інформацію"));
        keyboardRow.add(new KeyboardButton("Налаштування"));

        replyKeyboardMarkup.setKeyboard(row);
        return replyKeyboardMarkup;
    }
}
