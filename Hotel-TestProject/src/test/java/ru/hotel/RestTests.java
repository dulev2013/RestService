package ru.hotel;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.hotel.DTO.HotelDTO;
import ru.hotel.controllers.PropertiesHotelServerUri;
import ru.hotel.service.IHotelService;
import ru.hotel.utility.StringSequenceIdentifier;
import ru.hotel.utility.Utility;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Класс тестирует методы рест-контроллера
 * @author dulev
 *
 */
//Тестирование на корректность входных параметров сущностей реализуется в JPATests.
//В данном классе тесты входящих параметров(HotelDTO) представлены не все
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class RestTests {

	private MockMvc mockMvc;	
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private IHotelService service;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private String randomKey = new StringSequenceIdentifier().generateVarLength(12);
	
	@Before
	public void prepare() {
		service.deleteAll();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
	}

			
	@Test
	public void тест_добавления_нового_отеля() throws Exception {
		HotelDTO hotel = Utility.instanceHotelDTO();
		String jsonStr = mapper.writeValueAsString(hotel);				
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post(PropertiesHotelServerUri.mappingBase + PropertiesHotelServerUri.add).
				contentType(MediaType.APPLICATION_JSON).content(jsonStr);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		String resStr = result.getResponse().getContentAsString(); 
		assertThat(service.findById(resStr), notNullValue());
		service.deleteAll();
	}
	
	@Test
	public void тест_редактирования_существуещего_отеля() throws Exception {
		HotelDTO hotel = Utility.instanceHotelDTO();
		String newName = "Отель1";		
		String id = service.add(hotel);
		assertThat(id.isEmpty(), equalTo(false));
		hotel.setId(id);
		hotel.setName(newName);
		String jsonStr = mapper.writeValueAsString(hotel);				
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.put(PropertiesHotelServerUri.mappingBase + PropertiesHotelServerUri.update).
				contentType(MediaType.APPLICATION_JSON).content(jsonStr);
		mockMvc.perform(builder).andExpect(status().isOk());
		hotel = service.findById(hotel.getId());
		assertThat(hotel, notNullValue());
		assertThat(hotel.getName(), equalTo(newName));
		service.deleteAll();
	}
	
	@Test
	public void тест_поиска_отеля_по_идентификатору() throws Exception {
		HotelDTO hotel = Utility.instanceHotelDTO();	
		String id = service.add(hotel);
		assertThat(id.isEmpty(), equalTo(false));	
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get(PropertiesHotelServerUri.mappingBase + PropertiesHotelServerUri.findById).
				contentType(MediaType.APPLICATION_JSON).param("id", id);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		String resStr = result.getResponse().getContentAsString();
		hotel = mapper.readValue(resStr, HotelDTO.class);
		assertThat(hotel, instanceOf(HotelDTO.class));
		service.deleteAll();
	}
	
	@Test
	public void тест_удаления_отеля() throws Exception {
		HotelDTO hotel = Utility.instanceHotelDTO();	
		String id = service.add(hotel);
		assertThat(id.isEmpty(), equalTo(false));
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.delete(PropertiesHotelServerUri.mappingBase + PropertiesHotelServerUri.delete).
				contentType(MediaType.APPLICATION_JSON).param("id", id);
		mockMvc.perform(builder).andExpect(status().isOk());		
		assertThat(service.findById(id), nullValue());
	}

	@Test
	public void тест_поиска_несуществуещего_отеля() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.get(PropertiesHotelServerUri.mappingBase + PropertiesHotelServerUri.findById).
				contentType(MediaType.APPLICATION_JSON).param("id", randomKey);
		mockMvc.perform(builder).andExpect(status().isNotFound());	
	}
	
	@Test
	public void тест_редактирования_несуществуещего_отеля() throws Exception {
		HotelDTO hotel = Utility.instanceHotelDTO();
		hotel.setId(randomKey);
		String jsonStr = mapper.writeValueAsString(hotel);				
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.put(PropertiesHotelServerUri.mappingBase + PropertiesHotelServerUri.update).
				contentType(MediaType.APPLICATION_JSON).content(jsonStr);
		mockMvc.perform(builder).andExpect(status().isNotFound());
	}
	
	@Test
	public void тест_удаления_несуществуещего_отеля() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.delete(PropertiesHotelServerUri.mappingBase + PropertiesHotelServerUri.delete).
				contentType(MediaType.APPLICATION_JSON).param("id", randomKey);
		mockMvc.perform(builder).andExpect(status().isNotFound());
	}

}
