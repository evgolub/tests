package com.haron;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ConnectionUtils {

    public static Connection getMyConnection() throws SQLException,
            ClassNotFoundException {


        return OracleConnUtils.getOracleConnection();
    }

        public static void main(String[] args)  throws ClassNotFoundException,
                SQLException {

            Connection connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();


            String sql = "SELECT * from table(test_pkg_3.GET_NAME('СПб'))";
            ResultSet rs = statement.executeQuery(sql);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();

                Document doc = builder.newDocument();

                Element rootElement = doc.createElement("root");

                doc.appendChild(rootElement);

                while (rs.next()) {
                    rootElement.appendChild(getLanguage(doc, rs.getString(1), rs.getString(2)));
                }
                connection.close();

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);

                StreamResult file = new StreamResult(new File("D:/people.xml"));

                transformer.transform(source, file);

                System.out.println("Создание XML файла закончено");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    private static Node getLanguage(Document doc, String name, String city) {
        Element people = doc.createElement("People");

        people.appendChild(getPeopleElement(doc, people, "Имя", name));
        people.appendChild(getPeopleElement(doc, people, "Город", city));

        return people;
    }

    private static Node getPeopleElement(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}





