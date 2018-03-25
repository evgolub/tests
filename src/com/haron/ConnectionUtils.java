package com.haron;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.util.*;
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

        String sql = "SELECT * from table(test_pkg_3.GET_NAME('жен'))";
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<People> ppl = new ArrayList<>();
        while (rs.next()) {
            ppl.add( new People(
                    rs.getString(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));
        }

        System.out.println(ppl.toString()); //вывод коллекции ДО сортировки
            Collections.sort(ppl, People.COMPARE_BY_AGE); //сортировка по возрасту
            //Collections.sort(ppl,People.COMPARE_BY_NAME); //сортировка по имени
            System.out.println(ppl.toString()); //вывод коллекции ПОСЛЕ сортировки

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();

                Document doc = builder.newDocument();

                Element rootElement = doc.createElement("root");

                doc.appendChild(rootElement);

                for(int i =0 ; i<ppl.toArray().length; i++)
                {
                            rootElement.appendChild(getPeople(doc,
                            ppl.get(i).getName(),
                            ppl.get(i).getAge(),
                            ppl.get(i).getSex(),
                            ppl.get(i).getCity(),
                            ppl.get(i).getSocial(),
                            ppl.get(i).getCar()));
                }

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

    private static Node getPeople(Document doc, String Name, Integer Age, String Sex, String City, String SocialStatus, String Car) {
        Element people = doc.createElement("People");

        people.appendChild(getPeopleElement(doc, people, "Имя", Name));
       people.appendChild(getPeopleElement(doc, people, "Возраст", Age.toString()));
        people.appendChild(getPeopleElement(doc, people, "Пол", Sex));
        people.appendChild(getPeopleElement(doc, people, "Город", City));
        people.appendChild(getPeopleElement(doc, people, "Статус", SocialStatus));
        people.appendChild(getPeopleElement(doc, people, "Машина", Car));
        return people;
    }

    private static Node getPeopleElement(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}





