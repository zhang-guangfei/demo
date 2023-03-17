package com.neo.demo.role.infrastructure.repository.po;

import com.neo.health.common.domain.model.value.Status;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "role")
public class RolePO implements Serializable {

    @Id
    private String roleId;
    private String roleCode;
    private String roleName;
    private String roleDesc;
    private Status status;

}
