package ucn.datamatiker.afr_rmlp.housingenabler.models;

public class Result {
	private long idResult;
	private String caseName;
	private String date;
	private String address;
	private String city;
	private String zipcode;
	
	
	public Result(long idResult, String caseName, String date, String address, String city, String zipcode) {
		this.idResult = idResult;
		this.caseName = caseName;
		this.date = date;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public String getDate() {
		return this.date;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getZipcode() {
		return this.zipcode;
	}
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
	    return caseName;
	}
}
