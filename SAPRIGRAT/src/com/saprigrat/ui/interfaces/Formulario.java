package com.saprigrat.ui.interfaces;

import com.vaadin.ui.Component;

public interface Formulario extends Component
{
	String[]arrOrgsCca = {  "I Pen�nsula de Baja California",
							"II Noroeste",
							"III Pac�fico Norte",
							"IV Balsas",
							"V Pac�fico Sur",
							"VI R�o Bravo",
							"VII Cuencas Centrales del Norte",
							"VIII Lerma Santiago Pac�fico",
							"IX Golfo Norte",
							"X Golfo Centro",
							"XI Frontera del Sur",
							"XII Pen�nsula de Yucat�n",
							"XIII Aguas del Valle de M�xico" };

	String[]arrDtosR = {"001",
						"002",
						"003",
						"004",
						"005",
						"006",
						"008",
						"009",
						"010",
						"011",
						"013",
						"014",
						"016",
						"017",
						"018",
						"019",
						"020",
						"023",
						"024",
						"025",
						"026",
						"028",
						"029",
						"030",
						"031",
						"033",
						"034",
						"035",
						"037",
						"038",
						"041",
						"042",
						"043",
						"044",
						"045",
						"046",
						"048",
						"049",
						"050",
						"051",
						"052",
						"053",
						"056",
						"057",
						"059",
						"060",
						"061",
						"063",
						"066",
						"068",
						"073",
						"074",
						"075",
						"076",
						"082",
						"083",
						"084",
						"085",
						"086",
						"087",
						"088",
						"089",
						"090",
						"092",
						"093",
						"094",
						"095",
						"096",
						"097",
						"098",
						"099",
						"100",
						"101",
						"102",
						"103",
						"104",
						"105",
						"107",
						"108",
						"109",
						"110",
						"111",
						"112" };


	
	void setTipo (int tipo);
}
