package ru.javarush.client;

// нужно, чтобы получить wsdl описание и через него
// дотянуться до самого веб-сервиса
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
// такой эксепшн возникнет при работе с объектом URL
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.Base64;

// классы, чтобы пропарсить xml-ку c wsdl описанием
// и дотянуться до тега service в нем
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

// интерфейс нашего веб-сервиса (нам больше и нужно)
import ru.javarush.ws.InterfaceConnect;

public class HelloWebServiceClient {

    public static void main(String[] args) throws IOException {
//        // создаем ссылку на wsdl описание
//        URL url = new URL("http://localhost:1986/wss/hello?wsdl");
//
//        // Параметры следующего конструктора смотрим в самом первом теге WSDL описания - definitions
//        // 1-ый аргумент смотрим в атрибуте targetNamespace
//        // 2-ой аргумент смотрим в атрибуте name
//        QName qname = new QName("http://ws.javarush.ru/", "HelloWebServiceImplService");
//
//        // Теперь мы можем дотянуться до тега service в wsdl описании,
//        Service service = Service.create(url, qname);
//        // а далее и до вложенного в него тега port, чтобы
//        // получить ссылку на удаленный от нас объект веб-сервиса
//        HelloWebService hello = service.getPort(HelloWebService.class);
//
//        // Ура! Теперь можно вызывать удаленный метод
//        System.out.println(hello.getHelloString("JavaRush"));


          //Подключаемся к 1С
          // создаем ссылку на wsdl описание
          URL url = new URL("http://localhost/Dok/ws/ws1.1cws?wsdl");


          String authString = "Павлова Анастасия" + ":" + "";

          byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
          String authStringEnc = new String(authEncBytes);

          URLConnection urlConnection = url.openConnection();
          urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
          InputStream is = urlConnection.getInputStream();
          InputStreamReader isr = new InputStreamReader(is);


          QName qname = new QName("http://localhost/Dok/", "WebAgent");

          Service service = Service.create(url, qname);

          InterfaceConnect hello = service.getPort(InterfaceConnect.class);

          System.out.println(hello.CreatAgent());
    }

}
