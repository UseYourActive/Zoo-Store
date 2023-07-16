package com.example.zoostore.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "multimedia")
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}