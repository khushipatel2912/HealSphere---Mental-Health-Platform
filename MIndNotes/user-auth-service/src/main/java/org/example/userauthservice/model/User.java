package org.example.userauthservice.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String name;
    private String password;  // Only for manual registration, null for Google sign-in

    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider; // GOOGLE or LOCAL

    @Column(name = "dummy_id", unique = true, nullable = false)
    private String dummyId;

    @PrePersist
    public void generateDummyId() {
        this.dummyId = "DUMMY-" + System.currentTimeMillis();
    }

}
