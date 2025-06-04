package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для работы с настройками подключения к БД
 */
public class ConnectionProperty {
    private static final String CONFIG_FILE = "/config/config.properties";
    private static final Properties properties = new Properties();

    // Статический блок инициализации (выполняется при загрузке класса)
    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = ConnectionProperty.class.getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Файл конфигурации " + CONFIG_FILE + " не найден");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }

    /**
     * Получение значения свойства по ключу
     * @param key имя свойства
     * @return значение свойства
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Примеры конкретных свойств
    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUser() {
        return properties.getProperty("db.user");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}