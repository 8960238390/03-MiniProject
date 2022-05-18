package in.ashokit.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.EligibilityDetails;
import in.ashokit.report.ExcelGenerator;
import in.ashokit.report.PdfGenerator;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;
import in.ashokit.service.SearchPlanService;

@RestController
public class ReportRestController {

	@Autowired
	private SearchPlanService planService;

	@GetMapping("/plan-name")
	public List<String> getplanNames() {

		return planService.getPlansNames();

	}

	@GetMapping("/plan-status")
	public List<String> getplanStatus() {

		return planService.getPlanStatus();
	}

	@PostMapping("/search")
	public List<SearchResponse> search(@RequestBody SearchRequest reuest) {

		List<SearchResponse> searchPlans = planService.searchPlans(reuest);

		return searchPlans;
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse httpResponse) throws Exception {

		httpResponse.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String herderValue = "attachment;filename=plans.xls";
		httpResponse.setHeader(headerKey, herderValue);

		List<SearchResponse> searchPlans = planService.searchPlans(null);

		ExcelGenerator excel = new ExcelGenerator();

		excel.generateExcel(searchPlans, httpResponse);
	}

	@GetMapping("/pdf")
	public void generatePdf(HttpServletResponse httpServletResponse) throws Exception {

		httpServletResponse.setContentType("application/pdf");
		String headerKey = "Content/Disposition";
 		String headerValue = "attachment;filename=plans.pdf";
		httpServletResponse.setHeader(headerKey, headerValue);

		List<SearchResponse> searchPlans = planService.searchPlans(null);
		
		PdfGenerator pdfGenerator = new PdfGenerator();
		pdfGenerator.generatePdf(searchPlans, httpServletResponse);
	}
}
