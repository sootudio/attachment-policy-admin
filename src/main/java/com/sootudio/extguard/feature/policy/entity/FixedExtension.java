package com.sootudio.extguard.feature.policy.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    protected FixedExtension() { }

    public FixedExtension(String extension, boolean blocked) {
        this.extension = extension;
        this.blocked = blocked;
    }

    public String getExtension() { return extension; }
    public boolean isBlocked() { return blocked; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
