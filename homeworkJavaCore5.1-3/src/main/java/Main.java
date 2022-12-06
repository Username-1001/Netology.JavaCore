import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        String fileNameXML = "data.xml";

        List<Employee> employeeListCSV = parseCSV(columnMapping, fileName);
        writeString(stringToJson(employeeListCSV), "data.json");

        List<Employee> employeeListXML = parseXML(fileNameXML);
        writeString(stringToJson(employeeListXML), "data2.json");

        String json = readString("data.json");
        List<Employee> employeeListJson = jsonToList(json);
        employeeListJson.stream().forEach(System.out::println);
    }

    private static List<Employee> jsonToList(String json) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(json, new TypeToken<List<Employee>>(){}.getType());
    }

    private static String readString(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return builder.toString();
    }

    private static List<Employee> parseXML(String fileNameXML) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(fileNameXML));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            return null;
        }

        Node root = document.getDocumentElement();
        NodeList nodes = root.getChildNodes();

        List<Employee> result = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element employee = (Element) node;

                result.add(new Employee(Long.parseLong(employee.getElementsByTagName("id").item(0).getTextContent()),
                        employee.getElementsByTagName("firstName").item(0).getTextContent(),
                        employee.getElementsByTagName("lastName").item(0).getTextContent(),
                        employee.getElementsByTagName("country").item(0).getTextContent(),
                        Integer.parseInt(employee.getElementsByTagName("age").item(0).getTextContent())));
            }
        }
        return result;
    }

    private static void writeString(String json, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String stringToJson(List<Employee> employeeList) {
        Type listType = new TypeToken<List<Employee>>() {
        }.getType();
        return new GsonBuilder().create().toJson(employeeList, listType);
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
            CSVReader csvReader = new CSVReader(fileReader);

            ColumnPositionMappingStrategy<Employee> mappingStrategy = new ColumnPositionMappingStrategy<Employee>();
            mappingStrategy.setType(Employee.class);
            mappingStrategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(csvReader).withMappingStrategy(mappingStrategy).build();

            return csvToBean.parse();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
