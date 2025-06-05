package domain;
/**
* Класс данных о районах
*/
public class District {
// Идентификатор района
private Long id;
// Наименование района
private String namedistrict;
//Площадь района
private String districtArea;
//Год застройки
private String districYear;
//Кличество населения
private int districNumberOfPeople;

public District() {
}

public District(String namedistrict) {
	this.namedistrict = namedistrict;
}

public District(Long id, String namedistrict, String districtArea, String districYear, int districNumberOfPeople) {
	this.id = id;
	this.namedistrict = namedistrict;
	this.districtArea = districtArea;
	this.districYear = districYear;
	this.districNumberOfPeople = districNumberOfPeople;
}

public District(String namedistrict, String districtArea, String districYear, int districNumberOfPeople) {
	this.namedistrict = namedistrict;
	this.districtArea = districtArea;
	this.districYear = districYear;
	this.districNumberOfPeople = districNumberOfPeople;
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

//Геттеры и сеттеры

public String getNameDistrict() {
	return namedistrict;
}
public void setnamedistrict(String namedistrict) {
	this.namedistrict = namedistrict;
}

public String getDistrictArea() {
	return districtArea;
}
public void setDistrictArea(String districtArea) {
	this.districtArea = districtArea;
}

public String getDistrictYear() {
	return districYear;
}
public void setDistrictYear(String districYear) {
	this.districYear = districYear;
}

public int getDistrictNumberOfPeople() {
	return districNumberOfPeople;
}
public void setDistrictNumberOfPeople(int districNumberOfPeople) {
	this.districNumberOfPeople = districNumberOfPeople;
}


@Override
public String toString() {
return "District {" +
		"Id = " + id + 
		", namedistrict = " + namedistrict +
		", districtArea = " + districtArea +
		", districYear = " + districYear +
		", districNumberOfPeople = " + districNumberOfPeople + "}";
}
}
