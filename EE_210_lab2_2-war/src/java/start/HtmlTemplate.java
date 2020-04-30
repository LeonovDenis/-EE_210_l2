/*
 * Приложение "Messager".
 * Интерфейс HtmlTemplate.java.
 * (C) Ю.Д.Заковряшин, 2020
 */
package start;

/**
 * Интерфейс определяет набор строковых констант, представляющих стандартные
 * элементы дизайна страниц приложения.
 *
 * @author Ю.Д.Заковряшин, 2008-2020
 */
public interface HtmlTemplate {

    /**
     * Стандартный заголовок HTML-страницы.
     */
    public static final String HEAD
            = "<!DOCTYPE html>\n"
            + "<html>\n"
            + "    <head>\n"
            + "        <title>Отправляльщик, v.1.0</title>\n"
            + "        <meta charset=\"UTF-8\">\n"
            + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
            + "    </head>\n"
            + "    <body>\n"
            + "        <header style=\"height: 40px; text-align: center; line-height: 40px;\n"
            + "                color: ghostwhite; background-color: #0099ff\">\n"
            + "            <h2>Приложение Отправляльщик, v.2.1</h2>          \n"
            + "        </header>\n"
            + "        <hr width=\"100%\" align=\"center\" />";
    /**
     * Краткое описание приложения.
     */
    public static final String DESCRIPTION
            = "        <div style=\"text-align:  center\">\n"
            + "           Лабораторная №2."
            + "        </div>";
    /**
     * Html-форма для ввода имени(логина) пользователя.
     */
    public static final String LOGIN_FORM
            = "        <form action=\"Messager\" method=\"GET\" >\n"
            + "            <p  style=\"text-align: center\">\n"
            + "                 Введите логин (латиница от 2 до 8 символов):\n"
            + "                <input type=\"text\" name=\"login\" size=\"8\" required />\n"
            + "            </p>\n"
            + "            <p style=\"text-align: center\">\n"
            + "                <input type=\"submit\" value=\"Начать работу\"/>\n"
            + "            </p>\n"
            + "        </form>";
    /**
     * Форма ввода сообщения пользователя.
     */
    public static final String MESSAGE_FORM
            = "        <form action=\"Messager\" method=\"POST\" >\n"
            + "                               <style type=\"text/css\">\n"
            + "                                .tab{ margin-left: auto;\n"
            + "                                 margin-right: auto}\n"
            + "                            </style>\n"
            + "                         <table class=\"tab\" bgcolor=\"c0e4ff\" border=\"2\"\n"
            + "                            cellspacing=\"5\" cellpadding=\"5\" width=\"450\">\n"
            + "                        <tr>\n"
            + "                            <td colspan=\"2\">\n"
            + "                        <center>\n"
            + "                            Введите Ваше текстовое сообщение\n"
            + "                        </center>\n"
            + "                        </td>\n"
            + "                        </tr>\n"
            + "                        <tr >\n"
            + "                            <td >\n"
            + "                                Текст (латиница до 255 символов):\n"
            + "                            </td> \n"
            + "                            <td >\n"
            + "                                <input type=\"text\" name=\"message\" value=\"\" size=\"15\"required />\n"
            + "                            </td>\n"
            + "                        </tr>\n"
            + "                        <tr>\n"
            + "                            <td colspan=\"2\">\n"
            + "                                <style type=\"text/css\">\n"
            + "                                .bt{width:100%}\n"
            + "                            </style>\n"
            + "                            <input class=\"bt\" type=\"submit\" name=\"saveText\" value=\"Отправить сообщение\"/>\n"
            + "                        </td>\n"
            + "                    </tr>\n"
            + "                </table> "
            + "        </form>"
            + "<BR>";

