package com.turkcell.playcell.gamingplatform.common.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.turkcell.playcell.gamingplatform.common.enumtypes.TaskType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class TaskQueue extends BaseEntity {

    @OneToOne
    @JoinColumn(name="GAME_DETAIL_ID")
    private GameDetail gameDetail;

    private Instant executionTime;

    private TaskType taskType;

    private boolean isDone;

    public TaskQueue(GameDetail gameDetail, Instant executionTime, TaskType taskType) {
        this.gameDetail = gameDetail;
        this.executionTime = executionTime;
        this.taskType = taskType;
    }

}
