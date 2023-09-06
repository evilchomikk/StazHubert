package org.example.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Name {
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String lastname;

    public Name(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }
}
