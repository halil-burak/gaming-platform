package com.turkcell.playcell.gamingplatform.som.enumtypes;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WorkflowTypes {
	
	ACCOUNT_ACTIVATION("ACCOUNT_ACTIVATION"),
    CHANGE_MSISDN("CHANGE_MSISDN"),
    ACCOUNT_INACTIVATION("ACCOUNT_INACTIVATION"),
    ACCOUNT_SUSPENSION("ACCOUNT_SUSPENSION"),
    ACCOUNT_TAKEOVER("ACCOUNT_TAKEOVER"),
    SUBSCRIPTION_ACTIVATION("SUBSCRIPTION_ACTIVATION"),
    SUBSCRIPTION_CREATION("SUBSCRIPTION_CREATION"),
    SUBSCRIPTION_INACTIVATION("SUBSCRIPTION_INACTIVATION"),
    SUBSCRIPTION_RENEWAL("SUBSCRIPTION_RENEWAL"),
    SUBSCRIPTION_SUSPENSION("SUBSCRIPTION_SUSPENSION");

    @Getter
    private final  String value;

    private static final Map<String, WorkflowTypes> lookup = new HashMap<>();
    static {
        for(WorkflowTypes type : WorkflowTypes.values()) {
            lookup.put(type.getValue(), type);
        }
    }

    public static WorkflowTypes get(String workflow_label){
        return lookup.get(workflow_label);
    }



}
