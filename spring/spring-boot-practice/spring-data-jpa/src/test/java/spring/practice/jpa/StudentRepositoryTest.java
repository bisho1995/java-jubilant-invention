package spring.practice.jpa;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.practice.jpa.entities.Student;
import spring.practice.jpa.repositories.StudentRepository;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveStudent(){
        Student student1 = Student.builder().id(1L).name("testName1").testScore(100).build();
        studentRepository.save(student1);

        Optional<Student> student = studentRepository.findById(1L);
        Assert.assertEquals(true, student.isPresent());
        Assert.assertEquals(student1.toString(), student.get().toString());
    }
}