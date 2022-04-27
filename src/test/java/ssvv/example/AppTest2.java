package ssvv.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest2 {
    String filenameStudent = "src/Studenti2.xml";
    String filenameTema = "src/Teme2.xml";
    String filenameNota = "src/Note2.xml";
    StudentXMLRepo studentXMLRepo= new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filenameTema);
    NotaXMLRepo notaXMLRepo = new NotaXMLRepo(filenameNota);
    StudentValidator validator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
    Service service = new Service(studentXMLRepo, validator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);


    @Test
    void addStudent_newStudent_added(){
        Student student = new Student("ida", "numea", 99, "emaila");
        assertDoesNotThrow(() -> this.service.addStudent(student));
    }

    @Test
    void addTema_newTema_added(){
        Tema tema = new Tema("ida", "descr", 7, 2);
        assertDoesNotThrow(() -> this.service.addTema(tema));
    }

    @Test
    void addNota_newNota_added(){
        Nota nota = new Nota("ida", "ida", "ida", 8.5, LocalDate.now());
        assertDoesNotThrow(()-> this.service.addNota(nota, "bravo"));
    }

    @Test
    void createNewStudentAndTema_addNota_added(){
        Student student = new Student("idb", "numea", 99, "emaila");
        assertDoesNotThrow(() -> this.service.addStudent(student));
        Tema tema = new Tema("idb", "descr", 7, 2);
        assertDoesNotThrow(() -> this.service.addTema(tema));
        Nota nota = new Nota("idb", "idb", "idb", 8.5, LocalDate.now());
        assertDoesNotThrow(() -> this.service.addNota(nota, "gg"));
    }

//    @AfterEach
//    void cleanup(){
//        try {
//            Document document = DocumentBuilderFactory
//                    .newInstance()
//                    .newDocumentBuilder()
//                    .newDocument();
//            Element root  = document.createElement("inbox");
//            document.appendChild(root);
//
//            Transformer transformer = TransformerFactory.
//                    newInstance().newTransformer();
//            transformer.transform(new DOMSource(document),
//                    new StreamResult(filenameStudent));
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }

}
