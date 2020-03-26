package ru.maxt.epic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.maxt.epic.dto.QuoteDto;
import ru.maxt.epic.entity.Quote;
import ru.maxt.epic.repository.InstrumentRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuotesApplicationTests {

	private Logger log = LoggerFactory.getLogger(QuotesApplicationTests.class);

	@Autowired
	private InstrumentRepository instrRepo;
	@Autowired
	private MockMvc mockMvc;

	public static  void testPostQuote(MockMvc mockMvc, QuoteDto quote, int status) throws Exception {
		ObjectMapper om = new ObjectMapper();
		String jsonQuote = om.writeValueAsString(quote);
		mockMvc.perform(post("/quote").content(jsonQuote).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(status));
	}

	public static void testGetElvls(MockMvc mockMvc, int status,String result) throws Exception{
		mockMvc.perform(get("/elvls")).andExpect(status().is(status))
				.andExpect(content().json(result));
	}

	public static void testGetElvl(MockMvc mockMvc,String isin, int status,String body) throws Exception{
		mockMvc.perform(get("/elvl").param("isin",isin))
				.andExpect(status().is(status))
				.andExpect(content().string(body));
	}

	@Test
	public void testAllRestEndpoints() throws Exception {
		QuoteDto instr1quote1 = new QuoteDto("012345678910", 80, 81);
		QuoteDto instr2quote1 = new QuoteDto("012345678911",79,80);
		QuoteDto instr3quote1 = new QuoteDto("012345678912",28,56);
		testPostQuote(mockMvc,instr1quote1, 200);						//rule 3
		testPostQuote(mockMvc,instr2quote1,200);							//rule 3
		testPostQuote(mockMvc,instr3quote1,200);							//rule 3
		//todo: compare as json's disregarding order and formatting
		String expectedElvls = "{\"012345678911\":79.0,\"012345678910\":80.0,\"012345678912\":28.0}";
		testGetElvls(mockMvc,200,expectedElvls);


		QuoteDto instr1quote2 = new QuoteDto("012345678910",84,85);
		testPostQuote(mockMvc,instr1quote2,200);

		testGetElvl(mockMvc,"012345678910",200,"84.0");			//rule 1

		QuoteDto instr1quote3 = new QuoteDto("012345678910",60,65);
		testPostQuote(mockMvc,instr1quote3,200);
		testGetElvl(mockMvc,"012345678910",200,"65.0");			//rule 2

		QuoteDto instr4quote1 = new QuoteDto("012345678919",0,15);
		testPostQuote(mockMvc,instr4quote1,200);
		testGetElvl(mockMvc,"012345678919",200,"15.0");		//rule 4 (considering NO BID as bid==0)

	}






}
