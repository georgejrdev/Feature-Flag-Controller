package com.georgejrdev.utils.Interfaces;

public interface InterfaceFlagControl {
    boolean setNewFeatureFlag(String env) throws Exception;
    boolean setNewFeatureFlag(String url, String method);
    boolean setNewFeatureFlag(boolean value);
}