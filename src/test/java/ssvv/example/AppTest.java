package ssvv.example;

import domain.Student;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    String filenameStudent = "src/Studenti.xml";

    Student student;
    StudentValidator validator = new StudentValidator();
    Service service;

    @BeforeEach
    void setup(){
        student = new Student("id", "george", 1, "mail");
        service = new Service(new StudentXMLRepo(filenameStudent),
                validator,
                null,
                null,
                null,
                null
        );
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

    @Test
    void when_addStudent_idValid_thenSuccess(){
        assertDoesNotThrow(()-> service.addStudent(student));
        checkSuccessfulAdd();
    }

    @Test
    void when_addStudent_idEmpty_thenThrowException(){
        student.setID("");

        assertThrows(ValidationException.class, ()->service.addStudent(student));
        assertFalse(service.getAllStudenti().iterator().hasNext());
    }


    @Test
    void when_addStudent_idNull_thenThrowException(){
        student.setID(null);

        assertThrows(ValidationException.class, ()->service.addStudent(student));
        assertFalse(service.getAllStudenti().iterator().hasNext());
    }
    @Test
    void when_addStudent_notExistingId_thenSuccess(){
        Student student2 = new Student("newId", this.student.getNume(), this.student.getGrupa(),this.student.getEmail());

        assertDoesNotThrow(() -> service.addStudent(student2));
        assertTrue(service.getAllStudenti().iterator().hasNext());
    }


    @Test
    void when_addStudent_existingId_thenThrowException(){

        Student student2 = new Student("id", this.student.getNume(), this.student.getGrupa(),this.student.getEmail());
        service.addStudent(student);
        assertThrows(ValidationException.class, () -> service.addStudent(student2));
    }

    @Test
    void when_addStudent_validName_thenSuccess(){
        student.setNume("name");

        assertDoesNotThrow(()-> service.addStudent(student));
        checkSuccessfulAdd();
    }

    @Test
    void when_addStudent_emptyName_thenThrowException(){
        student.setNume("");

        assertThrows(ValidationException.class, ()->service.addStudent(student));
        assertFalse(service.getAllStudenti().iterator().hasNext());
    }

    @Test
    void when_addStudent_nullName_thenThrowException(){
        student.setNume(null);

        assertThrows(ValidationException.class, ()->service.addStudent(student));
        assertFalse(service.getAllStudenti().iterator().hasNext());
    }

    @Test
    void when_addStudent_validMail_thenSuccess(){
        student.setEmail("mail");

        assertDoesNotThrow(()-> service.addStudent(student));
        checkSuccessfulAdd();
    }

    @Test
    void when_addStudent_emptyMail_thenThrowException(){
        student.setEmail("");

        assertThrows(ValidationException.class, ()->service.addStudent(student));
        assertFalse(service.getAllStudenti().iterator().hasNext());
    }

    @Test
    void when_addStudent_nullMail_thenThrowException(){
        student.setEmail(null);

        assertThrows(ValidationException.class, ()->service.addStudent(student));
        assertFalse(service.getAllStudenti().iterator().hasNext());
    }

    @Test
    void when_addStudent_validGroup_thenSuccess(){
        student.setGrupa(13);

        assertDoesNotThrow(()-> service.addStudent(student));
        checkSuccessfulAdd();
    }

    @Test
    void when_addStudent_zeroGroup_thenThrowException(){
        student.setGrupa(0);

        assertThrows(ValidationException.class, ()->service.addStudent(student));
        assertFalse(service.getAllStudenti().iterator().hasNext());
    }

    @Test
    void when_addStudent_negativeGroup_thenThrowException(){
        student.setGrupa(-2);

        assertThrows(ValidationException.class, ()->service.addStudent(student));
        assertFalse(service.getAllStudenti().iterator().hasNext());
    }

    void checkSuccessfulAdd(){
        Iterator<Student> students = service.getAllStudenti().iterator();
        Student student = students.next();
        assertFalse(students.hasNext());
        checkStudent(student);
    }

    void checkStudent(Student student){
        assertEquals(this.student.getID(), student.getID());
        assertEquals(this.student.getNume(), student.getNume());
        assertEquals(this.student.getEmail(), student.getEmail());
        assertEquals(this.student.getGrupa(), student.getGrupa());
    }
}
