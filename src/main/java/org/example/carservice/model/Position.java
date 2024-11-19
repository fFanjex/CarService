package org.example.carservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "\"position\"")
public class Position {
    @Id
    @ColumnDefault("nextval('position_position_id_seq'::regclass)")
    @Column(name = "position_id", nullable = false)
    private Integer id;

    @Column(name = "position_name", nullable = false, length = 50)
    private String positionName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

}