package com.danram.server.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id", columnDefinition = "int")
    private Long image_id;

    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;
}
