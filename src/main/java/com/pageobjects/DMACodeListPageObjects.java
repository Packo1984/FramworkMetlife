package main.java.com.pageobjects;

public class DMACodeListPageObjects {

	public static final String CODE_ID_SEARCH_XPATH="//div[@class='ui-grid-header-cell ui-grid-clearfix ng-scope ng-isolate-scope ui-grid-coluiGrid-0006']/div[1][@class='ng-scope sortable']/div[2]/div[1][@class='ui-grid-filter-container ng-scope']/div[1][@class='ng-scope']/input[1][@class='ui-grid-filter-input ui-grid-filter-input-0']";
	public static final String CODE_EDIT_ICON_XPATH="//span[@class='glyphicon glyphicon-pencil']";
	public static final String SHORTNAME_FIELD_XPATH="//textarea[@name='shortName']";
	public static final String CANCEL_EDIT_XPATH="//button[contains(@class,'btn btn-default second-button ng-scope')]";
	public static final String CONFIRM_CANCEL_XPATH="//div[contains(@class,'confirm-message proceed confirm-message-active')]//button[contains(@class,'btn btn-primary btn-sm primary-button-sm')][contains(text(),'Yes')]";
	public static final String PROFILE_ICON_XPATH="//img[contains(@class,'ng-isolate-scope')]";
	public static final String LOGOUT_XPATH="//strong[contains(text(),'Logout')]";
	public static final String TABLE_XPATH="//div[@class='ui-grid-viewport ng-isolate-scope']";
	
	
	private DMACodeListPageObjects() {
	    throw new IllegalStateException("Utility class");
	  }
}


