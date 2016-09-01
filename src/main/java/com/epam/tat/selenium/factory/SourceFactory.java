package com.epam.tat.selenium.factory;

import com.epam.tat.selenium.source.reader.DBReader;
import com.epam.tat.selenium.source.reader.Reader;
import com.epam.tat.selenium.source.reader.XMLReader;

public class SourceFactory {

	public static Source getSource(String sourceType) {
		for (Source source : Source.values()){
			if (source.getSourceType().equals(sourceType)) {
				return source;
			}
		}
		return null;
	}
	
	public enum Source {
		XML("xml", new XMLReader()), DB("db", new DBReader());

		private String sourceType;
		private Reader reader;

		private Source(String sourceType, Reader reader) {
			this.reader = reader;
			this.sourceType = sourceType;
		}
		private String getSourceType(){
			return this.sourceType;
		}
		
		public Reader getReader() {
			return reader;
		}
		
		
	}
	
	
}
