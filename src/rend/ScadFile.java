package rend;

public class ScadFile implements ScadObject {
	private String Filetext;
	
	final String addcyl = " cylinder(%1$.3f,%2$.3f,%2$.3f);"; 
	final String translate = " translate([%1$.3f,%2$.3f,%3$.3f]){%s}";
	final String comment = "/*%s*/";

	public String getFiletext() {
		return Filetext;
	}

	public void setFiletext(String filetext) {
		Filetext = filetext;
	}
	
	public void comment(String s){
		s=String.format(comment, s);
	}

	@Override
	public String printcommand() {
		// TODO Auto-generated method stub
		return null;
	}
	
}