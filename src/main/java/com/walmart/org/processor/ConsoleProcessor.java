package com.walmart.org.processor;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.walmart.org.model.Console;

public class ConsoleProcessor implements ItemProcessor<Console, Console>  {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsoleProcessor.class);
	
	private static ArrayList<String> _consoleArray= new ArrayList<String>();

	private String company; 
	// @Override
	public Console process(Console item) throws Exception {
		company = item.getCompany();
	
		if(!_consoleArray.contains(company))
		{	
			_consoleArray.add(item.getCompany());
			
			LOG.info("Inserting Company :" + item.getCompany());
			Console _console = new Console(0,"",company);
			return _console;
		};
        return null;

	}

}
