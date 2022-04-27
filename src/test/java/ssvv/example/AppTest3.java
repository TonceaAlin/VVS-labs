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
public class AppTest3 {

    String filenameStudent = "src/Studenti3.xml";
    String filenameTema = "src/Teme3.xml";
    String filenameNota = "src/Note3.xml";
    StudentXMLRepo studentXMLRepo= new StudentXMLRepo(filenameStudent);
    TemaXMLRepo temaXMLRepo = new TemaXMLRepo(filenameTema);
    NotaXMLRepo notaXMLRepo = new NotaXMLRepo(filenameNota);
    StudentValidator validator = new StudentValidator();
    TemaValidator temaValidator = new TemaValidator();
    NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
    Service service = new Service(studentXMLRepo, validator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

    @Test
    void addStudent_newStudent_addedSuccess(){
        Student student = new Student("ida", "stud", 111, "email");
        assertDoesNotThrow(() -> this.service.addStudent(student));
    }

    @Test
    void addStudent_integration_addTema(){
        Student student = new Student("idb", "stud", 111, "email");
        assertDoesNotThrow(() -> this.service.addStudent(student));
        Tema tema = new Tema("idt", "descriere", 9, 3);
        assertDoesNotThrow(() -> this.service.addTema(tema));
    }

    @Test
    void addStudent_integration_addTema_integration_addNota(){
        Student student = new Student("idcc", "stud", 111, "email");
        assertDoesNotThrow(() -> this.service.addStudent(student));
        Tema tema = new Tema("idte1", "descriere", 7, 2);
        assertDoesNotThrow(() -> this.service.addTema(tema));
        Nota nota = new Nota("idn", "idcc", "idte1",9, LocalDate.now());
        assertDoesNotThrow(() -> this.service.addNota(nota, "Good JOB!"));
    }

    @AfterEach
    void cleanup(){
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .newDocument();
            Element root  = document.createElement("inbox");
            document.appendChild(root);

            Transformer transformer = TransformerFactory.
                    newInstance().newTransformer();
            transformer.transform(new DOMSource(document),
                    new StreamResult(filenameStudent));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
