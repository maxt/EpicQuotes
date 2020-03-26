package ru.maxt.epic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.maxt.epic.dto.QuoteDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class ValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void invalidIsinSizeTest() throws Exception {
        QuoteDto quote = new QuoteDto("USDRUB", 79, 80);
        QuotesApplicationTests.testPostQuote(mockMvc,quote, 400);
    }

    @Test
    public void correctIsinSizeTest() throws Exception {
        QuoteDto quote = new QuoteDto("012345678910", 80, 81);
        QuotesApplicationTests.testPostQuote(mockMvc,quote, 200);
    }

    @Test
    public void bidMoreThanAskTest() throws Exception {
        QuoteDto quote = new QuoteDto("012345678910", 81, 80);
        QuotesApplicationTests.testPostQuote(mockMvc,quote, 400);
    }


}
