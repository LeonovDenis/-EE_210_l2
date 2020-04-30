package start;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;
import wet.IDemoI;
import wet.IDemoI_Service;

@WebServlet(name = "Messager",
        urlPatterns = {"/Messager"})
public class Messager extends HttpServlet implements HtmlTemplate {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/EE_210_lab2_2-war/IDemoI?WSDL")
    private IDemoI_Service service;

    /**
     * Вложенное перечисление, определяющее типы форм приложения.
     */
    private static enum FormTypes {
        LOGIN, MESSAGE, LIST, SINGLE;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // Получение параметра запроса login
        String parameter = request.getParameter("login");
        String login = "";
        if (parameter == null) {
            response.sendRedirect("index.html");
            return;
        } else {
            login = parameter.trim();
        }
        try (PrintWriter out = response.getWriter()) {
            if (login == null || login.isEmpty() || !login.matches("\\w{2,8}")) {
                out.println(getForm(FormTypes.LOGIN, INVALID_NAME, null));
                return;
            }
            // Получение ссылки на сессию пользователя, если она открыта.
            HttpSession hs = request.getSession(false);
            if (hs != null) {
                // Если сессия открыта, то она закрывается для предотвращения
                // использования открытой сессии другим пользователем.
                hs.invalidate();
            }
            // Открытие новой сессии.
            hs = request.getSession(true);
            // Сохранение в атрибуте сессии имени (логина) пользователя.
            hs.setAttribute("user", login.trim());
            // Вывод ответа пользователю.
            out.println(getForm(FormTypes.MESSAGE, null, null));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // Получение коллекции всех параметров запроса
        Map<String, String[]> map = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        try (PrintWriter out = response.getWriter()) {
            // Получение ссылки на сессию.
            HttpSession hs = request.getSession(false);
            if (hs == null) {
                out.println(getForm(FormTypes.LOGIN, SESSION_CLOSED, null));
                return;
            }
            String user = hs.getAttribute("user").toString();
            String message = null;
            // Перебор полученной коллекции параметров запроса
            for (Map.Entry<String, String[]> m : map.entrySet()) {
                String key = m.getKey();
                switch (key) {
                    case "message": // Обработка собственно сообщения.
                        // Проверка корректности сообщения
                        if (m.getValue() != null && m.getValue().length > 0) {
                            message = m.getValue()[0].trim();
                        }
                        break;
                    case "saveText": // Команда на отправку сообщения.
                        boolean result = sendMessage(user, message);
                        // Оценка результата и формирование сообщения 
                        // пользователю
                        if (result) {
                            // Сообщение об успешной отправке
                            sb.append(MESSAGE)
                                    .append(message)
                                    .append(SENT);
                        } else {
                            // Описание ошибки при отправке сообщения
                            sb.append(NOT_SENT);
                        }
                        out.println(getForm(FormTypes.MESSAGE, sb.toString(), null));
                        return;
                    case "index":
                        try {
                            Integer index = Integer.parseInt(message);
                            if (index < 0 || index > 100) {
                                throw new NumberFormatException();
                            }
                            out.println(getForm(FormTypes.SINGLE, user, index));
                        } catch (NumberFormatException ignore) {
                            out.println(getForm(FormTypes.MESSAGE, BAD_NUMBER_MESSAGE, null));
                        }
                        return;
                    case "next":
                        out.println(getForm(FormTypes.MESSAGE, null, null));
                        return;
                    case "list": // Команда на вывод списка сообщения пользователя.
                        out.println(getForm(FormTypes.LIST, user, null));
                        return;
                    case "return": // Команда на выход из приложения.
                        hs.invalidate();
                        response.sendRedirect("index.html");
                        return;
                    default: // Обработка неизвестной команды.
                        out.println(getForm(FormTypes.LOGIN, UNKNOWN_COMMAND, null));
                }
            }
        }
    }

