package com.neo.demo.user.infrastructure.repository.po;

import com.neo.health.common.domain.model.value.Status;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user")
public class UserPO implements Serializable {

    @Id
    private String userId;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Status status;

}
