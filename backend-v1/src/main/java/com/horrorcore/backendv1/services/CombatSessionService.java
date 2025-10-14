package com.horrorcore.backendv1.services;

import com.horrorcore.backendv1.dtos.CombatInformation;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CombatSessionService {
    private final Map<String, CombatInformation> activeCombats = new ConcurrentHashMap<>();

    public String createSession(CombatInformation combat) {
        String sessionId = UUID.randomUUID().toString();
        activeCombats.put(sessionId, combat);
        return sessionId;
    }

    public CombatInformation getSession(String sessionId) {
        return activeCombats.get(sessionId);
    }

    public void endSession(String sessionId) {
        activeCombats.remove(sessionId);
    }
}
