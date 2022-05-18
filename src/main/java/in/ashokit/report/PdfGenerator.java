package in.ashokit.report;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.response.SearchResponse;

public class PdfGenerator {

	public void generatePdf(List<SearchResponse> records, HttpServletResponse httpResponse) throws IOException {

		Document document = new Document();

		PdfWriter pdfWriter = PdfWriter.getInstance(document, httpResponse.getOutputStream());

		document.open();

		Font font = new Font(Font.HELVETICA, 16, Font.BOLDITALIC, Color.RED);

		Paragraph para = new Paragraph("Eligibility Details", font);

		document.add(para);

		PdfPTable table = new PdfPTable(9);

		table.addCell("S.No");
		table.addCell("Holder Name");
		table.addCell("Holder SSN");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benifit Amt");
		table.addCell("Denial Reason");
		
		int sno=1;
		for(SearchResponse response : records) {
			
			table.addCell(String.valueOf(sno));
			table.addCell(response.getHolderName());
			table.addCell(String.valueOf(response.getHolderSsn()));
			table.addCell(response.getPlanName());
			table.addCell(response.getPlanStatus());
			table.addCell(String.valueOf(response.getStartDate()));
			table.addCell(String.valueOf(response.getEndDate()));
			table.addCell(String.valueOf(response.getBenefitAmt()));
			table.addCell(response.getDenialReason());

			sno++;
		}
		
		document.add(table);
		
		document.close();
		pdfWriter.close();
	}
}
