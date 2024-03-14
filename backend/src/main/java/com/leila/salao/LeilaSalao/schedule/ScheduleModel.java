package com.leila.salao.LeilaSalao.schedule;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.leila.salao.LeilaSalao.service.ServiceModel;
import com.leila.salao.LeilaSalao.user.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "shedule")
@Data
public class ScheduleModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    @JsonBackReference  
    private UserModel idUser;

    private Boolean accepted;

    private Date scheduled;

    @ManyToMany
    @JoinTable(name = "service_schedule", joinColumns = @JoinColumn(name = "scheduleId"), inverseJoinColumns = @JoinColumn(name = "serviceId"))
    @NotNull
    @JsonManagedReference
    private List<ServiceModel> service;

    public ScheduleModel() {
    }
}
