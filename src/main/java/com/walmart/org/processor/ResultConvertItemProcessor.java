package com.walmart.org.processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.batch.item.ItemProcessor;

import com.walmart.org.model.Resultado;

public class ResultConvertItemProcessor implements ItemProcessor<Resultado, Resultado>  {
	
		@Override
		public Resultado process(Resultado item) throws Exception {
			
			boolean isFloat = true;
			String UserScore = item.getUserScore();
			
			 try{
			        Float.parseFloat(UserScore);
			        
			    }catch(NumberFormatException e){
			    	isFloat = false;
			    }
			if(isFloat)
			{
				int metascore = Integer.parseInt(item.getMetascore());
				String Name = item.getName();
				String Console = item.getConsole_id();
				
				Float UserScoreFloat = Float.parseFloat(item.getUserScore());
				
				String Fecha = item.getFecha();
				SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy" , Locale.ENGLISH); 
				Date date = sdf.parse(Fecha);  
				
				Resultado _result = new Resultado(0, Name, Console, metascore,  UserScoreFloat, date,  "");
				
		        return _result;
			}
			return null;
			
		}
}

