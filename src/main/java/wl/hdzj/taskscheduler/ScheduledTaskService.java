package wl.hdzj.taskscheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


/** 计划任务
 * @author lipengbiao
 */
@Service
public class ScheduledTaskService {

    @Scheduled(fixedRate = 3600_000)
    public void recoveryUploadTempImage(){
        /**TODO 定期回收上传的且未被持久化的图片以及缩略图
         * 实现：仿照Redis回收过期对象的方法
         */
    }
}
