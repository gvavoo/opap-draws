package com.gvavoo.opap.draws.service;

import com.gvavoo.opap.draws.domain.Draw;

import java.time.LocalDateTime;
import java.util.List;

public interface DrawService {
	List<Draw> getDrawByDate(LocalDateTime localDateTime);
}
