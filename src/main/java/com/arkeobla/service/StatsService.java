package com.arkeobla.service;

import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class StatsService {
    private final AtomicLong homePageVisits = new AtomicLong(0);

    public void incrementHomePageVisits() {
        homePageVisits.incrementAndGet();
    }

    public long getHomePageVisits() {
        return homePageVisits.get();
    }
}
