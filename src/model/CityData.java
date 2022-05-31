package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CityData {

	private static ObservableList<City> data= FXCollections.observableArrayList();

	public static ObservableList<City> getData() {
		return data;
	}

	public static void setData(ObservableList<City> data) {
		CityData.data = data;
	}
}
