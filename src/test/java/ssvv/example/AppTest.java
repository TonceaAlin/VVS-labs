package ssvv.example;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import domain.Student;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaFileRepository;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void tc_add_newStudent_checkName(){
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("src/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        TemaXMLRepo temaXMLRepo = new TemaXMLRepo("src/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        NotaXMLRepo notaXMLRepo = new NotaXMLRepo("src/Note.xml");
        NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        Service service = new Service(studentXMLRepo,studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        Student s1 = new Student("a1", "bb",1, "em@em.com");
        assertSame("bb", service.addStudent(s1).getNume());
    }

    @Test
    public void tc_add_newStudent_checkGroup(){
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("src/Studenti.xml");
        StudentValidator studentValidator = new StudentValidator();
        TemaXMLRepo temaXMLRepo = new TemaXMLRepo("src/Teme.xml");
        TemaValidator temaValidator = new TemaValidator();
        NotaXMLRepo notaXMLRepo = new NotaXMLRepo("src/Note.xml");
        NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        Service service = new Service(studentXMLRepo,studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);
        Student s1 = new Student("a1", "bb",1, "em@em.com");
        assertSame(1, service.addStudent(s1).getGrupa());
    }



}
