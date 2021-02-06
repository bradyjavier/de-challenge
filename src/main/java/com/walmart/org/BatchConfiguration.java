package com.walmart.org;

import java.io.IOException;
import java.io.Writer;

import javax.sql.DataSource;
import java.lang.String;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import com.walmart.org.listener.JobListener;
import com.walmart.org.model.BestGame;
import com.walmart.org.model.Console;
import com.walmart.org.model.Resultado;
import com.walmart.org.processor.ConsoleProcessor;
import com.walmart.org.processor.ResulItemProcessor;
import com.walmart.org.processor.ResultConvertItemProcessor;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public DataSource dataSource;
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	private Resource FileConsole = new FileSystemResource("data/consoles.csv");
	private Resource FileResult= new FileSystemResource("data/result.csv");
	private Resource Top10BestConsoles = new FileSystemResource("data/Top_10_best_games_for_all_consoles.csv");
	private Resource Top10worstConsoles = new FileSystemResource("data/Top_10_worst_games_for_all_consoles.csv");
	private Resource BestGameForConsole = new FileSystemResource("data/Best_games_for_consoles.csv");
	private Resource WorsrGameForConsole = new FileSystemResource("data/Worst_games_for_consoles.csv");
	
	@Bean
	public FlatFileItemReader<Console> readerFileConsole(){
		return new FlatFileItemReaderBuilder<Console>()
			   .name("ConsoleItemReader")
			   .resource(FileConsole)
			   .delimited()
			   .names(new String[] {"NameConsole", "Company"})
			   .fieldSetMapper(new BeanWrapperFieldSetMapper<Console>() {{
				   setTargetType(Console.class);
			   }})
			   .linesToSkip(1)
			   .build();
	}
	
	@Bean
	public FlatFileItemReader<Resultado> readerFileResutl(){
		return new FlatFileItemReaderBuilder<Resultado>()
			   .name("ConsoleItemReader")
			   .resource(FileResult)
			   .delimited()
			   .names(new String[] {"metascore", "Name", "Console_id", "UserScore","Fecha"})
			   .fieldSetMapper(new BeanWrapperFieldSetMapper<Resultado>() {{
				   setTargetType(Resultado.class);
			   }})
			   .linesToSkip(1)
			   .build();
	}
	
	@Bean
	public ConsoleProcessor processorLoadCompany() {
		return new ConsoleProcessor();
	}
	
	@Bean
	public ItemReader<BestGame> itemReaderFileBestAllConsole(DataSource dataSource) {
	        return new JdbcCursorItemReaderBuilder<BestGame>()
	                .name("cursorItemReader")
	                .dataSource(dataSource)
	                .sql("SELECT TOP 10 Metascore, NameGame, NameConsole, Userscore, Fecha  FROM Result "
	                		+ "INNER JOIN Console ON "
	                		+ "Result.Console_ID = Console.ConsoleID "
	                		+ "INNER JOIN Game ON "
	                		+ "Result.NameGame_ID = Game.NameGameID "
	                		+ "ORDER BY Metascore DESC, Userscore DESC "
	                		)
	                .rowMapper(new BeanPropertyRowMapper<>(BestGame.class))
	                .build();
	 }
	
	@Bean
	public ItemReader<BestGame> itemReaderFileWorstAllConsole(DataSource dataSource) {

	        return new JdbcCursorItemReaderBuilder<BestGame>()
	                .name("cursorItemReader")
	                .dataSource(dataSource)
	                .sql("SELECT TOP 10 Metascore, NameGame, NameConsole, Userscore, Fecha FROM Result "
	                		+ "INNER JOIN Console ON "
	                		+ "Result.Console_ID = Console.ConsoleID "
	                		+ "INNER JOIN Game ON "
	                		+ "Result.NameGame_ID = Game.NameGameID "
	                		+ "ORDER BY Metascore ASC , Userscore ASC "
	                		)
	                .rowMapper(new BeanPropertyRowMapper<>(BestGame.class))
	                .build();
	 }
	
	@Bean
	public ItemReader<BestGame> itemReaderFileBestForConsole(DataSource dataSource) {

	        return new JdbcCursorItemReaderBuilder<BestGame>()
	                .name("cursorItemReader")
	                .dataSource(dataSource)
	                .sql("SELECT Metascore, NameGame, NameConsole,NameCompany, Userscore, Fecha  FROM ("
	                		+ "SELECT Metascore, NameGame, NameConsole, Userscore, Fecha, NameCompany, "
	                		+ "(Select 1 + Count(*) From V_RESULT_BEST  Where Console_ID = A.Console_ID AND RN < A.RN) RANK FROM  V_RESULT_BEST A ) "
	                		+ "WHERE RANK<=10 ORDER BY NameConsole DESC, Metascore DESC"
	                		)
	            
	                .rowMapper(new BeanPropertyRowMapper<>(BestGame.class))
	                .build();
	 }
	
	@Bean
	public ItemReader<BestGame> itemReaderFileWorstForConsole(DataSource dataSource) {

	        return new JdbcCursorItemReaderBuilder<BestGame>()
	                .name("cursorItemReader")
	                .dataSource(dataSource)
	                .sql("SELECT Metascore, NameGame, NameConsole,NameCompany, Userscore, Fecha  FROM ("
	                		+ "SELECT Metascore, NameGame, NameConsole, Userscore, Fecha, NameCompany, "
	                		+ "(Select 1 + Count(*) From V_RESULT_WORST  Where Console_ID = A.Console_ID AND RN < A.RN) RANK FROM  V_RESULT_WORST A ) "
	                		+ "WHERE RANK<=10 ORDER BY NameConsole DESC, Metascore ASC"
	                		)
	            
	                .rowMapper(new BeanPropertyRowMapper<>(BestGame.class))
	                .build();
	 }
	
	@Bean
	public ResulItemProcessor processorLoadGame() {
		return new ResulItemProcessor();
	}
	
	@Bean
	public ResultConvertItemProcessor ProcessorResult() {
		return new ResultConvertItemProcessor();
	}
	
	@Bean
	public JdbcBatchItemWriter<Console> writerLoadCompany(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Console>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO Company (NameCompany) VALUES (:Company)")
				.dataSource(dataSource)
				.build();
	} 
	
	@Bean
	public JdbcBatchItemWriter<Console> writerLoadConsole(DataSource dataSource){
		JdbcBatchItemWriter<Console> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO Console (NameConsole, CompanyID) VALUES (:NameConsole, "
				+ "(SELECT Company_ID FROM Company WHERE NameCompany = :Company) ) ");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return itemWriter;
	} 
	
	@Bean
	public FlatFileItemWriter<BestGame> writerFileBestAllConsole() 
	    {
	        FlatFileItemWriter<BestGame> writer = new FlatFileItemWriter<>();
	  
	        writer.setResource(Top10BestConsoles);
	        writer.setAppendAllowed(false);
	        writer.setLineAggregator(new DelimitedLineAggregator<BestGame>() {
	            {
	                setDelimiter(",");
	                setFieldExtractor(new BeanWrapperFieldExtractor<BestGame>() {
	                    {
	                        setNames(new String[] {"NameConsole","NameGame","Metascore", "Userscore", "Fecha"});
	                    }
	                });
	            }
	        });
	        
	        
	        writer.setHeaderCallback(new FlatFileHeaderCallback() {
				@Override
				public void writeHeader(Writer writer) throws IOException {
					 writer.write("NameConsole,NameGame,Metascore,Userscore,Fecha");
					
				}
	        });
	        return writer;
	    }
	
	public FlatFileItemWriter<BestGame> writerFileWorstConsole() 
    {
        FlatFileItemWriter<BestGame> writer = new FlatFileItemWriter<>();
  
        writer.setResource(Top10worstConsoles);
        writer.setAppendAllowed(false);
        writer.setLineAggregator(new DelimitedLineAggregator<BestGame>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<BestGame>() {
                    {
                        setNames(new String[] {"NameConsole","NameGame","Metascore", "Userscore", "Fecha" });
                    }
                });
            }
        });
        
        
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				 writer.write("NameConsole,NameGame,Metascore,Userscore,Fecha");
				
			}
        });
        
        return writer;
    }
	
	@Bean
	public JdbcBatchItemWriter<Resultado> writerLoadNameGame(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<Resultado>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO Game (NameGame) VALUES (:Name)")
				.dataSource(dataSource)
				.build();
	} 
	
	@Bean
	public JdbcBatchItemWriter<Resultado> writerLoadResult(DataSource dataSource){

		JdbcBatchItemWriter<Resultado> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO Result (Metascore, NameGame_ID, Console_ID, Userscore, Fecha) VALUES (:metascore_convert, "
				+ "(SELECT NameGameID FROM Game WHERE NameGame = :Name), "
				+ "(SELECT ConsoleID FROM Console WHERE NameConsole = :Console_id),"
				+ ":userScore_convert,"
				+ ":fecha_convert ) ");
		
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return itemWriter;
	} 
	
	public FlatFileItemWriter<BestGame> writerFileBestForConsole() 
    {
        FlatFileItemWriter<BestGame> writer = new FlatFileItemWriter<>();
  
        writer.setResource(BestGameForConsole);
        writer.setAppendAllowed(false);
        writer.setLineAggregator(new DelimitedLineAggregator<BestGame>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<BestGame>() {
                    {
                        setNames(new String[] {"NameCompany","NameConsole","NameGame","Metascore","Userscore", "Fecha"});
                    }
                });
            }
        });
        
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				 writer.write("NameCompany,NameConsole,NameGame,Metascore,Userscore,Fecha");
				
			}
        });
        
        return writer;
    }
	
	public FlatFileItemWriter<BestGame> writerFileWorstForConsole() 
    {
        FlatFileItemWriter<BestGame> writer = new FlatFileItemWriter<>();
  
        writer.setResource(WorsrGameForConsole);
        writer.setAppendAllowed(false);
        writer.setLineAggregator(new DelimitedLineAggregator<BestGame>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<BestGame>() {
                    {
                        setNames(new String[] {"NameCompany","NameConsole","NameGame","Metascore","Userscore", "Fecha"});
                    }
                });
            }
        });
        
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
			@Override
			public void writeHeader(Writer writer) throws IOException {
				 writer.write("NameCompany,NameConsole,NameGame,Metascore,Userscore,Fecha");
				
			}
        });
        
        return writer;
    }
	
	@Bean
	public Job JobProcessorCSV(JobListener listener,  Step LoadCompany,
													  Step LoadConsole, 
													  Step LoadGame, 
													  Step LoadResult, 
													  Step CreateFileBestAllConsole, 
													  Step CreateFileWorstAllConsole,
													  Step CreateFileBestGameForConsole,
													  Step CreateFileWorstGameForConsole) {
		return jobBuilderFactory.get("importConsoleJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(LoadCompany)
				.next(LoadConsole)
				.next(LoadGame)
				.next(LoadResult)
				.next(CreateFileBestAllConsole)
				.next(CreateFileWorstAllConsole)
				.next(CreateFileBestGameForConsole)
				.next(CreateFileWorstGameForConsole)
				.build();
	}
	
	@Bean
	public Step LoadCompany() {
		return stepBuilderFactory.get("LoadCompany")
				.<Console, Console> chunk(100)
				.reader(readerFileConsole())
		    	.processor(processorLoadCompany())
				.writer(writerLoadCompany(dataSource))
				.build();
	}
	
	@Bean
	public Step LoadConsole() {

		return stepBuilderFactory.get("LoadConsole")
				.<Console, Console> chunk(100)
				.reader(readerFileConsole())
				.writer(writerLoadConsole(dataSource))
				.build();
	}
	
	@Bean
	public Step LoadGame() {
		return stepBuilderFactory.get("LoadGame")
				.<Resultado, Resultado> chunk(1000)
				.reader(readerFileResutl())
		    	.processor(processorLoadGame())
				.writer(writerLoadNameGame(dataSource))
				.build();
	}
	
	@Bean
	public Step LoadResult() {

		return stepBuilderFactory.get("step4")
				.<Resultado, Resultado> chunk(1000)
				.reader(readerFileResutl())
		    	.processor(ProcessorResult())
				.writer(writerLoadResult(dataSource))
				.build();
	} 
	
	@Bean
	public Step CreateFileBestAllConsole() throws Exception {

		return stepBuilderFactory.get("step5")
				.<BestGame, BestGame> chunk(1000)
				.reader(itemReaderFileBestAllConsole(dataSource))
				.writer(writerFileBestAllConsole())
				.build();
	
	}
	
	@Bean
	public Step CreateFileWorstAllConsole() throws Exception {

		return stepBuilderFactory.get("step6")
				.<BestGame, BestGame> chunk(1000)
				.reader(itemReaderFileWorstAllConsole(dataSource))
				.writer(writerFileWorstConsole())
				.build();
	
	}
	
	@Bean
	public Step CreateFileBestGameForConsole() {
		
		return stepBuilderFactory.get("step7")
				.<BestGame, BestGame> chunk(1000)
				.reader(itemReaderFileBestForConsole(dataSource))
				.writer(writerFileBestForConsole())
				.build();
	} 
	
	@Bean
	public Step CreateFileWorstGameForConsole() {
		
		return stepBuilderFactory.get("step8")
				.<BestGame, BestGame> chunk(1000)
				.reader(itemReaderFileWorstForConsole(dataSource))
				.writer(writerFileWorstForConsole())
				.build();
	} 
	
	
}
	

