package com.gvavoo.opap.draws.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Draw {
	private String drawNo;
	private LocalDateTime drawTime;
	private List<String> results;

	public Draw () {
	}

	public Draw (String drawNo, LocalDateTime drawTime, List<String> results) {
		this.drawNo = drawNo;
		this.drawTime = drawTime;
		this.results = results;
	}

	public String getDrawNo () {
		return drawNo;
	}

	public void setDrawNo (String drawNo) {
		this.drawNo = drawNo;
	}

	public LocalDateTime getDrawTime () {
		return drawTime;
	}

	public void setDrawTime (LocalDateTime drawTime) {
		this.drawTime = drawTime;
	}

	public List<String> getResults () {
		return results;
	}

	public void setResults (List<String> results) {
		this.results = results;
	}
}
