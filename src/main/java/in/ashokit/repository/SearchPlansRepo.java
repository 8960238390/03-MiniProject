package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.EligibilityDetails;

@Repository
public interface SearchPlansRepo extends JpaRepository<EligibilityDetails, Integer>{

	//@Query(value="select DISTINCT PLAN_NAME from ELIGIBILITY_DTLS",nativeQuery = true)
	@Query("select DISTINCT planName from EligibilityDetails")
	public List<String> getPlanNames();
	
	@Query("select DISTINCT planName from EligibilityDetails")
	public List<String> getPlanStatus();
	
}
