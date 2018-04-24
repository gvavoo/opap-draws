package com.gvavoo.opap.draws.service;

import com.google.gson.*;
import com.gvavoo.opap.draws.domain.Draw;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KinoDrawService implements DrawService {

	@Value("${kino.draw.bydate}")
	String kinoDrawByDate;

	@Value("${file.ext}")
	String extension;

	public static final String DATE_FORMAT = "d-MM-yyyy'T'HH:mm:ss";


	public List<Draw> getDrawByDate (LocalDateTime localDateTime) {
		List<Draw> drawsList = new ArrayList<>();
		String date = formatDateForAPI(localDateTime);
		String uri = kinoDrawByDate + date + extension;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		JSONObject responseJsonBody = new JSONObject(response.getBody());
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
			@Override
			public LocalDateTime deserialize (JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
				return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(DATE_FORMAT));
			}
		}).setDateFormat(DATE_FORMAT).create();

		if(responseJsonBody.has("draws") && responseJsonBody.getJSONObject("draws").has("draw")) {
			Draw[] draws = gson.fromJson(responseJsonBody.getJSONObject("draws").getJSONArray("draw").toString(), Draw[].class);
			drawsList = Arrays.asList(draws);
		}
		return drawsList;
	}


	private String formatDateForAPI (LocalDateTime localDateTime) {
		return localDateTime.getDayOfMonth() + "-" + localDateTime.getMonthValue() + "-" + localDateTime.getYear();
	}
}
