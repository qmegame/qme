package org.qme.utils;

import org.qme.io.Logger;
import org.qme.io.Severity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

/**
 * This class is responsible for converting internal types like enums into human readable names per local
 * Right now we only have english however in the future we should expand to other languages
 * @since 0.3.0
 * @author cameron
 */
public class Language {

    private static Locale locale;
    private static Properties lang;

    /**
     * Switches the loaded language
     * Logs if the language isn't supported
     * @param locale the language to switch to
     */
    public static void switchLanguage(Locale locale) {
        InputStream stream = Language.class.getClassLoader().getResourceAsStream("locale/" + locale.toString() + ".properties");

        if (stream == null) {
            Logger.log("Something tried to switch to locale " + locale.toString() + " that either doesn't exist or isn't supported", Severity.WARNING);
            return;
        }
        Properties properties = new Properties();

        // Attempt to load properties
        try {
            properties.load(stream);
        } catch (IOException e) {
            Logger.log("Could not load locale " + locale.toString(), Severity.ERROR);
            e.printStackTrace();
        }

        lang = properties;
        Language.locale = locale;
    }

    /**
     * Gets a translation for a given key
     * @param key the key to get the translation for
     * @return the message in the current language
     */
    public static String getTranslation(String key) {
        if (!lang.containsKey(key)) {
            return "Translation missing: " + key;
        }
        return lang.getProperty(key);
    }


}
