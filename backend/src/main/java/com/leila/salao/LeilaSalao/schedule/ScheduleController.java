package com.leila.salao.LeilaSalao.schedule;

import org.springframework.web.bind.annotation.RestController;

import com.leila.salao.LeilaSalao.service.ServiceModel;
import com.leila.salao.LeilaSalao.service.ServiceRepository;
import com.leila.salao.LeilaSalao.user.UserModel;
import com.leila.salao.LeilaSalao.user.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    public ScheduleController() {
    }

    @PostMapping("/")
    public ResponseEntity<ScheduleModel> create(@RequestBody ScheduleDTO scheduleDTO, HttpServletRequest request) {
        ScheduleModel scheduleModel = new ScheduleModel();
        BeanUtils.copyProperties(scheduleDTO, scheduleModel);

        List<ServiceModel> services = serviceRepository.findAllById(scheduleDTO.services());
        scheduleModel.setService(services);

        UserModel userLoged = userRepository.findById((UUID) request.getAttribute("idUser")).get();
        scheduleModel.setIdUser(userLoged);
        scheduleModel.setAccepted(false);

        ScheduleModel sheduleCreated = this.scheduleRepository.save(scheduleModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(sheduleCreated);
    }
    
    @GetMapping("/getAllSchedules/")
    public ResponseEntity<Object> getAllSchedules(HttpServletRequest request) {
        UserModel userLoged = userRepository.findById((UUID) request.getAttribute("idUser")).get();

        if (userLoged.getUserType().getPermSeeAllScheadule()) {
            List<ScheduleModel> scheduleList = scheduleRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(scheduleList);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You hasn't permission to see all Schedules");
    }

    @GetMapping("/")
    public ResponseEntity<List<ScheduleModel>> getSchedules(HttpServletRequest request) {
        UserModel userLoged = userRepository.findById((UUID) request.getAttribute("idUser")).get();
        List<ScheduleModel> scheduleList = scheduleRepository.findByIdUser(userLoged);
        return ResponseEntity.status(HttpStatus.OK).body(scheduleList);
    }

    // put 

    // Delete
}
