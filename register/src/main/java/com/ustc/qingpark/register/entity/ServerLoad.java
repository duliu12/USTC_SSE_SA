package com.ustc.qingpark.register.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "server_load")
public class ServerLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer serverId;
    String time;
    Integer serverLoad;
    Integer type;
    String serverName;

}
