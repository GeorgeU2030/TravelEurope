package model;


import javafx.scene.image.ImageView;

public class City {

	private String name;
	private String code;
	private ImageView flag;
	
	public City(String name,String code,ImageView image) {
		this.name=name;
		this.code=code;
		this.flag=image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ImageView getFlag() {
		return flag;
	}

	public void setFlag(ImageView flag) {
		this.flag = flag;
	}
	
}
