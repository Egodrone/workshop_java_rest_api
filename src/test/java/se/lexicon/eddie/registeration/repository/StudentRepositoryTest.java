package se.lexicon.eddie.registeration.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import se.lexicon.eddie.registeration.entity.Student;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentRepositoryTest {
    private Student student;
    private ObjectMapper objectMapper;


    @Autowired
    private MockMvc mockMvc;

    private Student savedStudent;

    @BeforeEach
    public void setup() throws Exception {
        objectMapper= new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        student = new Student();
        student.setAge(43);
        student.setEmail("joe@gmail.com");
        student.setFirstName("Joe");
        student.setLastName("Watson");
        student.setGender("Male");
        student.setPhoneNumber("767676876");

        String studentJsonMessage= objectMapper.writeValueAsString(student);
        System.out.println("customerJsonMessage = " + studentJsonMessage);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/student/")
                .content(studentJsonMessage)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        ).andReturn();

        String responseJsonMessage = mvcResult.getResponse().getContentAsString();
        savedStudent = objectMapper.readValue(responseJsonMessage, new TypeReference<Student>() {});
    }


    @DisplayName("Save student to the database")
    @Test
    public void save_student() throws Exception {
        String studentJsonMessage= objectMapper.writeValueAsString(student);
        System.out.println("customerJsonMessage = " + studentJsonMessage);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/student/")
                .content(studentJsonMessage)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
    }


    @DisplayName("Find all students from the database")
    @Test
    public void find_students() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/student/")
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }


    @DisplayName("Find student by UUID")
    @Test
    public void find_student_by_uuid() throws Exception {
        System.out.println(savedStudent.getId());

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/student/" + savedStudent.getId())
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
    }


}
