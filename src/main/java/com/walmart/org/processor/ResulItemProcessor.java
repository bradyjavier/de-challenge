package com.walmart.org.processor;

import java.util.ArrayList;
import org.springframework.batch.item.ItemProcessor;
import com.walmart.org.model.Resultado;

public class ResulItemProcessor implements ItemProcessor<Resultado, Resultado>  {
	
	private static ArrayList<String> _ResultArray= new ArrayList<String>();

	private String NameGame; 
	
	// @Override
	public Resultado process(Resultado item) throws Exception {
		NameGame = item.getName();
	
		if(!_ResultArray.contains(NameGame))
		{	
			_ResultArray.add(item.getName());
			
			Resultado _result = new Resultado(0,"",NameGame ,"","","");
			return _result;
		};
		
        return null;

	}


}
