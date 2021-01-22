package com.ustc.qinpark.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "client_load")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer clientId;
    String time;
    @Column(name = "client_load")
    Integer load;
    Integer type;
}
