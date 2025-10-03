package com.horrorcore.backendv1.dtos;

import java.util.List;

public record StartCombatRequest(
        String playerCharacterId,
        List<String> monsterIds
) {}
