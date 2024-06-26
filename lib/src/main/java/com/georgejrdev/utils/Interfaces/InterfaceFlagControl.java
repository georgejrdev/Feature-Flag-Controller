package com.georgejrdev.utils.Interfaces;

public interface InterfaceFlagControl {
    boolean setNewFeatureFlag(String env, String shortDescription) throws Exception;
    boolean setNewFeatureFlag(String url, String method, String shortDescription);
}