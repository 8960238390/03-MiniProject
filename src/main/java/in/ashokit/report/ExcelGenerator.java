package in.ashokit.report;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import in.ashokit.response.SearchResponse;

public class ExcelGenerator {

	public void generateExcel(List<SearchResponse> searchResponseList , HttpServletResponse httpResponse) throws IOException {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow headRow = sheet.createRow(0);
		
		headRow.createCell(0).setCellValue("S.No");
		headRow.createCell(1).setCellValue("Holder Name");
		headRow.createCell(2).setCellValue("Holder SSN");
		headRow.createCell(3).setCellValue("Plan Name");
		headRow.createCell(4).setCellValue("Plan Status");
		headRow.createCell(5).setCellValue("Start Date");
		headRow.createCell(6).setCellValue("End Date");
		headRow.createCell(7).setCellValue("Benifit Amount");
		headRow.createCell(8).setCellValue("Denial Reason");
		
		
		for(int i=1 ; i<= searchResponseList.size(); i++) {
			
			SearchResponse searchResponse = searchResponseList.get(i-1);
			HSSFRow row = sheet.createRow(i);
			
			
			row.createCell(0).setCellValue(i);
			
			row.createCell(1).setCellValue(searchResponse.getHolderName());
			row.createCell(2).setCellValue(searchResponse.getHolderSsn());
			row.createCell(3).setCellValue(searchResponse.getPlanName());
			row.createCell(4).setCellValue(searchResponse.getPlanStatus());
			if(searchResponse.getStartDate()!=null)
				row.createCell(5).setCellValue(String.valueOf(searchResponse.getStartDate()));
			if(searchResponse.getEndDate()!=null)
				row.createCell(6).setCellValue(String.valueOf(searchResponse.getEndDate()));
			if(searchResponse.getBenefitAmt()!=null)
				row.createCell(7).setCellValue(searchResponse.getBenefitAmt());
			if(searchResponse.getDenialReason()!=null)
				row.createCell(8).setCellValue(searchResponse.getDenialReason());
			
		}
		
		workbook.write(httpResponse.getOutputStream());
		workbook.close();
	}
}
