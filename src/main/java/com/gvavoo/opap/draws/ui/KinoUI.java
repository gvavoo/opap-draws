package com.gvavoo.opap.draws.ui;

import com.gvavoo.opap.draws.domain.Draw;
import com.gvavoo.opap.draws.service.KinoDrawService;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.LocalDateTimeRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@SpringComponent
@UIScope
public class KinoUI extends VerticalLayout {
	public static final String DATE_TO_SHOW = "EEE, dd MMM yyyy";
	public static final String DATETIME_TO_SHOW = "EEE, dd MMM yyyy HH:mm:ss";

	private DateTimeField dateTimeField;
	private Grid<Draw> grid;
	private List<Draw> draw;

	@Autowired
	private KinoDrawService drawService;

	public void initialize() {
		grid = new Grid<>();
		grid.setHeight(250, Sizeable.Unit.PIXELS);
		grid.setWidth(90, Sizeable.Unit.PERCENTAGE);
		grid.addColumn(Draw::getDrawNo).setCaption("Draw Number");
		LocalDateTimeRenderer dateRenderer = new LocalDateTimeRenderer(DATETIME_TO_SHOW);
		grid.addColumn(Draw::getDrawTime).setCaption("Draw Date & Time").setRenderer(dateRenderer);
		grid.addColumn(Draw::getResults).setCaption("Results");

		dateTimeField = new DateTimeField();
		dateTimeField.setResolution(DateTimeResolution.DAY);
		dateTimeField.setValue(LocalDateTime.now());
		dateTimeField.setRangeEnd(LocalDateTime.now());
		dateTimeField.setDateFormat(DATE_TO_SHOW);
		dateTimeField.addValueChangeListener(e -> updateGrid(e.getValue()));

		this.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		this.addComponent(grid);
		this.addComponent(dateTimeField);
	}


	public void updateGrid(LocalDateTime localDateTime) {
		draw = drawService.getDrawByDate(localDateTime);
		if (draw == null) {
			Notification.show("No draw for this day. Please choose another day.", Notification.Type.WARNING_MESSAGE);
			return;
		}
		grid.setItems(draw);
	}
}
