package com.smart.aop;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Logable {
    private Logger logger = (Logger) Logger.getLogger(this.getClass());
    /**
     * 日志info级别
     *
     * @param message
     */
    public void logInfo(Object message)
    {
        if (logger.isInfoEnabled())
        {
            logger.info(message);
        }
    }

    /**
     * 日志info级别
     *
     * @param message
     * @param t
     */
    public void logInfo(Object message, Throwable t)
    {
        if (logger.isInfoEnabled())
        {
            logger.info(message, t);
        }
    }

    /**
     * 日志warn级别
     *
     * @param message
     */
    public void logWarn(Object message)
    {
        logger.warn(message);
    }

    /**
     * 日志warn级别
     *
     * @param message
     * @param t
     */
    public void logWarn(Object message, Throwable t)
    {
        if(logger.isEnabledFor(Level.WARN))
        {
            logger.warn(message, t);
        }
    }

    /**
     * 日志trace级别
     *
     * @param message
     */
    public void logTrace(Object message)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace(message);
        }
    }

    /**
     * 日志trace级别
     *
     * @param message
     * @param t
     */
    public void logTrace(Object message, Throwable t)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace(message, t);
        }
    }

    /**
     * 方法描述:日志error级别
     *
     * @param message
     */
    public void logError(Object message)
    {
        if(logger.isEnabledFor(Level.ERROR))
        {
            logger.error(message);
        }
    }

    /**
     * 日志trace级别
     *
     * @param message
     * @param t
     */
    public void logError(Object message, Throwable t)
    {
        if(logger.isEnabledFor(Level.ERROR))
        {
            logger.error(message);
        }
    }

}
