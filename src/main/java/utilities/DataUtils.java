package utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {
    private static final String test_data_path = "src/test/resources/TestData/"; //to make path dynamic below in method
    private static final String uploadPath = "/src/test/resources/testData/Uploads/";

    public static String uploadFilePath(WebDriver driver, String fileName) {
        return System.getProperty("user.dir") + uploadPath + fileName;

    }

    //TODO:reading data from json file
    public static String getJsonData(String fileName, String field) throws FileNotFoundException { //file i will read from + field i wanted
        try {
            FileReader fileReader = new FileReader(test_data_path + fileName + ".json");//initialize file
            JsonElement jsonElement = JsonParser.parseReader(fileReader); //read file
            return jsonElement.getAsJsonObject().get(field).getAsString(); // get field name from it
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPropertyValue(String fileName, String key) throws IOException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(test_data_path + fileName + ".properties")); //initialize and load from this path
            return properties.getProperty(key); //get key u wanted
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


}
