package edu.coldrain.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import edu.coldrain.domain.Ticket;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" // 컨트롤러 테스트를 위해서 추가한다.
})
@WebAppConfiguration // 컨트롤러 테스트를 위해서 추가한다.
@Log4j
public class SampleControllerTests {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testConvert() throws Exception {
		
		Ticket ticket = new Ticket();
		ticket.setTno(120);
		ticket.setOwner("admin");
		ticket.setGrade("AAA");
		
		//Gson 라이브러리는 JAVA의 객체를 JSON 문자열로 변환한다.
		String jsonString = new Gson().toJson(ticket);
		
		log.info("JSON_STRING = " + jsonString);
		
		//JSON으로 전달되는 데이터를 받아서 Ticket 타입으로 변환한다.
		mockMvc.perform(MockMvcRequestBuilders.post("/sample/ticket")
				.contentType(MediaType.APPLICATION_JSON) //전달되는 데이터가 JSON임을 명시한다.
				.content(jsonString)) //JSON 문자열을 넘겨주면 컨트롤러에 @RequestBody가 자바 객체로 변환한다.
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
}
