package com.walmart.org.processor;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.walmart.org.model.Resultado;


public class TESTPROCESSOR implements ItemProcessor<Resultado, Resultado> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ConsoleProcessor.class);
	
	private static ArrayList<String> _consoleArray= new ArrayList<String>();

	private String company; 
	
	// @Override
	public Resultado process(Resultado item) throws Exception {
		company = item.getName();
        return null;

	}

}
