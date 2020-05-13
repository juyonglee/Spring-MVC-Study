package com.gmail.juyonglee0208;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
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
@ContextConfiguration(classes = {RootConfig.class})
@Log4j
public class DatabaseConnectionTest {
	
	@Setter(onMethod_ = @Autowired)
	private DataSource dataSource;
	
	@Test
	public void connectionPoolTest() {
		try {
			assertNotNull(dataSource);
			Connection connection =  dataSource.getConnection();
			log.info(connection);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}
	
}