    private boolean sendMessage(String login, String message) {
        IDemoI port = service.getIDemoIPort();
        return port.add(login, message);
    }

    /**
     * Метод формирует заданную форму ответа с полями ввода и сообщением о
     * результате обработки запроса.
     *
     * @param type Определяет форму ответа пользователю.
     * @param msg Сообщение пользователю.
     * @return HTML-код формы ответа
     */
    private String getForm(FormTypes type, String msg, Integer index) {
        StringBuilder sb = new StringBuilder(HEAD);
        switch (type) {
            case MESSAGE: // Форма ввода сообщения
                sb.append(MESSAGE_FORM)
                        .append(SINGLE_FORM)
                        .append(BUTTON_FORM);
                if (msg != null) {
                    sb.append(START_MESSAGE)
                            .append(msg)
                            .append(END_MESSAGE);
                }
                break;
            case LIST: // Форма вывода списка сообщений 
                sb.append(getList(msg))
                        .append(MESSAGE_LIST_BUTTON_FORM);
                break;
            case SINGLE: // Форма вывода списка сообщений 
                sb.append(singleMessage(msg, index))
                        .append(MESSAGE_LIST_BUTTON_FORM);
                break;

            case LOGIN: // Форма ввода логина пользователя
                sb.append(LOGIN_FORM);
                if (msg != null) {
                    sb.append(START_MESSAGE)
                            .append(msg)
                            .append(END_MESSAGE);
                }
        }
        sb.append(FOOTER);
        return sb.toString();
    }

    /**
     * Метод возвращает HTML-код, содержащий список сообщений пользователя.
     *
     * @param login Логин (идентификатор) пользователя.
     * @return HTML-код, содержащий список сообщений пользователя.
     */
    private String getList(String login) {
        StringBuilder sb = new StringBuilder();
        IDemoI port = service.getIDemoIPort();
        List<String> allMessage = port.getAllMessage(login);
        if (allMessage.isEmpty()) {
            sb.append(START_MESSAGE)
                    .append(NULL_LIST)
                    .append(END_MESSAGE);
            return sb.toString();
        } else {
            sb.append(LIST_HEAD_START)
                    .append(START_MESSAGE_LEFT)
                    .append(login)
                    .append(END_MESSAGE_LEFT)
                    .append(LIST_HEAD_END);
            // Перебор коллекции сообщений и формирование HTML-код с его 
            // представлением.
            for (String msg : allMessage) {
                sb.append("<li>").append(msg).append("</li>\n");
            }
            sb.append("</ol>");
            return sb.toString();
        }
    }

    private String singleMessage(String login, int index) {
        StringBuilder sb = new StringBuilder();
        IDemoI port = service.getIDemoIPort();

        List<String> allMessage = port.getAllMessage(login);
        if (allMessage.isEmpty()) {
            sb.append(START_MESSAGE)
                    .append(NULL_LIST)
                    .append(END_MESSAGE);
            return sb.toString();
        } else if (allMessage.size() < index + 1) {
            sb.append(START_MESSAGE)
                    .append(NULL_INDEX)
                    .append(END_MESSAGE);
            return sb.toString();
        } else {
            String message = port.getMessage(login, index);
            sb.append(SINGLE_HEAD_START)
                    .append(START_MESSAGE_LEFT)
                    .append(index)
                    .append(END_MESSAGE_LEFT)
                    .append(SINGLE_HEAD_MID)
                    .append(START_MESSAGE_LEFT)
                    .append(login)
                    .append(END_MESSAGE_LEFT)
                    .append(SINGLE_HEAD_END);
            sb.append(SINGLE_FOOTER_START)
                    .append(message)
                    .append(SINGLE_FOOTER_END);
            return sb.toString();
        }
    }

    /**
     * Метод возвращает краткое описание сервлета.
     *
     * @return объект String, содержащий описание сервлета
     */
    @Override
    public String getServletInfo() {
        return "Сервлет Messager";
    }

}
