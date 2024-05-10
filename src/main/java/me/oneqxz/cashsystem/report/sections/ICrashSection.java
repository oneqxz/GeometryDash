package me.oneqxz.cashsystem.report.sections;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.Callable;

public interface ICrashSection {

    String formatSection(Throwable crush);

    default String addDetail(String title, Callable<Object> call)
    {
        try
        {
            return "\t%s: %s\n".formatted(title, call.call().toString());
        }
        catch (Exception e)
        {
            LogManager.getContext().getLogger("CrashReport").fatal("Error when generating a crash report!", e);
            return null;
        }
    }

    default String addDetail(String title)
    {
        return "\t%s\n".formatted(title);
    }
}
