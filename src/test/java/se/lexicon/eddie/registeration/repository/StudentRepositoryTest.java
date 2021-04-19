package se.lexicon.eddie.registeration.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
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


    @DisplayName("Save student to the database")
    @Test
    public String save_student_2() throws Exception {
        String studentJsonMessage= objectMapper.writeValueAsString(student);
        System.out.println("customerJsonMessage = " + studentJsonMessage);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/student/")
                .content(studentJsonMessage)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);

        //return uuid
        return null;
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
        //get uuid and search for it
        save_student_2();
    }


}
