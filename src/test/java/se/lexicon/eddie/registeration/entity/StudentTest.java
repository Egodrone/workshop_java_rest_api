package se.lexicon.eddie.registeration.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentTest {
    private Student student;
    private ObjectMapper objectMapper;


    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setup(){
        objectMapper= new ObjectMapper();

        student = new Student();
        student.setAge(43);
        student.setEmail("joe@gmail.com");
        student.setFirstName("Joe");
        student.setLastName("Watson");
        student.setGender("Male");
        student.setPhoneNumber("767676876");
    }


    @DisplayName("Display JSON for the student entity")
    @Test
    public void json_student_display() throws JsonProcessingException {
        // just to display JSON
        String studentJsonMessage= objectMapper.writeValueAsString(student);
        System.out.println("studentJsonMessage = " + studentJsonMessage);
    }


    @DisplayName("Test getters")
    @Test
    public void student_getters() {
        Assertions.assertEquals(43, student.getAge());
        Assertions.assertEquals("joe@gmail.com", student.getEmail());
        Assertions.assertEquals("joe", student.getFirstName().toLowerCase(Locale.ROOT));
        Assertions.assertEquals("watson", student.getLastName().toLowerCase(Locale.ROOT));
        Assertions.assertEquals("male", student.getGender().toLowerCase(Locale.ROOT));
        Assertions.assertEquals("767676876", student.getPhoneNumber());
    }


}
