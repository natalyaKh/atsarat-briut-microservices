package smilyk.atsarat.sceduler.services.scheduler;



import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import smilyk.atsarat.sceduler.enums.LoggerMessages;
import smilyk.atsarat.sceduler.models.PlanEntity;
import smilyk.atsarat.sceduler.repo.PlanRepo;
import smilyk.atsarat.sceduler.services.rabbit.RabbitService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class Scheduler {
    @Autowired
    PlanRepo planRepo;
    @Autowired
    RabbitService rabbitService;

    ModelMapper modelMapper = new ModelMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(cron = "0 40 7 ? * SUN-FRI", zone = "Asia/Jerusalem")
    /**
     * checking in 7 hours 40 minutes every day without saturday
     */
    public void schedulerCheckRecords() {
        LocalDateTime dateNow = LocalDateTime.now();
        LOGGER.info(LoggerMessages.SCHEDULER_START + dateNow);
        LOGGER.info(LoggerMessages.CHECKING_PLAN_RECORDS + dateNow);
        checkRecords();
    }

    private void checkRecords() {
        checkDate();
        checkDayOfWeeks();
    }

    private void checkDayOfWeeks() {
        LocalDateTime dateNow = LocalDateTime.now();
        LOGGER.info(dateNow + " check day of weeks");
        List<PlanEntity> recordsList = planRepo.findAll();
        DayOfWeek today = dateNow.getDayOfWeek();
        List<PlanEntity> dateRecordList = new ArrayList<>(recordsList.size());
        switch (today) {
            case MONDAY:
                dateRecordList = recordsList.stream().filter(rec -> !rec.getDeleted())
                        .filter(rec -> rec.getMonday() != null)
                                .collect(Collectors.toList());
                if (dateRecordList.size() != 0) {
                    for (PlanEntity record : dateRecordList) {
                        rabbitService.sendMessageToServer(record);
                        LOGGER.info(" send request to + " + record.getService() + " with UUIDChild = "
                                + record.getUuidChild() + " withDay " + "MONDAY");
                    }
                }
                break;
            case TUESDAY:
                dateRecordList = recordsList.stream().filter(rec -> !rec.getDeleted())
                        .filter(rec -> rec.getTuesday() != null)
                        .collect(Collectors.toList());
                if (dateRecordList.size() != 0) {
                    for (PlanEntity record : dateRecordList) {
                        rabbitService.sendMessageToServer(record);
                        LOGGER.info(" send request to + " + record.getService() + " with UUIDChild = "
                                + record.getUuidChild() + " withDay " + "TUESDAY");
                    }
                }
                break;
            case WEDNESDAY:
                dateRecordList = recordsList.stream().filter(rec -> !rec.getDeleted())
                        .filter(rec -> rec.getWednesday() != null)
                        .collect(Collectors.toList());
                if (dateRecordList.size() != 0) {
                    for (PlanEntity record : dateRecordList) {
                        rabbitService.sendMessageToServer(record);
                        LOGGER.info(" send request to + " + record.getService() + " with UUIDChild = "
                                + record.getUuidChild() + " withDay " + "WEDNESDAY");
                    }
                }
                break;
            case THURSDAY:
                dateRecordList = recordsList.stream().filter(rec -> !rec.getDeleted())
                        .filter(rec -> rec.getThursday() != null)
                        .collect(Collectors.toList());
                if (dateRecordList.size() != 0) {
                    for (PlanEntity record : dateRecordList) {
                        rabbitService.sendMessageToServer(record);
                        LOGGER.info(" send request to + " + record.getService() + " with UUIDChild = "
                                + record.getUuidChild() + " withDay " + "THURSDAY" + record.getUuidPlan());
                    }
                }
                break;
            case FRIDAY:
                dateRecordList = recordsList.stream().filter(rec -> !rec.getDeleted())
                        .filter(rec -> rec.getFriday() != null)
                        .collect(Collectors.toList());
                if (dateRecordList.size() != 0) {
                    for (PlanEntity record : dateRecordList) {
                        rabbitService.sendMessageToServer(record);
                        LOGGER.info(" send request to + " + record.getService() + " with UUIDChild = "
                                + record.getUuidChild() + " withDay " + "FRIDAY");
                    }
                }
                break;
            case SATURDAY:
                dateRecordList = recordsList.stream().filter(rec -> !rec.getDeleted())
                        .filter(rec -> rec.getSaturday() != null)
                        .collect(Collectors.toList());
                if (dateRecordList.size() != 0) {
                    for (PlanEntity record : dateRecordList) {
                        rabbitService.sendMessageToServer(record);
                        LOGGER.info(" send request to + " + record.getService() + " with UUIDChild = "
                                + record.getUuidChild() + " withDay " + "SATURDAY");
                    }
                }
                break;
            case SUNDAY:
                dateRecordList = recordsList.stream().filter(rec -> !rec.getDeleted())
                        .filter(rec -> rec.getSunday() != null)
                        .collect(Collectors.toList());
                if (dateRecordList.size() != 0) {
                    for (PlanEntity record : dateRecordList) {
                        rabbitService.sendMessageToServer(record);
                        LOGGER.info(" send request to + " + record.getService() + " with UUIDChild = "
                                + record.getUuidChild() + " withDay " + "SUNDAY");
                    }
                }
                break;

            default:
                LOGGER.info("Not found records" + dateNow.toLocalDate());
                break;
        }
        System.err.println(dateRecordList + " date");
    }

    private void checkDate() {
        LocalDateTime dateNow = LocalDateTime.now();
        LOGGER.info(dateNow + " check date" );

        List<PlanEntity> recordsList = planRepo.findAll();
        List<PlanEntity> dateRecordList = recordsList.stream().filter(rec -> !rec.getDeleted())
                .filter(rec -> rec.getDate() != null)
                .filter(rec -> rec.getDate().toLocalDate().equals(dateNow.toLocalDate()))
             .collect(Collectors.toList());
        System.err.println(dateRecordList + " date");
        if (dateRecordList.size() != 0) {
            for (PlanEntity record : dateRecordList) {
                rabbitService.sendMessageToServer(record);
                LOGGER.info("send request to + " + record.getService() + "with UUIDChild = "
                        + record.getUuidChild() + " date " + record.getUuidPlan());
            }
        }
    }
}
