package main.java.com.corecomponents;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSReader{
	protected String filepath;
	FileInputStream fis=null;
	Workbook workbook=null;
	Sheet sheet=null;
	Row row=null;
	Cell cell=null;
	String fileExtension=null;
	protected static final Logger LOGGER = Logger.getLogger(XLSReader.class.getName());
		
	public XLSReader(String filepath) {
		
		this.filepath = filepath;
		fileExtension = filepath.substring(filepath.indexOf(".x"));
		
	   try {
			fis = new FileInputStream(filepath);
			
			if(fileExtension.equals(".xlsx")){
				
				workbook = new XSSFWorkbook(fis);
				
				
			} else if(fileExtension.equals(".xls")){
				
				workbook = new HSSFWorkbook(fis);
				
			}
			
			sheet = workbook.getSheetAt(0);	
			fis.close();
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		} 
	}
	
	// returns the row count in a sheet
	public int getRowCount(String sheetname){	
		int sheetIndex = workbook.getSheetIndex(sheetname);
		if(sheetIndex==-1){			
			return 0;
		} else {			
			sheet = workbook.getSheetAt(sheetIndex);
			return sheet.getLastRowNum()+1;	
		}	
	}	
	
	
	// returns the data from a cell
	public String getCellData(String sheetname,int colNum,int rowNum){
		try{
		if(rowNum <=0)
			return "";
		
		int sheetIndex = workbook.getSheetIndex(sheetname);
		
		if(sheetIndex==-1)
			return "";
		
		sheet = workbook.getSheetAt(sheetIndex);
		row = sheet.getRow(rowNum-1);
		if(row==null)
			return "";
		cell = row.getCell(colNum-1);
		if(cell==null)
			return "";
		
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			  return cell.getStringCellValue();
		  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
			  return String.valueOf(cell.getNumericCellValue());
		  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return "";
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
	       }catch(Exception e){
	    	   LOGGER.warning(e.getMessage());
			   return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
	    	   
	       }
	}
		
	// returns true if data is set successfully else false
		public boolean setCellData(String sheetName,String colName,int rowNum, String data){
			try{

			if(rowNum<=0)
				return false;
			
			int sheetIndex = workbook.getSheetIndex(sheetName);
			int colNum=-1;
			if(sheetIndex==-1)
				return false;
			
			
			sheet = workbook.getSheetAt(sheetIndex);
			

			row=sheet.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum=i;
			}
			if(colNum==-1)
				return false;

			sheet.autoSizeColumn(colNum); 
			row = sheet.getRow(rowNum-1);
			if (row == null)
				row = sheet.createRow(rowNum-1);
			
			cell = row.getCell(colNum);	
			if (cell == null)
		        cell = row.createCell(colNum);

		    // cell style
		   CellStyle cs = workbook.createCellStyle();
		   cs.setWrapText(true);
		   cell.setCellStyle(cs);
		    cell.setCellValue(data);

		    FileOutputStream fileOut = new FileOutputStream(filepath);

			workbook.write(fileOut);

		    fileOut.close();	

			}
			catch(Exception e){
				LOGGER.warning(e.getMessage());
				return false;
			}
			return true;
		}	
		
		// returns true if sheet is created successfully else false
		public boolean addSheet(String  sheetname){		
			
			FileOutputStream fileOutputStream;
			try {
				 workbook.createSheet(sheetname);	
				 fileOutputStream = new FileOutputStream(filepath);
				 workbook.write(fileOutputStream);
			     fileOutputStream.close();		    
			} catch (Exception e) {			
				LOGGER.warning(e.getMessage());
				return false;
			}
			return true;
		}
		
		// returns true if sheet is removed successfully else false if sheet does not exist
		public boolean removeSheet(String sheetName){		
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1)
				return false;
			
			FileOutputStream fileOutputStream;
			try {
				workbook.removeSheetAt(index);
				fileOutputStream = new FileOutputStream(filepath);
				workbook.write(fileOutputStream);
			    fileOutputStream.close();		    
			} catch (Exception e) {			
				LOGGER.warning(e.getMessage());
				return false;
			}
			return true;
		}
		
		// returns true if column is created successfully
		public boolean addColumn(String sheetName,String colName){
			
			try{				

				int index = workbook.getSheetIndex(sheetName);
				if(index==-1)
					return false;
				
			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			
			sheet=workbook.getSheetAt(index);
			
			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);
	

			if(row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());
		        
		        cell.setCellValue(colName);
		        cell.setCellStyle(style);
		        
		        FileOutputStream fileOut = new FileOutputStream(filepath);
				workbook.write(fileOut);
			    fileOut.close();		    

			}catch(Exception e){
				LOGGER.warning(e.getMessage());
				return false;
			}
			
			return true;			
			
		}
		
		// removes a column and all the contents
		public boolean removeColumn(String sheetName, int colNum) {
			try{
			if(!isSheetExist(sheetName))
				return false;
	
			sheet=workbook.getSheet(sheetName);
			CellStyle style = workbook.createCellStyle();
			workbook.getCreationHelper();  
		
			for(int i=0;i<getRowCount(sheetName);i++){
				row=sheet.getRow(i);	
				if(row!=null){
					cell=row.getCell(colNum-1);
					if(cell!=null){
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			FileOutputStream fileOut = new FileOutputStream(filepath);
			workbook.write(fileOut);
		    fileOut.close();
			}
			catch(Exception e){
				LOGGER.warning(e.getMessage());
				return false;
			}
			return true;
			
		}
		
		
		// find whether sheets exists	
		public boolean isSheetExist(String sheetName){
			int index = workbook.getSheetIndex(sheetName);
			if(index==-1){
				index=workbook.getSheetIndex(sheetName.toUpperCase());
					return (index!=-1);
						
			}
			else
				return true;
		}
		
		
		// returns number of columns in a sheet	
		public int getColumnCount(String sheetName){
			// check if sheet exists
			if(!isSheetExist(sheetName))
			 return -1;
			
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			
			if(row==null)
				return -1;
			
			return row.getLastCellNum();			
			
		}
		
		
		//String sheetName, String testCaseName,String keyword ,String URL,String message
		public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,String message){
			if(!isSheetExist(sheetName))
				 return false;
			
		    sheet = workbook.getSheet(sheetName);
		    
		    for(int i=1;i<=getRowCount(sheetName);i++){
		    	if(getCellData(sheetName, 1, i).equalsIgnoreCase(testCaseName)){
		    		setCellData(sheetName, screenShotColName,i,message);
		    		break;
		    	}
		    }


			return true; 
		}
		
		public int testCaseRow(String sheetname,String testCase) {	
			
			int rowsCount=getRowCount(sheetname);
			
			sheet=workbook.getSheet(sheetname);	

	      	int actualRowNumber = 0;	

			for(int i=0;i<=rowsCount;i++) {
				String caseNumber= getCellData(sheetname,1,i);
				if(caseNumber.equalsIgnoreCase(testCase)) {
					actualRowNumber=i;
					break;
				}
			}
			return actualRowNumber;
	 }
		
     public int testCaseColumns(String sheetname,String testCase){	
    	    int testcaserownumber=testCaseRow(sheetname,testCase);
    	    sheet = workbook.getSheet(sheetname);
			row = sheet.getRow(testcaserownumber);
			return row.getLastCellNum(); 	
	 }	
		
		
		public Map<String,String> gettestdata(String sheetname,int testCaseRowNumber) {
			 Map<String,String> testdata = new HashMap<>();	 
			 sheet=workbook.getSheet(sheetname);
			 int colCount=sheet.getRow(0).getLastCellNum();			
			 String headerrowdata=null;
			 String actualrowdata=null;
			 for(int i=1;i<=colCount;i++) {				 
				  headerrowdata=getCellData(sheetname, i, 1);
				  actualrowdata=getCellData(sheetname,i,testCaseRowNumber);				  	
				  testdata.put(headerrowdata, actualrowdata);		  
			 }
			 return testdata;
			 	
		}
		
		//-------------Dhanraj-----------
		
		// Find row number based on passed row header value for factoring
		public int getRowNumberByRowHeader(String sheetName, String rowHeader) {
			int totalRows=getRowCount(sheetName);
			sheet = workbook.getSheet(sheetName);
			int rowNum;
			for(rowNum=1;rowNum<=totalRows-1;rowNum++){
				row = sheet.getRow(rowNum);
				cell=row.getCell(0);
				if(cell.getStringCellValue().equals(rowHeader)) {

					break;
				}
				
			}
			return rowNum;
			
		}
		
		// Find column number based on passed column header value for factoring
		public int getColumnNumberByColumnHeader(String sheetName, String columnHeader, int header) {
			int totalColumns=getColumnCount(sheetName);
			sheet = workbook.getSheet(sheetName);
			int colNum;
			int count=0;
			row = sheet.getRow(header);
			for(colNum=0;colNum<=totalColumns;colNum++){
				
				cell=row.getCell(colNum);
				if(cell != null && cell.getStringCellValue().contentEquals(columnHeader)) {
						count=count+1;
					}
				if(count==2) {
					break;
				}
			}
			return colNum;
		}
		
		

		// Method to fill out upper table data 
		public void setCellDataValue(String sheetName, String rowHeader, String  columnHeader, String data, int header) throws IOException, InterruptedException {
			int rowNum=getRowNumberByRowHeader(sheetName, rowHeader);
			int cellNum=getColumnNumberByColumnHeader(sheetName, columnHeader, header);
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rowNum);
			cell = row.getCell(cellNum);
			cell.setCellValue(data);
			FileOutputStream fileOut = new FileOutputStream(filepath);
			workbook.write(fileOut);
		    fileOut.close();
		    Thread.sleep(2000);
			
		}	
		public void setCellDataThroghStaticRowCol(String sheetName, int rowNum, int cellNum, String data) throws IOException, InterruptedException {
			
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(rowNum);
			cell = row.getCell(cellNum);
			cell.setCellValue(data);
			FileOutputStream fileOut = new FileOutputStream(filepath);
			workbook.write(fileOut);
		    fileOut.close();
		    Thread.sleep(2000);
		}
		

	
		
	
}
