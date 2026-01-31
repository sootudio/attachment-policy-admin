package com.sootudio.extguard.feature.policy.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "custom_extension",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_custom_extension", columnNames = "extension")
    }
)
public class CustomExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extension", length = 20, nullable = false)
    private String extension;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    protected CustomExtension() { }

    public CustomExtension(String extension) {
        this.extension = extension;
    }

    public Long getId() { return id; }
    public String getExtension() { return extension; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
