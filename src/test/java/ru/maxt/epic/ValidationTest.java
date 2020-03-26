package ru.maxt.epic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.maxt.epic.dto.QuoteDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationTest {

    @Autowired
    private MockMvc mockMvc;

    private void testInput(QuoteDto quote, int status) throws Exception {
        ObjectMapper om = new ObjectMapper();
        String jsonQuote = om.writeValueAsString(quote);
        mockMvc.perform(post("/quote").content(jsonQuote).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(status));
    }

    @Test
    public void invalidIsinSizeTest() throws Exception {
        QuoteDto quote = new QuoteDto("USDRUB", 79, 80);
        testInput(quote, 400);
    }

    @Test
    public void correctIsinSizeTest() throws Exception {
        QuoteDto quote = new QuoteDto("012345678910", 80, 81);
        testInput(quote, 200);
    }

    @Test
    public void bidMoreThanAskTest() throws Exception {
        QuoteDto quote = new QuoteDto("012345678910", 81, 80);
        testInput(quote, 400);
    }


}