    /**
     * Форма ввода сообщения пользователя.
     */
    public static final String SINGLE_FORM
            = "        <form action=\"Messager\" method=\"POST\" >\n"
            + "                                 <style type=\"text/css\">\n"
            + "                                .tab{ margin-left: auto;\n"
            + "                                 margin-right: auto}\n"
            + "                            </style>\n"
            + "                         <table class=\"tab\" bgcolor=\"c0e4ff\" border=\"2\"\n"
            + "                            cellspacing=\"5\" cellpadding=\"5\" width=\"450\">\n"
            + "                        <tr>\n"
            + "                            <td colspan=\"2\">\n"
            + "                        <center>\n"
            + "                            Форма получения одного сообщения пользователя\n"
            + "                        </center>\n"
            + "                        </td>\n"
            + "                        </tr>\n"
            + "                        <tr >\n"
            + "                            <td >\n"
            + "                                Индекс сообщения (целое число от 0 до 100):\n"
            + "                            </td> \n"
            + "                            <td align=\"center\">\n"
            + "                                <input type=\"text\" name=\"message\" value=\"\" size=\"10\"required />\n"
            + "                            </td>\n"
            + "                        </tr>\n"
            + "                        <tr>\n"
            + "                            <td colspan=\"2\">\n"
            + "                                <style type=\"text/css\">\n"
            + "                                .bt{width:100%}\n"
            + "                            </style>\n"
            + "                            <input class=\"bt\" type=\"submit\" name=\"index\" value=\"Получить сообщение\"/>\n"
            + "                        </td>\n"
            + "                    </tr>\n"
            + "                </table> "
            + "        </form>";
    /**
     * Форма ввода сообщения пользователя.
     */
    public static final String BUTTON_FORM
            = "        <form action=\"Messager\" bgcolor=\"c0e4ff\" method=\"POST\" >\n"
            + "            <p  style=\"text-align: center\">\n"
            + "            </p>\n"
            + "            <p style=\"text-align: center\">\n"
            + "                <input type=\"submit\" name=\"list\" value=\"Вывести список всех текстовых сообщений пользователя\"/>&nbsp;\n"
            + "                <input type=\"submit\" name=\"return\" value=\"Завершить сессию\"/>\n"
            + "            </p>\n"
            + "        </form>";

    /**
     * Форма вывода списка ранее введённых сообщений пользователя.
     */
    public static final String MESSAGE_LIST_BUTTON_FORM 
            = "        <form action=\"Messager\" method=\"POST\" >\n"
            + "            <p style=\"text-align: center\">\n"
            + "                <!-- Кнопка отправки команд -->           \n"
            + "                <input type=\"submit\" name=\"next\" value=\"Продолжить\"/>&nbsp;\n"
            + "                <input type=\"submit\" name=\"return\" value=\"Завершить сессию\"/>&nbsp;\n"
            + "            </p>\n"
            + "        </form>";
    /**
     * HTML-код, определяющий начало сообщения приложения.
     */
    public static final String START_MESSAGE 
            = "            "
            + "<p  style=\"text-align: center; color:#F00000;\">\n";
     /**
     * HTML-код, определяющий начало сообщения приложения.
     */
    public static final String START_MESSAGE_LEFT 
            = "            "
            + "<span  style=\"text-align: left; color:#F00000;\">";
    /**
     * HTML-код, определяющий конец сообщения приложения.
     */
    public static final String END_MESSAGE 
            = "            </p>\n";
    /**
     * HTML-код, определяющий конец сообщения приложения.
     */
    public static final String END_MESSAGE_LEFT 
            = "            </span>";
    /**
     * Дополнительные сообщения.
     */
    public static final String SESSION_CLOSED = "Сессия пользователя закрыта";
    public static final String MESSAGE = "Сообщение \"";
    public static final String SENT = "\" отправлено";
    public static final String NOT_SENT = "Сообщение НЕ отправлено";
    public static final String UNKNOWN_COMMAND = "Неизвестная команда";
    public static final String NULL_MESSAGE = "Пустое сообщение не отправлено";
    public static final String BAD_NUMBER_MESSAGE = "Это не индекс";
    public static final String CONNECTION_ERROR = "Ошибка: отсутствует "
            + "соединение с сервисом";
    public static final String LIST_HEAD_START
            = "            <h3>Список сообщений пользователя : ";
    public static final String LIST_HEAD_END
            = "            </h3>\n            <ol>\n";
    public static final String SINGLE_HEAD_START
            = "            <h3>Выбрано сообщение с индексом ";
    public static final String SINGLE_HEAD_MID
            = "             пользователя ";
    public static final String SINGLE_HEAD_END
            = "            </h3>\n";
    public static final String SINGLE_FOOTER_START
            = "            <h3>Текст сообщения : ";
    public static final String SINGLE_FOOTER_END
            = "            </h3>\n";
    public static final String NULL_LIST 
            = " Список сообщений пуст";
    public static final String NULL_INDEX 
            = " Сообщения с таким индексом нет";
    
    /**
     * Стандартный подвал страницы приложения.
     */
    public static final String FOOTER = "        <hr width=\"100%\" align=\"center\" />\n"
            + "        <footer style=\"height:20px; text-align: center; line-height: 20px;\n"
            + "                color: ghostwhite; font-size: smaller; font-weight: bolder; \n"
            + "                background-color: #0099ff\">\n"
            + "            Погребок"
            + "        </footer>\n"
            + "    </body>\n"
            + "</html>";
    public static final String INVALID_NAME = "Имя пользователя задано с ошибкой";
}
