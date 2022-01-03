package io.netty.example.study.client.codec.dispatcher;

import io.netty.example.study.common.OperationResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestPendingCenter {

    private final Map<Long,OperationResultFuture> map = new ConcurrentHashMap<>();
    public void add(Long streamId,OperationResultFuture operationResultFuture){
        map.put(streamId,operationResultFuture);
    }

    public void set(Long streamId, OperationResult operationResult){
        OperationResultFuture operationResultFuture = map.get(streamId);
        if (operationResult != null){
            operationResultFuture.setSuccess(operationResult);
            map.remove(streamId);
        }
    }
}
