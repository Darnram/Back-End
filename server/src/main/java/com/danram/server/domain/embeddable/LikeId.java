package com.danram.server.domain.embeddable;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class LikeId implements Serializable {
    private Long likeId;
    private Long type;
    private Long id;
}
