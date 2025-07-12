package com.chabiamine.Shortify.services;


import com.chabiamine.Shortify.dto.LinkAnalyticsDto;
import com.chabiamine.Shortify.repositories.visitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LinkAnalyticsService {

    @Autowired
    private final visitRepository visitRepo;

    public LinkAnalyticsService(visitRepository visitRepo) {
        this.visitRepo = visitRepo;
    }

    public LinkAnalyticsDto getAnalyticsForLink(Long urlId) {

        LinkAnalyticsDto dto = new LinkAnalyticsDto();

        dto.totalClicks = (int) visitRepo.countByUrlId(urlId);
        dto.firstClick = visitRepo.findFirstByUrlIdOrderByTimestampAsc(urlId)
                .map(v -> v.getTimestamp().toString()).orElse("N/A");
        dto.lastClick = visitRepo.findFirstByUrlIdOrderByTimestampDesc(urlId)
                .map(v -> v.getTimestamp().toString()).orElse("N/A");

        dto.referrers = toMap(visitRepo.findTopReferrers(urlId));
        dto.deviceTypes = parseUserAgents(visitRepo.findDeviceTypes(urlId));

        // You could also fetch geo IP here (optional)
        dto.topCountries = Map.of("MockCountry", 100); // Placeholder




        return dto;
    }

    private Map<String, Integer> toMap(List<Object[]> results) {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Object[] row : results) {
            map.put((String) row[0], ((Long) row[1]).intValue());
        }
        map.entrySet().removeIf(e -> e.getKey() == null);
        return map;
    }

    private Map<String, Integer> parseUserAgents(List<Object[]> results) {
        Map<String, Integer> map = new LinkedHashMap<>();

        for (Object[] row : results) {
            if (row[0] == null || row[1] == null) {
                continue; // Skip rows with null values
            }

            String agent = ((String) row[0]).toLowerCase();
            int count = ((Long) row[1]).intValue();

            String type;
            if (agent.contains("mobile")) {
                type = "Mobile";
            } else if (agent.contains("windows") || agent.contains("mac") || agent.contains("linux")) {
                type = "Desktop";
            } else {
                type = "Bot";
            }

            map.merge(type, count, Integer::sum);
        }

        // Optional: Safety net (can remove if above logic is airtight)
        map.entrySet().removeIf(e -> e.getKey() == null);

        return map;
    }



}
