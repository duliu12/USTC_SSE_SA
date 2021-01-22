package com.ustc.qinpark.client.repository;

import com.ustc.qinpark.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAll();

    Client findOneById(int id);
}
