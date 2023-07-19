package com.example.zoostore.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items") //plural name
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id") //singular name
    private UUID id;

    @Column(name = "product_name") //singular name
    private String productName;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false) // moje i bez referencedColumnName
    private Vendor vendor;

    @OneToMany(mappedBy = "item") //shte imame mnojestvo url-ove
    @Column(name = "multimedia")
    private Set<Multimedia> multimedia;

    @ManyToMany
    @Column(name = "tag")
    @JoinTable(
            name = "item_tag",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags;

    private boolean archived = false;
}
//TODO da vrushtam razlichni builder obekti ot tozi klas pri editvane. Moje da nqma multimediq ili neshto shodno za tova trqbva purvo da se deklarira i posle da se vurne sprqmo situaciqta. Purvo obhojdame kolekciite za sushestvuvaneto na MM i posle s if davame konkretnoto neshto, koeto shte vryshtame
