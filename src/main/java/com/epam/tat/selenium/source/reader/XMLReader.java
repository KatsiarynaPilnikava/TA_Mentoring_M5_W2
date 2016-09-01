package com.epam.tat.selenium.source.reader;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.epam.tat.selenium.entities.ConfigData;

public class XMLReader implements Reader {

	private static String driverProp = null;
	


	public static void convertToXML() {
		ConfigData data = new ConfigData("google_chrome", "http://localhost:4444/wd/hub", "https://mail.ru/");
		try {

			File file = new File("D:\\config.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ConfigData.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(data, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		

	}

	public ConfigData read() {
		try {

			File file = new File("D:\\config.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(ConfigData.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ConfigData data = (ConfigData) jaxbUnmarshaller.unmarshal(file);
			return data;

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;

	}

}
