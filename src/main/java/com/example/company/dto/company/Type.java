package com.example.company.dto.company;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
    NULL,
    FULL,
    SMALL,
    MEDIUM,
    GROUP,
    DORMANT,
    INTERIM,
    INITIAL,
    TOTAL_EXEMPTION_FULL,
    TOTAL_EXEMPTION_SMALL,
    PARTIAL_EXEMPTION,
    AUDIT_EXEMPTION_SUBSIDIARY,
    FILING_EXEMPTION_SUBSIDIARY,
    MICRO_ENTITY,
    NO_ACCOUNTS_TYPE_AVAILABLE,
    AUDITED_ABRIDGED,
    UNAUDITED_ABRIDGED;

    @JsonValue
    public String toValue() {
        return this.name().toLowerCase().replace('_', '-');
    }
}
