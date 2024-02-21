package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface StatisticService {

  RestfulCommonResponse getStatisticByWeek();

  RestfulCommonResponse getStatisticByMonth();

  RestfulCommonResponse getStatisticByYear();

  RestfulCommonResponse getStatisticRevenue(JsonObject payload);

  RestfulCommonResponse getStatisticBestSellers(JsonObject payload);

  RestfulCommonResponse getStatisticBills(JsonObject payload);
}
