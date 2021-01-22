package com.ustc.qinpark.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "client_online")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer clientId;
    String clientName;
    String time;
    String status;
}
