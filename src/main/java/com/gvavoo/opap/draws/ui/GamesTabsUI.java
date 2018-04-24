package com.gvavoo.opap.draws.ui;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;


@SpringComponent
@UIScope
public class GamesTabsUI extends TabSheet {

	@Autowired
	KinoUI kinoUI;

	public void initialize() {
		kinoUI.initialize();
		this.addTab(kinoUI, "Kino");
		this.addTab(new VerticalLayout(), "Joker");
	}
}
