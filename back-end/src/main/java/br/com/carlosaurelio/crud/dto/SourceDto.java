package br.com.carlosaurelio.crud.dto;

public class SourceDto {
	private String url;
	
	public SourceDto(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
