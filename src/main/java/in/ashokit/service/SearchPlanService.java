package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.EligibilityDetails;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;

public interface SearchPlanService {

	List<String> getPlansNames();
	List<String> getPlanStatus();
	List<SearchResponse> searchPlans(SearchRequest searchRequest);
}
