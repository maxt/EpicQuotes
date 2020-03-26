package ru.maxt.epic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.web.servlet.MockMvc;
import ru.maxt.epic.dto.QuoteDto;
import ru.maxt.epic.repository.InstrumentRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuotesApplicationTests {

	private Logger log = LoggerFactory.getLogger(QuotesApplicationTests.class);

	@Autowired
	private InstrumentRepository instrRepo;
	@Autowired
	private MockMvc mockMvc;







}
