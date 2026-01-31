package com.sootudio.extguard.feature.policy.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "fixed_extension")
public class FixedExtension {

    @Id
    @Column(name = "extension", length = 20, nullable = false)
    private String extension;

    @Column(name = "blocked", nullable = false)
    private boolean blocked;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public FixedExtension(String extension, boolean blocked) {
        this.extension = extension;
        this.blocked = blocked;
    }

    public void changeBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
