package com.base.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogKit {
    public LogKit() {
    }

    public static void synchronizeLog() {
        LogKit.Holder.log = LoggerFactory.getLogger(LogKit.class);
    }

    public static void logNothing(Throwable t) {
    }

    public static void debug(String message) {
        LogKit.Holder.log.debug(message);
    }

    public static void debug(String format, Object... arguments) {
        debug(StringUtil.format(format, arguments));
    }

    public static void debug(Throwable t, String format, Object... arguments) {
        debug(StringUtil.format(format, new Object[]{arguments, t}));
    }

    public static void debug(String message, Throwable t) {
        LogKit.Holder.log.debug(message, t);
    }

    public static void info(String message) {
        LogKit.Holder.log.info(message);
    }

    public static void info(String message, Throwable t) {
        LogKit.Holder.log.info(message, t);
    }

    public static void info(String format, Object... arguments) {
        info(StringUtil.format(format, arguments));
    }

    public static void info(Throwable t, String format, Object... arguments) {
        LogKit.Holder.log.info(format, arguments, t);
    }

    public static void warn(String message) {
        LogKit.Holder.log.warn(message);
    }

    public static void warn(String message, Throwable t) {
        LogKit.Holder.log.warn(message, t);
    }

    public static void warn(String format, Object... arguments) {
        warn(StringUtil.format(format, arguments));
    }

    public static void warn(Throwable t, String format, Object... arguments) {
        LogKit.Holder.log.warn(format, arguments, t);
    }

    public static void error(String message) {
        LogKit.Holder.log.error(message);
    }

    public static void error(String message, Throwable t) {
        LogKit.Holder.log.error(message, t);
    }

    public static void error(String format, Object... arguments) {
        error(StringUtil.format(format, arguments));
    }

    public static void error(Throwable t, String format, Object... arguments) {
        LogKit.Holder.log.error(format, arguments, t);
    }

    public static void fatal(String message) {
        fatal(message);
    }

    public static void fatal(String message, Throwable t) {
        fatal(message, t);
    }

    public static void fatal(String format, Object... arguments) {
        fatal(StringUtil.format(format, arguments));
    }

    public static void fatal(Throwable t, String format, Object... arguments) {
        fatal(StringUtil.format(format, new Object[]{arguments, t}));
    }

    public static boolean isDebugEnabled() {
        return LogKit.Holder.log.isDebugEnabled();
    }

    public static boolean isInfoEnabled() {
        return LogKit.Holder.log.isInfoEnabled();
    }

    public static void main(String[] args) {
        debug("123");
    }

    private static class Holder {
        private static Logger log = LoggerFactory.getLogger(LogKit.class);

        private Holder() {
        }
    }
}
