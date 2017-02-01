package org.recap.util;

import org.recap.RecapConstants;
import org.recap.model.search.SearchRecordsRequest;
import org.recap.model.search.SearchRecordsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by rajeshbabuk on 2/1/17.
 */
@Service
public class SearchUtil {

    Logger logger = LoggerFactory.getLogger(SearchUtil.class);

    @Value("${server.protocol}")
    String serverProtocol;

    @Value("${scsb.url}")
    String scsbUrl;

    public SearchRecordsResponse requestSearchResults(SearchRecordsRequest searchRecordsRequest) {
        SearchRecordsResponse searchRecordsResponse = new SearchRecordsResponse();
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set(RecapConstants.API_KEY, RecapConstants.RECAP);
            HttpEntity<SearchRecordsRequest> httpEntity = new HttpEntity<>(searchRecordsRequest, headers);

            ResponseEntity<SearchRecordsResponse> responseEntity = restTemplate.exchange(serverProtocol + scsbUrl + RecapConstants.SCSB_SEARCH_SERVICE_URL, HttpMethod.POST, httpEntity, SearchRecordsResponse.class);
            searchRecordsResponse = responseEntity.getBody();
            return searchRecordsResponse;
        } catch (Exception e) {
            logger.error("" + e.getMessage());
            return searchRecordsResponse;
        }
    }
}
