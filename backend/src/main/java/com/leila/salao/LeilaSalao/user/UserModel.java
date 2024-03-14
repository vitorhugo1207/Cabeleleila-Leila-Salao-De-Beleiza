package com.leila.salao.LeilaSalao.user;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.leila.salao.LeilaSalao.employerType.UserTypeModel;
import com.leila.salao.LeilaSalao.schedule.ScheduleModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;

    private UUID apiKey;

    @ManyToOne
    @JoinColumn(name = "userType", nullable = false)
    @JsonBackReference
    private UserTypeModel userType;

    @OneToMany(mappedBy = "idUser")
    @Transient
    @JsonManagedReference
    private List<ScheduleModel> schedule;
    
    @CreationTimestamp
    private LocalDateTime createAt;

    public UserModel() {
    }
}
