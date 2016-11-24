package com.demorest;

import com.demorest.entity.Artist;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArtistEndPointTests {

	@LocalServerPort
	int randomServerPort;

	@MockBean
	RestTemplate template;

	@Test
	public void artistsIsNotEmpty() {
		List<Artist> mockList = new ArrayList<>();
		Artist artist1 = new Artist(1,"Mockito","Test",999, LocalDateTime.now());
		Artist artist2 = new Artist(2,"Mockito","Test",999, LocalDateTime.now());
		Artist artist3 = new Artist(3,"Mockito","Test",999, LocalDateTime.now());
		Artist artist4 = new Artist(4,"Mockito","Test",999, LocalDateTime.now());
		mockList.add(artist1);
		mockList.add(artist2);
		mockList.add(artist3);
		mockList.add(artist4);
		Mockito.when(
				template.getForObject(
						Matchers.anyString(),
						Matchers.eq(List.class)
				)
		).thenReturn(mockList);

		List<Artist> list = this.template.getForObject("http://localhost:"+randomServerPort+"/artists", List.class);
		assertThat(list).contains(artist1,artist2,artist3,artist4);
	}

	@Test
	public void artistsIsEmpty() {
		List<Artist> mockList = new ArrayList<>();
		Mockito.when(
				template.getForObject(
						Matchers.anyString(),
						Matchers.eq(List.class)
				)
		).thenReturn(mockList);

		List<Artist> list = this.template.getForObject("http://localhost:"+randomServerPort+"/artists", List.class);
		assertThat(list).isEmpty();
	}

	@Test
	public void artistIsRightEntity() {
		Artist a = new Artist(1,"Mockito","Test",1,LocalDateTime.now());
		ResponseEntity<Artist> artist = new ResponseEntity<Artist>(a, HttpStatus.OK);
		Mockito.when(
				template.getForEntity(
						Matchers.anyString(),
						Matchers.eq(Artist.class)
				)
		).thenReturn(artist);

		ResponseEntity<Artist> entity = this.template.getForEntity("http://localhost:"+randomServerPort+"/artists/1", Artist.class);
		assertThat(entity.getStatusCode().is2xxSuccessful());
		assertThat(entity.getBody()).isExactlyInstanceOf(Artist.class);
	}
}
