package org.carlos.bluetree.authentication.user.entity;

import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
public class Audit {

    private LocalDateTime createdOn;

    private LocalDateTime updatedAt;

    @PrePersist
    private void prePersist(){
        createdOn = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate(){
        updatedAt = LocalDateTime.now();
    }

}
