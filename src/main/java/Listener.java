//import com.sun.xml.internal.ws.util.xml.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.jms.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class Listener implements MessageListener {
    MessageConsumer consumer;
    MessageProducer producer;
    Session session;

    public Listener(MessageConsumer mc, MessageProducer mp, Session ses) {
        consumer = mc;
        producer = mp;
        session = ses;
    }

    //обработка сообщений
    @Override
    public void onMessage(Message message) {
        System.out.println(message.toString());
        try {
            if (message instanceof ObjectMessage) {
                consumer.close();
            }
            if (message instanceof TextMessage) {
                TextMessage tmsg = (TextMessage) message;
                String text = tmsg.getText();
                TextMessage sessionMessage = session.createTextMessage(workWithMessage(text));
                producer.send(sessionMessage);
                System.out.println("Send '" + sessionMessage.getText() + "'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //парсер
    public String workWithMessage(String text) throws ParserConfigurationException, IOException, SAXException {
        String responce = "";

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(text));
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();

        int index = text.indexOf("credituat_ikar_test1");
        if (index != -1) {
            NodeList nodesB = doc.getElementsByTagNameNS("soap","Body");
            String lastName = StringUtils.substringBetween(text,"lastName>","</");
            for (int i = 0; i < nodesB.getLength(); i++) {
                Element element = (Element) nodesB.item(i);
            }
            responce = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\" + \n" +
                    "\"<soapenv:Body>\" + \n" +
                    "\"<ns:openAccountResponse xmlns:ns=\"http://diasoft5nt.credit.gpb.ru\">\" + \n" +
                    "\"<ns:return xmlns:ax281=\"http://diasoft5nt.credit.gpb.ru/xsd\" xmlns:ax278=\"http://sql.java/xsd\"type=\"ru.gpb.credit.diasoft5nt.OpenAccountResponse\">\" + \n" +
                    "\"<ax281:accountNumber>AccNumber</ax281:accountNumber>\" + \n" +
                    "\"<ax281:dealID xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                    "\"<ax281:dealNumber xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                    "\"<ax281:institutionID xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                    "\"<ax281:message>\" + \n" +
                    "\"Клиент: успешно создан \"" + lastName + " Н. А.-3\". Договор: успешно создан с номером \"" + CommonData.randomAlpha(3) + " -" + CommonData.randomNumber(6) + "/" + CommonData.randomNumber(2) + "-" + CommonData.randomNumber(5) + "\"\" + \n" +
                    "\"</ax281:message>\" + \n" +
                    "\"<ax281:resourceID xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                    "\"</ns:return>\" + \n" +
                    "\"</ns:openAccountResponse>\" + \n" +
                    "\"</soapenv:Body>\" + \n" +
                    "\"</soapenv:Envelope>\" + \n";
        } else {
            NodeList nodesB = doc.getElementsByTagName("Body");
            String srcSystem = StringUtils.substringBetween(text,"srcSystem>","</");
            String firstName = StringUtils.substringBetween(text,"firstName>","</");
            String number = StringUtils.substringBetween(text,"number>","</");
            String serial = StringUtils.substringBetween(text,"serial>","</");
            String identityDocument = StringUtils.substringBetween(text,"identityDocument>","</");
            String lastName = StringUtils.substringBetween(text,"lastName>","</");
            String middleName = StringUtils.substringBetween(text,"middleName>","</");
            String srcId = StringUtils.substringBetween(text,"srcId>","</");
            for (int i = 0; i < nodesB.getLength(); i++) {
                Element element = (Element) nodesB.item(i);
            }
                responce = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\" + \n" +
                        "\"<soapenv:Body>\" + \n" +
                        "\"<ns:getAccounts6Response xmlns:ns=\"http://underwriting.gpb.ru\" xmlns:ax21=\"http://underwriting.gpb.ru/xsd\">\" + \n" +
                        "\"<ns:return type=\"ru.gpb.underwriting.AccountInfo5\">\" + \n" +
                        "\"<ax21:data type=\"ru.gpb.underwriting.AccountData\">\" + \n" +
                        "\"<ax21:closeDate>1900-01-01T01:00:00.000Z</ax21:closeDate>\" + \n" +
                        "\"<ax21:name>Фамилия Имя Отчество</ax21:name>\" + \n" +
                        "\"<ax21:openDate>2000-05-25T00:00:00.000Z</ax21:openDate>\" + \n" +
                        "\"<ax21:picture>423ХХХХХХХХХХХХХХХ</ax21:picture>\" + \n" +
                        "\"</ax21:data>\" + \n" +
                        "\"<ax21:data2 type=\"ru.gpb.underwriting.AccountData2\">\" + \n" +
                        "\"<ax21:closeDate>1900-01-01T01:00:00.000Z</ax21:closeDate>\" + \n" +
                        "\"<ax21:name> Фамилия Имя Отчество </ax21:name>\" + \n" +
                        "\"<ax21:openDate>2000-05-25T00:00:00.000Z</ax21:openDate>\" + \n" +
                        "\"<ax21:picture>42301ХХХХХХХХХХХХХХХ</ax21:picture>\" + \n" +
                        "\"<ax21:mainPicture xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                        "\"</ax21:data2>\" + \n" +
                        "\"<ax21:date xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                        "\"<ax21:message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                        "\"<ax21:srcSystem>\"" + srcSystem + "\"</ax21:srcSystem>\" + \n" +
                        "\"<ax21:versionInfo>1</ax21:versionInfo>\" + \n" +
                        "\"</ax21:data5>\" + \n" +
                        "\"<ax21:bankBic xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                        "\"<ax21:branchCode xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                        "\"<ax21:code>0</ax21:code>\" + \n" +
                        "\"<ax21:customer type=\"ru.gpb.underwriting.Customer\">\" + \n" +
                        "\"<ax21:firstName>\"" + firstName + "\"</ax21:firstName>\" + \n" +
                        "\"<ax21:identityDocument type=\"ru.gpb.underwriting.IdentityDocument\">\" + \n" +
                        "\"<ax21:number>\"" + number + "\"</ax21:number>\" + \n" +
                        "\"<ax21:serial>\"" + serial + "\"</ax21:serial>\" + \n" +
                        "\"<ax21:identityDocument>\"" + identityDocument + "\"</ax21:identityDocument>\" + \n" +
                        "\"<ax21:lastName>\"" + lastName + "\"</ax21:lastName>\" + \n" +
                        "\"<ax21:middleName>\"" + middleName + "\"</ax21:middleName>\" + \n" +
                        "\"<ax21:srcId>\"" + srcId + "\"</ax21:srcId>\" + \n" +
                        "\"<ax21:srcSystem/>\" + \n" +
                        "\"<ax21:date xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                        "\"<ax21:message xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\" + \n" +
                        "\"<ax21:srcSystem>ret_scaffold_ret_jun</ax21:srcSystem>\" + \n" +
                        "\"<ax21:versionInfo>1</ax21:versionInfo>\" + \n" +
                        "\"</ns:return>\" + \n" +
                        "\"</ns:getAccounts6Response>\" + \n" +
                        "\"</soapenv:Body>\" + \n" +
                        "\"</soapenv:Envelope>\" + \n";
            }
        return responce;
    }

    private String getCharacterDataFromElement (Element e){
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }
}
