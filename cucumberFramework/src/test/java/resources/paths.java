package resources;

public enum paths {
basecreatelocation("maps/api/place/add/json"),
	basegetlocation("maps/api/place/get/json"),
	baseddeletelocation("maps/api/place/delete/json");
	private String resource;
	 paths(String resource)
	 {
		 this.resource=resource;
	 }
	 public String getresource() {
		 return resource;
		 
	 }
}
