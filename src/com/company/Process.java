package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Process {
    private List<Question> questions;
    private int indexQuestion;
    private int counter;

    private void initQuestions(){
        questions = new ArrayList<>();

        String[] s = {"Жовта ріка", "Річка Ніл", "Річка Ганг", "Темза"};
        questions.add(new Question("З якою річкою звязаний розвиток Єгипту?", Arrays.asList(s), 1));

        s = new String[]{"Російська імперія", "Іспанська імперія", "Монгольська", "Британська імперія"};
        questions.add(new Question("Під контролем якої імперії було найбільше всього земель ?", Arrays.asList(s), 2));

        s = new String[]{"Людовик XVI", "Людовик XIV", "Генри VIII", "Королева Єлизавета I"};
        questions.add(new Question("Хто з цих абсолютних монархів був відомий в істроії як Король-Сонце?", Arrays.asList(s), 1));

        s = new String[]{"Ацтеки", "Хопі", "Інки", "Мая"};
        questions.add(new Question("Яку імперію завоював Ернан Кортес по дорозі до Нового Світу ?", Arrays.asList(s), 0));

        s = new String[]{"Едірне", "Коринф", "Візантія", "Рим"};
        questions.add(new Question("Яке початкоме ймення носило місто Константинополь, відомий сьогодні як Істамбул?", Arrays.asList(s), 2));

        s = new String[]{"Рабство", "Британська тиранія", "Державні права", "Доктрина про територіальну експансію"};
        questions.add(new Question("Що з нижче переліченого привело до розвязання Громадянської війни в США?", Arrays.asList(s), 2));

        s = new String[]{"Наполеон Бонапарт", "Джордж Вашингтон", "Карл Великий", "Олександр Македонський"};
        questions.add(new Question("Хто з цих воєнокомандувачів обєднав більшу частину Західної Європи вперше після Риму?", Arrays.asList(s), 0));

        s = new String[]{"Династія Мінь", "Династія Тан", "Династія Хань", "Династія Цинь"};
        questions.add(new Question("Як називалася перша династія, обєднавша Китай?", Arrays.asList(s), 3));

        s = new String[]{"Африка", "Америка", "Схід", "Близький Схід"};
        questions.add(new Question("Який з цих регіонів переживав золотий вік в темні часи європейської історії?", Arrays.asList(s), 3));

        s = new String[]{"Самодержавець", "Імператор", "Цар", "Президент"};
        questions.add(new Question("Який титул носили російські правителі до 1917 року ?", Arrays.asList(s), 1));

        s = new String[]{"Афіни", "Спарта", "Фіви", "Коринф"};
        questions.add(new Question("В якому з цих грецьких міст-держав процвітала пряма демократія?", Arrays.asList(s), 0));

        s = new String[]{"Наполеон", "Олександр Македонський", "Мехмед Завойовник", "Чинхисхан"};
        questions.add(new Question("Хто завоював Константинополь, завершив тим самим існування Візантійської і Римської імперії?", Arrays.asList(s), 2));

        s = new String[]{"Полінезійці", "Готи", "Аборигени", "Кельти"};
        questions.add(new Question("До якої культури належать люди з острову Пасхи?", Arrays.asList(s), 0));

        s = new String[]{"Вікінги", "Італійці", "Іспанці", "Англійці"};
        questions.add(new Question("Хто вперше відкрив Америку?", Arrays.asList(s), 2));

        s = new String[]{"Мартин Ван Бюрен", "Уліс Грант", "Ендрю Джексон", "Ендрю Джонсон"};
        questions.add(new Question("Який американський президент почав масове позбавлення від індійців (\"Дорога сліз\")?", Arrays.asList(s), 2));

        s = new String[]{"Китайська культура", "Персидська культура", "Греко-романська культура", "Єгипетська культура"};
        questions.add(new Question("Зазвичай ренесанс розглядаєтья як возєднання європейської спільноти з якою культурою ?", Arrays.asList(s), 2));

        s = new String[]{"Константин", "Калигула", "Октавіан", "Тіберій"};
        questions.add(new Question("Який римський імператор легалізував християнство в Римській Імперії?", Arrays.asList(s), 0));

        s = new String[]{"Західна", "Єгипетська", "Індійська", "Китайська"};
        questions.add(new Question("Яка з цих цивилізацій жила довше за всіх?", Arrays.asList(s), 3));
    }

    Process(){
        initQuestions();
        indexQuestion = 0;
        counter = 0;
    }

    public int getCounter() {
        return counter;
    }

    public String getOneQuestion(){
        if(indexQuestion == questions.size()){
            return " ";
        }

        return questions.get(indexQuestion).toString();
    }

    public boolean canAsk(){
        if(indexQuestion < questions.size()){
            return true;
        }
        return false;
    }

    public void checkAnswer(String callData){
       int callD = Integer.parseInt(callData);

       if (callD == questions.get(indexQuestion - 1).getAnswerIndex()) {
           counter++;
       }
    }

    public List<String> getAnswers(){
        if(indexQuestion == questions.size()){
            return null;
        }
        List<String> answers = questions.get(indexQuestion).getAnswers();
        indexQuestion++;

        return answers;
    }

    public void clear(){
        indexQuestion = 0;
        counter = 0;
    }
}
