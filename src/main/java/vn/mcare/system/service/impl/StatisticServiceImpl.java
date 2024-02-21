package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.pojo.api.input.StatisticInput;
import vn.mcare.system.common.pojo.api.output.RevenueOutput;
import vn.mcare.system.common.pojo.api.output.StatisticBillOutput;
import vn.mcare.system.common.pojo.api.output.StatisticOutput;
import vn.mcare.system.common.pojo.dto.RevenueDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.repository.impl.StatisticRepoImpl;
import vn.mcare.system.service.intface.StatisticService;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StatisticServiceImpl implements StatisticService {

  private final StatisticRepoImpl statisticRepo;
  private final Gson gson;

  @Override
  public RestfulCommonResponse getStatisticByWeek() {
    try {
      StatisticOutput out = statisticRepo.getStatisticByWeek();
      return new RestfulSuccessResponse().setMessages("Statistic complete").setData(gson.toJsonTree(out));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getStatisticByMonth() {
    try {
      StatisticOutput out = statisticRepo.getStatisticByMonth();
      return new RestfulSuccessResponse().setMessages("Statistic complete").setData(gson.toJsonTree(out));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getStatisticByYear() {
    try {
      StatisticOutput out = statisticRepo.getStatisticByYear();
      return new RestfulSuccessResponse().setMessages("Statistic complete").setData(gson.toJsonTree(out));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getStatisticRevenue(JsonObject payload) {
    try {
      StatisticInput input = gson.fromJson(payload, StatisticInput.class);

      Double sumSubPrice = 0.0;
      Double sumVatPrice = 0.0;
      Double sumTotalPrice = 0.0;
      Double sumCostOfGoods = 0.0;

      List<RevenueDto> revenues = statisticRepo.getStatisticRevenue(input);

      if (revenues.size() > 0) {
        sumSubPrice = revenues.stream().map(x -> x.getSubPrice()).mapToDouble(Double::doubleValue).sum();
        sumVatPrice = revenues.stream().map(x -> x.getVatPrice()).mapToDouble(Double::doubleValue).sum();
        sumTotalPrice = revenues.stream().map(x -> x.getTotalPrice()).mapToDouble(Double::doubleValue).sum();
        sumCostOfGoods = revenues.stream().map(x -> x.getCostOfGoods()).mapToDouble(Double::doubleValue).sum();
      }

      RevenueOutput output = new RevenueOutput()
              .setFromDate(input.getFromDate())
              .setToDate(input.getToDate())
              .setRevenues(revenues)
              .setSumSubPrice(sumSubPrice)
              .setSumVatPrice(sumVatPrice)
              .setSumTotalPrice(sumTotalPrice)
              .setSumRevenue(sumTotalPrice - sumCostOfGoods)
              .setSumCostOfGoods(sumCostOfGoods);

      return new RestfulSuccessResponse().setMessages("Statistic complete").setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getStatisticBestSellers(JsonObject payload) {
    try {
      StatisticInput input = gson.fromJson(payload, StatisticInput.class);

      StatisticOutput out = statisticRepo.getStatisticBestSellers(input);
      return new RestfulSuccessResponse().setMessages("Statistic complete").setData(gson.toJsonTree(out));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getStatisticBills(JsonObject payload) {
    try {
      StatisticInput input = gson.fromJson(payload, StatisticInput.class);

      StatisticBillOutput output = statisticRepo.getStatisticBills(input);
      return new RestfulSuccessResponse().setMessages("Statistic complete").setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

}
