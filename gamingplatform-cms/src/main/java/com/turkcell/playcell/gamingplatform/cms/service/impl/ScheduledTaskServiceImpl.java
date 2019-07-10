package com.turkcell.playcell.gamingplatform.cms.service.impl;

import com.turkcell.playcell.gamingplatform.cms.service.ScheduledTaskService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ScheduledTaskServiceImpl  implements ScheduledTaskService {


//    @Override
//    @Scheduled(fixedDelay = 600000)
//    public void publishGame() {
//        List<TaskQueue> taskQueuesForPublish = taskQueueService.getReadyToExecution(TaskType.PUBLISH);
//        taskQueuesForPublish.forEach(taskQueue ->{
//            // todo:already publish check log
//            taskQueue.getGameDetail().setPublishStatus(PublishStatus.PUBLISH);
//            taskQueue.setDone(true);
//
//        });
//
//        List<TaskQueue> taskQueuesForUnpublish = taskQueueService.getReadyToExecution(TaskType.UNPUBLISH);
//        taskQueuesForUnpublish.forEach(taskQueue ->{
//            // todo:already publish check log
//            taskQueue.getGameDetail().setPublishStatus(PublishStatus.UNPUBLISH);
//            taskQueue.setDone(true);
//
//        });
//    }
}