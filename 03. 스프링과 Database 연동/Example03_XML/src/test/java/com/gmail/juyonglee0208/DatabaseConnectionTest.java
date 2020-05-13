package com.gmail.juyonglee0208;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DatabaseConnectionTest {
	
	@Setter(onMethod_ = @Autowired)
	private DataSource datasource;
	
	@Test
	public void connectionTest() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "juyonglee", "1234");
			log.info("-----------------------");
			log.info(connection);
			log.info("-----------------------");
		} catch (SQLException e) {
			fail();
			log.error(e.getMessage());
		}
	}
	
	@Test
	public void connectionPoolTest() {
		try {
			Connection connection = datasource.getConnection();
			log.info("-----------------------");
			log.info("[Connection Pool Test]");
			log.info(connection);
			log.info("-----------------------");
		} catch (SQLException e) {
			fail(e.getMessage());
		}	
	}
}
