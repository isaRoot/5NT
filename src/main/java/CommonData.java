import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class CommonData {
    String nameTemplate;
    String urlConnection;
    String outQueue;
    String inputQueue;

    public String getUrlConnection() {
        return urlConnection;
    }

    public void setUrlConnection(String urlConnection) {
        this.urlConnection = urlConnection;
    }

    public String getOutQueue() {
        return outQueue;
    }

    public void setOutQueue(String outQueue) {
        this.outQueue = outQueue;
    }

    public String getInputQueue() {
        return inputQueue;
    }

    public void setInputQueue(String inputQueue) {
        this.inputQueue = inputQueue;
    }

    //чтение конфига
    public void readConfig(){
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("./config.properties");
            property.load(fis);

            setUrlConnection(property.getProperty("URL"));
            setOutQueue(property.getProperty("OutputQueue"));
            setInputQueue(property.getProperty("InputQueue"));

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    //чтение шаблона
    public static String readTemplate(String nameTemplate) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader(nameTemplate));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
        }
        return stringBuilder.toString();
    }

    //генерация случайных чисел
    public static String randomNumber(int count){
        Random random = new Random();
        String randomNumber = "";
        for(int i = 0; i < count; i++){
            randomNumber += String.valueOf(random.nextInt());
        }
        return randomNumber;
    }
    //генерация случайных слов
    public static String randomAlpha(int count){
        Random random = new Random();
        String randomAlpha = "";
        for(int i = 0; i < count; i++){
            char randomNumber = (char)('A' + random.nextInt(26));
        }
        return randomAlpha;
    }
}
