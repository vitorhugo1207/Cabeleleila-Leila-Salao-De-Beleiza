package com.leila.salao.LeilaSalao.schedule;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leila.salao.LeilaSalao.user.UserModel;

public interface ScheduleRepository extends JpaRepository<ScheduleModel, Integer> {
    List<ScheduleModel> findByIdUser(UserModel idUser);
}
