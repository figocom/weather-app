package com.figo.weatherapp.entity;

import com.figo.weatherapp.entity.template.AbsIntEntity;
import com.figo.weatherapp.utils.TableNameConstant;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = TableNameConstant.Subscription)
public class Subscription extends AbsIntEntity {
    @Column("user_id")
    private Integer userId;
    @Column("city_id")
    private Integer cityId;
    @Builder.Default
    private boolean active= true;

}
