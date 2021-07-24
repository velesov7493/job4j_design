package ru.job4j.isp;

import ru.job4j.isp.io.ConsoleInput;
import ru.job4j.isp.io.ConsoleOutput;
import ru.job4j.isp.io.Input;
import ru.job4j.isp.io.Output;

public class MenuDemo {

    private Menu menu;
    private Input input;
    private Output output;
    private boolean finish;

    private Command closeCmd = () -> finish = true;

    private Command addItemCmd = () -> {
        String parentIndex = input.askStr("Индекс родителя: ");
        String name = input.askStr("Заголовок нового пункта меню: ");
        menu.addItem(parentIndex, name);
    };

    private Command removeItemCmd = () -> {
        String index = input.askStr("Полный индекс пункта меню: ");
        menu.removeItem(index);
    };

    private Command showMenuCmd = () -> menu.print();

    public MenuDemo(Input aInput, Output aOutput) {
        input = aInput;
        output = aOutput;
        menu = new SimpleMenu(output);
        menu.addItem("", "Основное");
        menu.addItem("1", "Операции");
        menu.addItem("1.1", "Добавить пункт меню");
        menu.addItem("1.1", "Убрать пункт меню");
        menu.addItem("1.1", "Показать меню");
        menu.addItem("1", "Выход");
        menu.addItem("", "Справка");
        MenuItem item = menu.getItemByFullIndex("1.1.1");
        item.setCommand(addItemCmd);
        item = menu.getItemByFullIndex("1.1.2");
        item.setCommand(removeItemCmd);
        item = menu.getItemByFullIndex("1.1.3");
        item.setCommand(showMenuCmd);
        item = menu.getItemByFullIndex("1.2");
        item.setCommand(closeCmd);
    }

    public void run() {
        menu.print();
        finish = false;
        while (!finish) {
            String itemIndex = input.askStr("Выберите пункт меню: ");
            menu.runItemCommand(itemIndex);
        }
    }

    public static void main(String[] args) {
        MenuDemo instance = new MenuDemo(new ConsoleInput(), new ConsoleOutput());
        instance.run();
    }
}
