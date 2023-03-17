package com.neo.demo.permission.infrastructure.repository.po;

import com.neo.health.common.domain.model.value.Status;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "permission")
public class PermissionPO implements Serializable {


    @Id
    private String permissionId;
    private String permissionCode;
    private String permissionName;
    private String permissionDesc;
    private Status permissionStatus;

}
