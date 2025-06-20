package domain;
/**
* Класс для данных области
*/
public class Region {
// Идентификатор сотрудника
private Long id;
 // название области
private String regionName;
 // площадь
private String regionArea;
 // административный центр
private String regionCity;
 // глава
private String regionHead;
 
 // Навигационное свойства - ссылка на район
private District district;

private Long idDi;

public Region() {
}

 public Region(Long id, String regionName, String regionArea, String regionCity,
 String regionHead, Long idDi) {
	 this.id 		 = id;
	 this.regionName = regionName;
	 this.regionArea = regionArea;
	 this.regionHead = regionHead;
	 this.regionCity = regionCity;
	 this.idDi 		 = idDi;
 }

 public Region(String regionName, String regionArea, String regionCity,
 String regionHead, District district) {
 this.regionName = regionName;
 this.regionArea = regionArea;
 this.regionHead = regionHead;
 this.regionCity = regionCity;
 this.district = district;
 }

public String getregionName() {
return regionName;
}
public void setregionName(String regionName) {
this.regionName = regionName;
}

public String getregionArea() {
return regionArea;
}
public void setregionArea(String regionArea) {
this.regionArea = regionArea;
}

public String getregionCity() {
return regionCity;
}

public void setregionCity(String regionCity) {
this.regionCity = regionCity;
}

public District getDistrict() {
	return district;
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getregionHead() {
return regionHead;
}

public Long getregionIdDi() {
return idDi;
}

public void setregionHead(String regionHead) {
this.regionHead = regionHead;
}

public void setDistrict(District district) {
	this.district = district;
}

public void setIdDi(Long idDi) {
	this.idDi = idDi;
}


@Override
public String toString() {
return "Region {" + "Id = " + id +
", regionName = " + regionName +
", regionArea = " + regionArea +
", regionCity = " + regionCity +
", regionHead = " + regionHead +
", district = " + district +
"}";
}
}