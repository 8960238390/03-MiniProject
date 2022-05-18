package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.ashokit.entity.EligibilityDetails;
import in.ashokit.repository.SearchPlansRepo;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;

@Service
public class SearchPlanServiceImpl implements SearchPlanService {

	@Autowired
	private SearchPlansRepo plansRepo;

	@Override
	public List<String> getPlansNames() {

		List<String> planNames = plansRepo.getPlanNames();

		return planNames;
	}

	@Override
	public List<String> getPlanStatus() {

		List<String> planStatus = plansRepo.getPlanStatus();

		return planStatus;
	}

	@Override
	public List<SearchResponse> searchPlans(SearchRequest searchRequest) {

		List<EligibilityDetails> allRecords;

		if (isSearchReqEmpty(searchRequest)) {

			allRecords = plansRepo.findAll();

		} else {

			EligibilityDetails entity = new EligibilityDetails();

			if (searchRequest.getPlanName() != null && !searchRequest.getPlanName().equals("")) {
				entity.setPlanName(searchRequest.getPlanName());
			}

			if (searchRequest.getPlanStatus() != null && !searchRequest.getPlanStatus().equals("")) {
				entity.setPlanStatus(searchRequest.getPlanStatus());
			}

			if (searchRequest.getStartDate() != null && searchRequest.getEndDate() != null) {
				entity.setStartDate(searchRequest.getStartDate());
				entity.setEndDate(searchRequest.getEndDate());
			}

			Example<EligibilityDetails> example = Example.of(entity);

			allRecords = plansRepo.findAll(example);

		}

		List<SearchResponse> response = new ArrayList<>();
		
		for(EligibilityDetails record : allRecords) {
			
			SearchResponse searchResponse = new SearchResponse();
			BeanUtils.copyProperties(record, searchResponse);
			response.add(searchResponse);
		}
		
		
		
		return response;
	}//searchPlan()
	
	private boolean isSearchReqEmpty(SearchRequest searchRequest) {
		
		if(searchRequest.getPlanName()!=null && !searchRequest.getPlanName().equals(""))
			return false;
		
		if(searchRequest.getPlanStatus()!=null && !searchRequest.getPlanStatus().equals(""))
			return false;
		
		if(searchRequest.getStartDate()!=null && searchRequest.getEndDate()!=null)
			return false;
		
		return true;
	}//isSearchReqEmpty()

}
