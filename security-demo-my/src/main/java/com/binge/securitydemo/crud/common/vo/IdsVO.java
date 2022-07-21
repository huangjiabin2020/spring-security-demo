package com.binge.securitydemo.crud.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: security-demo
 * @description:
 * @author: HJB
 * @create: 2022-07-21 17:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class IdsVO {
    private List<Integer> ids;
    private List<String> idsStr;
}
