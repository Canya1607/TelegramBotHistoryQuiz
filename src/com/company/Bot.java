package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    private Process MyProcess;
    private final String[] callD = {"0", "1", "2", "3"};

    private InlineKeyboardMarkup markupInlineStart() {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        //
        List<InlineKeyboardButton> rowInline = new ArrayList<InlineKeyboardButton>();

        rowInline.add(new InlineKeyboardButton().setText("Почати").setCallbackData("btn_start"));

        //Set the keyboard to the markup
        rowsInline.add(rowInline);

        //Add it to the message
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }

    private InlineKeyboardMarkup markupInlineAnswers(List<String> answers) {
        if(answers == null){
            return null;
        }

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<List<InlineKeyboardButton>>();
        //
        List<InlineKeyboardButton> rowInline1 = new ArrayList<InlineKeyboardButton>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<InlineKeyboardButton>();
        List<InlineKeyboardButton> rowInline3 = new ArrayList<InlineKeyboardButton>();
        List<InlineKeyboardButton> rowInline4 = new ArrayList<InlineKeyboardButton>();


        rowInline1.add(new InlineKeyboardButton().setText(answers.get(0)).setCallbackData(callD[0]));
        rowInline2.add(new InlineKeyboardButton().setText(answers.get(1)).setCallbackData(callD[1]));
        rowInline3.add(new InlineKeyboardButton().setText(answers.get(2)).setCallbackData(callD[2]));
        rowInline4.add(new InlineKeyboardButton().setText(answers.get(3)).setCallbackData(callD[3]));

        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);
        rowsInline.add(rowInline4);

        //Add it to the message
        markupInline.setKeyboard(rowsInline);

        return markupInline;
    }

    private void procCommand(long chatID, String command){
        //String msg_text = "nothing";
        switch (command){
            case "/start":
                MyProcess = new Process();
                sendMsg(chatID, "Привіт! Вас чекає опитування на тему \"Як добре ви знаєте історію?\"", markupInlineStart());
                break;
            case "/new":
                MyProcess.clear();
                sendMsg(chatID, "Ну що почнем?", markupInlineStart());
                break;
            default:
                sendMsg(chatID, "Такої команди не існує");
                break;
        }

        //sendMsg(chatID, msg_text);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if(message_text.charAt(0) == '/'){
                procCommand(chat_id, message_text);
            }
        }
        if (update.hasCallbackQuery()) {
            String callData = update.getCallbackQuery().getData();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            int msg_id = update.getCallbackQuery().getMessage().getMessageId();

            editMsg(chat_id, msg_id);
            String msgText = MyProcess.getOneQuestion();
            if(msgText.equals(" ")) {
                MyProcess.checkAnswer(callData);
                sendMsg(chat_id, "Питання закінчились, ви можете почати заново за допомою команди /new\n" +
                        String.format("Ви вгадали %d питань з 18", MyProcess.getCounter()));
            } else {
                switch (callData) {
                    case "btn_start":
                        sendMsg(chat_id, msgText, markupInlineAnswers(MyProcess.getAnswers()));
                        break;
                    case "0":
                        MyProcess.checkAnswer(callData);
                        sendMsg(chat_id, msgText, markupInlineAnswers(MyProcess.getAnswers()));
                        break;
                    case "1":
                        MyProcess.checkAnswer(callData);
                        sendMsg(chat_id, msgText, markupInlineAnswers(MyProcess.getAnswers()));
                        break;
                    case "2":
                        MyProcess.checkAnswer(callData);
                        sendMsg(chat_id, msgText, markupInlineAnswers(MyProcess.getAnswers()));
                        break;
                    case "3":
                        MyProcess.checkAnswer(callData);
                        sendMsg(chat_id, msgText, markupInlineAnswers(MyProcess.getAnswers()));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "GeniusesBot";
    }

    @Override
    public String getBotToken() {
        return "692544363:AAFFJMyINdIIsY4S_7pFlKhhMpe0BidZbwk";
    }

    private void sendMsg(long chatID, String text) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatID)
                .setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(long chatID, String text, ReplyKeyboard replyMarkup) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatID)
                .setText(text)
                .setReplyMarkup(replyMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void editMsg(long chatID, int msgID){
        EditMessageReplyMarkup editMarkup = new EditMessageReplyMarkup()
                .setChatId(chatID)
                .setMessageId(msgID)
                .setReplyMarkup(null);

        try {
            execute(editMarkup);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
