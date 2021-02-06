package com.walmart.org.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.walmart.org.model.Company;
import com.walmart.org.model.Console;
import com.walmart.org.model.NameGame;
import com.walmart.org.model.ResultadoConvert;


@Component
public class JobListener extends JobExecutionListenerSupport {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JobListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("FINALIZÓ EL JOB!! Verifica los resultados:");
			
			jdbcTemplate
			.query("SELECT Company_ID, NameCompany FROM Company",
					(rs, row) -> new Company(rs.getInt(1), rs.getString(2)))
			.forEach(_Company -> LOG.info("Company < " + _Company + " >"));

			jdbcTemplate
					.query("SELECT ConsoleID, NameConsole, CompanyID FROM Console",
							(rs, row) -> new Console(rs.getInt(1), rs.getString(2) , rs.getString(3) ))
					.forEach(_console -> LOG.info("Console < " + _console + " >"));
			
		
			jdbcTemplate
			.query("SELECT top 10  NameGameID, NameGame FROM Game",
					(rs, row) -> new NameGame(rs.getInt(1), rs.getString(2) ))
			.forEach(_NameGame -> LOG.info("Game < " + _NameGame + " >"));
			

			jdbcTemplate
			.query("SELECT top 10  ResultID, Metascore, NameGame_ID, Console_ID, Userscore, Fecha FROM Result ",
					(rs, row) -> new ResultadoConvert(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6)  ))
			.forEach(ResultadoConvert -> LOG.info("Result < " + ResultadoConvert + " >"));
			
		}
	}

}

