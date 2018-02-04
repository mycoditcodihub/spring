package com.ibeka.spring5recipeapp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class) //This is really an integration test rather than a unit test. This loads the Spring Context, so if we run this test and it fails, it means that it failed to load the Spring Context. When the test runs successfully, it loads the Spring Context and then shuts down.
@SpringBootTest
public class Spring5RecipeAppApplicationTests {

	@Test
	public void contextLoads() {
	}

}
