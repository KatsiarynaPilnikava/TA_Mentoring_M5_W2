package com.epam.tat.selenium.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ConfigData {
	private String browser;
	private String gridUrl;
	private String url;

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getGridUrl() {
		return gridUrl;
	}

	public void setGrid_url(String grid_url) {
		this.gridUrl = grid_url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ConfigData(String browser, String grid_url, String url) {
		super();
		this.browser = browser;
		this.gridUrl = grid_url;
		this.url = url;
	}
	public ConfigData(){
		
	}

	@Override
	public String toString() {
		return "ConfigData [browser=" + browser + ", grid_url=" + gridUrl
				+ ", url=" + url + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((browser == null) ? 0 : browser.hashCode());
		result = prime * result
				+ ((gridUrl == null) ? 0 : gridUrl.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigData other = (ConfigData) obj;
		if (browser == null) {
			if (other.browser != null)
				return false;
		} else if (!browser.equals(other.browser))
			return false;
		if (gridUrl == null) {
			if (other.gridUrl != null)
				return false;
		} else if (!gridUrl.equals(other.gridUrl))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
