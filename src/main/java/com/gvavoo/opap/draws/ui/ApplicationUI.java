package com.gvavoo.opap.draws.ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
public class ApplicationUI extends UI {

	@Autowired
	GamesTabsUI gamesTabsUI;

	@Override
	protected void init (VaadinRequest request) {
		gamesTabsUI.initialize();
		Label label = new Label("<h1>Draws</h1>", ContentMode.HTML);
		VerticalLayout verticalLayout = new VerticalLayout(label, gamesTabsUI);
		setContent(verticalLayout);
	}
}
