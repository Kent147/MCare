package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;

@Getter
public class GetCustomerInput {
    private String search;
    private String sort;
    private Integer limit;
    private Integer page;
}
