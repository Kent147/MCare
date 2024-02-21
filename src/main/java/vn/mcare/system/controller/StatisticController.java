package vn.mcare.system.controller;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.Statistic;
import vn.mcare.system.common.exception.CommonExceptions;
import vn.mcare.system.common.helper.validation.ValidationLayer;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.service.intface.StatisticService;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StatisticController {

  private final StatisticService statisticService;
  private final ValidationLayer validationLayer;

  @GetMapping(value = Statistic.STATISTIC_WEEK)
  public RestfulCommonResponse getStatisticByWeek() {
    return statisticService.getStatisticByWeek();
  }

  @GetMapping(value = Statistic.STATISTIC_MONTH)
  public RestfulCommonResponse getStatisticByMonth() {
    return statisticService.getStatisticByMonth();
  }

  @GetMapping(value = Statistic.STATISTIC_YEAR)
  public RestfulCommonResponse getStatisticByYear() {
    return statisticService.getStatisticByYear();
  }

  @PostMapping(value = Statistic.STATISTIC_REVENUE)
  public RestfulCommonResponse getStatisticRevenue(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputStatistic(payload);
      return statisticService.getStatisticRevenue(payload);
    } catch (CommonExceptions.ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PostMapping(value = Statistic.STATISTIC_BEST_SELLERS)
  public RestfulCommonResponse getStatisticBestSellers(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputStatistic(payload);
      return statisticService.getStatisticBestSellers(payload);
    } catch (CommonExceptions.ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PostMapping(value = Statistic.STATISTIC_BILLS)
  public RestfulCommonResponse getStatisticBills(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputStatistic(payload);
      return statisticService.getStatisticBills(payload);
    } catch (CommonExceptions.ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

}
