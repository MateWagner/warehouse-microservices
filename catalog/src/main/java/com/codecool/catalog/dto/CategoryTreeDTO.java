package com.codecool.catalog.dto;

import java.util.UUID;

public record CategoryTreeDTO(UUID categoryPID, String name, CategoryTreeDTO subCategory) {
}
